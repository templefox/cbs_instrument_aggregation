package cbt.instrument;

import cbt.instrument.model.Instrument;
import cbt.instrument.publish.Publisher;
import cbt.instrument.rule.AggregationRule;

import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;

public class AggregationService {
    private List<AggregationRule> rules = new ArrayList<>();
    private Publisher publisher;

    //Assuming infinite memory
    private ConcurrentHashMap<String,ConcurrentLinkedDeque<Instrument>> cache = new ConcurrentHashMap<>();

    public void receive(Instrument abstractInstrument){
        if(abstractInstrument==null){
            //log
            return;
        }
        Deque<Instrument> historyQueue = cache.computeIfAbsent(abstractInstrument.getExchangeCode(), k->new ConcurrentLinkedDeque<>());
        historyQueue.offerFirst(abstractInstrument);
        Instrument publish = null;
        for (AggregationRule rule : rules) {
            Instrument result = rule.merge(new ArrayList<>(historyQueue));
            if(result != null){
                publish = result;
                break;
            }
        }

        if(publish!=null) send(publish);
    }

    public void send(Instrument result){
        publisher.publish(result);
    }

    public void addRule(AggregationRule rule, int priority) {
        rules.add(priority,rule);
    }

    public AggregationService setPublisher(Publisher publisher) {
        this.publisher = publisher;
        return this;
    }
}
