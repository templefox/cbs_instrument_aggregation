package cbt.instrument;

import cbt.instrument.model.Instrument;
import cbt.instrument.model.InstrumentFactory;
import cbt.instrument.publish.Publisher;
import cbt.instrument.rule.AggregationRule;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class AggregationServiceTest {
    private AggregationService aggregationService = new AggregationService();
    private AggregationRule rule = mock(AggregationRule.class);
    private Publisher publisher = mock(Publisher.class);

    @Test
    public void testApplyRule(){
        aggregationService.addRule(rule,0);

        Instrument instrument = new InstrumentFactory().create(Instrument.Type.Prime, new String[]{"lme", "14-03-2018", "18-03-2018", "LME_PB", "Lead 13 March 2018", "PB_03_2018", "FALSE"});
        aggregationService.receive(instrument);
        aggregationService.receive(instrument);

        ArgumentCaptor<List> ins = ArgumentCaptor.forClass(List.class);
        verify(rule,times(2)).merge(ins.capture());

        assertEquals(1,ins.getAllValues().get(0).size());
        assertEquals(2,ins.getAllValues().get(1).size());
    }
}