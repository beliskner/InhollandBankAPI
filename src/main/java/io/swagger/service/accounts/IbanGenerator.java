package io.swagger.service.accounts;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import java.io.Serializable;
import java.util.OptionalLong;
import java.util.stream.Stream;

public class IbanGenerator implements IdentifierGenerator {

    private static final String COUNTRY_CODE = "NL";
    private static final String CONTROL_NUMBER = "00";
    private static final String BANK_CODE = "INHO";
    private static final int AMOUNT_OF_CHARACTERS_BEFORE_ACCOUNTNUMBER_STARTS = 8;
    private static final int AMOUNT_OF_MAX_DIGITS_IN_ACCOUNT_NUMBER = 10;


    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object obj) throws HibernateException {

        String query = String.format("select %s from %s",
                session.getEntityPersister(obj.getClass().getName(), obj)
                        .getIdentifierPropertyName(),
                obj.getClass().getSimpleName());

        Stream ids = session
                .createQuery(query)
                .stream()
                .filter(id -> id
                        .toString()
                        .contains(COUNTRY_CODE + CONTROL_NUMBER + BANK_CODE));

        OptionalLong max = ids
                .mapToLong(iban -> Long
                        .parseLong(iban
                            .toString()
                            .substring(AMOUNT_OF_CHARACTERS_BEFORE_ACCOUNTNUMBER_STARTS)))
                .max();
        // als er geen geldige ibans zijn doe dit
        if (!max.isPresent()) return COUNTRY_CODE + CONTROL_NUMBER + BANK_CODE + "0000000001";
        //anders gebruik max om een nieuwe iban te genereren
        return COUNTRY_CODE + CONTROL_NUMBER + BANK_CODE + createAccountNumberFromLatestId(max.getAsLong());

    }
        
    private String createAccountNumberFromLatestId(Long latestId){
        Long newAccountNumber = latestId + 1;
        int amountOfZeros =  AMOUNT_OF_MAX_DIGITS_IN_ACCOUNT_NUMBER -  newAccountNumber.toString().length() ;
        
        StringBuilder sb = new StringBuilder();
        sb.append(newAccountNumber);

        while (amountOfZeros > 0){
            sb.insert(0, "0");
            amountOfZeros--;
        }
        
        return sb.toString(); 
    }
}
