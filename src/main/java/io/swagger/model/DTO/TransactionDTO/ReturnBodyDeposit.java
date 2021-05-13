package io.swagger.model.DTO.TransactionDTO;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.model.BaseModels.BaseTransaction;
import io.swagger.v3.oas.annotations.media.Schema;
import org.threeten.bp.OffsetDateTime;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * Return body type of Deposit
 */
@Schema(description = "Return body type of Deposit")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-05-13T15:50:27.304Z[GMT]")


public class ReturnBodyDeposit extends BaseTransaction  {
  @JsonProperty("toAccount")
  private String toAccount = null;

  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("Datetime")
  private OffsetDateTime datetime = null;

  public ReturnBodyDeposit toAccount(String toAccount) {
    this.toAccount = toAccount;
    return this;
  }

  /**
   * IBAN of the account recieving the transaction
   * @return toAccount
   **/
  @Schema(example = "NL01INHO0000000003", description = "IBAN of the account recieving the transaction")
  
    public String getToAccount() {
    return toAccount;
  }

  public void setToAccount(String toAccount) {
    this.toAccount = toAccount;
  }

  public ReturnBodyDeposit id(Integer id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
   **/
  @Schema(example = "1", description = "")
  
    public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public ReturnBodyDeposit datetime(OffsetDateTime datetime) {
    this.datetime = datetime;
    return this;
  }

  /**
   * Datetime the transaction was executed
   * @return datetime
   **/
  @Schema(example = "2017-07-21T17:32:28Z", description = "Datetime the transaction was executed")
  
    @Valid
    public OffsetDateTime getDatetime() {
    return datetime;
  }

  public void setDatetime(OffsetDateTime datetime) {
    this.datetime = datetime;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ReturnBodyDeposit returnBodyDeposit = (ReturnBodyDeposit) o;
    return Objects.equals(this.toAccount, returnBodyDeposit.toAccount) &&
        Objects.equals(this.id, returnBodyDeposit.id) &&
        Objects.equals(this.datetime, returnBodyDeposit.datetime) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(toAccount, id, datetime, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ReturnBodyDeposit {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    toAccount: ").append(toIndentedString(toAccount)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    datetime: ").append(toIndentedString(datetime)).append("\n");
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
