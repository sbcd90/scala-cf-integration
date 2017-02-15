package com.sap.cf

import akka.actor.{ActorSystem, Props}
import akka.io.IO
import akka.routing.RoundRobinPool
import akka.pattern.ask
import akka.util.Timeout
import spray.can.Http
import scalaz._
import Scalaz._

import scala.concurrent.duration._

object Boot {

  def main(args: Array[String]): Unit = {
    implicit val system = ActorSystem("on-spray-can")
    val availableProcessors = Runtime.getRuntime.availableProcessors()

    val noOfServices = availableProcessors
    val service = system.actorOf(RoundRobinPool(noOfServices).props(Props[SimpleActor]), "simple-actor")
    println(s"Started running on ${availableProcessors} spray-service actors")

    implicit val timeout = Timeout(5.seconds)
    println(System.getenv("PORT").point[Option].get)
    IO(Http) ? Http.Bind(service, interface = "0.0.0.0", port = (if(System.getenv("PORT") != null) System.getenv("PORT") else "8088").toInt)
  }
}