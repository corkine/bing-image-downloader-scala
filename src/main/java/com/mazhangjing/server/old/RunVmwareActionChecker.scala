package com.mazhangjing.server.old

import java.nio.file.Paths

import Utils.Try
import org.slf4j.LoggerFactory

class RunVmwareActionChecker(val runCommand:String, val runCommandWellCheck:String,
                             val needRemovePathList: List[String])
  extends ActionChecker[Boolean, String] {

  private val logger = LoggerFactory.getLogger(classOf[QueryActionChecker])

  override val ifCondition: Either[Exception, Boolean] => Option[ActionCaller[String]] = {
    case Left(e) =>
      logger.warn(s"RunVmwareActionChecker run with a Exception, init LockRemove Sequence${e.getMessage}")
      Some(new RemovePathActionCaller(needRemovePathList.map(s => Paths.get(s))))
    case Right(_) => logger.info(s"RunVmwareActionChecker run well, exist system now..."); None
  }

  override def call: Either[Exception, Boolean] = Try {
    import sys.process._
    val res = runCommand.!!
    if (res.contains(runCommandWellCheck)) Right(true)
    else Left(new RuntimeException(s"运行了命令，但是得到了非期望的结果 $res"))
  }
}


object RunVmwareActionChecker {

}