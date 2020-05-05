
package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;

public class UserController {

    @FXML
    private TableView<Pies> table2;

    @FXML
    private TableColumn<Pies, Double> DiscountView;

    @FXML
    private TableColumn<Pies,String> NameView;

    @FXML
    private TableColumn<Pies,Double > PriceView;

    @FXML
    private TableColumn<Pies,Integer> AmountView;

    @FXML
    private Button ButtonAdd;

    @FXML
    private TextField amountfield;

    @FXML
    private Label AmountSignUp;

    @FXML
    private Button ButtonDelete;

    @FXML
    private Button ButtonSend;

    @FXML
    private Label ExitSignUp;
    ObservableList<Pies> ob1= FXCollections.observableArrayList();//ob1
    Pies p1=new Pies();
    Manager connection = new Manager();
    private static  String TotalPrice;
    @FXML
    void initialize (){
        ob1.clear();
        fillOne();
        showOne();
        ButtonAdd.setOnAction(event -> {
            String amount=amountfield.getText().trim();
            Pies pies;
            pies =p1;
            double a=p1.getPrice()-p1.getPrice()*p1.getDiscount()*0.01;
            pies.setPrice(a);
            pies.setAmount(Integer.parseInt(amount));
            ob1.add(pies);
            amountfield.setText(" ");
        });
        ButtonDelete.setOnAction(event -> {
            ob1.remove(p1);
        });
        ButtonSend.setOnAction(event -> {
            try {
                BufferedWriter bufferedWriter= new BufferedWriter(new FileWriter("test.txt",true));
            String result=" ";
            for(int i=0;i<ob1.size();i++){
                result+="Название" + ob1.get(i).getName()+" Цена" + ob1.get(i).getPrice() +"Количество" +ob1.get(i).getAmount()+ " ad";

            }
            result+=" ------------------------------------";
            result+="Общая сумма" + TotalPrice + " ";
            bufferedWriter.write(result);
            bufferedWriter.close();
            } catch (IOException e) {

            }
            totalPrice();
            ob1.clear();
            showOne();
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("YOUR TOTAL PRICE: " + TotalPrice);
            alert.show();


        });
        ExitSignUp.setOnMouseClicked(event->{
            try {
                Parent parent = FXMLLoader.load(getClass().getResource("/sample/sample.fxml"));
                Stage stage = new Stage();
                stage.setTitle("Главная страница");
                stage.setScene(new Scene(parent));
                stage.show();
                ExitSignUp.getScene().getWindow().hide();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void totalPrice() {
        double total =0;
        for(int i=0 ;i<ob1.size();i++){
            total += ob1.get(i).getPrice()*ob1.get(i).getAmount();
        }
        TotalPrice = Double.toString(total);
    }

    public void fillOne()  {

        try {
            ob1 = connection.getAllPies();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
    public void showOne(){
        NameView.setCellValueFactory(new PropertyValueFactory<>("name"));
        PriceView.setCellValueFactory(new PropertyValueFactory<>("price"));
        DiscountView.setCellValueFactory(new PropertyValueFactory<>("discount"));
        table2.setItems(ob1);
    }

    public void select1(MouseEvent mouseEvent) {
        int row = table2.getSelectionModel().getSelectedCells().get(0).getRow();
        p1 = table2.getItems().get(row);

    }
    }



}
