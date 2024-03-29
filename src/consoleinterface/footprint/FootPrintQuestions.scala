package consoleinterface.footprint

import consoleinterface.StartOptions.FootPrintData
import consoleinterface.footprint.FootPrintConsoleOps.printTryAgain
import javafx.collections.ObservableList
import javafx.scene.control.CheckBox

import scala.annotation.tailrec
import scala.io.StdIn.readLine

object FootPrintQuestions {

  def setFootPrintData(): FootPrintData = {
    lazy val averageElectricity = FootPrintQuestions.averageElectricBill()
    lazy val distanceCar = distance_car()
    val points = distanceCar._1 + distance_publicT() + holidayDest() + averageGasBill() + averageElectricity._1 + sourceOfEnergy() + typeOfEater() + typeOfFood() + magazines() + purchases() + typeOfProperty() + household() + childrenHousehold() + amountOfWaste() + dishwasher() + washingMachine() + recycledWaste()
    FootPrintData(points, averageElectricity._2)
  }

  def distance_car(): (Int, Double, Double) = {
    println("Distance travelled monthly by private car:\n")
    val kms = readLine().toDouble
    val points = if (kms > 2000) 12
    else if (kms > 1250 && kms < 2000) 10
    else if (kms > 125 && kms < 1250) 6
    else if (kms < 125 && kms > 0) 4
    else 0
    println("Type your private car consumption (Liters/100kms):")
    val consumption = readLine().toDouble
    (points, kms, consumption)
  }

  def distanceOfCar(kms: Int): Int = {
    val points = if (kms > 2000) 12
    else if (kms > 1250 && kms < 2000) 10
    else if (kms > 125 && kms < 1250) 6
    else if (kms < 125 && kms > 0) 4
    else 0
    points
  }

  @scala.annotation.tailrec
  def distance_publicT(): Int = {
    println("Distance travelled annually by public transport:\n")
    println("1.More than 32000 km\n2.Between 25000 and 32000 km\n3.Between 15000 and 25000 km\n4.Between 1500 and 15000 km\n5.Less than 1500 km\n6.No km")
    val input = readLine()
    input match {
      case "1" => 12
      case "2" => 10
      case "3" => 6
      case "4" => 4
      case "5" => 2
      case "6" => 0
      case _ =>
        printTryAgain()
        distance_publicT()
    }
  }

  def distanceOfPublicT(options: ObservableList[String], option: String): Int = options.indexOf(option) match {
    case 0 => 12
    case 1 => 10
    case i => 10 - i * 2
  }

  @scala.annotation.tailrec
  def holidayDest(): Int = {
    println("Holiday Destination:\n")
    println("1.Close to home(Country)\n2.Short distance away(Continent)\n3.Long flight away(Rest of the world)")
    val input = readLine()
    input match {
      case "1" => 2
      case "2" => 6
      case "3" => 20
      case _ =>
        printTryAgain()
        holidayDest()
    }
  }

  def holidayDestPoints(options: ObservableList[String], option: String): Int = options.indexOf(option) match {
    case 0 => 2
    case 1 => 6
    case 2 => 20
  }

  @scala.annotation.tailrec
  def averageGasBill(): Int = {
    println("What is your average quarterly gas or oil bill?\n")
    println("1.More than 280 Euros\n2.Between 170 and 280 euros\n3.Between 50 and 170 euros\n4.Less than 50 euros")
    val input = readLine()
    input match {
      case "1" => 8
      case "2" => 5
      case "3" => 3
      case "4" => 1
      case _ =>
        printTryAgain()
        averageGasBill()
    }
  }

  def averageGasBillPoints(options: ObservableList[String], option: String): Int =
    options.indexOf(option) match {
      case 0 => 8
      case 1 => 5
      case 2 => 3
      case 3 => 1
    }


  def averageElectricBill(): (Int, Double) = {
    println("How much KwH do you spend on average per month on electricity?\n")
    val input = readLine().toDouble
    val euros = input * 0.15
    val points = if (euros > 70) 10
    else if (euros > 42 && euros < 70) 7
    else if (euros > 12 && euros < 42) 5
    else 1
    (points, input)
  }

  def averageElectricBillPoints(option: Double): Int = {
    val euros = option * 0.15
    val points = if (euros > 70) 10
    else if (euros > 42 && euros < 70) 7
    else if (euros > 12 && euros < 42) 5
    else 1
    points
  }

  @scala.annotation.tailrec
  def sourceOfEnergy(): Int = {
    println("From what source does your energy supply come from?\n")
    println("1.Renewable / Green Tariff\n2.Non renewable")
    val input = readLine()
    input match {
      case "1" => 2
      case "2" => 15
      case _ =>
        printTryAgain()
        sourceOfEnergy()
    }
  }

  def sourceOfEnergyPoints(options: ObservableList[String], option: String): Int = options.indexOf(option) match {
    case 0 => 2
    case 1 => 15
  }

  @scala.annotation.tailrec
  def typeOfEater(): Int = {
    println("Are you:\n")
    println("1.Vegan\n2.Vegetarian\n3.Regular meat eater\n4.Heavy Meat eater")
    val input = readLine()
    input match {
      case "1" => 2
      case "2" => 4
      case "3" => 8
      case "4" => 10
      case _ =>
        printTryAgain()
        typeOfEater()
    }
  }

  def typeOfEaterPoints(options: ObservableList[String], option: String): Int = options.indexOf(option) match {
    case 0 => 2
    case 1 => 4
    case 2 => 8
    case 3 => 10
  }

  @scala.annotation.tailrec
  def typeOfFood(): Int = {
    println("The main type of food consumed is:\n")
    println("1.Mostly fresh, locally grown\n2.Mix of fresh and convenience\n3.Mostly convenience")
    val input = readLine()
    input match {
      case "1" => 2
      case "2" => 6
      case "3" => 12
      case _ =>
        printTryAgain()
        typeOfFood()
    }
  }

  def typeOfFoodPoints(options: ObservableList[String], option: String): Int = options.indexOf(option) match {
    case 0 => 2
    case i => i * 6
  }

  @scala.annotation.tailrec
  def magazines(): Int = {
    println("How many magazines or newspapers do you buy or get delivered each week?\n")
    println("1.More than 20\n2.Between 10 and 20\n3.Between 1 and 10\n4.None")
    val input = readLine()
    input match {
      case "1" => 8
      case "2" => 6
      case "3" => 4
      case "4" => 0
      case _ =>
        printTryAgain()
        magazines()
    }
  }

  def magazinesPoints(options: ObservableList[String], option: String): Int = options.indexOf(option) match {
    case 3 => 0
    case i => 8 - i * 2
  }

  @scala.annotation.tailrec
  def purchases(): Int = {
    println("How much furniture and other commodities such as machines, gadgets do you purchase each year?\n")
    println("1.More than 7\n2.Between 5 and 7\n3.Between 3 and 5\n4.Less than 3\n5.Hardly any, or second hand")
    val input = readLine()
    input match {
      case "1" => 10
      case "2" => 8
      case "3" => 6
      case "4" => 4
      case "5" => 2
      case _ =>
        printTryAgain()
        purchases()
    }
  }

  def purchasesPoints(options: ObservableList[String], option: String): Int = {
    val index = options.indexOf(option)
    10 - index * 2
  }

  @scala.annotation.tailrec
  def typeOfProperty(): Int = {
    println("What type of property do you live in?\n")
    println("1.Large sized house\n2.Medium size house\n3.Small size house\n4.Flat / apartment")
    val input = readLine()
    input match {
      case "1" => 10
      case "2" => 7
      case "3" => 4
      case "4" => 2
      case _ =>
        printTryAgain()
        typeOfProperty()
    }
  }

  def typeOfPropertyPoints(options: ObservableList[String], option: String): Int = options.indexOf(option) match {
    case 0 => 10
    case 1 => 7
    case 2 => 4
    case 3 => 2
  }

  @scala.annotation.tailrec
  def household(): Int = {
    println("How many other people live in your household?\n")
    println("1.No other person\n2.One other person\n3.Two other person\n4.Three other person\n5.Four other person\n6.Five other person\n7.More than five people")
    val input = readLine()
    input match {
      case "1" => 14
      case "2" => 12
      case "3" => 10
      case "4" => 8
      case "5" => 6
      case "6" => 4
      case "7" => 2
      case _ =>
        printTryAgain()
        household()
    }
  }

  def householdPoints(options: ObservableList[String], option: String): Int = {
    val index = options.indexOf(option)
    14 - index * 2
  }

  @scala.annotation.tailrec
  def childrenHousehold(): Int = {
    println("How many children do you have in this household?\n")
    println("1.No children\n2.One child\n3.Two children\n4.Three children\n5.Four children\n6.More than four children")
    val input = readLine()
    input match {
      case "1" => 0
      case "2" => 3
      case "3" => 6
      case "4" => 8
      case "5" => 10
      case "6" => 12
      case _ =>
        printTryAgain()
        childrenHousehold()
    }
  }

  def childrenHouseholdPoints(options: ObservableList[String], option: String): Int = options.indexOf(option) match {
    case i if (0 to 2).contains(i) => i * 3
    case i => i * 2 + 2
  }

  @scala.annotation.tailrec
  def amountOfWaste(): Int = {
    println("Amount of domestic waste produced each week(a full large bin is approx 30 kg)\n")
    println("1.More than 120kg\n2.Between 90 and 120kg\n3.Between 60 and 90kg\n4.Between 30 and 60kg\n5.Between 15 and 30kg\n6.Less than 15kg")
    val input = readLine()
    input match {
      case "1" => 50
      case "2" => 40
      case "3" => 30
      case "4" => 20
      case "5" => 10
      case "6" => 5
      case _ =>
        printTryAgain()
        amountOfWaste()
    }
  }

  def amountOfWastePoints(options: ObservableList[String], option: String): Int = options.indexOf(option) match {
      case i if i < 5 => 50 - i * 10
      case 5 => 5
    }

  @scala.annotation.tailrec
  def dishwasher(): Int = {
    println("If you have a dishwasher, how many times do you run it on an average week?\n")
    println("1.More than 9 times\n2.Between 4 and 9 times\n3.Between 1 and 4 times\n4.Not applicable")
    val input = readLine()
    input match {
      case "1" => 3
      case "2" => 2
      case "3" => 1
      case "4" => 0
      case _ =>
        printTryAgain()
        dishwasher()
    }
  }

  def dishwasherPoints(options: ObservableList[String], option: String): Int = 3 - options.indexOf(option)

  @scala.annotation.tailrec
  def washingMachine(): Int = {
    println("If you have a washing machine, how many times do you run it on an average week?\n")
    println("1.More than 9 times\n2.Between 4 and 9 times\n3.Between 1 and 4 times\n4.Not applicable")
    val input = readLine()
    input match {
      case "1" => 3
      case "2" => 2
      case "3" => 1
      case "4" => 0
      case _ =>
        printTryAgain()
        washingMachine()
    }
  }

  def washingMachinePoints(options: ObservableList[String], option: String): Int = 3 - options.indexOf(option)

  def recycledWaste(): Int = {
    println("Do dispose of waste, you're going to use up valuable land. So, start this section with 24 points. Do you recycle the following items?\n")
    println("Glass (Y or N)")
    val glass = readLine()
    val glassPoints = dealPoints(glass)

    println("Plastic (Y or N)")
    val plastic = readLine()
    val plasticPoints = dealPoints(plastic)

    println("Paper (Y or N)")
    val paper = readLine()
    val paperPoints = dealPoints(paper)

    println("Aluminium (Y or N)")
    val aluminium = readLine()
    val aluminiumPoints = dealPoints(aluminium)

    println("Steel cans (Y or N)")
    val steel = readLine()
    val steelPoints = dealPoints(steel)

    println("Food waste (Y or N)")
    val food = readLine()
    val foodPoints = dealPoints(food)

    24 + glassPoints + plasticPoints + paperPoints + aluminiumPoints + steelPoints + foodPoints
  }

  def recycledWastePoints(list: List[CheckBox]): Int = {
    list.count(i => i.isSelected) * - 4 + 24
  }

  @tailrec
  def dealPoints(input: String): Int = input.toLowerCase() match {
    case "y" => -4
    case "n" => 0
    case _ =>
      FootPrintConsoleOps.printTryAgain()
      dealPoints(readLine())
  }
}
