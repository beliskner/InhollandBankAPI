package io.swagger.model.DTO.TransactionDTO.Wrappers;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.model.Transaction;

public class ReturnBodyTransactionWrapper {
    @JsonProperty("Transaction")
    Transaction transaction;

    @JsonProperty("Message")
    String message;

    @JsonProperty("Succes")
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
