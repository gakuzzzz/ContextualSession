package models

import _root_.libs.ContextualSession
import scalikejdbc._

case class Task(id: Int, title: String, done: Boolean)

object Task extends ContextualSession {

  val * = { rs: WrappedResultSet =>
    Task(rs.intOpt("id").get, rs.string("title"), rs.booleanOpt("done").get)
  }

  def findAll: Seq[Task] = SQL("""SELECT * FROM task""").map(*).list.apply()

  def create(title: String): Task = {
    val id = SQL(
      """INSERT INTO task (title, done) VALUES (?, false)"""
    ).bind(title).updateAndReturnGeneratedKey.apply()
    Task(id.toInt, title, false)
  }

}


