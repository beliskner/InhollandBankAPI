package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.model.BaseAccount;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Request body type of account (Current or Savings)
 */
@Schema(description = "Request body type of account (Current or Savings)")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-05-11T11:11:38.637Z[GMT]")


public class RequestBodyAccount extends BaseAccount  {
  @JsonProperty("holderId")
  private Integer holderId = null;

  public RequestBodyAccount holderId(Integer holderId) {
    this.holderId = holderId;
    return this;
  }

  /**
   * Get holderId
   * @return holderId
   **/
  @Schema(example = "1", required = true, description = "")
      @NotNull

    public Integer getHolderId() {
    return holderId;
  }

  public void setHolderId(Integer holderId) {
    this.holderId = holderId;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RequestBodyAccount requestBodyAccount = (RequestBodyAccount) o;
    return Objects.equals(this.holderId, requestBodyAccount.holderId) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(holderId, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RequestBodyAccount {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    holderId: ").append(toIndentedString(holderId)).append("\n");
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
