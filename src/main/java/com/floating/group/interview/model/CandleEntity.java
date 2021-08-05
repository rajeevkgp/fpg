package com.floating.group.interview.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

public class CandleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonProperty("id")
    private Long id;

    @Column(name = "exchange")
    private String exchange;

    @Column(name = "currency_pair")
    private String currency_pair;

    @Column(name = "time")
    private Long time;

    @Column(name = "low")
    private BigDecimal low;

    @Column(name = "high")
    private BigDecimal high;

    @Column(name = "open")
    private BigDecimal open;

    @Column(name = "close")
    private BigDecimal close;

    @Column(name = "volume")
    private BigDecimal volume;

    public Long getTime() {
        return this.time;
    }

}
