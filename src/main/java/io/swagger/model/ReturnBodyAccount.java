package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.model.BaseAccount;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Return body type of Account
 */
@Schema(description = "Return body type of Account")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-05-11T11:11:38.637Z[GMT]")


public class ReturnBodyAccount extends BaseAccount  {
  @JsonProperty("holderId")
  private Integer holderId = null;

  @JsonProperty("iban")
  private String iban = null;

  @JsonProperty("balance")
  private BigDecimal balance = null;

  public ReturnBodyAccount holderId(Integer holderId) {
    this.holderId = holderId;
    return this;
  }

  /**
   * The ID of the holder the account will be bound to
   * @return holderId
   **/
  @Schema(example = "1", description = "The ID of the holder the account will be bound to")
  
    public Integer getHolderId() {
    return holderId;
  }

  public void setHolderId(Integer holderId) {
    this.holderId = holderId;
  }

  public ReturnBodyAccount iban(String iban) {
    this.iban = iban;
    return this;
  }

  /**
   * IBAN of the account
   * @return iban
   **/
  @Schema(example = "NL01INHO0000000002", description = "IBAN of the account")
  
    public String getIban() {
    return iban;
  }

  public void setIban(String iban) {
    this.iban = iban;
  }

  public ReturnBodyAccount balance(BigDecimal balance) {
    this.balance = balance;
    return this;
  }

  /**
   * Balance of the account
   * minimum: 0
   * @return balance
   **/
  @Schema(example = "500.25", description = "Balance of the account")
  
    @Valid
  @DecimalMin("0")  public BigDecimal getBalance() {
    return balance;
  }

  public void setBalance(BigDecimal balance) {
    this.balance = balance;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ReturnBodyAccount returnBodyAccount = (ReturnBodyAccount) o;
    return Objects.equals(this.holderId, returnBodyAccount.holderId) &&
        Objects.equals(this.iban, returnBodyAccount.iban) &&
        Objects.equals(this.balance, returnBodyAccount.balance) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(holderId, iban, balance, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ReturnBodyAccount {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    holderId: ").append(toIndentedString(holderId)).append("\n");
    sb.append("    iban: ").append(toIndentedString(iban)).append("\n");
    sb.append("    balance: ").append(toIndentedString(balance)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
