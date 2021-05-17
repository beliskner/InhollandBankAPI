package io.swagger.api.AccountsController;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;

public class IbanGenerator implements IdentifierGenerator {

    private static final String COUNTRY_CODE = "NL";
    private static final String CONTROL_NUMBER = "00";
    private static final String BANK_CODE = "INHO";
    private static final int AMOUNT_OF_MAX_DIGITS_IN_IBAN = 34;


    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object obj) throws HibernateException {

        String query = String.format("select %s from %s",
                session.getEntityPersister(obj.getClass().getName(), obj)
                        .getIdentifierPropertyName(),
                obj.getClass().getSimpleName());

        return "ff";



    }
}
