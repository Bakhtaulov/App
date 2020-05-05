package sample;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


    public class Controller {

        @FXML
        private TextField password_field;

        @FXML
        private Button authSignInButton;

        @FXML
        private Button loginSignUpButton;

        @FXML
        private TextField login_field;

        @FXML
        private Button signUpGuest;

         @FXML
         private URL location;

         Manager connection=new Manager();


        @FXML
        void initialize()  throws IOException  {
           authSignInButton.setOnAction(event -> {
               String login=login_field.getText().trim();
               String Password=password_field.getText().trim();
               boolean exist=false;

               try {
                   exist=connection.check(login,Password);
               } catch (SQLException throwables) {
                   throwables.printStackTrace();
               } catch (ClassNotFoundException e) {
                   e.printStackTrace();
               }
               if(login.equals("Adminka")&&Password.equals("Adminka")){
                   try {
                       Parent parent= FXMLLoader.load(getClass().getResource("/sample/Adminka.fxml"));
                       Stage stage=new Stage();
                       stage.setTitle("Admin");
                       stage.setScene(new Scene(parent));
                       stage.show();
                       login_field.getScene().getWindow().hide();
                   } catch (IOException e) {
                       e.printStackTrace();
                   }
               }
               else if(exist){
                   try {
                       Parent root=FXMLLoader.load(getClass().getResource("/sample/Adminka.fxml"));
                      Stage stage = new Stage();
                      stage.setTitle("User");
                      stage.setScene(new Scene(root));
                      stage.show();
                      password_field.getScene().getWindow().hide();
                   } catch (IOException e) {
                       e.printStackTrace();
                   }
               }

           });
           loginSignUpButton.setOnAction(event -> {
               Parent parent=null;
               try {
                   parent=FXMLLoader.load(getClass().getResource("/sample/Check.fxml"));
                  Stage stage= new Stage();
                  stage.setTitle("Sign in");
                  stage.setScene(new Scene(parent));
                  stage.show();
                  loginSignUpButton.getScene().getWindow().hide();
               } catch (IOException e) {
                   e.printStackTrace();
               }

           });
             signUpGuest.setOnAction(event -> {
                 Parent parent=null;
                 try {
                     parent=FXMLLoader.load(getClass().getResource("/sample/Adminka.fxml"));
                Stage stage= new Stage();
                stage.setTitle("Guest");
                stage.setScene(new Scene(parent));
                stage.show();
                signUpGuest.getScene().getWindow().hide();
                 } catch (IOException e) {
                     e.printStackTrace();
                 }

             });
        }
        }

