package graphicalInterface.startingScenes.createProfile

import javafx.fxml.{FXML, FXMLLoader}
import javafx.scene.{Parent, Scene}
import javafx.scene.control.{ScrollPane, TextField}
import javafx.scene.input.{KeyCode, KeyEvent}
import javafx.stage.Stage

class NewProfile {
  @FXML
  var username: TextField = _

  def keyPressed(e: KeyEvent): Unit = if (e.getCode == KeyCode.ENTER) createProfile()

  //TODO - exception for when user tries to create profile with name used before

  def createProfile(): Unit = {
    val loader = new FXMLLoader(getClass.getResource("Questionnaire.fxml"))
    val questioner: ScrollPane = loader.load()
    loader.getController[Questionnaire].setUsername(username.getText)
    val stage = username.getScene.getWindow.asInstanceOf[Stage]
    stage.setScene(new Scene(questioner))
    stage.setMaximized(true)
  }

  def goBack(): Unit = {
    val loader = new FXMLLoader(getClass.getResource("../ProfileMenu.fxml"))
    val root: Parent = loader.load()
    username.getScene.setRoot(root)
  }
}