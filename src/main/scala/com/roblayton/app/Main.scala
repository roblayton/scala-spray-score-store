package com.roblayton.app

import akka.actor.{Props, ActorSystem}
import akka.io.IO
import com.roblayton.app.config.Configuration
import com.roblayton.app.rest.RestServiceActor
import spray.can.Http

object Main extends App with Configuration {
  implicit val actorSystem = ActorSystem("rest-service")

  var restService = actorSystem.actorOf(Props[RestServiceActor], "rest-endpoint")

  IO(Http) ! Http.Bind(restService, serviceHost, servicePort)
}
