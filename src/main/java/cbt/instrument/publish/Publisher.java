package cbt.instrument.publish;

import cbt.instrument.model.Instrument;

public interface Publisher {
    void publish(Instrument abstractInstrument);
}
