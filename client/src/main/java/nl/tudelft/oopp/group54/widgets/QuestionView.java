package nl.tudelft.oopp.group54.widgets;

import java.io.IOException;

import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import nl.tudelft.oopp.group54.communication.ServerCommunication;
import nl.tudelft.oopp.group54.controllers.LectureRoomSceneController;
import nl.tudelft.oopp.group54.models.responseentities.BanIpResponse;
import nl.tudelft.oopp.group54.models.responseentities.DeleteQuestionResponse;
import nl.tudelft.oopp.group54.models.responseentities.EditQuestionResponse;
import nl.tudelft.oopp.group54.models.responseentities.PostAnswerResponse;

import nl.tudelft.oopp.group54.models.responseentities.VoteResponse;

public abstract class QuestionView extends AnchorPane {

    private GridPane outerGridPane;
    private GridPane voteGridPane;
    private GridPane verticalGridPane;
    protected GridPane horizontalGridPane;
    private MenuBar menuBar;

    private TextArea questionTextArea;
    protected TextArea answerTextArea;
    private Label userName;

    protected MenuButton dropDown;

    private MenuItem delete;
    private MenuItem markAnswer;
    private MenuItem answerText;
    private MenuItem ban;
    private MenuItem edit;

    private Button upvoteButton;

    private Label currentScore;

    private String text;
    private String questionId;
    private String userNameString;
    private Integer voteCount;
    private String userIp;
    private String userId;

    protected LectureRoomSceneController owner;

    /**
     * Instantiates a new Question view.
     *
     * @param text       the text
     * @param questionId the question id
     * @param userName   the user name
     * @param userIp     the user ip
     * @param voteCount  the vote count
     */
    public QuestionView(String text, String questionId, String userName, String userIp, Integer voteCount) {
        this.text = text;
        this.userNameString = userName;
        this.questionId = questionId;
        this.voteCount = voteCount;
        this.userIp = userIp;

        this.menuBar = new MenuBar();

        addOuterGridPane();

        addVoteGridPane();

        addVerticalGridPane();

        addHorizontalGridPane();
        
        createAnswerTextArea();

        this.getChildren().addAll(this.outerGridPane, this.menuBar);

        this.owner = null;

        // this.getStylesheets().add("stylesheets/defaultTheme.css");

    }


    private void addOuterGridPane() {
        this.outerGridPane = new GridPane();

        setTopAnchor(this.outerGridPane, 0.0);
        setRightAnchor(this.outerGridPane, 0.0);
        setLeftAnchor(this.outerGridPane, 0.0);
        setBottomAnchor(this.outerGridPane, 0.0);

        ColumnConstraints col1 = new ColumnConstraints();
        col1.setMinWidth(30);

        ColumnConstraints col2 = new ColumnConstraints();
        col2.setFillWidth(true);
        col2.setHgrow(Priority.ALWAYS);

        this.outerGridPane.getColumnConstraints().addAll(col1, col2);
        
        RowConstraints row1 = new RowConstraints();
        RowConstraints row2 = new RowConstraints();
        row2.setPrefHeight(0);
        
        this.outerGridPane.getRowConstraints().addAll(row1, row2);

    }


    private void addVoteGridPane() {
        this.voteGridPane = new GridPane();

        this.upvoteButton = new Button("^");
        this.currentScore = new Label(this.voteCount.toString());

        this.voteGridPane.add(this.upvoteButton, 0, 0);
        this.voteGridPane.add(this.currentScore, 0, 1);

        this.outerGridPane.add(this.voteGridPane, 0, 0);
    }

    private void addVerticalGridPane() {
        this.verticalGridPane = new GridPane();

        this.questionTextArea = new TextArea(text);

        this.questionTextArea.setWrapText(true);
        this.questionTextArea.setEditable(false);
        this.questionTextArea.setPrefRowCount(3);

        GridPane.setHgrow(this.questionTextArea, Priority.ALWAYS);

        this.questionTextArea.setPrefSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);

        this.verticalGridPane.add(this.questionTextArea, 0, 1);

        RowConstraints row1 = new RowConstraints();
        row1.setMinHeight(27.5);
        this.verticalGridPane.getRowConstraints().add(row1);

        this.outerGridPane.add(this.verticalGridPane, 1, 0);
    }

    private void addHorizontalGridPane() {
        this.horizontalGridPane = new GridPane();

        this.userName = new Label(userNameString);


        this.horizontalGridPane.add(this.userName, 0, 0);

        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(50);

        ColumnConstraints column2 = new ColumnConstraints();
        column2.setHalignment(HPos.RIGHT);
        column2.setFillWidth(true);
        column2.setPercentWidth(50);

        this.horizontalGridPane.getColumnConstraints().addAll(column1, column2);

        this.verticalGridPane.add(this.horizontalGridPane, 0, 0);

    }

    protected void addDropDown() {
        this.dropDown = new MenuButton("Options");
        this.delete = new MenuItem("Delete");
        this.markAnswer = new MenuItem("Mark answered");
        this.answerText = new MenuItem("Answer with text");
        this.ban = new MenuItem("Ban author");
        this.edit = new MenuItem("Edit");

        dropDown.getItems().addAll(delete, edit, markAnswer, answerText, ban);

        attachEventHandlers();

        this.horizontalGridPane.add(dropDown, 1, 0);
    }
    
    private void createAnswerTextArea() {
        this.answerTextArea = new TextArea();

        this.answerTextArea.setWrapText(true);
        this.answerTextArea.setPrefRowCount(3);
        this.answerTextArea.setVisible(false);
        
        this.outerGridPane.add(answerTextArea, 1, 1);
    }
    
    protected void toggleAnswerTextArea(boolean visible) {
        if (visible) {
            outerGridPane.getRowConstraints().add(1, new RowConstraints());
        } else {
            RowConstraints row1 = new RowConstraints();
            RowConstraints row2 = new RowConstraints();
            row2.setPrefHeight(0);
            
            this.outerGridPane.getRowConstraints().addAll(row1, row2);
        }
        this.answerTextArea.setVisible(visible);
    }

    private void attachEventHandlers() {
        upvoteButton.setOnAction(event -> {
            vote();
        });

        delete.setOnAction(event -> {
            delete();
        });

        markAnswer.setOnAction(event -> {
            markAnswered();
        });

        answerText.setOnAction(event -> {
            answerWithText();
        });

        ban.setOnAction(event -> {
            banAuthor();
        });
        
        edit.setOnAction(event -> {
            edit();
        });
    }

    public void setOwner(LectureRoomSceneController owner) {
        this.owner = owner;
    }

    public LectureRoomSceneController getOwner() {
        return owner;
    }


    private void childConfiguration() {
        setBottomAnchor(outerGridPane, 0.0);
        setTopAnchor(outerGridPane, 0.0);
        setLeftAnchor(outerGridPane, 0.0);
        setRightAnchor(outerGridPane, 0.0);
        setLeftAnchor(menuBar, 0.0);
        setRightAnchor(menuBar, 0.0);
    }

    private void vote() {
        VoteResponse response = null;
        try {
            response = ServerCommunication.voteOnQuestion(Integer.valueOf(questionId));
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        if (!response.isSuccess()) {
            System.out.println(response.getMessage());
        }

        if (response != null) {
            owner.displayStatusMessage(response.getMessage());
        }

        owner.refreshButtonClickedAfter();
    }

    protected void delete() {
        DeleteQuestionResponse response = null;
        System.out.println("requesting");
        try {
            response = ServerCommunication.deleteQuestion(this.questionId);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        if (!response.getSuccess()) {
            System.out.println(response.getMessage());
        }
        if (response != null) {
            owner.displayStatusMessage(response.getMessage());
        }

        owner.refreshButtonClickedAfter();

    }

    public String getUserIp() {
        return userIp;
    }

    public void setUserIp(String userIp) {
        this.userIp = userIp;
    }

    protected void markAnswered() {
        PostAnswerResponse response = null;

        try {
            response = ServerCommunication.postAnswer(this.questionId, "");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        if (!response.getSuccess()) {
            System.out.println(response.getMessage());
        }

        if (response != null) {
            owner.displayStatusMessage(response.getMessage());
        }

        owner.refreshButtonClickedAfter();
    }

    private void answerWithText() {
        toggleAnswerTextArea(true);
        this.answerTextArea.requestFocus();
        this.answerTextArea.setEditable(true);
        this.answerTextArea.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                PostAnswerResponse response = null;
                
                String text = answerTextArea.getText();
                
                text = text.trim();

                try {
                    response = ServerCommunication.postAnswer(this.questionId, text);
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }

                if (!response.getSuccess()) {
                    System.out.println(response.getMessage());
                }

                if (response != null) {
                    owner.displayStatusMessage(response.getMessage());
                }

                owner.refreshButtonClickedAfter();
            }
        });
    }
    
    private void edit() {
        this.questionTextArea.setEditable(true);
        this.questionTextArea.requestFocus();
        this.questionTextArea.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                this.questionTextArea.setEditable(false);
                EditQuestionResponse response = null;
                
                String text = this.questionTextArea.getText();
                
                text = text.trim();
                
                try {
                    response = ServerCommunication.editQuestion(this.questionId, text);
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
                
                if (!response.getSuccess()) {
                    System.out.println(response.getMessage());
                }

                if (response != null) {
                    owner.displayStatusMessage(response.getMessage());
                }
                questionTextArea.setEditable(false);
                owner.refreshButtonClickedAfter();
            }
        });
    }



    /**
     * Update the Options dropdown for Lecturer.
     */
    private void updateLecturer() {

    }

    /**
     * Update the Options for student.
     */
    private void updateStudent() {
    }

    /**
     * Update the Options dropdown for Moderator.
     */
    private void updateModerator() {

    }

    /**
     * Update question view.
     */
    public void updateQuestionView() {
        if (this.owner.getDs().getPrivilegeId().equals(1)) {
            updateLecturer();
        }

        if (this.owner.getDs().getPrivilegeId().equals(2)) {
            updateModerator();
        }

        if (this.owner.getDs().getPrivilegeId().equals(3)) {
            updateStudent();
        }
    }

    private void banAuthor() {
        BanIpResponse response = null;

        try {
            response = ServerCommunication.banIp(this.questionId, this.userIp);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        if (response.getSuccess()) {
            owner.displayStatusMessage("Users with this question's author's IP "
                    + "have been banned from posting anymore questions.");
        }

        owner.refreshButtonClickedAfter();

    }

    public void toggleLecturerMode(boolean b) {
        //implemented in child class
    }

}

