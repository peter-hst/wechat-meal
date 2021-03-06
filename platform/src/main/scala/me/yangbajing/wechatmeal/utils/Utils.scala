package me.yangbajing.wechatmeal.utils

import java.security.SecureRandom
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.concurrent.TimeUnit

import scala.concurrent.duration.{Duration, FiniteDuration}

/**
 * 工具
 * Created by Yang Jing (yangbajing@gmail.com) on 2015-08-15.
 */
object Utils {
  private final val LOW = 33
  private final val HIGH = 127
  private final val RANDOM_STRING = (0 to 9).mkString + ('a' to 'z').mkString + ('A' to 'Z').mkString + ",.?!~`@#$%^&*()_-+=|[]{};:<>/"
  private final val RANDOM_STRING_LENGTH = RANDOM_STRING.length

  val formatDate = DateTimeFormatter.ofPattern("YYYY-MM-dd")
  val formatTime = DateTimeFormatter.ofPattern("HH:mm:ss")
  val random = new SecureRandom()

  def randomString(size: Int): String = {
    assert(size > 0, s"size: $size must be > 0")
    (0 until size).map(_ => RANDOM_STRING.charAt(random.nextInt(RANDOM_STRING_LENGTH))).mkString
  }

  def getFileExtension(filename: String): String = {
    val idx = filename.lastIndexOf('.')
    if (idx == -1) "" else filename.substring(idx + 1)
  }

  @inline
  def nextPrintableChar(): Char = (random.nextInt(HIGH - LOW) + LOW).toChar

  @inline
  def randomNextInt(begin: Int, bound: Int) = random.nextInt(bound - begin) + begin

  def durationFormNow(endAt: LocalDateTime, seconds: Long = 0L, now: LocalDateTime = LocalDateTime.now()): FiniteDuration = {
    require(now.isBefore(endAt))
    Duration(java.time.Duration.between(now, endAt).getSeconds + seconds, TimeUnit.SECONDS)
  }

  def currentTimeSeconds() = System.currentTimeMillis() / 1000
}
