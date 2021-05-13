package io.swagger.model.DTO.TransactionDTO;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.model.BaseModels.BaseTransaction;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;

/**
 * Request body type of a Transaction
 */
@Schema(description = "Request body type of a Transaction")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-05-13T15:50:27.304Z[GMT]")


public class RequestBodyTransaction extends BaseTransaction  {
  @JsonProperty("fromAccount")
  private String fromAccount = null;

  @JsonProperty("toAccount")
  private String toAccount = null;

  public RequestBodyTransaction fromAccount(String fromAccount) {
    this.fromAccount = fromAccount;
    return this;
  }

  /**
   * IBAN of the account making the transaction
   * @return fromAccount
   **/
  @Schema(example = "NL01INHO0000000002", required = true, description = "IBAN of the account making the transaction")
      @NotNull

    public String getFromAccount() {
    return fromAccount;
  }

  public void setFromAccount(String fromAccount) {
    this.fromAccount = fromAccount;
  }

  public RequestBodyTransaction toAccount(String toAccount) {
    this.toAccount = toAccount;
    return this;
  }

  /**
   * IBAN of the account recieving the transaction
   * @return toAccount
   **/
  @Schema(example = "NL01INHO0000000003", required = true, description = "IBAN of the account recieving the transaction")
      @NotNull

    public String getToAccount() {
    return toAccount;
  }

  public void setToAccount(String toAccount) {
    this.toAccount = toAccount;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RequestBodyTransaction requestBodyTransaction = (RequestBodyTransaction) o;
    return Objects.equals(this.fromAccount, requestBodyTransaction.fromAccount) &&
        Objects.equals(this.toAccount, requestBodyTransaction.toAccount) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(fromAccount, toAccount, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RequestBodyTransaction {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    fromAccount: ").append(toIndentedString(fromAccount)).append("\n");
    sb.append("    toAccount: ").append(toIndentedString(toAccount)).append("\n");
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
