/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.exceptions.DAOException;

/**
 *
 * @author Igor Ternyuk <xmonad100 at gmail.com>
 */
public interface VotingDAO{
    public void vote(String osId) throws DAOException;
}
