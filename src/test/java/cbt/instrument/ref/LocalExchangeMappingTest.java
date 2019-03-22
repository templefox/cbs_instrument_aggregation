package cbt.instrument.ref;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LocalExchangeMappingTest {
    private LocalExchangeMapping localExchangeMapping;

    @Before
    public void setup(){
        localExchangeMapping = new LocalExchangeMapping();
    }

    @Test
    public void addMap() {
        localExchangeMapping.addMap("LME","Crude Oil 01 April 2018","WTI_04_2018");
        String key = localExchangeMapping.getCode("LME", "Crude Oil 01 April 2018");
        assertEquals("WTI_04_2018",key);

        localExchangeMapping.addMap("NYMEX","201804WTI","WTI_04_2018");
        String key2 = localExchangeMapping.getCode("NYMEX", "201804WTI");
        assertEquals("WTI_04_2018",key);
    }

    @Test
    public void getCode() {
        String key = localExchangeMapping.getCode("LME", "Lead 13 March 2018");
        assertEquals("PB_03_2018",key);
    }

    @Test
    public void emptyKey(){
        String code = localExchangeMapping.getCode("LME", "A key should be bypassed");
        assertEquals("A key should be bypassed",code);

        String code2 = localExchangeMapping.getCode("NYMEX", "Another key should be bypassed");
        assertEquals("Another key should be bypassed",code2);
    }
}