package io.swagger.model.DTO.TransactionDTO;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;

/**
 * TanDTO
 */
@Schema(description = "Return the TAN")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-05-13T15:50:27.304Z[GMT]")
public class TanDTO {
  @JsonProperty("TAN")
  private Integer TAN = null;

  @JsonIgnore
  public TanDTO TAN(Integer TAN) {
    this.TAN = TAN;
    return this;
  }

  /**
   * Get TAN
   * @return TAN
   **/
  @Schema(example = "1234", description = "")
  public Integer getTAN() {
    return TAN;
  }

  @JsonIgnore
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
    TanDTO tanDTO = (TanDTO) o;
    return Objects.equals(this.TAN, tanDTO.TAN);
  }

  @Override
  public int hashCode() {
    return Objects.hash(TAN);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TanDTO {\n");
    sb.append("    TAN: ").append(toIndentedString(TAN)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  @JsonIgnore
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
