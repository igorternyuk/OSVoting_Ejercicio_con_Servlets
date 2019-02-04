/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import com.sun.istack.internal.logging.Logger;
import dao.exceptions.DAOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import models.OSRegisterCount;

/**
 * 
 * @author Igor Ternyuk <xmonad100 at gmail.com>
 */
public class OSRegisterCountDAOImpl implements OSRegisterCountDAO{
    private static final String SQL_COUNT_VOTES = "SELECT * FROM countVotes;";
    private static final Logger log = Logger.getLogger(OSRegisterCountDAOImpl.class);
    private ConectionFactory connectionFactory = new ConectionFactory();
    
    @Override
    public List<OSRegisterCount> getRegisterCounts() throws DAOException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        List<OSRegisterCount> list = new ArrayList<>();
        try{
            connection = connectionFactory.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQL_COUNT_VOTES);
            while (resultSet.next()) {
                String osName = resultSet.getString(1);
                int voteCount = resultSet.getInt(2);
                list.add(new OSRegisterCount(osName, voteCount));                
            }            
        } catch(SQLException ex){
            log.log(Level.SEVERE, "Error during vote counting " + ex.getMessage());
            throw new DAOException("Error during vote counting ", ex);
        } finally {
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
            } catch(SQLException ex){
                log.log(Level.SEVERE, "Could not close result set, statement or connection " + ex.getMessage());
                throw new DAOException("Error during vote counting ", ex);
            }
        }
        return list;
    }

}
