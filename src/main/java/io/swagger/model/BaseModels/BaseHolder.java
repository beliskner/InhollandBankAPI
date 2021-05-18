package io.swagger.model.BaseModels;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.model.Enums.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.validation.annotation.Validated;

import javax.persistence.MappedSuperclass;
import javax.validation.Valid;

/**
 * Base of holder
 */
@Schema(description = "Base of holder")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-05-13T15:50:27.304Z[GMT]")
@MappedSuperclass
public class BaseHolder   {
  @JsonProperty("firstName")
  private String firstName = null;

  @JsonProperty("lastName")
  private String lastName = null;

  @JsonProperty("email")
  private String email = null;

  /**
   * Role of the holder (Employee or Customer)
   */

  @JsonProperty("role")
  private Role role = null;

  @JsonProperty("dailyLimit")
  private BigDecimal dailyLimit = null;

  public BaseHolder firstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  /**
   * First name of the holder
   * @return firstName
   **/
  @Schema(example = "John", description = "First name of the holder")
  
    public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public BaseHolder lastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  /**
   * Last name of the holder
   * @return lastName
   **/
  @Schema(example = "Doe", description = "Last name of the holder")
  
    public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public BaseHolder email(String email) {
    this.email = email;
    return this;
  }

  /**
   * Email of the holder
   * @return email
   **/
  @Schema(example = "john@doe.com", description = "Email of the holder")
  
    public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public BaseHolder role(Role role) {
    this.role = role;
    return this;
  }

  /**
   * Role of the holder (Employee or Customer)
   * @return role
   **/
  @Schema(description = "Role of the holder (Employee or Customer)")
  
    public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  public BaseHolder dailyLimit(BigDecimal dailyLimit) {
    this.dailyLimit = dailyLimit;
    return this;
  }

  /**
   * Combined transaction amount cannot exeed this limit per day. Defined by Holder.
   * @return dailyLimit
   **/
  @Schema(example = "5000", description = "Combined transaction amount cannot exeed this limit per day. Defined by Holder.")
  
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
    BaseHolder baseHolder = (BaseHolder) o;
    return Objects.equals(this.firstName, baseHolder.firstName) &&
        Objects.equals(this.lastName, baseHolder.lastName) &&
        Objects.equals(this.email, baseHolder.email) &&
        Objects.equals(this.role, baseHolder.role) &&
        Objects.equals(this.dailyLimit, baseHolder.dailyLimit);
  }

  @Override
  public int hashCode() {
    return Objects.hash(firstName, lastName, email, role, dailyLimit);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BaseHolder {\n");
    
    sb.append("    firstName: ").append(toIndentedString(firstName)).append("\n");
    sb.append("    lastName: ").append(toIndentedString(lastName)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    role: ").append(toIndentedString(role)).append("\n");
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
