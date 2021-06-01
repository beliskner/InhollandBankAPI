package io.swagger.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.model.BaseModels.BaseTransaction;
import io.swagger.v3.oas.annotations.media.Schema;
import org.threeten.bp.OffsetDateTime;
import org.springframework.validation.annotation.Validated;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.Valid;

/**
 * Model of full transaction object
 */
@Schema(description = "Model of full transaction object")
@Validated
@Entity
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-05-13T15:50:27.304Z[GMT]")


public class Transaction extends BaseTransaction  {
  @JsonProperty("performedHolder")
  private Integer performedHolder = null;

  @JsonProperty("fromAccount")
  private String fromAccount = null;

  @JsonProperty("toAccount")
  private String toAccount = null;

  @Id
  @GeneratedValue
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

  @JsonProperty("TAN")
  private Integer TAN = null;

  public Transaction performedHolder(Integer performedHolder) {
    this.performedHolder = performedHolder;
    return this;
  }

  /**
   * Holder ID performing the transaction
   * @return performedHolder
   **/
  @Schema(example = "1", description = "Holder ID performing the transaction")
  
    public Integer getPerformedHolder() {
    return performedHolder;
  }

  public void setPerformedHolder(Integer performedHolder) {
    this.performedHolder = performedHolder;
  }

  public Transaction fromAccount(String fromAccount) {
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

  public Transaction toAccount(String toAccount) {
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

  public Transaction id(Integer id) {
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

  public Transaction datetime(OffsetDateTime datetime) {
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

  public Transaction status(StatusEnum status) {
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

  @JsonIgnore
  public Transaction TAN(Integer TAN) {
    this.TAN = TAN;
    return this;
  }

  /**
   * Four integer TAN code to confirm transaction
   * @return TAN
   **/
  @Schema(example = "1234", description = "Four integer TAN code to confirm transaction")
  
    public Integer getTAN() {
    return TAN;
  }

  public void setTAN(Integer TAN) {
    this.TAN = TAN;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Transaction transaction = (Transaction) o;
    return Objects.equals(this.performedHolder, transaction.performedHolder) &&
        Objects.equals(this.fromAccount, transaction.fromAccount) &&
        Objects.equals(this.toAccount, transaction.toAccount) &&
        Objects.equals(this.id, transaction.id) &&
        Objects.equals(this.datetime, transaction.datetime) &&
        Objects.equals(this.status, transaction.status) &&
        Objects.equals(this.TAN, transaction.TAN) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(performedHolder, fromAccount, toAccount, id, datetime, status, TAN, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Transaction {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    performedHolder: ").append(toIndentedString(performedHolder)).append("\n");
    sb.append("    fromAccount: ").append(toIndentedString(fromAccount)).append("\n");
    sb.append("    toAccount: ").append(toIndentedString(toAccount)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    datetime: ").append(toIndentedString(datetime)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    TAN: ").append(toIndentedString(TAN)).append("\n");
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
