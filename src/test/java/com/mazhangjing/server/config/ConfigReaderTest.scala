package com.mazhangjing.server.config

import java.nio.file.Paths

import org.scalatest.FunSuite

class ConfigReaderTest extends FunSuite {

  test("testCall") {
    new ConfigReader(Paths.get("/Users/corkine/Nutstore Files/WorkFolder/helper/src/main/resources/config.yml")).call match {
      case Left(e) => println(e)
      case Right(c) => println(c)
    }
  }

}
