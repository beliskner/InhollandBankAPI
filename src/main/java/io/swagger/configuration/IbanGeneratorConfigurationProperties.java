package io.swagger.configuration;


import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "iban")
public class IbanGeneratorConfigurationProperties{

    private  String countryCode;
    private  String controlNumber;
    private  String bankCode;
    private  int maxDigitsInAccountNumber;

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getControlNumber() {
        return controlNumber;
    }

    public void setControlNumber(String controlNumber) {
        this.controlNumber = controlNumber;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public int getMaxDigitsInAccountNumber() {
        return maxDigitsInAccountNumber;
    }

    public void setMaxDigitsInAccountNumber(int maxDigitsInAccountNumber) {
        this.maxDigitsInAccountNumber = maxDigitsInAccountNumber;
    }

    public int getAmountOfCharactersBeforeAccountNumberStarts() {
        return countryCode.length() + controlNumber.length() + bankCode.length();
    }


    public IbanGeneratorConfigurationProperties() {
        System.out.println();


    }
}