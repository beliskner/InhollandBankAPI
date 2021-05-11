package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Body1
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-05-11T11:11:38.637Z[GMT]")


public class Body1   {
  @JsonProperty("TAN")
  private Integer TAN = null;

  public Body1 TAN(Integer TAN) {
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
    Body1 body1 = (Body1) o;
    return Objects.equals(this.TAN, body1.TAN);
  }

  @Override
  public int hashCode() {
    return Objects.hash(TAN);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Body1 {\n");
    
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
