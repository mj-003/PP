package zad3;

import akka.actor.AbstractActor;
import akka.actor.Props;

public class Filtration extends AbstractActor {
    private final int resourcesRequired = 25;
    private final int successRate = 5;
    private final int processingTime = 14;
    private int availableResources;

    public Filtration() {
        this.availableResources = resourcesRequired;
    }

    static public Props props() {
        return Props.create(Filtration.class, Filtration::new);
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(Message.class, msg -> {
                    if (availableResources >= resourcesRequired) {
                        Thread.sleep(processingTime);
                        if (Math.random() * 100 < successRate) {
                            availableResources -= resourcesRequired;
                            System.out.println("Filtration successfully completed.");
                            sender().tell(new Message(true), getSelf());
                        } else {
                            System.out.println("Filtration failed.");
                            sender().tell(new Message(false), getSelf());
                        }
                    } else {
                        System.out.println("Insufficient resources for filtration.");
                        sender().tell(new Message(false), getSelf());
                    }
                })
                .build();
    }
}
