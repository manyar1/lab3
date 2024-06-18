package ru.daniel.exchangeRateTracking.model;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@Entity
@Table(name = "exchange_rate")
public class ExchangeDB {

    @Id
    @Column(name = "id")
    @GenericGenerator(name="kaugen" , strategy="increment")
    @GeneratedValue(generator="kaugen")
    private Integer id;

    @Column(name = "date")
    private String date;

    @Column(name = "country")
    private String country;

    @Column(name = "currency")
    private String currency;

    @Column(name = "amount")
    private String amount;

    @Column(name = "code")
    private String code;

    @Column(name = "rate")
    private String rate;

    public ExchangeDB() {}

    public ExchangeDB(Integer id, String date, String country,
                      String currency, String amount, String code, String rate) {
        this.id = id;
        this.date = date;
        this.country = country;
        this.currency = currency;
        this.amount = amount;
        this.code = code;
        this.rate = rate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "ExchangeDB{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", country='" + country + '\'' +
                ", currency='" + currency + '\'' +
                ", amount='" + amount + '\'' +
                ", code='" + code + '\'' +
                ", rate='" + rate + '\'' +
                '}';
    }
}
