package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.model.BaseHolder;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Return body of the Holder
 */
@Schema(description = "Return body of the Holder")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-05-11T11:11:38.637Z[GMT]")


public class ReturnBodyHolder extends BaseHolder  {
  @JsonProperty("id")
  private Integer id = null;

  /**
   * Status of holder. Can be Active or Frozen
   */
  public enum StatusEnum {
    ACTIVE("Active"),
    
    FROZEN("Frozen");

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
  @JsonProperty("status")
  private StatusEnum status = null;

  @JsonProperty("accounts")
  @Valid
  private List<Object> accounts = null;

  public ReturnBodyHolder id(Integer id) {
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

  public ReturnBodyHolder status(StatusEnum status) {
    this.status = status;
    return this;
  }

  /**
   * Status of holder. Can be Active or Frozen
   * @return status
   **/
  @Schema(description = "Status of holder. Can be Active or Frozen")
  
    public StatusEnum getStatus() {
    return status;
  }

  public void setStatus(StatusEnum status) {
    this.status = status;
  }

  public ReturnBodyHolder accounts(List<Object> accounts) {
    this.accounts = accounts;
    return this;
  }

  public ReturnBodyHolder addAccountsItem(Object accountsItem) {
    if (this.accounts == null) {
      this.accounts = new ArrayList<Object>();
    }
    this.accounts.add(accountsItem);
    return this;
  }

  /**
   * Array of accounts belonging to the Holder
   * @return accounts
   **/
  @Schema(description = "Array of accounts belonging to the Holder")
  
    public List<Object> getAccounts() {
    return accounts;
  }

  public void setAccounts(List<Object> accounts) {
    this.accounts = accounts;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ReturnBodyHolder returnBodyHolder = (ReturnBodyHolder) o;
    return Objects.equals(this.id, returnBodyHolder.id) &&
        Objects.equals(this.status, returnBodyHolder.status) &&
        Objects.equals(this.accounts, returnBodyHolder.accounts) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, status, accounts, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ReturnBodyHolder {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    accounts: ").append(toIndentedString(accounts)).append("\n");
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
