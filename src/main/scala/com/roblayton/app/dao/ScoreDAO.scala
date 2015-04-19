package com.roblayton.app.dao

import com.roblayton.app.config.Configuration
import com.roblayton.app.domain._
import java.sql._
import scala.Some
import scala.slick.driver.MySQLDriver.simple.Database.threadLocalSession
import scala.slick.driver.MySQLDriver.simple._
import slick.jdbc.meta.MTable

class ScoreDAO extends Configuration {

  // init db
  private val db = Database.forURL(url = "jdbc:mysql://%s:%d/%s".format(dbHost, dbPort, dbName),
    user = dbUser, password = dbPassword, driver = dbDriver)

  // create tables if they don't exist
  db.withSession {
    if (MTable.getTables("scores").list().isEmpty) {
      Scores.ddl.create
    }
  }

  def create(score: Score): Either[Failure, Score] = {
    try {
      val id = db.withSession {
        Scores returning Scores.id insert score
      }
      Right(score.copy(id = Some(id)))
    } catch {
      case e: SQLException =>
        Left(databaseError(e))
    }
  }

  def delete(id: Long): Either[Failure, Score] = {
    try {
      db.withTransaction {
        val query = Scores.where(_.id === id)
        val scores = query.run.asInstanceOf[List[Score]]
        scores.size match {
          case 0 =>
            Left(notFoundError(id))
          case _ => {
            query.delete
            Right(scores.head)
          }
        }
      }
    } catch {
      case e: SQLException =>
        Left(databaseError(e))
    }
  }

  def top(params: ScoreSearchParameters): Either[Failure, List[Score]] = {
    try {
      db.withSession {
        val query = for {
          score <- Scores if {
            Seq(
              params.initials.map(score.initials is _)
            ).flatten match {
              case Nil => ConstColumn.TRUE
              case seq => seq.reduce(_ && _)
            }
          }
        } yield score

        Right(query.run.toList)
      }
    } catch {
      case e: SQLException =>
        Left(databaseError(e))
    }
  }

  protected def databaseError(e: SQLException) =
    Failure("%d: %s".format(e.getErrorCode, e.getMessage), FailureType.DatabaseFailure)

  protected def notFoundError(scoreId: Long) =
    Failure("Score with id=%d does not exist".format(scoreId), FailureType.NotFound)
}
