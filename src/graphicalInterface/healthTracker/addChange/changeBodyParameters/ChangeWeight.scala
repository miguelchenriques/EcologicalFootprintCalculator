package graphicalInterface.healthTracker.addChange.changeBodyParameters

import consoleinterface.healthtracker.options.BodyChange
import graphicalInterface.HomePage
import javafx.fxml.FXML
import javafx.scene.control.TextField
import main.Date
import main.healthTracker.{Body, CaloricMaps}

class ChangeWeight {
  private var home: HomePage = _
  private var caloricMaps: CaloricMaps = _

  def initialize(home: HomePage, caloricMaps: CaloricMaps): Unit = {
    this.home = home
    this.caloricMaps = caloricMaps
  }

  @FXML
  var weightInput : TextField = _

  def changeWeight() ={
    val healthTracker = home.getHealthTracker
    val newHealthTracker = Body.changeBody(BodyChange.ChangeWeight(weightInput.getText.toInt, Date.today),healthTracker)
    home.updateHealthTracker(newHealthTracker)
  }

}