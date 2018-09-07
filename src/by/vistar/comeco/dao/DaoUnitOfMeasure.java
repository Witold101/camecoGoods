package by.vistar.comeco.dao;

import by.vistar.comeco.entity.UnitOfMeasure;
import by.vistar.comeco.interfaces.DAO;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

import static by.vistar.comeco.dao.DaoConstants.*;

public class DaoUnitOfMeasure extends DaoMain implements DAO<Long, UnitOfMeasure> {

    private static volatile DaoUnitOfMeasure INSTANCE = null;

    private DaoUnitOfMeasure(){

    }

    public static DaoUnitOfMeasure getInstance() {
        DaoUnitOfMeasure daoUnitOfMeasure = INSTANCE;
        if (daoUnitOfMeasure == null) {
            synchronized (DaoUnitOfMeasure.class) {
                daoUnitOfMeasure = INSTANCE;
                if (daoUnitOfMeasure == null) {
                    INSTANCE = daoUnitOfMeasure = new DaoUnitOfMeasure();
                }
            }
        }
        return daoUnitOfMeasure;
    }

    @Override
    public UnitOfMeasure add(UnitOfMeasure unitOfMeasure) throws SQLException {
        PreparedStatement pst = mysqlPrepareStatement.get("addUnit");
        pst.setString(1, unitOfMeasure.getName());
        pst.setString(2, unitOfMeasure.getFullName());
        pst.executeUpdate();
        ResultSet rs = pst.getGeneratedKeys();
        if (rs.next()) {
            unitOfMeasure.setId(rs.getLong(1));
        }
        rs.close();
        return unitOfMeasure;
    }

    @Override
    public void dell(Long id) throws SQLException {
        PreparedStatement pst = mysqlPrepareStatement.get("dellUnit");
        pst.setLong(1, id);
        pst.executeUpdate();
    }

    @Override
    public UnitOfMeasure edit(UnitOfMeasure unitOfMeasure) throws SQLException {
        PreparedStatement pst = mysqlPrepareStatement.get("editUnit");
        pst.setString(1, unitOfMeasure.getName());
        pst.setString(2, unitOfMeasure.getFullName());
        pst.setLong(3,unitOfMeasure.getId());
        if (pst.executeUpdate()>0) {
            return unitOfMeasure;
        }else {
            return null;
        }
    }

    @Override
    public UnitOfMeasure get(Long id) throws SQLException {
        UnitOfMeasure unitOfMeasure = null;
        PreparedStatement pst = mysqlPrepareStatement.get("getUnit");
        pst.setLong(1, id);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            unitOfMeasure = new UnitOfMeasure();
            unitOfMeasure.setId(rs.getLong("id"));
            unitOfMeasure.setName(rs.getString("name"));
            unitOfMeasure.setFullName(rs.getString("full_name"));
        }
        rs.close();
        return unitOfMeasure;
    }
}
