package com.roblayton.app.rest

import akka.actor.Actor
import akka.event.slf4j.SLF4JLogging
import com.roblayton.app.dao.ScoreDAO
import com.roblayton.app.domain._
import spray.http._
import spray.routing._

// Actor
class RestServiceActor extends Actor with RestService {
  implicit def actorRefFactory = context
  def receive = runRoute(rest)
}

// Service
trait RestService extends HttpService with SLF4JLogging {
  val scoreService = new ScoreDAO
  implicit val executionContext = actorRefFactory.dispatcher

  val rest = respondWithMediaType(MediaTypes.`application/json`) {
    get {
      path("scores") {
        complete {
          "Hello World!"
        }
      }
    }
  }
}
