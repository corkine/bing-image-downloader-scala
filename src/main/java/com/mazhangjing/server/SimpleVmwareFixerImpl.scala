package com.mazhangjing.server

import java.io.File
import java.nio.file.{Path, Paths}

import com.mazhangjing.server.config.{Config, ConfigReader}
import org.apache.commons.io.FileUtils
import org.slf4j.LoggerFactory

class SimpleVmwareFixerImpl {

  private val logger = LoggerFactory.getLogger(getClass)

  private val config: Config = {
    val reader = new ConfigReader(Paths.get(System.getProperty("user.dir") + File.separator + "config.yml"))
    reader.call match {
      case Left(e) => logger.warn(s"Can't Read From User.dir/config.yml, ${e.getMessage}"); new Config()
      case Right(c) => c match {
        case None => logger.warn(s"Can't Read From User.dir/config.yml"); new Config()
        case Some(cc) => cc
      }
    }
  }

  private def doRetrySequence():Unit = {
    (0 to config.getRetry).foreach(time => {
      logger.info(s"Try For $time times")
      if (runSequence()) return
    })
  }

  private def runSequence(): Boolean = {
    checkIfHaveVmNow match {
      case Some(true) =>
        logger.info("[Sequence] 检查虚拟机符合预期，程序终止 0"); true
      case None =>
        logger.error("[Sequence] 检查虚拟机过程出错，程序终止 -1"); false
      case Some(false) =>
        logger.info("[Sequence] 检查虚拟机返回结果不符合预期，试着重启虚拟机")
        tryRunVmNow match {
          case None =>
            logger.error("[Sequence] 重启虚拟机过程出错，程序终止 -1"); false
          case Some(true) =>
            logger.info("[Sequence] 重启虚拟机返回正常，程序终止 0"); true
          case Some(false) =>
            logger.info("[Sequence] 重启虚拟机返回不正常，尝试删除锁文件")
            if (tryDeleteNow) {
              logger.info("[Sequence] 删除锁文件(可能不存在)完毕。"); false
            } else {
              logger.info("[Sequence] 删除锁文件发生错误。"); false
            }
        }
    }

  }

  import sys.process._

  private def Try[T](op: => Option[T]):Option[T] = {
    try op catch { case e: Exception => System.err.println(e.getMessage); None }
  }

  private def checkIfHaveVmNow: Option[Boolean] = Try {
    val res = config.getVmCheckCommand.!!
    if (res.contains(config.getVmCheckContains)) Some(true)
    else Some(false)
  }

  private def tryRunVmNow: Option[Boolean] = Try {
    val res = config.getVmRunCommand.!!
    if (res.contains(config.getVmRunContains)) Some(true)
    else Some(false)
  }

  private def tryDeleteNow: Boolean = {
    var clearMark = true
    config.getNeedRemovePathList.forEach(fileName => {
      val file = Paths.get(fileName).toFile
      safeDeleteFileOrFiles(file) match {
        case None => clearMark = false; logger.warn("在删除文件时发生错误")
        case Some(true) =>
        case Some(false) => logger.warn("在删除文件时存在空文件")
      }
    })
    clearMark
  }

  private def safeDeleteFileOrFiles(path: File): Option[Boolean] = Try {
    if (path.exists()) {
      if (path.isDirectory) {
        FileUtils.deleteDirectory(path)
        Some(true)
      } else {
        path.delete()
        Some(true)
      }
    } else Some(false)
  }

}

object SimpleVmwareFixerImpl {
  def main(args: Array[String]): Unit = {
    new SimpleVmwareFixerImpl().doRetrySequence()
  }
}
