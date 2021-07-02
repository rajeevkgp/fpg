package com.floating.group.interview.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "crypto_data")
public class CryptoData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long unix;

    private Date date;

    private String symbol;

    private BigDecimal open;

    private BigDecimal high;

    private BigDecimal low;

    private BigDecimal close;

    @Column(name = "volume_btc")
    private BigDecimal volumeBtc;

    @Column(name = "volume_usdt")
    private BigDecimal volumeUsdt;

    public CryptoData(Long unix, Date date, String symbol, BigDecimal open, BigDecimal high, BigDecimal low, BigDecimal close, BigDecimal volumeBtc, BigDecimal volumeUsdt) {
        this.unix = unix;
        this.date = date;
        this.symbol = symbol;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.volumeBtc = volumeBtc;
        this.volumeUsdt = volumeUsdt;
    }

    @Override
    public String toString() {
        return "CryptoData{" +
                "id=" + id +
                ", unix=" + unix +
                ", date=" + date +
                ", symbol='" + symbol + '\'' +
                ", open=" + open +
                ", high=" + high +
                ", low=" + low +
                ", close=" + close +
                ", volumeBtc=" + volumeBtc +
                ", volumeUsdt=" + volumeUsdt +
                '}';
    }
}
