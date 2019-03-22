package cbt.instrument.publish;

import cbt.instrument.model.Instrument;

import java.time.format.DateTimeFormatter;

public class StdoutPublisher implements Publisher {
    @Override
    public void publish(Instrument abstractInstrument) {
        StringBuilder sb = new StringBuilder("|LAST_TRADING_DATE|DELIVERY_DATE|MARKET|LABEL|TRADABLE|\n|");
        sb.append(abstractInstrument.getLastTradingDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))).append("|")
                .append(abstractInstrument.getDeliveryDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))).append("|")
                .append(abstractInstrument.getMarket()).append("|")
                .append(abstractInstrument.getLabel()).append("|")
                .append(abstractInstrument.isTradeable()?"TRUE":"FALSE").append("|");

        publish(sb.toString());
    }

    void publish(String str){
        System.out.println();
        System.out.println("Publishing...");
        System.out.println(str);
        System.out.println("Publishing done.");
    }
}
