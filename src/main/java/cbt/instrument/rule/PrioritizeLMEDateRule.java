package cbt.instrument.rule;

import cbt.instrument.model.Instrument;

import java.util.List;

public class PrioritizeLMEDateRule implements AggregationRule {

    @Override
    public Instrument merge(List<Instrument> instruments) {
        if(instruments.size() == 0) return null;

        Instrument current = instruments.get(0);

        if (current.getType()== Instrument.Type.Prime) {
            //The incoming one is PRIME
            for (int i = 1; i < instruments.size(); i++) {
                Instrument oldInstrument = instruments.get(i);
                if(oldInstrument.getType()== Instrument.Type.Lme){
                    return new Instrument(oldInstrument.getLastTradingDate(),oldInstrument.getDeliveryDate(),current.getMarket(),current.getLabel(),false);
                }
            }

            //If no historical lme, bypass.
            return new Instrument(current.getLastTradingDate(), current.getDeliveryDate(), current.getMarket(), current.getLabel(), false);
        }else if (current.getType()== Instrument.Type.Lme){
            return new Instrument(current.getLastTradingDate(), current.getDeliveryDate(), current.getMarket(), current.getLabel(), true);
        }
        return null;
    }
}
