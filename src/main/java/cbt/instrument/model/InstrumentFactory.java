package cbt.instrument.model;

import cbt.instrument.Constant;
import cbt.instrument.ref.ExchangeMapping;

import java.time.LocalDate;

public class InstrumentFactory {

    private ExchangeMapping mapping;

    public Instrument create(Instrument.Type type, String[] args){
        switch (type) {
            case Prime:
                if (args.length < 7) return null;
                return new Instrument()
                        .setType(Instrument.Type.Prime)
                        .setLastTradingDate(LocalDate.parse(args[1], Constant.formatter))
                        .setDeliveryDate(LocalDate.parse(args[2], Constant.formatter))
                        .setMarket(args[3])
                        .setLabel(args[4])
                        .setExchangeCode(args[5])
                        .setTradeable(Boolean.getBoolean(args[6]));
            case Lme:
                if (args.length < 5) return null;
                String key = mapping.getCode("LME", args[4]);
                return new Instrument()
                        .setType(Instrument.Type.Lme)
                        .setLastTradingDate(LocalDate.parse(args[1], Constant.formatter))
                        .setDeliveryDate(LocalDate.parse(args[2], Constant.formatter))
                        .setMarket(args[3])
                        .setLabel(args[4])
                        .setExchangeCode(key);
            default:
                return null;
        }

    }

    public void setMapping(ExchangeMapping mapping) {
        this.mapping = mapping;
    }
}
