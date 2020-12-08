package graphicalInterface.footprintCalculator

import javafx.fxml.{FXML, FXMLLoader}
import javafx.scene.control.Label
import javafx.scene.layout.Pane

class FootprintCalculator {
  @FXML
  private var footprintDisplay: Pane = _

  @FXML
  private var footprintLabel: Label = _

  def transportationMenu() = {
    val loader = new FXMLLoader(getClass.getResource("transportation/Transportation.fxml"))
    footprintDisplay.getChildren.clear()
    footprintDisplay.getChildren.add(loader.load())
  }

  def wasteMenu() = {
    val loader = new FXMLLoader(getClass.getResource("waste/Waste.fxml"))
    footprintDisplay.getChildren.clear()
    footprintDisplay.getChildren.add(loader.load())
  }

  def electricityMenu()={
    val loader = new FXMLLoader(getClass.getResource("electricity/Electricity.fxml"))
    footprintDisplay.getChildren.clear()
    footprintDisplay.getChildren.add(loader.load())
  }


  def displayEarthsNeeded() ={
    footprintDisplay.getChildren.clear()
    footprintLabel.setText("displayEarthsNeeded")
  }

  def displayTotalCO2Emissions()={
    footprintDisplay.getChildren.clear()
    footprintLabel.setText("displayTotalCO2Emissions")
  }


}
