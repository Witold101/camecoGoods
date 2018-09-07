package by.vistar.comeco.dao;

import by.vistar.comeco.entity.Good;
import by.vistar.comeco.entity.UnitOfMeasure;
import by.vistar.comeco.interfaces.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class DaoGood extends DaoMain implements DAO<Long, Good> {

    private static volatile DaoGood INSTANCE = null;

    private DaoGood() {

    }

    public static DaoGood getInstance() {
        DaoGood daoGood = INSTANCE;
        if (daoGood == null) {
            synchronized (DaoGood.class) {
                daoGood = INSTANCE;
                if (daoGood == null) {
                    INSTANCE = daoGood = new DaoGood();
                }
            }
        }
        return daoGood;
    }


    @Override
    public Good add(Good good) throws SQLException {
        PreparedStatement pst = mysqlPrepareStatement.get("addGood");
        pst.setString(1, good.getName());
        pst.setString(2, good.getFullName());
        pst.setString(3, good.getNameAdd());
        if (good.getUnit() != null) {
            pst.setLong(4, good.getUnit().getId());
        } else {
            pst.setNull(4, Types.NULL);
        }
        pst.executeUpdate();
        ResultSet rs = pst.getGeneratedKeys();
        if (rs.next()) {
            good.setId(rs.getLong(1));
        }
        rs.close();
        return good;
    }

    @Override
    public void dell(Long id) throws SQLException {
        PreparedStatement pst = mysqlPrepareStatement.get("dellGood");
        pst.setLong(1, id);
        pst.executeUpdate();
    }

    @Override
    public Good edit(Good good) throws SQLException {
        PreparedStatement pst = mysqlPrepareStatement.get("editGood");
        pst.setString(1, good.getName());
        pst.setString(2, good.getFullName());
        pst.setString(3, good.getNameAdd());
        pst.setLong(4, good.getUnit().getId());
        pst.setLong(5, good.getId());
        if (pst.executeUpdate() > 0) {
            return good;
        } else {
            return null;
        }
    }

    @Override
    public Good get(Long id) throws SQLException {
        Good good = null;
        PreparedStatement pst = mysqlPrepareStatement.get("getGood");
        pst.setLong(1, id);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            good = new Good();
            good.setId(rs.getLong("id"));
            good.setName(rs.getString("name"));
            good.setFullName(rs.getString("full_name"));
            good.setNameAdd(rs.getString("name_add"));
            DaoUnitOfMeasure daoUnitOfMeasure = DaoUnitOfMeasure.getInstance();
            if (rs.getLong("unit_id") > 0) {
                good.setUnit(daoUnitOfMeasure.get(rs.getLong("unit_id")));
            }else {
                good.setUnit(null);
            }
        }
        rs.close();
        return good;
    }
}
