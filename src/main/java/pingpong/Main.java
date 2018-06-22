package pingpong;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.pattern.Patterns;
import akka.util.Timeout;
import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.FiniteDuration;

import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws Exception {
        ActorSystem actorSystem = ActorSystem.create("calculator-system");

        ActorRef pingActor = actorSystem.actorOf(Props.create(PingPongService.class), "pingpong-service");

        Future<Object> ask = Patterns.ask(pingActor, "ping", new Timeout(10, TimeUnit.SECONDS));
        Object result = Await.result(ask, new FiniteDuration(5, TimeUnit.SECONDS));
        System.out.println(result);

    }
}
