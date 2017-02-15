package com.sap.cf

import akka.actor.Actor
import akka.event.Logging
import spray.routing.HttpService
import spray.http._

import scala.concurrent.ExecutionContext

class SimpleActor extends Actor with SimpleRestService {

  implicit val ec = ExecutionContext.global

  def actorRefFactory = context

  override def receive = runRoute(serviceRoute)
}

trait SimpleRestService extends HttpService {

  this: SimpleActor =>
    val log = Logging(context.system, this)

  val serviceRoute =
    path("") {
      get {
        respondWithMediaType(MediaTypes.`text/html`) {
          complete("Hello World")
        }
      }
    }
}