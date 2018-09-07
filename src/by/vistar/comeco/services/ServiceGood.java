package by.vistar.comeco.services;

import by.vistar.comeco.dao.DaoGood;
import by.vistar.comeco.entity.Good;
import by.vistar.comeco.interfaces.DaoService;

import java.sql.Connection;
import java.sql.SQLException;

public class ServiceGood implements DaoService<Long,Good> {
    private Connection connection;
    private DaoGood daoGood;

    public ServiceGood(Connection connection) {
        this.connection = connection;
        this.daoGood = DaoGood.getInstance();
        try {
            this.daoGood.initPrepareStatement(connection);
            this.daoGood.initTable();
        } catch (SQLException e) {
            System.out.println("Error initPrepareStatement");
            e.printStackTrace();
        }
    }

    private void startTransaction() {
        try {
            this.connection.setAutoCommit(false);
        } catch (SQLException e) {
            System.out.println("Error init setAutocommit = false");
            e.printStackTrace();
        }
    }

    private void commit() {
        try {
            this.connection.commit();
        } catch (SQLException e) {
            System.out.println("Error commit");
            e.printStackTrace();
        }
    }

    public Good add(Good good) {
        if (good != null) {
            startTransaction();
            try {
                daoGood.add(good);
            } catch (SQLException e) {
                System.out.println("Error add good in DB.");
                e.printStackTrace();
            }
            commit();
        }
        return good;
    }

    public void dell(Long id) {
        if (id != null) {
            startTransaction();
            try {
                daoGood.dell(id);
            } catch (SQLException e) {
                System.out.println("Error dell good from DB");
                e.printStackTrace();
            }
            commit();
        } else {
            System.out.println("Error id == null");
        }
    }

    public Good edit(Good good) {
        if (good != null) {
            startTransaction();
            try {
                daoGood.edit(good);
            } catch (SQLException e) {
                System.out.println("Error edit good in DB");
                e.printStackTrace();
            }
            commit();
        }
        return good;
    }

    public Good get(Long id) {
        Good good = null;
        if (id != null) {
            startTransaction();
            try {
                good = daoGood.get(id);
            } catch (SQLException e) {
                System.out.println("Error get good from DB");
                e.printStackTrace();
            }
            commit();
        } else {
            System.out.println("Error id == null");
        }
        return good;
    }


    public void closeConnectionAndPrepareStatement(){
        try {
            connection.close();
            daoGood.closePrepareStatement();
        } catch (SQLException e) {
            System.out.println("Error close connection end prepareStatement");
            e.printStackTrace();
        }
    }
}
