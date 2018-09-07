package by.vistar.comeco.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import static by.vistar.comeco.dao.DaoConstants.*;
import static by.vistar.comeco.dao.DaoConstants.MYSQL_NEW_TABLE_GOOOD;
import static by.vistar.comeco.dao.DaoConstants.MYSQL_NEW_TABLE_UNIT;

public abstract class DaoMain {

    Map<String, PreparedStatement> mysqlPrepareStatement = null;

    public void initPrepareStatement(Connection connection) throws SQLException {
        if (mysqlPrepareStatement != null) {
            mysqlPrepareStatement.clear();
        } else {
            mysqlPrepareStatement = new HashMap<>();
        }
        mysqlPrepareStatement.put("addUnit", connection.prepareStatement(MYSQL_ADD_UNIT_OF_MEASURE, Statement.RETURN_GENERATED_KEYS));
        mysqlPrepareStatement.put("dellUnit", connection.prepareStatement(MYSQL_DELL_UNIT_OF_MEASURE));
        mysqlPrepareStatement.put("editUnit", connection.prepareStatement(MYSQL_EDIT_UNIT_OF_MEASURE));
        mysqlPrepareStatement.put("getUnit", connection.prepareStatement(MYSQL_GET_UNIT_OF_MEASURE));
        mysqlPrepareStatement.put("newTableUnit", connection.prepareStatement(MYSQL_NEW_TABLE_UNIT));
        mysqlPrepareStatement.put("newTableGood", connection.prepareStatement(MYSQL_NEW_TABLE_GOOOD));
        mysqlPrepareStatement.put("addGood", connection.prepareStatement(MYSQL_ADD_GOOD, Statement.RETURN_GENERATED_KEYS));
        mysqlPrepareStatement.put("dellGood", connection.prepareStatement(MYSQL_DELL_GOOD));
        mysqlPrepareStatement.put("editGood", connection.prepareStatement(MYSQL_EDIT_GOOD));
        mysqlPrepareStatement.put("getGood", connection.prepareStatement(MYSQL_GET_GOOD));
    }

    public void closePrepareStatement() throws SQLException {
        for (PreparedStatement ps : mysqlPrepareStatement.values()) {
            ps.close();
        }
    }

    public void initTable() throws SQLException {
        PreparedStatement pst = mysqlPrepareStatement.get("newTableUnit");
        pst.execute();
        pst = mysqlPrepareStatement.get("newTableGood");
        pst.execute();
    }
}
