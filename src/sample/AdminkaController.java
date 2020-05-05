package sample;

import java.awt.event.MouseEvent;
import  java.io.IOException;
import com.sun.javafx.fxml.ObservableListChangeEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.awt.geom.Rectangle2D;
import java.sql.SQLException;
import java.util.Collections;
import java.util.PropertyResourceBundle;

import static java.util.Arrays.fill;

public class AdminkaController {

    @FXML
    private Label AddSignUp;

    @FXML
    private TextField NameFieldUp;

    @FXML
    private TextField PriceFieldUp;

    @FXML
    private TextField DiscountFieldUp;

    @FXML
    private Button ButtonAdd;

    @FXML
    private Button DeleteButton;

    @FXML
    private Label ExitButton;

    @FXML
    private TableView<Pies> table;

    @FXML
    private TableColumn<Pies,Integer> IdsignUp;

    @FXML
    private TableColumn<Pies,String> NameSignUp;

    @FXML
    private TableColumn<Pies,Double> PriceSignUp;

    @FXML
    private TableColumn<Pies,Integer> DiscountSignUp;
    Manager connection= new Manager();
    public static ObservableList ob= FXCollections.observableArrayList();
    Pies pies=new Pies();
    @FXML
    void initialize() {
        ob.clear();
        fill();
        show();
        ButtonAdd.setOnAction(event -> {
            String name=NameFieldUp.getText().trim();
            String price=PriceFieldUp.getText().trim();
            String discount=DiscountFieldUp.getText().trim();
            Pies pies= new Pies(null,name,Double.parseDouble(price),Double.parseDouble(discount));

            try {
                connection.addPies(pies);
                ob.clear();
                fill();
                show();
                NameFieldUp.setText(" ");
                PriceFieldUp.setText(" ");
                DiscountFieldUp.setText(" ");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        ExitButton.setOnMouseClicked( mouseEvent ->  {
            try {
                Parent parent = FXMLLoader.load(getClass().getResource("/sample/sample.fxml"));
                Stage stage= new Stage();
                stage.setTitle("The main page ");
                stage.setScene(new Scene(parent));
                stage.show();
                ExitButton.getScene().getWindow().hide();
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
         DeleteButton.setOnAction(event ->{
             try {
                 connection.deletePies(pies.getId());
                 ob.clear();
                 fill();
                 show();
              } catch (SQLException throwables) {
                 throwables.printStackTrace();
             } catch (ClassNotFoundException e) {
                 e.printStackTrace();
             }

         });

    }
    public  void fill(){
        try {
            ob=connection.getAllPies();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public  void show(){
        IdsignUp.setCellValueFactory(new PropertyValueFactory<>("Id"));
        NameSignUp.setCellValueFactory(new PropertyValueFactory<>("Name"));
        PriceSignUp.setCellValueFactory(new PropertyValueFactory<>("Price"));
        DiscountSignUp.setCellValueFactory(new PropertyValueFactory<>("Discount"));

    }
    public void Choose(MouseEvent mouseEvent) {
         int row=table.getSelectionModel().getSelectedCells().get(0).getRow();
         pies=table.getItems().get(row);
    }
}

