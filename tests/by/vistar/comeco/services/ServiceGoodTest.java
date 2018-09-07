package by.vistar.comeco.services;

import by.vistar.comeco.dao.DaoConstants;
import by.vistar.comeco.entity.Good;
import by.vistar.comeco.entity.UnitOfMeasure;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;

import static by.vistar.comeco.services.ServiceUnitOfMeasureTest.getConnection;
import static org.junit.Assert.*;

public class ServiceGoodTest {

    private Connection connection;
    private ServiceUnitOfMeasure serviceUnitOfMeasure;
    private ServiceGood serviceGood;


    @Before
    public void initTest() {
        this.connection = getConnection();
        this.serviceUnitOfMeasure = new ServiceUnitOfMeasure(connection);
        this.serviceGood = new ServiceGood(connection);
        new ServiceInitDropTables(connection).initTable();
    }

    @After
    public void closeTest() {
        new ServiceInitDropTables(connection).dropTable();
        serviceGood.closeConnectionAndPrepareStatement();
    }

    @Test
    public void add() {
        Good good = new Good();
        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        good.setName("Steel toe caps 701/10");
        good.setFullName("Металический подносок с резинкой модель 701/10");
        good.setNameAdd("Production China");
        unitOfMeasure.setName("prs");
        unitOfMeasure.setFullName("Пар");
        serviceUnitOfMeasure.add(unitOfMeasure);
        good.setUnit(unitOfMeasure);
        serviceGood.add(good);
        Good goodTest = serviceGood.get(good.getId());
        assertEquals(good.getId(),goodTest.getId());
        assertEquals(good.getName(),goodTest.getName());
        assertEquals(good.getFullName(),goodTest.getFullName());
        assertEquals(good.getNameAdd(),goodTest.getNameAdd());
        assertEquals(good.getUnit().getId(),goodTest.getUnit().getId());

        good.setName("Steel toe caps 701/10 test");
        good.setFullName("Металический подносок с резинкой модель 701/10 test");
        good.setNameAdd("Production China test");
        good.setUnit(null);
        serviceGood.add(good);
        goodTest = serviceGood.get(good.getId());
        assertEquals(good.getId(),goodTest.getId());
        assertEquals(good.getName(),goodTest.getName());
        assertEquals(good.getFullName(),goodTest.getFullName());
        assertEquals(good.getNameAdd(),goodTest.getNameAdd());
        assertNull(goodTest.getUnit());
    }

    @Test
    public void dell() {
        Good good = new Good();
        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        good.setName("Steel toe caps 701/10");
        good.setFullName("Металический подносок с резинкой модель 701/10");
        good.setNameAdd("Production China");
        unitOfMeasure.setName("prs");
        unitOfMeasure.setFullName("Пар");
        serviceUnitOfMeasure.add(unitOfMeasure);
        good.setUnit(unitOfMeasure);
        serviceGood.add(good);
        serviceGood.dell(good.getId());
        assertNull(serviceGood.get(good.getId()));
    }

    @Test
    public void edit() {
        Good good = new Good();
        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        good.setName("Steel toe caps 701/10");
        good.setFullName("Металический подносок с резинкой модель 701/10");
        good.setNameAdd("Production China");
        unitOfMeasure.setName("prs");
        unitOfMeasure.setFullName("Пар");
        serviceUnitOfMeasure.add(unitOfMeasure);
        good.setUnit(unitOfMeasure);
        serviceGood.add(good);

        good.setName("Steel toe caps 701/10 test");
        good.setFullName("Металический подносок с резинкой модель 701/10 test");
        good.setNameAdd("Production China test");
        Good goodTest =  serviceGood.edit(good);
        assertEquals(goodTest.getName(),good.getName());
        assertEquals(goodTest.getFullName(),good.getFullName());
        assertEquals(goodTest.getNameAdd(),good.getNameAdd());
    }

    @Test
    public void get() {
        Good good = new Good();
        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        good.setName("Steel toe caps 701/10");
        good.setFullName("Металический подносок с резинкой модель 701/10");
        good.setNameAdd("Production China");
        unitOfMeasure.setName("prs");
        unitOfMeasure.setFullName("Пар");
        serviceUnitOfMeasure.add(unitOfMeasure);
        good.setUnit(unitOfMeasure);
        serviceGood.add(good);

        Good goodTest = serviceGood.get(good.getId());
        assertEquals(goodTest.getId(),good.getId());
        assertEquals(goodTest.getName(),good.getName());
        assertEquals(goodTest.getFullName(),good.getFullName());
        assertEquals(goodTest.getNameAdd(),good.getNameAdd());
        assertEquals(goodTest.getUnit().getName(),unitOfMeasure.getName());
        assertEquals(goodTest.getUnit().getFullName(),unitOfMeasure.getFullName());
        assertEquals(goodTest.getUnit().getId(),unitOfMeasure.getId());

        good.setName("Steel toe caps 701/10 test");
        good.setFullName("Металический подносок с резинкой модель 701/10 test");
        good.setNameAdd("Production China test");
        good.setUnit(null);
        serviceGood.add(good);
        goodTest = serviceGood.get(good.getId());
        assertEquals(goodTest.getId(),good.getId());
        assertEquals(goodTest.getName(),good.getName());
        assertEquals(goodTest.getFullName(),good.getFullName());
        assertEquals(goodTest.getNameAdd(),good.getNameAdd());
        assertNull(goodTest.getUnit());
    }
}