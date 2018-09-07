package by.vistar.comeco.services;

import by.vistar.comeco.dao.DaoConstants;
import by.vistar.comeco.dao.DaoUnitOfMeasure;
import by.vistar.comeco.entity.UnitOfMeasure;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class ServiceUnitOfMeasureTest {

    Connection connection;
    ServiceUnitOfMeasure serviceUnitOfMeasure;

    static Connection getConnection() {
        String url = "jdbc:mysql://localhost:3306/mydb?useUnicode=true&characterEncoding=UTF-8&useSSL=false&useLegacyDatetimeCode=false&amp&serverTimezone=UTC";
        String driver = "com.mysql.jdbc.Driver";
        String user = "root";
        String password = "wertywerty101";
        try {
            try {
                try {
                    Class.forName(driver).newInstance();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Before
    public void initTest() {
        connection = getConnection();
        serviceUnitOfMeasure = new ServiceUnitOfMeasure(connection);
        new ServiceInitDropTables(connection).initTable();
    }

    @After
    public void closeTest() {
        new ServiceInitDropTables(connection).dropTable();
        serviceUnitOfMeasure.closeConnectionAndPrepareStatement();
    }

    @Test
    public void add() {
        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setName("kg");
        unitOfMeasure.setFullName("Килограммы");
        serviceUnitOfMeasure.add(unitOfMeasure);
        assertEquals(unitOfMeasure.getName(), "kg");
        assertEquals(unitOfMeasure.getFullName(), "Килограммы");
        assertTrue(unitOfMeasure.getId() > 0);
    }

    @Test
    public void dell() {
        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setName("kg");
        unitOfMeasure.setFullName("Килограммы");
        serviceUnitOfMeasure.add(unitOfMeasure);
        assertTrue(unitOfMeasure.getId() > 0);
        serviceUnitOfMeasure.dell(unitOfMeasure.getId());
        assertTrue(serviceUnitOfMeasure.get(unitOfMeasure.getId()) == null);
    }

    @Test
    public void edit() {
        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setName("kg");
        unitOfMeasure.setFullName("Килограммы");
        serviceUnitOfMeasure.add(unitOfMeasure);

        unitOfMeasure.setName("kgTest");
        unitOfMeasure.setFullName("Килограммы Test");
        serviceUnitOfMeasure.edit(unitOfMeasure);

        UnitOfMeasure unitOfMeasureTest = serviceUnitOfMeasure.get(unitOfMeasure.getId());
        assertEquals(unitOfMeasureTest.getName(), "kgTest");
        assertEquals(unitOfMeasureTest.getFullName(), "Килограммы Test");
        assertEquals(unitOfMeasureTest.getId(),unitOfMeasure.getId());
    }

    @Test
    public void get() {
        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setName("kg");
        unitOfMeasure.setFullName("Килограммы");
        serviceUnitOfMeasure.add(unitOfMeasure);
        UnitOfMeasure unitOfMeasureTest = serviceUnitOfMeasure.get(unitOfMeasure.getId());
        assertEquals(unitOfMeasure.getId(),unitOfMeasureTest.getId());
        assertEquals(unitOfMeasure.getName(),unitOfMeasureTest.getName());
        assertEquals(unitOfMeasure.getFullName(),unitOfMeasureTest.getFullName());
    }
}