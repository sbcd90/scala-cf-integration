package controllers

import play.mvc.{Controller, Result, Results}

class MainController extends Controller {
  def index(): Result = Results.ok("Hello World")
}