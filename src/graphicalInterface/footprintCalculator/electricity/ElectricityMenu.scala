package graphicalInterface.footprintCalculator.electricity

import graphicalInterface.FxApp.loadPage
import graphicalInterface.HomePage
import javafx.fxml.FXML
import javafx.scene.control.Label
import javafx.scene.layout.Pane

class ElectricityMenu {
  @FXML
  var pane: Pane = _
  @FXML
  var electricityLabel: Label = _

  var homePage: HomePage = _

  def setHomePage(homePage: HomePage): Unit = {
    this.homePage = homePage
    electricityLabel.setText(s"${homePage.getFootPrint.electricity.monthlyConsumption} kWh")
  }

  def setElectricitySourcesDisplay(): Unit ={
    loadPage[SetElectricitySources](pane).setHomePage(homePage)
  }

  def getTotalEmissionsFromElectricityDisplay(): Unit ={
    loadPage[SeeElectricitySources](pane).initialize(homePage)
  }

  def getSolarPanelRecommendationDisplay(): Unit ={
    loadPage[SolarPanels](pane).setHomePage(homePage)
  }

  def changeElectricityConsumptionDisplay(): Unit ={
    loadPage[ChangeConsumption](pane).setHomePage(homePage)
  }
}
