package by.vistar.comeco.services;

import by.vistar.comeco.dao.DaoUnitOfMeasure;
import by.vistar.comeco.entity.UnitOfMeasure;
import by.vistar.comeco.interfaces.DaoService;

import java.sql.Connection;
import java.sql.SQLException;

public class ServiceUnitOfMeasure extends ServiceMain implements DaoService<Long,UnitOfMeasure> {
    private Connection connection;
    private DaoUnitOfMeasure daoUnitOfMeasure;

    public ServiceUnitOfMeasure(Connection connection) {
        super(connection);
        this.connection = connection;
        this.daoUnitOfMeasure = DaoUnitOfMeasure.getInstance();
        try {
            this.daoUnitOfMeasure.initPrepareStatement(connection);
            this.daoUnitOfMeasure.initTable();
        } catch (SQLException e) {
            System.out.println("Error initPrepareStatement");
            e.printStackTrace();
        }
    }

    public UnitOfMeasure add(UnitOfMeasure unitOfMeasure) {
        if (unitOfMeasure != null) {
            startTransaction();
            try {
                daoUnitOfMeasure.add(unitOfMeasure);
            } catch (SQLException e) {
                System.out.println("Error add user in DB.");
                e.printStackTrace();
            }
            commit();
        }
        return unitOfMeasure;
    }

    public void dell(Long id) {
        if (id != null) {
            startTransaction();
            try {
                daoUnitOfMeasure.dell(id);
            } catch (SQLException e) {
                System.out.println("Error dell user from DB");
                e.printStackTrace();
            }
            commit();
        } else {
            System.out.println("Error id == null");
        }
    }

    public UnitOfMeasure edit(UnitOfMeasure unitOfMeasure) {
        if (unitOfMeasure != null){
            startTransaction();
            try {
                daoUnitOfMeasure.edit(unitOfMeasure);
            } catch (SQLException e) {
                System.out.println("Error edit user in DB");
                e.printStackTrace();
            }
            commit();
        }
        return unitOfMeasure;
    }

    public UnitOfMeasure get(Long id){
        UnitOfMeasure unitOfMeasure = null;
        if(id!=null){
            startTransaction();
            try {
                unitOfMeasure = daoUnitOfMeasure.get(id);
            } catch (SQLException e) {
                System.out.println("Error get user from DB");
                e.printStackTrace();
            }
            commit();
        }else {
            System.out.println("Error id == null");
        }
        return unitOfMeasure;
    }

    public void closeConnectionAndPrepareStatement(){
        try {
            connection.close();
            daoUnitOfMeasure.closePrepareStatement();
        } catch (SQLException e) {
            System.out.println("Error close connection end prepareStatement");
            e.printStackTrace();
        }
    }

}
