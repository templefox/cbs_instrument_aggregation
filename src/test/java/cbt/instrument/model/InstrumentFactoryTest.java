package cbt.instrument.model;

import cbt.instrument.ref.LocalExchangeMapping;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class InstrumentFactoryTest {
    private InstrumentFactory factory = new InstrumentFactory();

    @Test
    public void createNull() {
        Instrument instrument = factory.create(Instrument.Type.Prime, new String[]{"", ""});
        assertNull(instrument);
    }
    @Test
    public void createPrime() {
        Instrument instrument = factory.create(Instrument.Type.Prime, new String[]{"lme","14-03-2018","18-03-2018","LME_PB","Lead 13 March 2018","PB_03_2018","FALSE"});
        assertNotNull(instrument);
    }
    @Test
    public void createLme() {
        LocalExchangeMapping mock = mock(LocalExchangeMapping.class);
        factory.setMapping(mock);
        doReturn("lme unique key").when(mock).getCode("LME","Lead 13 March 2018");

        Instrument instrument = factory.create(Instrument.Type.Lme, new String[]{"lme","15-03-2018","17-03-2018","LME_PB","Lead 13 March 2018"});
        assertNotNull(instrument);
        assertEquals("lme unique key",instrument.getExchangeCode());
    }
}