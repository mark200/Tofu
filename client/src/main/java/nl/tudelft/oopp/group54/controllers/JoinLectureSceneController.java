package nl.tudelft.oopp.group54.controllers;

import nl.tudelft.oopp.group54.Datastore;
import nl.tudelft.oopp.group54.communication.ServerCommunication;
import nl.tudelft.oopp.group54.models.responseentities.CreateLectureResponse;
import nl.tudelft.oopp.group54.models.responseentities.JoinLectureResponse;
import nl.tudelft.oopp.group54.util.TextFormatterFactory;
import nl.tudelft.oopp.group54.util.TextFormatterType;
import nl.tudelft.oopp.group54.views.ApplicationScene;
import nl.tudelft.oopp.group54.views.MainView;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class JoinLectureSceneController extends AbstractApplicationController {

  @FXML
  Button enterLectureButton;

  @FXML
  TextField joinIdTextField;

  @FXML
  TextField enterNameTextField;

  Datastore ds = Datastore.getInstance();


  @Override
  public void performControllerSpecificSetup() {
    System.out.println("Join controller config");
    joinIdTextField.setTextFormatter(
            TextFormatterFactory.createFormatter(TextFormatterType.JOIN_ID)
    );
    enterNameTextField.setTextFormatter(
            TextFormatterFactory.createFormatter(TextFormatterType.NAME)
    );
  }

  public void enterLectureButtonClicked() {

    CharSequence joinIdTextFieldText = joinIdTextField.getCharacters();
    CharSequence enterNameTextFieldText = enterNameTextField.getCharacters();

    boolean joinIdTextFieldMissing = joinIdTextFieldText.length() <= 0;
    boolean enterNameTextFieldMissing = enterNameTextFieldText.length() <= 0;

    if (joinIdTextFieldMissing || enterNameTextFieldMissing) {

      if(joinIdTextFieldMissing) {
        this.shakeWidget(this.joinIdTextField, 2.0);
      }

      if(enterNameTextFieldMissing) {
        this.shakeWidget(this.enterNameTextField, -2.0);
      }

      this.displayStatusMessage("Please fill in the missing necessary information.");

      return;
    }
    
    String joinIdTextFieldString = joinIdTextFieldText.toString();

    String[] textFieldParts = joinIdTextFieldString.split("/");

    Integer lectureId = 0;
    String joinId = "";

    for(int i = 0; i < textFieldParts.length; i++) {
      if(textFieldParts[i].matches("-?\\d+")) {
        lectureId = Integer.parseInt(textFieldParts[i]);
      }
      if(textFieldParts[i].matches(".{2,50}")) {
        joinId = textFieldParts[i];
      }
    }

    JoinLectureResponse response = null;
    try {
      response = ServerCommunication.joinLecture(enterNameTextFieldText.toString(), lectureId, joinId);
    } catch (Exception e) {
      e.printStackTrace();
      this.displayStatusMessage(e.getMessage());
    }

    if(response.getSuccess()) {

      System.out.println(response);

      this.ds.setJoinLectureResponse(response);
      this.ds.setUserId(response.getUserID());
      this.ds.setLectureId(lectureId);
      MainView.changeScene(ApplicationScene.LECTUREROOM, true);
    } else {
      this.displayStatusMessage(response.getMessage());
    }
  }
}