package libs

import play.api.mvc._
import scala.util.DynamicVariable
import scalikejdbc._

object ContextualSession {

  def inSession[A](f: => A): A =
    if (context.value.isDefined) f
    else DB localTx { s => context.withValue(Some(s))(f) }

  val context = new DynamicVariable[Option[DBSession]](None)

}
trait ContextualSession {

  import ContextualSession._

  implicit def session: DBSession = context.value.get // None の時は例外でOK

}
object TxAction {

  import ContextualSession._

  def apply[A](bodyParser: BodyParser[A])(block: Request[A] => Result): Action[A] = new Action[A] {
    def parser = bodyParser
    def apply(ctx: Request[A]) = inSession { block(ctx) }
  }

  def apply(block: Request[AnyContent] => Result): Action[AnyContent] = apply(BodyParsers.parse.anyContent)(block)

  def apply(block: => Result): Action[AnyContent] = apply(_ => block)

}
