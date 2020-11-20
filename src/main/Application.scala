package main

import scala.annotation.tailrec
import fileOperations.FileOperations
import consoleinterface.ConsoleOps.{getUserChoice, printOptions}
import consoleinterface._
import calorieCounter.{AddCaloricActivityToState, CaloricInformationOps, CalorieStateOps, ChangeBody, ImpureFunctions}
import calorieCounter.caloricstructures.CaloricMaps
import consoleinterface.caloriescouter.options.{AddCaloricActivity, BodyChange, CaloricInformation}
import consoleinterface.footprint.FootPrintQuestions
import main.footprint.TransportMeans._
import main.footprint._
import main.footprint.FootPrintOps
import main.footprint.footprintstructs.energy.{EnergyImpure, EnergySource}
import main.footprint.footprintstructs.transport.TransportationImpure
import main.footprint.footprintstructs.waste.WasteImpure
import footprintstructs.waste.TypeOfWaste
import main.fileOperations.FileOperations._
import main.footprint.footprintstructs.{FootPrintData, FootPrintDataImpure, WaterImpure}


object Application extends App {

  val foodMap = loadCaloriesMap("Food.txt", s => s.toInt)
  val drinksMap = loadCaloriesMap("Drinks.txt", s => s.toInt)
  val exercisesMap = loadCaloriesMap("Exercises.txt", s => s.toDouble)

  val caloricMaps = CaloricMaps(foodMap, drinksMap, exercisesMap)

  @tailrec
  def main_loop(states: States): Unit = {
    printOptions()
    val userChoice = getUserChoice(caloricMaps)

    userChoice match {
      case Quit =>

      // Saves the current states to a file
      case SaveStates =>
        saveStates(states)
        main_loop(states)

      // Adds a caloric activity (Food, Drink or Sport) to the calorie counter
      case activity: AddCaloricActivity =>
        val newCalorieCounter = AddCaloricActivityToState.addCaloricActivityToState(activity, states.calorieCounter, caloricMaps)
        main_loop(states.copy(calorieCounter = newCalorieCounter))

      // Sets the Weight goal
      case SetGoal(goal,date) => {
        val newCalorieCounter = states.calorieCounter.copy(goal = (goal,date))
        main_loop(states.copy(calorieCounter = newCalorieCounter))
      }

      // Handles all types of caloric Information requests
      case information: CaloricInformation =>
        CaloricInformationOps.getCaloricInformation(information, states.calorieCounter)
        main_loop(states)

      // Prints the body params
      case GetBody =>
        ImpureFunctions.printBodyInformation(states.calorieCounter.body)
        main_loop(states)

        //Handles all types of body change
      case bodyParam: BodyChange =>
        val newCalorieCounter = ChangeBody.changeBody(bodyParam, states.calorieCounter)
        main_loop(states.copy(calorieCounter = newCalorieCounter))

      case AddTransportTrip(mean: TransportMean, km: Double, date: Date) => mean match {
        case Car(consumption, fuel) =>
          val newFootPrint = FootPrintOps.addCarConsumption(states.footPrintState, consumption, km, fuel, date)
          main_loop(states.copy(footPrintState = newFootPrint))
        case publicTransport =>
          val newFootPrint = FootPrintOps.addPublicTransportEmissions(states.footPrintState, publicTransport, km,date)
          main_loop(states.copy(footPrintState = newFootPrint))
      }

      case GetTransportEmissions =>
        TransportationImpure.printTransportEmissions(states.footPrintState)
        main_loop(states)

      case GetTransportHistory =>
        TransportationImpure.history(states.footPrintState.transportTrips)
        main_loop(states)

      case AddWaste(kg: Int, typeOfWaste: TypeOfWaste) =>
        val newFootPrint = FootPrintOps.addWaste(states.footPrintState, kg, typeOfWaste)
        main_loop(states.copy(footPrintState = newFootPrint))

      case GetWasteEmissions =>
        WasteImpure.printWasteEmissions(states.footPrintState)
        main_loop(states)

      case SetEnergySource(source: EnergySource) =>
        val newFootPrintState = FootPrintOps.setEnergySource(states.footPrintState, source)
        main_loop(states.copy(footPrintState = newFootPrintState))

      case GetEnergyEmissions =>
        EnergyImpure.getEnergyEmissions(states.footPrintState)
        main_loop(states)

      case SetWaterConsumption(amount: Double) =>
        val newFootPrintState = FootPrintOps.setWaterConsumption(states.footPrintState, amount)
        main_loop(states.copy(footPrintState = newFootPrintState))

      case GetWaterEmissions =>
        WaterImpure.printWaterImpure(states.footPrintState)
        main_loop(states)

      case GetEcologicalFootPrint => {
        FootPrintDataImpure.printResults(states.footPrintState)
        main_loop(states)
      }
    }
  }

  def start() = {
    //Gets the choice load or create new profile
    val choice = ConsoleOps.FirstPrompt()

    //Gets the states
    val states = choice match {
      case StartOptions.LoadState => {
        val username = FileOperations.getUsername()
        loadStates(username) match {
          //If there is no state saved loadStates returns None so it asks for a new Profile
          case None => {
            printLoadError
            val profileData = ConsoleOps.newProfile()
            CalorieStateOps.createStates(profileData)
          }
          case Some(states) => states
        }
      }
      case profileData: StartOptions.NewProfile => CalorieStateOps.createStates(profileData)
    }

    main_loop(states)
  }
  start()
}
