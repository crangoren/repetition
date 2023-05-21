package jdbcEx;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseApp {

    static final String DATABASE_URL = "jdbc:sqlite:javadb.db";
    static Connection connection;
    static Statement statement;

    static {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(DATABASE_URL);
            statement = connection.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws SQLException {
        DatabaseApp databaseApp = new DatabaseApp();
//        databaseApp.dropTable();
//        databaseApp.createTable();
//        databaseApp.insertNewBicycle("bike1", "no1");
//        databaseApp.insertNewBicyclePS("model", "serial");
        databaseApp.searchForBike();
        //databaseApp.updateBicycle("3sgfdg65", "bike1");


    }

    public void createTable() throws SQLException {
        String createTable = "create table bicycle (" +
                "id integer not null primary key," +
                "model varchar(30) not null," +
                "serial_no varchar(10))";
        statement.execute(createTable);
    }

    public void insertNewBicycle(String model, String serial) throws SQLException {
        String insertSQL = "insert into bicycle (model, serial_no) values ('"+model+"', '"+serial+"')";
        statement.execute(insertSQL);
    }

    public void insertNewBicyclePS(String model, String serial){
        try (PreparedStatement ps = connection.prepareStatement("insert into bicycle (model, serial_no) values (?, ?)"))
        {
            for (int i = 1; i < 11; i++) {
                ps.setString(1, model + " " + i);
                ps.setString(2, serial + " " + (i+10));
                ps.addBatch();
            }
            int[] ints = ps.executeBatch();

//            ps.setString(1, model);
//            ps.setString(2, serial);
//            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void dropTable() throws SQLException {
        String dropSql = "drop table bicycle";
        statement.execute(dropSql);
    }



    public void updateBicycle(String serial, String model) throws SQLException {
        String sql = "update bicycle set serial_no = '"+serial+"' where model = '"+model+"'";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.execute();
    }


    public List<Bicycle> searchForBike() throws SQLException {
        String sql = "select * from bicycle";

           ResultSet resultSet =  statement.executeQuery(sql);
           List<Bicycle> list = new ArrayList<>();

            while (resultSet.next()) {
                list.add(new Bicycle(resultSet.getInt("id"),
                        resultSet.getString("model"),
                        resultSet.getString("serial_no")));
                //System.out.println(resultSet.getInt("id") + " " + resultSet.getString("model") + " " + resultSet.getString("serial_no"));

            }
        System.out.println(list);
        return list;
    }
}
