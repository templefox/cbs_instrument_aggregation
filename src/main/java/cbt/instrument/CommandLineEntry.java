package cbt.instrument;

import cbt.instrument.model.Instrument;
import cbt.instrument.model.InstrumentFactory;
import cbt.instrument.publish.StdoutPublisher;
import cbt.instrument.ref.LocalExchangeMapping;
import cbt.instrument.rule.PrioritizeLMEDateRule;

import java.util.Scanner;

public class CommandLineEntry {
    public static void main(String[] args) {
        //Inject services
        AggregationService aggregationService = new AggregationService();
        aggregationService.addRule(new PrioritizeLMEDateRule(), 0);
        aggregationService.setPublisher(new StdoutPublisher());

        InstrumentFactory instrumentFactory = new InstrumentFactory();
        LocalExchangeMapping localExchangeMapping = new LocalExchangeMapping();
        instrumentFactory.setMapping(localExchangeMapping);


        help();
        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            String[] input = sc.nextLine().split(",");

            if (input.length < 1) return;

            String command = input[0];

            switch (command) {
                case "LME":
                    aggregationService.receive(instrumentFactory.create(Instrument.Type.Lme, input));
                    break;
                case "PRIME":
                    aggregationService.receive(instrumentFactory.create(Instrument.Type.Prime, input));
                    break;
                case "Map":
                    localExchangeMapping.addMap(input[1], input[2], input[3]);
                    break;
                default:
                    help();
            }
        }
    }

    private static void help() {
        System.out.println("Using stdin as the upstream of instruments.");
        System.out.println("LME for lme instrument");
        System.out.println("    e.g.    LME,15-03-2018,17-03-2018,LME_PB,Lead 13 March 2018");
        System.out.println("PRIME for PRIME instrument");
        System.out.println("    e.g.    PRIME,14-03-2018,18-03-2018,LME_PB,Lead 13 March 2018,PB_03_2018,FALSE");
        System.out.println("Map to add/edit exchange code mapping, for example to add key mapping from LME to PRIME, use <EXCHANGE>,<EXCHANGE_KEY>,<UNIFIED_KEY>");
        System.out.println("    e.g.    Map,LME,Lead 13 March 2018,PB_03_2018");
        System.out.println("Help to show this view.");
        System.out.println("---------------------------------------------------");
    }
}
