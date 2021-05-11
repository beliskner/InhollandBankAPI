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
 * Request and response body to change the daily limit of a holder
 */
@Schema(description = "Request and response body to change the daily limit of a holder")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-05-11T11:11:38.637Z[GMT]")


public class BodyDailyLimit   {
  @JsonProperty("dailyLimit")
  private BigDecimal dailyLimit = null;

  public BodyDailyLimit dailyLimit(BigDecimal dailyLimit) {
    this.dailyLimit = dailyLimit;
    return this;
  }

  /**
   * Get dailyLimit
   * @return dailyLimit
   **/
  @Schema(example = "500", description = "")
  
    @Valid
    public BigDecimal getDailyLimit() {
    return dailyLimit;
  }

  public void setDailyLimit(BigDecimal dailyLimit) {
    this.dailyLimit = dailyLimit;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BodyDailyLimit bodyDailyLimit = (BodyDailyLimit) o;
    return Objects.equals(this.dailyLimit, bodyDailyLimit.dailyLimit);
  }

  @Override
  public int hashCode() {
    return Objects.hash(dailyLimit);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BodyDailyLimit {\n");
    
    sb.append("    dailyLimit: ").append(toIndentedString(dailyLimit)).append("\n");
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
