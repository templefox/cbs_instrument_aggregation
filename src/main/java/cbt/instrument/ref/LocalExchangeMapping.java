package cbt.instrument.ref;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class LocalExchangeMapping implements ExchangeMapping {
    private Map<String,Map<String,String>> codeMap = new HashMap<>();

    public LocalExchangeMapping(){
        //Using label as the key of lme instrument
        this.addMap("LME","Lead 13 March 2018","PB_03_2018");
    }

    public void addMap(String exchange, String exchangeCode, String instrumentCode) {
        codeMap.computeIfAbsent(exchange, k -> new HashMap<>()).put(exchangeCode, instrumentCode);
    }

    @Override
    public String getCode(String exchange, String exchangeCode){
        return codeMap.getOrDefault(exchange, Collections.emptyMap()).getOrDefault(exchangeCode,exchangeCode);
    }
}
