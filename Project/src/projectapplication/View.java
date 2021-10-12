package projectapplication;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

//View class to show the view using JavaFX
public class View extends Application {

    Scene scene;
    TextField custNameTextField, billAmountTextField;
    ToggleGroup radioGroup;
    Controller controller;  ///controller object

    @Override
    public void start(Stage primaryStage) {
    	//Set grid panes
        GridPane titleGridPane = titleGridPane();
        GridPane custGridPane = custGridPane();
        GridPane serviceGridPane = serviceGridPane();
        
        Separator horizontalSeparator = new Separator(Orientation.HORIZONTAL);
        Separator verticalSeparator = new Separator(Orientation.VERTICAL);

        Button btn1 = new Button();
        btn1.setText("Enter");
        
        GridPane buttonGridPane = new GridPane();
        buttonGridPane.add(btn1, 40, 4, 1, 1);
        buttonGridPane.add(horizontalSeparator, 0, 8, 1, 1);
        buttonGridPane.setHgap(10);
        buttonGridPane.setVgap(10);
       
        
        //Button use to process the details 
        btn1.setOnAction(new EventHandler<ActionEvent>() {

        	//set action for button choice
        	@Override
            public void handle(ActionEvent event) {

                int selectedResponse = 1;
                RadioButton selectedRadioButton = (RadioButton) radioGroup.getSelectedToggle();
                if (selectedRadioButton.getText().equals("Poor")) {
                    selectedResponse = 1;
                } else if (selectedRadioButton.getText().equals("Fair")) {
                    selectedResponse = 2;
                } else if (selectedRadioButton.getText().equals("Good")) {
                    selectedResponse = 3;
                } else if (selectedRadioButton.getText().equals("Excellent")) {
                    selectedResponse = 4;
                }
                
                //error handling for incorrect input
                Alert a = new Alert(AlertType.ERROR);
                if (custNameTextField.getText().toString().equals("")) {
                    a.setContentText("Your name is required");
                    a.show();
                }else if (billAmountTextField.getText().toString().equals("")) {
                    a.setContentText("Your bill is required");
                    a.show();
                }else if (Double.valueOf(billAmountTextField.getText().toString())==0 || Double.valueOf(billAmountTextField.getText().toString())>=1000) {
                    a.setContentText("Your bill should be greater than 0 and less than 1000");
                    a.show();
                } else {
                    controller = new Controller(custNameTextField.getText(), Double.parseDouble(billAmountTextField.getText()), selectedResponse);
                    GridPane showGridPane = showGridPane();
                    StackPane root = new StackPane();
                    root.getChildren().add(showGridPane);
                    
                    scene = new Scene(root, 900, 350);
                    primaryStage.setScene(scene);
                    primaryStage.show();
                }
            }
        });
        
        
        //Final gridPane shows all the structure on the screen
        GridPane gridPane = new GridPane();
        gridPane.add(titleGridPane, 0, 0, 1, 1);
        gridPane.add(custGridPane, 0, 1, 1, 1);
        gridPane.add(verticalSeparator, 1, 1, 1, 1);
        gridPane.add(serviceGridPane, 2, 1, 1, 1);
        gridPane.add(buttonGridPane, 0, 3, 1, 1);
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        StackPane root = new StackPane();
        root.getChildren().add(gridPane);

        scene = new Scene(root, 900, 350);

        primaryStage.setTitle("Resturant Service App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    //Title Grid pane to show the title of project
    private GridPane titleGridPane() {
        Label titleLabel = new Label("Resturant Service App");
        titleLabel.setFont(new Font("Arial", 22));
        Separator horizontalSeparator = new Separator(Orientation.HORIZONTAL);

        GridPane titleGridPane = new GridPane();
        titleGridPane.add(titleLabel, 30, 4, 1, 1);
        titleGridPane.add(horizontalSeparator, 0, 8, 1, 1);
        titleGridPane.setHgap(10);
        titleGridPane.setVgap(10);

        return titleGridPane;
    }

    
    //To show the label and textFeild of customer name
    //And the label and textFeild of bill amount
    private GridPane custGridPane() {

        Separator horizontalSeparator = new Separator(Orientation.HORIZONTAL);

        Label custNameLabel = new Label("Enter your name");
        Label billAmountLabel = new Label("Enter your bill");
        custNameTextField = new TextField();
        billAmountTextField = new TextField();

        GridPane custGridPane = new GridPane();
        custGridPane.add(custNameLabel, 10, 0, 1, 1);
        custGridPane.add(custNameTextField, 13, 0, 1, 1);
        custGridPane.add(billAmountLabel, 10, 1, 1, 1);
        custGridPane.add(billAmountTextField, 13, 1, 1, 1);

        custGridPane.setHgap(10);
        custGridPane.setVgap(10);

        return custGridPane;
    }

    //Radio group to select the desire service
    private GridPane serviceGridPane() {
        Label serviceLabel = new Label("How good was your service?");
        serviceLabel.setFont(new Font("Arial", 16));

        RadioButton poorRadioButton = new RadioButton("Poor");
        poorRadioButton.setSelected(true);
        RadioButton fairRadioButton = new RadioButton("Fair");
        RadioButton goodRadioButton = new RadioButton("Good");
        RadioButton excellentRadioButton = new RadioButton("Excellent");

        radioGroup = new ToggleGroup();

        poorRadioButton.setToggleGroup(radioGroup);
        fairRadioButton.setToggleGroup(radioGroup);
        goodRadioButton.setToggleGroup(radioGroup);
        excellentRadioButton.setToggleGroup(radioGroup);

        TilePane radioTilePane = new TilePane(poorRadioButton, fairRadioButton, goodRadioButton, excellentRadioButton);
        radioTilePane.setTileAlignment(Pos.CENTER);

        GridPane serviceGridPane = new GridPane();
        serviceGridPane.add(serviceLabel, 0, 0, 1, 1);
        serviceGridPane.add(radioTilePane, 0, 2, 1, 1);
        serviceGridPane.setHgap(10);
        serviceGridPane.setVgap(10);

        return serviceGridPane;
    }

    //Print data and show the image of service 
    private GridPane showGridPane() {

        GridPane titleGridPane = titleGridPane();
        
        TextArea textArea = new TextArea();
        textArea.setText(controller.printInfo());
        textArea.setEditable(false);

        Image image = new Image(controller.serviceImage());
        ImageView imageView = new ImageView(image);

        GridPane showGridPane = new GridPane();
        showGridPane.add(titleGridPane, 5, 1, 1, 1);
        showGridPane.add(textArea, 5, 2, 1, 1);
        showGridPane.add(imageView, 7, 2, 1, 1);
        showGridPane.setHgap(10);
        showGridPane.setVgap(10);

        return showGridPane;
    }

}
