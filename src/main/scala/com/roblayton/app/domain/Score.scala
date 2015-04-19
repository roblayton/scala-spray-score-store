package com.roblayton.app.domain

import scala.slick.driver.MySQLDriver.simple._

/**
 * @param id          primary key
 * @param initials    player's 3-letter initials 
 * @param score       
 */

 case class Score(id: Option[Long], initials: String, score: Int)

 object Scores extends Table[Score]("scores") {
  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def initials = column[String]("initials")
  def score = column[Int]("score")

  def * = id.? ~ initials ~ score <>(Score, Score.unapply _)

  val findById = for {
    id <- Parameters[Long]
    score <- this if score.id is id
  } yield score
 }
