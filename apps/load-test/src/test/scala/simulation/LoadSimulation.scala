package simulation

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration._

class LoadSimulation extends Simulation {

  val targetUrl = System.getProperty("TARGET_URL")
  val simUsers = System.getProperty("SIM_USERS").toInt

  val myScenario = scenario("Webflux Demo").exec(
    repeat(7) {
      exec(
        http("request_1").get(targetUrl)
      ).pause(1 ,2 )
    }
  )
  setUp(myScenario.inject(rampUsers(simUsers) during(20)))
}
/*
* //reactive

./gradlew -p apps/load-test -DTARGET_URL=http://localhost:8082/300  -DSIM_USERS=5000 gatlingRun

//block

./gradlew -p apps/load-test -DTARGET_URL=http://localhost:8083/300  -DSIM_USERS=5000 gatlingRun
* */