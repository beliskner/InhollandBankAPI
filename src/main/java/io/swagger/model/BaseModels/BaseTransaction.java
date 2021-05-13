package io.swagger.model.BaseModels;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Request body type of a Transaction
 */
@Schema(description = "Request body type of a Transaction")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-05-13T15:50:27.304Z[GMT]")


public class BaseTransaction   {
  @JsonProperty("Amount")
  private BigDecimal amount = null;

  /**
   * Type of transaction. Can be Transfer or Refund.
   */
  public enum TransactionTypeEnum {
    TRANSFER("Transfer"),
    
    REFUND("Refund"),
    
    DEPOSIT("Deposit"),
    
    WITHDRAWAL("Withdrawal");

    private String value;

    TransactionTypeEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static TransactionTypeEnum fromValue(String text) {
      for (TransactionTypeEnum b : TransactionTypeEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("TransactionType")
  private TransactionTypeEnum transactionType = null;

  public BaseTransaction amount(BigDecimal amount) {
    this.amount = amount;
    return this;
  }

  /**
   * Amount of currency performed in transaction
   * minimum: 0
   * @return amount
   **/
  @Schema(example = "500.01", description = "Amount of currency performed in transaction")
  
    @Valid
  @DecimalMin("0")  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  public BaseTransaction transactionType(TransactionTypeEnum transactionType) {
    this.transactionType = transactionType;
    return this;
  }

  /**
   * Type of transaction. Can be Transfer or Refund.
   * @return transactionType
   **/
  @Schema(description = "Type of transaction. Can be Transfer or Refund.")
  
    public TransactionTypeEnum getTransactionType() {
    return transactionType;
  }

  public void setTransactionType(TransactionTypeEnum transactionType) {
    this.transactionType = transactionType;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BaseTransaction baseTransaction = (BaseTransaction) o;
    return Objects.equals(this.amount, baseTransaction.amount) &&
        Objects.equals(this.transactionType, baseTransaction.transactionType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(amount, transactionType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BaseTransaction {\n");
    
    sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
    sb.append("    transactionType: ").append(toIndentedString(transactionType)).append("\n");
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
