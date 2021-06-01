package io.swagger.model.DTO.TransactionDTO.Wrappers;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.model.Transaction;

public class TransactionWrapper {
    Transaction transaction;

    String message;

    Boolean succes;

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getSucces() {
        return succes;
    }

    public void setSucces(Boolean succes) {
        this.succes = succes;
    }
}
