package calc;

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

        ActorRef calculatorActor = actorSystem.actorOf(Props.create(CalculatorService.class), "calculator-service");

        Future<Object> ask = Patterns.ask(calculatorActor, "message", new Timeout(10, TimeUnit.SECONDS));
        Object result = Await.result(ask, new FiniteDuration(5, TimeUnit.SECONDS));
        System.out.println(result);


    }
}
