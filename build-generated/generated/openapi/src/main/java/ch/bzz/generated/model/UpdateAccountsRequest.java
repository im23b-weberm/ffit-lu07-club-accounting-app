package ch.bzz.generated.model;

import java.net.URI;
import java.util.Objects;
import ch.bzz.generated.model.AccountUpdate;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * UpdateAccountsRequest
 */

@JsonTypeName("updateAccounts_request")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.7.0")
public class UpdateAccountsRequest {

  @Valid
  private List<@Valid AccountUpdate> accounts = new ArrayList<>();

  public UpdateAccountsRequest accounts(List<@Valid AccountUpdate> accounts) {
    this.accounts = accounts;
    return this;
  }

  public UpdateAccountsRequest addAccountsItem(AccountUpdate accountsItem) {
    if (this.accounts == null) {
      this.accounts = new ArrayList<>();
    }
    this.accounts.add(accountsItem);
    return this;
  }

  /**
   * Get accounts
   * @return accounts
   */
  @Valid 
  @Schema(name = "accounts", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("accounts")
  public List<@Valid AccountUpdate> getAccounts() {
    return accounts;
  }

  public void setAccounts(List<@Valid AccountUpdate> accounts) {
    this.accounts = accounts;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UpdateAccountsRequest updateAccountsRequest = (UpdateAccountsRequest) o;
    return Objects.equals(this.accounts, updateAccountsRequest.accounts);
  }

  @Override
  public int hashCode() {
    return Objects.hash(accounts);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UpdateAccountsRequest {\n");
    sb.append("    accounts: ").append(toIndentedString(accounts)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

