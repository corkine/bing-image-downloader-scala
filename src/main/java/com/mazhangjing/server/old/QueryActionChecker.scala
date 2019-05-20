package com.mazhangjing.server.old

import Utils.Try
import org.slf4j.LoggerFactory

class QueryActionChecker(val checkCommand:String,
                         val checkCommandTest:String,
                         val runCommand:String,
                         val runCommandWellCheck:String,
                         val needRemovePathList: List[String]) extends ActionChecker[Boolean, Boolean] {

  private val logger = LoggerFactory.getLogger(getClass)

  override val ifCondition: Either[Exception, Boolean] => Option[ActionCaller[Boolean]] = {
    case Left(e) => logger.info(s"Can't Execute Query Action: $checkCommand, ${e.getMessage}"); None
    case Right(_) => Some(new RunVmwareActionChecker(runCommand, runCommandWellCheck, needRemovePathList))
  }

  override def call: Either[Exception, Boolean] = Try {
    import sys.process._
    val res = checkCommand.!!
    if (res.contains(checkCommandTest)) {
      Right(res.contains(checkCommandTest))
    } else Left(new RuntimeException(s"$checkCommand 命令没有获取预期的结果： $res"))
  }
}

object QueryActionChecker {

}
