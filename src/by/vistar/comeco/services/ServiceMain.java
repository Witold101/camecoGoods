package by.vistar.comeco.services;

import java.sql.Connection;
import java.sql.SQLException;

public class ServiceMain {

    Connection connection;

    public ServiceMain(Connection connection) {
        this.connection = connection;
    }

    public void startTransaction() {
        try {
            this.connection.setAutoCommit(false);
        } catch (SQLException e) {
            System.out.println("Error init setAutocommit = false");
            e.printStackTrace();
        }
    }

    public void commit() {
        try {
            this.connection.commit();
        } catch (SQLException e) {
            System.out.println("Error commit");
            e.printStackTrace();
        }
    }
}
