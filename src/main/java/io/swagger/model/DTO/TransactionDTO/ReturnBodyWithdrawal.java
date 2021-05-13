package io.swagger.model.DTO.TransactionDTO;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.model.BaseModels.BaseTransaction;
import io.swagger.v3.oas.annotations.media.Schema;
import org.threeten.bp.OffsetDateTime;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * Return body type of Withdrawal
 */
@Schema(description = "Return body type of Withdrawal")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-05-13T15:50:27.304Z[GMT]")


public class ReturnBodyWithdrawal extends BaseTransaction  {
  @JsonProperty("fromAccount")
  private String fromAccount = null;

  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("Datetime")
  private OffsetDateTime datetime = null;

  public ReturnBodyWithdrawal fromAccount(String fromAccount) {
    this.fromAccount = fromAccount;
    return this;
  }

  /**
   * IBAN of the account making the transaction
   * @return fromAccount
   **/
  @Schema(example = "NL01INHO0000000003", description = "IBAN of the account making the transaction")
  
    public String getFromAccount() {
    return fromAccount;
  }

  public void setFromAccount(String fromAccount) {
    this.fromAccount = fromAccount;
  }

  public ReturnBodyWithdrawal id(Integer id) {
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

  public ReturnBodyWithdrawal datetime(OffsetDateTime datetime) {
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
    ReturnBodyWithdrawal returnBodyWithdrawal = (ReturnBodyWithdrawal) o;
    return Objects.equals(this.fromAccount, returnBodyWithdrawal.fromAccount) &&
        Objects.equals(this.id, returnBodyWithdrawal.id) &&
        Objects.equals(this.datetime, returnBodyWithdrawal.datetime) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(fromAccount, id, datetime, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ReturnBodyWithdrawal {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    fromAccount: ").append(toIndentedString(fromAccount)).append("\n");
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
