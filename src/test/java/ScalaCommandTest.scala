
import org.scalatest.{FlatSpec, Matchers}

import scala.sys.process._

class ScalaCommandTest extends FlatSpec with Matchers {
  "A command" should "work well" in {
    val result = "ls -a".!!
    println(result)
  }

/*  "A ActionChecker" should "work well" in {
    class CatActionCaller(val fileName:String) extends ActionCaller[String] {
      override def call: Either[Exception, String] = try {
        val res = s"cat $fileName".!!
        Right(res)
      } catch { case e: Exception => Left(e) }
    }
    val cc = new ActionChecker[String] {
      override val ifCondition: Either[Exception, String] => Option[CatActionCaller] = {
        case Left(exp) => System.err.println(exp.getMessage); None
        case Right(name) => System.out.println(s"Get Result: $name"); Option(new CatActionCaller(name))
      }

      override def call: Either[Exception, String] = try {
          val res = "ls -a".!!
          res.split("\n").toBuffer.find(_.contains("pom.xml")) match {
            case None => Left(new NoSuchElementException("没有此元素"))
            case Some(va) => Right(va.trim)
          }
        } catch {case e: Exception => Left(e)}
    }
    val result = cc.doCheckAndGetNextActionCaller.get.call match {
      case Right(re) => re
      case Left(e) => e.printStackTrace(System.out); ""
    }
    assert(!result.isEmpty, "应该执行 cat pom.xml 命令并得到结果")
  }

  import com.mazhangjing.server.old.Utils.Try

  "A ConditionChecker" should "worl well" in {
    class ErrorMessageCaller(val exception: Exception) extends ActionCaller[String] {
      override def call: Either[Exception, String] = Try {
        println(s"ErrorMessage is Called by errAction Now... ${exception.getMessage}")
        Right("Done")
      }
    }
    val rightAction = new ActionCaller[String] {
      override def call: Either[Exception, String] = Try {
        println("EveryThing run well...")
        Right("Done")
      }
    }
    val cc = new ConditionChecker[String] {
      override val from: Either[Exception, String] = Left(new NoSuchElementException("一个自定义的错误"))
      override val ifCondition: Either[Exception, String] => Some[ActionCaller[String]] = {
        case Left(v) => Some(new ErrorMessageCaller(v))
        case Right(_) => Some(rightAction)
      }
    }
    cc.doCheckAndGetNextActionCaller match {
      case Some(c) =>
        c.call match {
          case Left(e) => println(s"Called 2st and Find error: $e"); throw new RuntimeException("应该在errorAction 和 rightAction 中都妥善安置")
          case Right(res) => println(s"Finally Get Result $res")
        }
      case None => println("No ActionCaller"); throw new RuntimeException("在 ConditioNChecker 中均返回了 Some")
    }

    val cd = new ConditionChecker[String] {
      override val from: Either[Exception, String] = Right("42")
      override val ifCondition: Either[Exception, String] => Some[ActionCaller[String]] = {
        case Left(v) => Some(new ErrorMessageCaller(v))
        case Right(_) => Some(rightAction)
      }
    }
    cd.doCheckAndGetNextActionCaller match {
      case Some(c) =>
        c.call match {
          case Left(e) => println(s"Called 2st and Find error: $e"); throw new RuntimeException("应该在errorAction 和 rightAction 中都妥善安置")
          case Right(res) => println(s"Finally Get Result $res")
        }
      case None => println("No ActionCaller"); throw new RuntimeException("在 ConditioNChecker 中均返回了 Some")
    }
  }*/
}
