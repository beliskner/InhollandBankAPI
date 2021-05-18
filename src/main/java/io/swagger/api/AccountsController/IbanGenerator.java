package io.swagger.api.AccountsController;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.util.List;
import java.util.OptionalLong;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class IbanGenerator implements IdentifierGenerator {

    private static final String COUNTRY_CODE = "NL";
    private static final String CONTROL_NUMBER = "00";
    private static final String BANK_CODE = "INHO";
    private static final int AMOUNT_OF_MAX_DIGITS_IN_ACCOUNT_NUMBER = 26;


    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object obj) throws HibernateException {

        String query = String.format("select %s from %s",
                session.getEntityPersister(obj.getClass().getName(), obj)
                        .getIdentifierPropertyName(),
                obj.getClass().getSimpleName());

        //haalt alle id's  op die beginnen met NL00INHO
        Stream ids = session.createQuery(query).stream().filter(x -> x.toString().contains(COUNTRY_CODE + CONTROL_NUMBER + BANK_CODE));


        //haalt de letters (NL00INHO van de strings af, converteert de resterende string (bestaande uit cijfers) naar een long, en kiest de hoogste waarde uit.
        OptionalLong max = ids.mapToLong(x -> {
            return Long.parseLong(x.toString().substring(8));
        }).max();

        // als er geen geldige ibans zijn doe dit
        if (!max.isPresent()){
            return "ff";
        }

        //anders return max
        return COUNTRY_CODE + CONTROL_NUMBER + BANK_CODE + max;



    }
}
