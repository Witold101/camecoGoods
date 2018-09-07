package by.vistar.comeco.services;

import by.vistar.comeco.dao.DaoConstants;
import by.vistar.comeco.dao.DaoUnitOfMeasure;
import by.vistar.comeco.interfaces.InitDropTables;

import java.sql.Connection;
import java.sql.SQLException;

public class ServiceInitDropTables extends ServiceMain implements InitDropTables {

    Connection connection;
    DaoUnitOfMeasure daoUnitOfMeasure;

    public ServiceInitDropTables(Connection connection) {
        super(connection);
        this.connection = connection;
        daoUnitOfMeasure = DaoUnitOfMeasure.getInstance();
    }


    @Override
    public void initTable() {
        startTransaction();
        try {
            daoUnitOfMeasure.initTable();
        } catch (SQLException e) {
            System.out.println("Error initTable from DB");
            e.printStackTrace();
        }
        commit();
    }

    @Override
    public void dropTable() {
        String sql = "DROP TABLE `" + DaoConstants.TABLE_NAME_UNIT_OF_MEASURE + "`;";
        String sql2 = "DROP TABLE `" + DaoConstants.TABLE_NAME_GOOD + "`;";
        try {
            connection.prepareStatement(sql2).execute();
            connection.prepareStatement(sql).execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
