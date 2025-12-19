package org.example.teahouse.simulations.steep;

import io.gatling.javaapi.core.ChainBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;

import java.time.Duration;

import static io.gatling.http.HeaderNames.Accept;
import static io.gatling.http.HeaderNames.ContentType;
import static io.gatling.http.HeaderValues.ApplicationJson;
import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;

public class SteepTeaSimulation extends Simulation {
    final Duration duration = Duration.ofMinutes(120);
    final int usersPerSec = 5;

    final HttpProtocolBuilder httpProtocol = http.baseUrl("http://localhost:8090")
        .contentTypeHeader(ApplicationJson())
        .acceptHeader(ApplicationJson());

    final ChainBuilder makeRandomTea = exec(http("makeRandomTea")
        .get(_ -> "/tea/%s".formatted(generateTeaName()))
        .queryParam("size", _ -> generateTeaSize())
        .header(ContentType(), ApplicationJson())
        .header(Accept(), ApplicationJson())
    );

    String generateTeaName() {
        double random = Math.random();
        if (random < 0.1) return "english breakfast";
        else if (random < 0.7) return "sencha";
        else if (random < 0.9) return "da hong pao";
        else return "gyokuro";
    }

    String generateTeaSize() {
        double random = Math.random();
        if (random < 0.5) return "small";
        else if (random < 0.75) return "medium";
        else return "large";
    }

    {
        System.out.println(("duration: %s".formatted(duration)));
        System.out.println(("constantUsersPerSec: %d".formatted(usersPerSec)));
        setUp(scenario("steepTea")
            .exec(makeRandomTea)
            .injectOpen(constantUsersPerSec(usersPerSec).during(duration))
        ).protocols(httpProtocol);
    }
}
