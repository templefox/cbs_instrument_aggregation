package cbt.instrument.model;

import java.time.LocalDate;

public class Instrument {
    private LocalDate lastTradingDate;
    private LocalDate deliveryDate;
    private String market;
    private String label;
    private String exchangeCode;
    private boolean tradeable;
    private Type type;

    public Instrument() {
    }

    public Instrument(LocalDate lastTradingDate, LocalDate deliveryDate, String market, String label, boolean tradeable){
        this(Type.Publish,lastTradingDate,deliveryDate,market,label,null,tradeable);
    }

    public Instrument(Type type, LocalDate lastTradingDate, LocalDate deliveryDate, String market, String label, String exchangeCode, boolean tradeable) {
        this.lastTradingDate = lastTradingDate;
        this.deliveryDate = deliveryDate;
        this.market = market;
        this.label = label;
        this.exchangeCode = exchangeCode;
        this.tradeable = tradeable;
        this.type = type;
    }

    public LocalDate getLastTradingDate() {
        return lastTradingDate;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public String getMarket() {
        return market;
    }

    public String getLabel() {
        return label;
    }

    public boolean isTradeable() {
        return tradeable;
    }

    public String getExchangeCode() {
        return exchangeCode;
    }

    Instrument setLastTradingDate(LocalDate lastTradingDate) {
        this.lastTradingDate = lastTradingDate;
        return this;
    }

    Instrument setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
        return this;
    }

    Instrument setMarket(String market) {
        this.market = market;
        return this;
    }

    Instrument setLabel(String label) {
        this.label = label;
        return this;
    }

    Instrument setExchangeCode(String exchangeCode) {
        this.exchangeCode = exchangeCode;
        return this;
    }

    Instrument setTradeable(boolean tradeable) {
        this.tradeable = tradeable;
        return this;
    }

    public enum Type{
        Prime,
        Lme,
        Publish
    }

    public Type getType() {
        return type;
    }

    Instrument setType(Type type) {
        this.type = type;
        return this;
    }

}
