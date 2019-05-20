package com.mazhangjing.server.config

import java.io.FileReader
import java.nio.file.Path

import com.mazhangjing.server.old.Utils.Try
import com.mazhangjing.server.old.ActionCaller
import org.yaml.snakeyaml.Yaml

class ConfigReader(val fromPath:Path) extends ActionCaller[Option[Config]] {
  override def call: Either[Exception, Option[Config]] = Try {
    val inp = new FileReader(fromPath.toFile)
    val config = new Yaml().loadAs(inp, classOf[Config]) match {
      case null => None
      case c => Some(c)
    }
    inp.close()
    Right(config)
  }
}
