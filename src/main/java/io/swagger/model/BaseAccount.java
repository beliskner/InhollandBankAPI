package io.swagger.model;

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
 * Base of account
 */
@Schema(description = "Base of account")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-05-13T15:50:27.304Z[GMT]")


public class BaseAccount   {
  /**
   * Type of the account (Current or Savings)
   */
  public enum AccountTypeEnum {
    CURRENT("Current"),
    
    SAVINGS("Savings");

    private String value;

    AccountTypeEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static AccountTypeEnum fromValue(String text) {
      for (AccountTypeEnum b : AccountTypeEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("accountType")
  private AccountTypeEnum accountType = null;

  /**
   * Status of account. Can be open or closed
   */
  public enum StatusEnum {
    OPEN("Open"),
    
    CLOSED("Closed");

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

  @JsonProperty("minBalance")
  private BigDecimal minBalance = null;

  @JsonProperty("maxTransfer")
  private BigDecimal maxTransfer = null;

  public BaseAccount accountType(AccountTypeEnum accountType) {
    this.accountType = accountType;
    return this;
  }

  /**
   * Type of the account (Current or Savings)
   * @return accountType
   **/
  @Schema(description = "Type of the account (Current or Savings)")
  
    public AccountTypeEnum getAccountType() {
    return accountType;
  }

  public void setAccountType(AccountTypeEnum accountType) {
    this.accountType = accountType;
  }

  public BaseAccount status(StatusEnum status) {
    this.status = status;
    return this;
  }

  /**
   * Status of account. Can be open or closed
   * @return status
   **/
  @Schema(description = "Status of account. Can be open or closed")
  
    public StatusEnum getStatus() {
    return status;
  }

  public void setStatus(StatusEnum status) {
    this.status = status;
  }

  public BaseAccount minBalance(BigDecimal minBalance) {
    this.minBalance = minBalance;
    return this;
  }

  /**
   * Balance cannot be lower than this amount. Defined by Holder.
   * @return minBalance
   **/
  @Schema(example = "-500.25", description = "Balance cannot be lower than this amount. Defined by Holder.")
  
    @Valid
    public BigDecimal getMinBalance() {
    return minBalance;
  }

  public void setMinBalance(BigDecimal minBalance) {
    this.minBalance = minBalance;
  }

  public BaseAccount maxTransfer(BigDecimal maxTransfer) {
    this.maxTransfer = maxTransfer;
    return this;
  }

  /**
   * Single transaction cannot exeed this limit. Defined by Holder.
   * @return maxTransfer
   **/
  @Schema(example = "500", description = "Single transaction cannot exeed this limit. Defined by Holder.")
  
    @Valid
    public BigDecimal getMaxTransfer() {
    return maxTransfer;
  }

  public void setMaxTransfer(BigDecimal maxTransfer) {
    this.maxTransfer = maxTransfer;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BaseAccount baseAccount = (BaseAccount) o;
    return Objects.equals(this.accountType, baseAccount.accountType) &&
        Objects.equals(this.status, baseAccount.status) &&
        Objects.equals(this.minBalance, baseAccount.minBalance) &&
        Objects.equals(this.maxTransfer, baseAccount.maxTransfer);
  }

  @Override
  public int hashCode() {
    return Objects.hash(accountType, status, minBalance, maxTransfer);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BaseAccount {\n");
    
    sb.append("    accountType: ").append(toIndentedString(accountType)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    minBalance: ").append(toIndentedString(minBalance)).append("\n");
    sb.append("    maxTransfer: ").append(toIndentedString(maxTransfer)).append("\n");
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
