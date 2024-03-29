package main

import java.time.LocalDate

case class Date(localDate: LocalDate) {
  def getDay(): Int = Date.getDay(this)

  def getMonth(): Int = Date.getMonth(this)

  def getYear(): Int = Date.getYear(this)

  override def equals(obj: Any): Boolean = {
    obj match {
      case date: Date => this.localDate.equals(date.localDate)
      case _ => false
    }
  }

  def minusDays(days: Int): Date = Date.minusDays(this, days)

  def <(date: Date): Boolean = this.localDate.compareTo(date.localDate) < 0

  def >(date: Date): Boolean = this.localDate.compareTo(date.localDate) > 0

  override def toString: String = s"${this.getDay()}/${this.getMonth()}/${this.getYear()}"

  def >=(date: Date): Boolean = this > date || this == date

  def <=(date: Date): Boolean = this < date || this == date

  def -(date: Date): Int = Date.subtractDates(this, date)
  
  override def hashCode(): Int = ((this.localDate.toEpochDay % 197) + this.getDay() * 31).toInt
}

object Date {

  implicit object DefaultOrder extends Ordering[Date] {
    override def compare(x: Date, y: Date): Int = x.localDate.compareTo(y.localDate)
  }

  def minusDays(date: Date, days: Int): Date = Date(date.localDate.minusDays(days.toLong))

  def createDate(day: Int, month: Int, year: Int): Date = Date(LocalDate.of(year, month, day))

  def today(): Date = Date(LocalDate.now())

  def getDay(date: Date): Int = date.localDate.getDayOfMonth

  def getMonth(date: Date): Int = date.localDate.getMonthValue

  def getYear(date: Date): Int = date.localDate.getYear

  def subtractDates(date1: Date, date2: Date): Int = (date2.localDate.toEpochDay-date1.localDate.toEpochDay).toInt
}
