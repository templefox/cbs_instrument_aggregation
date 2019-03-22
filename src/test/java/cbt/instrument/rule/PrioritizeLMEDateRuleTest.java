package cbt.instrument.rule;

import cbt.instrument.model.Instrument;
import com.google.common.collect.ImmutableList;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class PrioritizeLMEDateRuleTest {
    private PrioritizeLMEDateRule rule = new PrioritizeLMEDateRule();

    @Test
    public void allPrimes() {
        {
            ImmutableList.Builder<Instrument> builder = ImmutableList.<Instrument>builder()
                    .add(new Instrument(Instrument.Type.Prime, LocalDate.of(2000, 1, 1), LocalDate.of(2000, 1, 1), "LME_PB", "PB", "PB_01_2000", true));

            Instrument merge = rule.merge(builder.build());
            assertEquals(LocalDate.of(2000, 1, 1), merge.getDeliveryDate());
            assertFalse(merge.isTradeable());
        }
        {
            ImmutableList.Builder<Instrument> builder = ImmutableList.<Instrument>builder()
                    .add(new Instrument(Instrument.Type.Prime, LocalDate.of(2000, 2, 1), LocalDate.of(2000, 2, 1), "LME_PB", "PB", "PB_02_2000", true))
                    .add(new Instrument(Instrument.Type.Prime, LocalDate.of(2000, 1, 1), LocalDate.of(2000, 1, 1), "LME_PB", "PB", "PB_01_2000", true));
            Instrument merge = rule.merge(builder.build());
            assertEquals(LocalDate.of(2000, 2, 1), merge.getDeliveryDate());
            assertFalse(merge.isTradeable());
        }
    }

    @Test
    public void allLmeShouldBeTradeable() {
        {
            ImmutableList.Builder<Instrument> builder = ImmutableList.<Instrument>builder()
                    .add(new Instrument(Instrument.Type.Lme, LocalDate.of(2000, 1, 1), LocalDate.of(2000, 1, 1), "LME_PB", "PB", "PB_01_2000", true));

            Instrument merge = rule.merge(builder.build());
            assertEquals(LocalDate.of(2000, 1, 1), merge.getDeliveryDate());
            assertTrue(merge.isTradeable());
        }
        {
            ImmutableList.Builder<Instrument> builder = ImmutableList.<Instrument>builder()
                    .add(new Instrument(Instrument.Type.Lme, LocalDate.of(2000, 2, 1), LocalDate.of(2000, 2, 1), "LME_PB", "PB", "PB_02_2000", false))
                    .add(new Instrument(Instrument.Type.Lme, LocalDate.of(2000, 1, 1), LocalDate.of(2000, 1, 1), "LME_PB", "PB", "PB_01_2000", true));
            Instrument merge = rule.merge(builder.build());
            assertEquals(LocalDate.of(2000, 2, 1), merge.getDeliveryDate());
            assertTrue(merge.isTradeable());
        }
    }

    @Test
    public void lmeOverwrite() {
        ImmutableList.Builder<Instrument> builder = ImmutableList.<Instrument>builder()
                .add(new Instrument(Instrument.Type.Prime, LocalDate.of(2000, 2, 1), LocalDate.of(2000, 2, 1), "LME_PB", "PB", "PB_02_2000", false))
                .add(new Instrument(Instrument.Type.Lme, LocalDate.of(2000, 1, 1), LocalDate.of(2000, 1, 1), "LME_PB", "PB", "PB_01_2000", true));
        Instrument merge = rule.merge(builder.build());
        assertEquals(LocalDate.of(2000, 1, 1), merge.getDeliveryDate());
        assertFalse(merge.isTradeable());
    }

    @Test
    public void primeFirstThenLme() {
        ImmutableList.Builder<Instrument> builder = ImmutableList.<Instrument>builder()
                .add(new Instrument(Instrument.Type.Lme, LocalDate.of(2000, 1, 1), LocalDate.of(2000, 1, 1), "LME_PB", "PB", "PB_01_2000", true))
                .add(new Instrument(Instrument.Type.Prime, LocalDate.of(2000, 2, 1), LocalDate.of(2000, 2, 1), "LME_PB", "PB", "PB_02_2000", false));

        Instrument merge = rule.merge(builder.build());
        assertEquals(LocalDate.of(2000, 1, 1), merge.getDeliveryDate());
        assertTrue(merge.isTradeable());
    }
}