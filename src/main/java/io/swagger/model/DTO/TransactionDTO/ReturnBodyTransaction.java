package io.swagger.model.DTO.TransactionDTO;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.model.BaseModels.BaseTransaction;
import io.swagger.v3.oas.annotations.media.Schema;
import org.threeten.bp.OffsetDateTime;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * Return body type of Transaction
 */
@Schema(description = "Return body type of Transaction")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-05-13T15:50:27.304Z[GMT]")


public class ReturnBodyTransaction extends BaseTransaction {
  @JsonProperty("fromAccount")
  private String fromAccount = null;

  @JsonProperty("toAccount")
  private String toAccount = null;

  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("Datetime")
  private OffsetDateTime datetime = null;

  /**
   * Status of transaction. Can be Accepted or Pending.
   */
  public enum StatusEnum {
    APPROVED("Approved"),
    
    PENDING("Pending"),
    
    DISAPPROVED("Disapproved");

    private String value;

    StatusEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static StatusEnum fromValue(String text) {
      for (StatusEnum b : StatusEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("Status")
  private StatusEnum status = null;

  @JsonProperty("performedHolder")
  private Integer performedHolder = null;

  public ReturnBodyTransaction fromAccount(String fromAccount) {
    this.fromAccount = fromAccount;
    return this;
  }

  /**
   * IBAN of the account making the transaction
   * @return fromAccount
   **/
  @Schema(example = "NL01INHO0000000002", description = "IBAN of the account making the transaction")
  
    public String getFromAccount() {
    return fromAccount;
  }

  public void setFromAccount(String fromAccount) {
    this.fromAccount = fromAccount;
  }

  public ReturnBodyTransaction toAccount(String toAccount) {
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

  public ReturnBodyTransaction id(Integer id) {
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

  public ReturnBodyTransaction datetime(OffsetDateTime datetime) {
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

  public ReturnBodyTransaction status(StatusEnum status) {
    this.status = status;
    return this;
  }

  /**
   * Status of transaction. Can be Accepted or Pending.
   * @return status
   **/
  @Schema(description = "Status of transaction. Can be Accepted or Pending.")
  
    public StatusEnum getStatus() {
    return status;
  }

  public void setStatus(StatusEnum status) {
    this.status = status;
  }

  public ReturnBodyTransaction performedHolder(Integer performedHolder) {
    this.performedHolder = performedHolder;
    return this;
  }

  /**
   * Holder that performed this operation
   * @return performedHolder
   **/
  @Schema(example = "1", description = "Holder that performed this operation")
  
    public Integer getPerformedHolder() {
    return performedHolder;
  }

  public void setPerformedHolder(Integer performedHolder) {
    this.performedHolder = performedHolder;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ReturnBodyTransaction returnBodyTransaction = (ReturnBodyTransaction) o;
    return Objects.equals(this.fromAccount, returnBodyTransaction.fromAccount) &&
        Objects.equals(this.toAccount, returnBodyTransaction.toAccount) &&
        Objects.equals(this.id, returnBodyTransaction.id) &&
        Objects.equals(this.datetime, returnBodyTransaction.datetime) &&
        Objects.equals(this.status, returnBodyTransaction.status) &&
        Objects.equals(this.performedHolder, returnBodyTransaction.performedHolder) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(fromAccount, toAccount, id, datetime, status, performedHolder, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ReturnBodyTransaction {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    fromAccount: ").append(toIndentedString(fromAccount)).append("\n");
    sb.append("    toAccount: ").append(toIndentedString(toAccount)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    datetime: ").append(toIndentedString(datetime)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    performedHolder: ").append(toIndentedString(performedHolder)).append("\n");
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
