package cbt.instrument.rule;

import cbt.instrument.model.Instrument;

import java.util.List;

public interface AggregationRule {
    /**
     * Should keep the param immutable
     */
    Instrument merge(List<Instrument> abstractInstruments);
}
