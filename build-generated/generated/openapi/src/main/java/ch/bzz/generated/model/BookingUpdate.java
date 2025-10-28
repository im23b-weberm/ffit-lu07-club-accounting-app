package ch.bzz.generated.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.time.LocalDate;
import java.util.Arrays;
import org.openapitools.jackson.nullable.JsonNullable;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.NoSuchElementException;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * BookingUpdate
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.7.0")
public class BookingUpdate {

  private Integer id;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private JsonNullable<LocalDate> date = JsonNullable.<LocalDate>undefined();

  private JsonNullable<String> text = JsonNullable.<String>undefined();

  private JsonNullable<Integer> debit = JsonNullable.<Integer>undefined();

  private JsonNullable<Integer> credit = JsonNullable.<Integer>undefined();

  private JsonNullable<Float> amount = JsonNullable.<Float>undefined();

  public BookingUpdate id(Integer id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
   */
  
  @Schema(name = "id", example = "17", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("id")
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public BookingUpdate date(LocalDate date) {
    this.date = JsonNullable.of(date);
    return this;
  }

  /**
   * Get date
   * @return date
   */
  @Valid 
  @Schema(name = "date", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("date")
  public JsonNullable<LocalDate> getDate() {
    return date;
  }

  public void setDate(JsonNullable<LocalDate> date) {
    this.date = date;
  }

  public BookingUpdate text(String text) {
    this.text = JsonNullable.of(text);
    return this;
  }

  /**
   * Get text
   * @return text
   */
  
  @Schema(name = "text", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("text")
  public JsonNullable<String> getText() {
    return text;
  }

  public void setText(JsonNullable<String> text) {
    this.text = text;
  }

  public BookingUpdate debit(Integer debit) {
    this.debit = JsonNullable.of(debit);
    return this;
  }

  /**
   * Get debit
   * @return debit
   */
  
  @Schema(name = "debit", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("debit")
  public JsonNullable<Integer> getDebit() {
    return debit;
  }

  public void setDebit(JsonNullable<Integer> debit) {
    this.debit = debit;
  }

  public BookingUpdate credit(Integer credit) {
    this.credit = JsonNullable.of(credit);
    return this;
  }

  /**
   * Get credit
   * @return credit
   */
  
  @Schema(name = "credit", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("credit")
  public JsonNullable<Integer> getCredit() {
    return credit;
  }

  public void setCredit(JsonNullable<Integer> credit) {
    this.credit = credit;
  }

  public BookingUpdate amount(Float amount) {
    this.amount = JsonNullable.of(amount);
    return this;
  }

  /**
   * Get amount
   * @return amount
   */
  
  @Schema(name = "amount", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("amount")
  public JsonNullable<Float> getAmount() {
    return amount;
  }

  public void setAmount(JsonNullable<Float> amount) {
    this.amount = amount;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BookingUpdate bookingUpdate = (BookingUpdate) o;
    return Objects.equals(this.id, bookingUpdate.id) &&
        equalsNullable(this.date, bookingUpdate.date) &&
        equalsNullable(this.text, bookingUpdate.text) &&
        equalsNullable(this.debit, bookingUpdate.debit) &&
        equalsNullable(this.credit, bookingUpdate.credit) &&
        equalsNullable(this.amount, bookingUpdate.amount);
  }

  private static <T> boolean equalsNullable(JsonNullable<T> a, JsonNullable<T> b) {
    return a == b || (a != null && b != null && a.isPresent() && b.isPresent() && Objects.deepEquals(a.get(), b.get()));
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, hashCodeNullable(date), hashCodeNullable(text), hashCodeNullable(debit), hashCodeNullable(credit), hashCodeNullable(amount));
  }

  private static <T> int hashCodeNullable(JsonNullable<T> a) {
    if (a == null) {
      return 1;
    }
    return a.isPresent() ? Arrays.deepHashCode(new Object[]{a.get()}) : 31;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BookingUpdate {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    date: ").append(toIndentedString(date)).append("\n");
    sb.append("    text: ").append(toIndentedString(text)).append("\n");
    sb.append("    debit: ").append(toIndentedString(debit)).append("\n");
    sb.append("    credit: ").append(toIndentedString(credit)).append("\n");
    sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
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

