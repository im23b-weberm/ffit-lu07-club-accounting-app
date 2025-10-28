package ch.bzz.generated.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * Booking
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.7.0")
public class Booking {

  private Integer id;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate date;

  private String text;

  private Integer debit;

  private Integer credit;

  private Float amount;

  public Booking() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public Booking(Integer id, LocalDate date, String text, Integer debit, Integer credit, Float amount) {
    this.id = id;
    this.date = date;
    this.text = text;
    this.debit = debit;
    this.credit = credit;
    this.amount = amount;
  }

  public Booking id(Integer id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
   */
  @NotNull 
  @Schema(name = "id", example = "15", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("id")
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Booking date(LocalDate date) {
    this.date = date;
    return this;
  }

  /**
   * Get date
   * @return date
   */
  @NotNull @Valid 
  @Schema(name = "date", example = "Sat Mar 15 01:00:00 CET 2025", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("date")
  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public Booking text(String text) {
    this.text = text;
    return this;
  }

  /**
   * Get text
   * @return text
   */
  @NotNull 
  @Schema(name = "text", example = "Bargeld abgehoben", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("text")
  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public Booking debit(Integer debit) {
    this.debit = debit;
    return this;
  }

  /**
   * Get debit
   * @return debit
   */
  @NotNull 
  @Schema(name = "debit", example = "1000", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("debit")
  public Integer getDebit() {
    return debit;
  }

  public void setDebit(Integer debit) {
    this.debit = debit;
  }

  public Booking credit(Integer credit) {
    this.credit = credit;
    return this;
  }

  /**
   * Get credit
   * @return credit
   */
  @NotNull 
  @Schema(name = "credit", example = "1020", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("credit")
  public Integer getCredit() {
    return credit;
  }

  public void setCredit(Integer credit) {
    this.credit = credit;
  }

  public Booking amount(Float amount) {
    this.amount = amount;
    return this;
  }

  /**
   * Get amount
   * @return amount
   */
  @NotNull 
  @Schema(name = "amount", example = "200.0", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("amount")
  public Float getAmount() {
    return amount;
  }

  public void setAmount(Float amount) {
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
    Booking booking = (Booking) o;
    return Objects.equals(this.id, booking.id) &&
        Objects.equals(this.date, booking.date) &&
        Objects.equals(this.text, booking.text) &&
        Objects.equals(this.debit, booking.debit) &&
        Objects.equals(this.credit, booking.credit) &&
        Objects.equals(this.amount, booking.amount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, date, text, debit, credit, amount);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Booking {\n");
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

