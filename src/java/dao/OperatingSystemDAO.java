/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.exceptions.DAOException;
import java.util.List;
import models.OperatingSystem;

/**
 *
 * @author Igor Ternyuk <xmonad100 at gmail.com>
 */
public interface OperatingSystemDAO {
    public void addNew(OperatingSystem os) throws DAOException;
    public void update(OperatingSystem os) throws DAOException;
    public OperatingSystem findById(String id) throws DAOException;
    public List<OperatingSystem> findByName(String nameFilter) throws DAOException;
    public List<OperatingSystem> getAll() throws DAOException;
    public void remove(String id) throws DAOException; 
    
}
