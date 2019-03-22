package cbt.instrument.publish;

import cbt.instrument.model.Instrument;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

public class StdoutPublisherTest {
    private StdoutPublisher publisher = spy(new StdoutPublisher());

    @Test
    public void publish() {
        publisher.publish(new Instrument(LocalDate.of(2019,12,2),LocalDate.of(2019,12,3),"nymx","WTI",false));
        verify(publisher).publish("|LAST_TRADING_DATE|DELIVERY_DATE|MARKET|LABEL|TRADABLE|\n" +
                "|02-12-2019|03-12-2019|nymx|WTI|FALSE|");
    }
}