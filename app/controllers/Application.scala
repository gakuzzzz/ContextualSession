package controllers

import play.api.mvc._
import views._
import models._
import _root_.libs._

object Application extends Controller {

  def index = TxAction {
    Ok(html.index(Task.findAll))
  }

}

