/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import dao.exceptions.DAOException;
import java.util.List;
import models.OSRegisterCount;

/**
 * 
 * @author Igor Ternyuk <xmonad100 at gmail.com>
 */
public interface OSRegisterCountDAO {
    public List<OSRegisterCount> getRegisterCounts() throws DAOException;
}
