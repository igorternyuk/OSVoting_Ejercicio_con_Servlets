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
import java.sql.SQLException;
import java.util.logging.Level;

/**
 * 
 * @author Igor Ternyuk <xmonad100 at gmail.com>
 */
public class VotingDAOImpl implements VotingDAO {
    private static final String SQL_VOTE = "INSERT INTO vote VALUES(UUID(),?, NOW());";
    private static final Logger log = Logger.getLogger(VotingDAOImpl.class);
    private ConectionFactory conexion = new ConectionFactory();
    
    @Override
    public void vote(String osId) throws DAOException {
        log.info("Voting for os with id = " + osId);
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            connection = conexion.getConnection();
            statement = connection.prepareStatement(SQL_VOTE);
            statement.setString(1, osId);
            statement.execute();
            log.info("Successfully voted for os with id = " + osId);
        } catch(SQLException ex){
            log.log(Level.SEVERE, "Could not vote for OS with id = " + osId + " ", ex);
            throw new DAOException("Voting error ", ex);
        } finally{
            try{
                if(statement != null){
                    statement.close();
                }
                if(connection != null){
                    connection.close();
                }
            } catch(SQLException ex){
                log.log(Level.SEVERE, "Could not close statement or connection ", ex);
                throw new DAOException("Connection closing error  ", ex);
            }
        }
    }

}
