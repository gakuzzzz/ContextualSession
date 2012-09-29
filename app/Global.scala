import play.api._
import models._
import _root_.libs.ContextualSession._

object Global extends GlobalSettings {

  override def onStart(app: Application) {
    inSession {
      if (Task.findAll.isEmpty) {
        Seq("部屋の掃除", "数学のレポート", "食材の買出し") foreach Task.create
      }
    }
  }

}
