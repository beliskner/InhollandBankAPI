package io.swagger.service.accounts;

import io.swagger.configuration.IbanGeneratorConfigurationProperties;
import io.swagger.helpers.ApplicationContextHolder;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import java.io.Serializable;
import java.util.OptionalLong;
import java.util.stream.Stream;

public class IbanGenerator implements IdentifierGenerator {


    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object obj) throws HibernateException {

        IbanGeneratorConfigurationProperties configurationProperties = ApplicationContextHolder.getBean(IbanGeneratorConfigurationProperties.class);

        String countryCode = configurationProperties.getCountryCode();
        String bankCode = configurationProperties.getBankCode();
        String controlNumber = configurationProperties.getControlNumber();
        int maxDigits = configurationProperties.getMaxDigitsInAccountNumber();
        int amountOfCharactersBeforeAccountNumberStarts = configurationProperties.getAmountOfCharactersBeforeAccountNumberStarts();


        String query = String.format("select %s from %s",
                session.getEntityPersister(obj.getClass().getName(), obj)
                        .getIdentifierPropertyName(),
                obj.getClass().getSimpleName());

        Stream ids = session
                .createQuery(query)
                .stream()
                .filter(id -> id
                        .toString()
                        .contains(countryCode + controlNumber + bankCode));

        OptionalLong max = ids
                .mapToLong(iban -> Long
                        .parseLong(iban
                            .toString()
                            .substring(amountOfCharactersBeforeAccountNumberStarts)))
                .max();
        // als er geen geldige ibans zijn doe dit
        if (!max.isPresent()) return countryCode + controlNumber + bankCode + "0000000001";
        //anders gebruik max om een nieuwe iban te genereren
        return countryCode + controlNumber + bankCode + createAccountNumberFromLatestId(max.getAsLong(), maxDigits);

    }
        
    private String createAccountNumberFromLatestId(Long latestId, int maxDigits) {
        Long newAccountNumber = latestId + 1;
        int amountOfZeros =  maxDigits -  newAccountNumber.toString().length() ;
        
        StringBuilder sb = new StringBuilder();
        sb.append(newAccountNumber);

        while (amountOfZeros > 0){
            sb.insert(0, "0");
            amountOfZeros--;
        }
        
        return sb.toString(); 
    }
}
