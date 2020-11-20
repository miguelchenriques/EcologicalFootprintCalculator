package consoleinterface.footprint

import main.footprint.footprintstructs.FootPrintData

import scala.io.StdIn.readLine

object FootPrintQuestions {

  def setFootPrintData(): FootPrintData = {
    lazy val averageElectricity = FootPrintQuestions.averageElectricBill()
    lazy val distanceCar = distance_car()
    val points = distanceCar._1 + distance_publicT() + holidayDest() + averageGasBill() + averageElectricity._1 + sourceOfEnergy() + typeOfEater() + typeOfFood() + magazines() + purchases() + typeOfProperty() + household() + childrenHousehold() + amountOfWaste() + dishwasher() + washingMachine() + recycledWaste()
    FootPrintData(points, averageElectricity._2, distanceCar._2, distanceCar._3)
  }

  def distance_car(): (Int, Double, Double) = {
    println("Distance travelled monthly by private car:\n")
    val kms = readLine().toDouble
    var points = 0
    if (kms > 2000) points = 12
    if (kms > 1250 && kms < 2000) points = 10
    if (kms > 125 && kms < 1250) points = 6
    if (kms < 125) points = 4
    if (kms == 0) points = 0
    println("Type your private car consumption (Liters/100kms):")
    val consumption = readLine().toDouble
    (points, kms, consumption)
  }

  def distance_publicT(): Int = {
    println("Distance travelled anually by public transport:\n")
    println("1.More than 32000 km\n2.Between 25000 and 32000 km\n3.Between 15000 and 25000 km\n4.Between 1500 and 15000 km\n5.Less than 1500 km\n6.No km")
    val input = readLine().toInt
    input match {
      case 1 => 12
      case 2 => 10
      case 3 => 6
      case 4 => 4
      case 5 => 2
      case 6 => 0
    }
  }

  def holidayDest(): Int = {
    println("Holiday Destination:\n")
    println("1.Close to home(Country)\n2.Short distance away(Continent)\n3.Long flight away(Rest of the world)")
    val input = readLine().toInt
    input match {
      case 1 => 2
      case 2 => 6
      case 3 => 20
    }
  }

  def averageGasBill(): Int = {
    println("What is your average quarterly gas or oil bill?\n")
    println("1.More than 280 Euros\n2.Between 170 and 280 euros\n3.Between 50 and 170 euros\n4.Less than 50 euros")
    val input = readLine().toInt
    input match {
      case 1 => 8
      case 2 => 5
      case 3 => 3
      case 4 => 1
    }
  }

  def averageElectricBill(): (Int, Double) = {
    println("How much KwH do you spend on average per month on electricity?\n")
    val input = readLine().toDouble
    val euros = input * 0.15
    var points = 0
    if (euros > 70) points = 10
    if (euros > 42 && euros < 70) points = 7
    if (euros > 12 && euros < 42) points = 5
    if (euros < 12) points = 1
    (points, input)
  }

  def sourceOfEnergy(): Int = {
    println("From what source does your energy supply come from?\n")
    println("1.Renewable / Green Tariff\n2.Non renewable")
    val input = readLine().toInt
    input match {
      case 1 => 2
      case 2 => 15
    }
  }

  def typeOfEater(): Int = {
    println("Are you:\n")
    println("1.Vegan\n2.Vegetarian\n3.Regular meat eater\n4.Heavy Meat eater")
    val input = readLine().toInt
    input match {
      case 1 => 2
      case 2 => 4
      case 3 => 8
      case 4 => 10
    }
  }

  def typeOfFood(): Int = {
    println("The main type of food consumed is:\n")
    println("1.Mostly fresh, locally grown\n2.Mix of fresh and convenience\n3.Mostly convenience")
    val input = readLine().toInt
    input match {
      case 1 => 2
      case 2 => 6
      case 3 => 12
    }
  }

  def magazines(): Int = {
    println("How many magazines or newspapers do you buy or get delivered each week?\n")
    println("1.More than 20\n2.Between 10 and 20\n3.Between 1 and 10\n4.None")
    val input = readLine().toInt
    input match {
      case 1 => 8
      case 2 => 6
      case 3 => 4
      case 4 => 0
    }
  }

  def purchases(): Int = {
    println("How much furniture and other commodities such as machines, gadgets do you purchase each year?\n")
    println("1.More than 7\n2.Between 5 and 7\n3.Between 3 and 5\n4.Less than 3\n5.Hardly any, or second hand")
    val input = readLine().toInt
    input match {
      case 1 => 10
      case 2 => 8
      case 3 => 6
      case 4 => 4
      case 5 => 2
    }
  }

  def typeOfProperty(): Int = {
    println("What type of property do you live in?\n")
    println("1.Large sized house\n2.Medium size house\n3.Small size house\n4.Flat / apartment")
    val input = readLine().toInt
    input match {
      case 1 => 10
      case 2 => 7
      case 3 => 4
      case 4 => 2
    }
  }

  def household(): Int = {
    println("How many other people live in your household?\n")
    println("1.No other person\n2.One other person\n3.Two other person\n4.Three other person\n5.Four other person\n6.Five other person\n7.More than five people")
    val input = readLine().toInt
    input match {
      case 1 => 14
      case 2 => 12
      case 3 => 10
      case 4 => 8
      case 5 => 6
      case 6 => 4
      case 7 => 2
    }
  }

  def childrenHousehold(): Int = {
    println("How many children do you have in this household?\n")
    println("1.No children\n2.One child\n3.Two children\n4.Three children\n5.Four children\n6.More than four children")
    val input = readLine().toInt
    input match {
      case 1 => 0
      case 2 => 3
      case 3 => 6
      case 4 => 8
      case 5 => 10
      case 6 => 12
    }
  }

  def amountOfWaste(): Int = {
    println("Amount of domestic waste produced each week(a full large bin is aprox 30 kg)\n")
    println("1.More than 120kg\n2.Between 90 and 120kg\n3.Between 60 and 90kg\n4.Between 30 and 60kg\n5.Between 15 and 30kg\n6.Less than 15kg")
    val input = readLine().toInt
    input match {
      case 1 => 50
      case 2 => 40
      case 3 => 30
      case 4 => 20
      case 5 => 10
      case 6 => 5
    }
  }

  def dishwasher(): Int = {
    println("If you have a dishwasher, how many times do you run it on an average week?\n")
    println("1.More than 9 times\n2.Between 4 and 9 times\n3.Between 1 and 4 times\n4.Not applicable")
    val input = readLine().toInt
    input match {
      case 1 => 3
      case 2 => 2
      case 3 => 1
      case 4 => 0
    }
  }

  def washingMachine(): Int = {
    println("If you have a washing machine, how many times do you run it on an average week?\n")
    println("1.More than 9 times\n2.Between 4 and 9 times\n3.Between 1 and 4 times\n4.Not applicable")
    val input = readLine().toInt
    input match {
      case 1 => 3
      case 2 => 2
      case 3 => 1
      case 4 => 0
    }
  }

  def recycledWaste(): Int = {
    println("Do dispose of waste, you're going to use up valuable land. So, start this section with 24 points. Do you recycle the following items?\n")
    val points = 24
    println("Glass (Y or N)")
    val glass = readLine()
    glass match {
      case "Y" => points - 4
      case "N" => points
    }
    println("Plastic (Y or N)")
    val plastic = readLine()
    plastic match {
      case "Y" => points - 4
      case "N" => points
    }
    println("Paper (Y or N)")
    val paper = readLine()
    paper match {
      case "Y" => points - 4
      case "N" => points
    }
    println("Aluminium (Y or N)")
    val aluminium = readLine()
    aluminium match {
      case "Y" => points - 4
      case "N" => points
    }
    println("Steel cans (Y or N)")
    val steel_cans = readLine()
    steel_cans match {
      case "Y" => points - 4
      case "N" => points
    }
    println("Food waste (Y or N)")
    val food_waste = readLine()
    food_waste match {
      case "Y" => points - 4
      case "N" => points
    }
    points
  }
}
