package com.mazhangjing.server.old

/**
  * 定义一个动作，调用 Call 方法返回结果
  */
trait ActionCaller[B] {
  def call: Either[Exception, B]
}

/**
  * 实现一个判断 ActionCaller Call 产生的结果并且返回另一个 ActionCaller 的检查器
  */
trait Checker[A,B] {
  val ifCondition: Either[Exception, A] => Option[ActionCaller[B]]
}

/**
  * 外部结果检查器
  */
trait ConditionChecker[A,B] extends Checker[A,B] {
  val from: Either[Exception, A]
  def doCheckAndGetNextActionCaller: Option[ActionCaller[B]] = ifCondition(from)
}

/**
  * 内部结果检查器
  */
trait ActionChecker[A,B] extends ActionCaller[A] with Checker[A,B] {
  def doCheckAndGetNextActionCaller: Option[ActionCaller[B]] = ifCondition(call)
}

object Utils {
  def Try[T](op: => Either[Exception,T]):Either[Exception,T] = try { op } catch { case e: Exception => Left(e)}
}
