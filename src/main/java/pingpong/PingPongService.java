package pingpong;

import akka.actor.AbstractLoggingActor;

public class PingPongService extends AbstractLoggingActor {
    public Receive createReceive() {
        return receiveBuilder().match(String.class, s -> {
            System.out.println(s);
            sender().tell("pong", self());
        }).build();
    }
}
