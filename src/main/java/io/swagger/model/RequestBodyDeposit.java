package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.model.BaseTransaction;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Request body type of a Deposit
 */
@Schema(description = "Request body type of a Deposit")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-05-11T10:26:20.927Z[GMT]")


public class RequestBodyDeposit extends BaseTransaction implements OneOfbody {
  @JsonProperty("toAccount")
  private String toAccount = null;

  public RequestBodyDeposit toAccount(String toAccount) {
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
    RequestBodyDeposit requestBodyDeposit = (RequestBodyDeposit) o;
    return Objects.equals(this.toAccount, requestBodyDeposit.toAccount) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(toAccount, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RequestBodyDeposit {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
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
