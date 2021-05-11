package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Request and response body to change the minimal balance of an account
 */
@Schema(description = "Request and response body to change the minimal balance of an account")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-05-11T10:26:20.927Z[GMT]")


public class MinBalance   {
  @JsonProperty("minBalance")
  private BigDecimal minBalance = null;

  public MinBalance minBalance(BigDecimal minBalance) {
    this.minBalance = minBalance;
    return this;
  }

  /**
   * Get minBalance
   * @return minBalance
   **/
  @Schema(example = "500", description = "")
  
    @Valid
    public BigDecimal getMinBalance() {
    return minBalance;
  }

  public void setMinBalance(BigDecimal minBalance) {
    this.minBalance = minBalance;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MinBalance minBalance = (MinBalance) o;
    return Objects.equals(this.minBalance, minBalance.minBalance);
  }

  @Override
  public int hashCode() {
    return Objects.hash(minBalance);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MinBalance {\n");
    
    sb.append("    minBalance: ").append(toIndentedString(minBalance)).append("\n");
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
