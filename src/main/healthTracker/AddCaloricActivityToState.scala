package main.healthTracker

import consoleinterface._
import main.States.HealthTracker
import HealthCalculations.calculateExerciseCalories
import consoleinterface.caloriescouter.options.AddCaloricActivity._
import CalorieStateOps.addCaloricActivity
import consoleinterface.caloriescouter.options.AddCaloricActivity
import main.healthTracker.caloricstructures.{Body, CaloricMaps, Drink, Food, Sport, Water}

object AddCaloricActivityToState {

  def addCaloricActivityToState(activity: AddCaloricActivity, state: HealthTracker, maps: CaloricMaps): HealthTracker = activity match {
    case AddDrink(_,_,_) => addCaloricActivity(state, activity , drinkDensity(activity, maps), drinkAttributes)
    case AddFood(_,_,_) => addCaloricActivity(state, activity, foodDensity(activity, maps), foodAttributes)
    case AddSport(_,_,_) => addCaloricActivity(state, activity, sportDensity(activity, maps, state.body), sportAttributes)
    case AddWaterCup(date) => addCaloricActivity(state, activity, 0, a => ("Water", 250, Water, date))
  }

  def drinkDensity(activity: AddCaloricActivity, maps: CaloricMaps): Float = {
    val density = maps.drinksMap(activity.asInstanceOf[AddDrink].drink)
    density.toFloat/100
  }

  def foodDensity(activity: AddCaloricActivity, maps: CaloricMaps): Float = {
    val density = maps.foodMap(activity.asInstanceOf[AddFood].food)
    (density.toInt/100).toFloat
  }

  def sportDensity(activity: AddCaloricActivity, maps: CaloricMaps, body: Body): Float = {
    val density = maps.exercisesMap(activity.asInstanceOf[AddSport].sport)
    -calculateExerciseCalories(density, 1, body.weight)
  }

  def foodAttributes(activity: AddCaloricActivity) = {
    val food = activity.asInstanceOf[AddFood]
    (food.food, food.quantity, Food, food.date)
  }

  def drinkAttributes(activity: AddCaloricActivity) = {
    val drink = activity.asInstanceOf[AddDrink]
    (drink.drink, drink.quantity, Drink, drink.date)
  }

  def sportAttributes(activity: AddCaloricActivity) = {
    val sport = activity.asInstanceOf[AddSport]
    (sport.sport, sport.minutes, Sport, sport.date)
  }

}