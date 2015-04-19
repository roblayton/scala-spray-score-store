package com.roblayton.app.config

import com.typesafe.config.ConfigFactory
import util.Try

trait Configuration {
  val config = ConfigFactory.load()

  // Service
  lazy val serviceHost = Try(config.getString("service.host")).getOrElse("localhost")
  lazy val servicePort = Try(config.getInt("service.port")).getOrElse(8080)

  // Database
  lazy val dbDriver = Try(config.getString("db.driver")).getOrElse("com.mysql.jdbc.Driver")
  lazy val dbHost = Try(config.getString("db.host")).getOrElse("localhost")
  lazy val dbPort = Try(config.getInt("db.port")).getOrElse(3306)
  lazy val dbName = Try(config.getString("db.name")).getOrElse("mysql")
  lazy val dbUser = Try(config.getString("db.user")).toOption.orNull
  lazy val dbPassword = Try(config.getString("db.password")).toOption.orNull
}
