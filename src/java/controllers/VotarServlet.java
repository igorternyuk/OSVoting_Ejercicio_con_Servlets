/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import dao.OSRegisterCountDAO;
import dao.OSRegisterCountDAOImpl;
import dao.VotingDAO;
import dao.VotingDAOImpl;
import dao.exceptions.DAOException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.OSRegisterCount;

/**
 * 
 * @author Igor Ternyuk <xmonad100 at gmail.com>
 */

@WebServlet(name = "VotarServlet", urlPatterns = {"/votar.do" })
public class VotarServlet extends HttpServlet{
    
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res){
        processRequest(req, res);
    }
    
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res){
        processRequest(req, res);
    }
    
    private void processRequest(HttpServletRequest req, HttpServletResponse res){
        res.setContentType("text/html;charset=UTF-8");
        PrintWriter out;
        VotingDAO votingDAO = new VotingDAOImpl();
        
        try {
            out = res.getWriter();
            String[] parameterValues = req.getParameterValues("checkOS");
            for (String parameterValue : parameterValues) {
                votingDAO.vote(parameterValue);
            }
            
            req.getRequestDispatcher("index.jsp").forward(req, res);
        } catch (IOException | ServletException | DAOException ex) {
            Logger.getLogger(VotarServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        

    }
}
