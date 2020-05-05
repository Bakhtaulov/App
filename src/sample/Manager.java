package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.awt.image.DataBuffer;
import java.sql.*;

public class Manager {
    Connection DbConnection;
    public Connection getDbConnection()
            throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        DbConnection = DriverManager.getConnection("jdbc:mysql://localhost :3306/project?useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=true","root","root");
        return DbConnection;
    }
    public void addUsers(Users user) throws SQLException, ClassNotFoundException {
        String query = "INSERT INTO user (id,name,surname,login,password) VALUES(null,?,?,?,?)";
        PreparedStatement statement = getDbConnection().prepareStatement(query);
        statement.setString(1,user.getName());
        statement.setString(2,user.getSurname());
        statement.setString(3,user.getLogin());
        statement.setString(4,user.getPassword());
        statement.executeUpdate();
    }
    public boolean check(String login,String password) throws SQLException, ClassNotFoundException {
        String q = "SELECT * FROM user";
        PreparedStatement  stat = getDbConnection().prepareStatement(q);
        ResultSet rs = stat.executeQuery();
        int count =0;
        while(rs.next()){
            if(login.equals(rs.getString(4))&&password.equals(rs.getString(5))){
                return true;
            }
        }
        return false;
    }
    public ObservableList<Pies> getAllPies() throws SQLException, ClassNotFoundException {
        ObservableList<Pies> pies = FXCollections.observableArrayList();
        String query = "SELECT * FROM cake";
        PreparedStatement stat = getDbConnection().prepareStatement(query);
        ResultSet r = stat.executeQuery();
        while(r.next()){
            pies.add(new Pies(r.getString(1),r.getString(2),r.getDouble(3),r.getDouble(4)));
        }
        return pies;
    }
    public void addPies(Pies pies) throws SQLException, ClassNotFoundException {
        String query = "INSERT INTO cake (id,name,price,discount) VALUES(null,?,?,?)";
        PreparedStatement stat = getDbConnection().prepareStatement(query);
        stat.setString(1,pies.getName());
        stat.setDouble(2,pies.getPrice());
        stat.setDouble(3,pies.getDiscount());
        stat.executeUpdate();
        stat.close();
    }
    public void deletePies(String id) throws SQLException, ClassNotFoundException {
        String query = "DELETE FROM pie WHERE id=?";
        PreparedStatement st = getDbConnection().prepareStatement(query);
        st.setString(1,id);
        st.executeUpdate();
        st.close();
    }

}
