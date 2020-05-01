package db;

import java.sql.*;

public class DBConnection {

    private static final String URL = "jdbc:h2:D:/EPAM_project/GameIndustry/gameInd";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public void getConnection() {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {
            if (!connection.isClosed()) {
                System.out.println("Соединение с БД установлено");
            }

        } catch (SQLException e) {
            System.out.println("Соединение с драйвером не установлено!");
            e.printStackTrace();
        }
    }
}
