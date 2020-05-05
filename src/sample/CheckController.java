package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.jar.Attributes;

public class CheckController {


        @FXML
        private TextField nameSignUp;

        @FXML
        private TextField surnameSignUp;

        @FXML
        private TextField loginSignUp;

        @FXML
        private Button REGISTRATION;

        @FXML
        private PasswordField PASSWORD;

        @FXML
        private Label CheckSignUp;

        @FXML
        private Label exitSignUp;
        Manager manager= new Manager();
        @FXML
        void initialize(){
            REGISTRATION.setOnAction(event ->{
                String name= nameSignUp.getText().trim();
                String surname=surnameSignUp.getText().trim();
                String login= loginSignUp.getText().trim();
                String password=PASSWORD.getText().trim();
                try {
                    manager.addUsers(new Users(null,name,surname,login,password));

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                nameSignUp.setText(" ");
                surnameSignUp.setText(" ");
                loginSignUp.setText(" ");
                PASSWORD.setText(" ");
            } );
            exitSignUp.setOnMouseClicked(mouseEvent -> {
                try {
                    Parent parent = FXMLLoader.load(getClass().getResource("/sample/sample.fxml"));
                    Stage stage= new Stage();
                    stage.setTitle("The main page of Sample");
                    stage.setScene(new Scene(parent));
                    stage.show();
                    exitSignUp.getScene().getWindow().hide();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }

    }



