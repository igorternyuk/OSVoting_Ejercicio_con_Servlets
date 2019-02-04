/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import com.sun.istack.internal.logging.Logger;
import dao.exceptions.DAOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import models.OperatingSystem;

/**
 * 
 * @author Igor Ternyuk <xmonad100 at gmail.com>
 */
public class OperatingSystemDAOImpl implements OperatingSystemDAO{
    private static final String SQL_INSERT = "INSERT INTO operatingSystem VALUES(UUID(),'?');";
    private static final String SQL_UPDATE = "UPDATE operatingSystem SET name='?' WHERE id='?';";
    private static final String SQL_FIND_BY_ID = "SELECT name from operatingSystem WHERE id='?';";
    private static final String SQL_FIND_BY_NAME = "SELECT * from operatingSystem WHERE name LIKE '%?%';";
    private static final String SQL_REMOVE = "DELETE FROM operatingSystem WHERE id='?';";
    private static final String SQL_GET_ALL = "SELECT * FROM operatingSystem;";
    private static final Logger log = Logger.getLogger(OperatingSystemDAOImpl.class);
    private ConectionFactory conexion = new ConectionFactory();
    
    @Override
    public void addNew(OperatingSystem os) throws DAOException{
        log.info("Creating the new OS with name = " + os.getName());
        Connection connection = null;
        PreparedStatement statement = null;
                
        try{
            log.info("Open connection");
            connection = conexion.getConnection();
            statement = connection.prepareStatement(SQL_INSERT);
            statement.setString(1, os.getName());
            statement.execute();
            log.info("The new OS with name = " + os.getName() + " was successfully created");
        } catch(SQLException ex){
            log.log(Level.SEVERE, "Could not create new OS " + ex.getMessage());
            throw new DAOException("Could not create new OS", ex);
        } finally {
            try{
                if(statement != null){
                    statement.close();
                }
                if(connection != null){
                    connection.close();
                }
                
            }
            catch(SQLException ex){
               log.log(Level.SEVERE, "Could close statement or connection " + ex.getMessage());
                throw new DAOException("Could close statement or connection", ex); 
            }
        }
        
    }

    @Override
    public void update(OperatingSystem os) throws DAOException {
        log.info("Updating OS with ID = " + os.getId());
        Connection connection = null;
        PreparedStatement statement = null;
                
        try{
            log.info("Open connection");
            connection = conexion.getConnection();
            statement = connection.prepareStatement(SQL_UPDATE);
            statement.setString(1, os.getName());
            statement.setString(2, os.getId());
            statement.execute();
            log.info("The OS with ID = " + os.getId() + " was successfully updated");
        } catch(SQLException ex){
            log.log(Level.SEVERE, "Could not update OS " + ex.getMessage());
            throw new DAOException("Could not update OS with id = " + os.getId(), ex);
        } finally {
            try{
                if(statement != null){
                    statement.close();
                }
                if(connection != null){
                    connection.close();
                }
            }
            catch(SQLException ex){
               log.log(Level.SEVERE, "Could close statement or connection " + ex.getMessage());
                throw new DAOException("Could close statement or connection", ex); 
            }
        }
    }

    @Override
    public OperatingSystem findById(String id) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        OperatingSystem os = OperatingSystem.NULL_OS;
        
        try{
            connection = conexion.getConnection();
            statement = connection.prepareStatement(SQL_FIND_BY_ID);
            statement.setString(1, id);
            resultSet = statement.executeQuery();
            if(resultSet.next()){
                os = new OperatingSystem(resultSet.getString("id"),
                        resultSet.getString("name"));
            }
        }
        catch(SQLException ex){
            log.log(Level.SEVERE, "Could not found os with id = " + id);
            throw new DAOException("Could not find OS", ex);
        } finally{
            try{
                if(resultSet != null){
                    resultSet.close();
                }
                if(statement != null){
                    statement.close();
                }
                if(connection != null){
                    connection.close();
                }
            }
            catch(SQLException ex){
               log.log(Level.SEVERE, "Could close resultSet, statement or connection " + ex.getMessage());
                throw new DAOException("CCould close resultSet, statement or connection", ex); 
            }
        }
        return os;
    }

    @Override
    public List<OperatingSystem> findByName(String nameFilter) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<OperatingSystem> osList = new ArrayList<>();
        
        try{
            connection = conexion.getConnection();
            statement = connection.prepareStatement(SQL_FIND_BY_NAME);
            statement.setString(1, nameFilter);
            resultSet = statement.executeQuery();
            while(resultSet.next()){
                OperatingSystem os = new OperatingSystem(resultSet.getString("id"),
                        resultSet.getString("name"));
                osList.add(os);
            }
        }
        catch(SQLException ex){
            log.log(Level.SEVERE, "No matches found for filter " + nameFilter);
            throw new DAOException("Could not find OS", ex);
        } finally{
            try{
                if(resultSet != null){
                    resultSet.close();
                }
                if(statement != null){
                    statement.close();
                }
                if(connection != null){
                    connection.close();
                }
            }
            catch(SQLException ex){
               log.log(Level.SEVERE, "Could close resultSet, statement or connection " + ex.getMessage());
                throw new DAOException("CCould close resultSet, statement or connection", ex); 
            }
        }
        return osList;
    }

    @Override
    public void remove(String id) throws DAOException {
        log.info("Removing OS with ID = " + id);
        Connection connection = null;
        PreparedStatement statement = null;
                
        try{
            log.info("Open connection");
            connection = conexion.getConnection();
            statement = connection.prepareStatement(SQL_REMOVE);
            statement.setString(1, id);
            statement.execute();
            log.info("The OS with ID = " + id + " was successfully removed");
        } catch(SQLException ex){
            log.log(Level.SEVERE, "Could not remove OS " + ex.getMessage());
            throw new DAOException("Could not remove OS with id = " + id, ex);
        } finally {
            try{
                if(statement != null){
                    statement.close();
                }
                if(connection != null){
                    connection.close();
                }
            }
            catch(SQLException ex){
               log.log(Level.SEVERE, "Could close statement or connection " + ex.getMessage());
                throw new DAOException("Could close statement or connection", ex); 
            }
        }
    }

    @Override
    public List<OperatingSystem> getAll() throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<OperatingSystem> osList = new ArrayList<>();
        
        try{
            connection = conexion.getConnection();
            statement = connection.prepareStatement(SQL_GET_ALL);
            resultSet = statement.executeQuery();
            while(resultSet.next()){
                OperatingSystem os = new OperatingSystem(resultSet.getString(1),
                        resultSet.getString(2));
                osList.add(os);
            }
        }
        catch(SQLException ex){
            log.log(Level.SEVERE, "Could not fetch any OS ");
            throw new DAOException("Could not fetch any OS ", ex);
        } finally{
            try{
                if(resultSet != null){
                    resultSet.close();
                }
                if(statement != null){
                    statement.close();
                }
                if(connection != null){
                    connection.close();
                }
            }
            catch(SQLException ex){
               log.log(Level.SEVERE, "Could close resultSet, statement or connection " + ex.getMessage());
                throw new DAOException("CCould close resultSet, statement or connection", ex); 
            }
        }
        return osList;
    }
    
//    public static void main(String[] args) {
//        OperatingSystemDAOImpl dao = new OperatingSystemDAOImpl();
//        try {
//            List<OperatingSystem> lista = dao.getAll();
//            for(OperatingSystem os: lista){
//                System.out.println(os);
//            }
//        } catch (DAOException ex) {
//            java.util.logging.Logger.getLogger(OperatingSystemDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

}
