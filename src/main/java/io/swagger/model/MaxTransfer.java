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
 * Request and response body to change the daily transfer of an account
 */
@Schema(description = "Request and response body to change the daily transfer of an account")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-05-13T15:50:27.304Z[GMT]")


public class MaxTransfer   {
  @JsonProperty("maxTransfer")
  private BigDecimal maxTransfer = null;

  public MaxTransfer maxTransfer(BigDecimal maxTransfer) {
    this.maxTransfer = maxTransfer;
    return this;
  }

  /**
   * Get maxTransfer
   * @return maxTransfer
   **/
  @Schema(example = "500", description = "")
  
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
    MaxTransfer maxTransfer = (MaxTransfer) o;
    return Objects.equals(this.maxTransfer, maxTransfer.maxTransfer);
  }

  @Override
  public int hashCode() {
    return Objects.hash(maxTransfer);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MaxTransfer {\n");
    
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
