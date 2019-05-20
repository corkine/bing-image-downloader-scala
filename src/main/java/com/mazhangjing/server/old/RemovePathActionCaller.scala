package com.mazhangjing.server.old

import java.nio.file.Path

import org.apache.commons.io.FileUtils
import com.mazhangjing.server.old.Utils.Try

class RemovePathActionCaller(val path: List[Path],
                             val deleteNotExistFileWithNoException: Boolean = true) extends ActionCaller[String] {
  override def call: Either[Exception, String] = Try {
    val sb = new StringBuilder
    path.map(p => {
      val (_, inf) = doWithPath(p)
      sb.append(s"For file $p, Delete Status: $inf")
    })
    Right(sb.toString())
  }

  private def doWithPath(path:Path):(Boolean, String) = {
    if (path.toFile.isDirectory) {
      val directory = path.toFile
      FileUtils.deleteDirectory(directory)
      (true, "已删除文件夹")
    } else {
      val needDeleteFile = path.toFile
      if (needDeleteFile.exists()) {
        if (!needDeleteFile.delete()) (false, "文件存在，但无法删除")
        else (true, "文件存在，已删除")
      } else {
        if (deleteNotExistFileWithNoException) {
          (false, "文件不存在")
        } else {
          (true, "文件不存在，但是标准不严格")
        }
      }
    }
  }
}
