<%@page import="dao.OSRegisterCountDAOImpl"%>
<%@page import="models.OSRegisterCount"%>
<%@page import="dao.OSRegisterCountDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.exceptions.DAOException"%>
<%@page import="java.util.List"%>
<%@page import="models.OperatingSystem"%>
<%@page import="dao.OperatingSystemDAOImpl"%>
<%@page import="dao.OperatingSystemDAO"%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>TODO supply a title</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <h1>Choose your OS:</h1>
        <form action="votar.do" method="post">
            <%
                OperatingSystemDAO dao = new OperatingSystemDAOImpl();
                List<OperatingSystem> osList = new ArrayList<>();
                try{
                    osList = dao.getAll();
                } catch(DAOException ex){
                    out.println(ex.getMessage());
                }
                for(OperatingSystem os: osList){
                    out.println("<input type='checkbox' name='checkOS' value='" + os.getId() + "'>"+ os.getName() +"<br>");
                }
                
            %>
            
            <br><input type="submit" name="btnVote" value="Vote"/><br>
            
            <%
                List<OSRegisterCount> osRegisters = new ArrayList<>();
                try{
                    OSRegisterCountDAO OSRegisterCountDAO = new OSRegisterCountDAOImpl();
                    osRegisters = OSRegisterCountDAO.getRegisterCounts();
                } catch(DAOException ex){
                    out.println(ex.getMessage());
                }
            %>
            <br>
            <p>Votes:</p>
            <table border="1">
                <tr>
                    <th>OS</th>
                    <th>Votes</th>
                </tr>
                <% for(OSRegisterCount osRegister: osRegisters){ %>
                <tr>
                    <td><%= osRegister.getOsName() %></td>
                    <td><%= osRegister.getVoteCount() %></td>
                </tr>    
                <%}%>
                
            </table>
        </form>
            
        
    </body>
</html>
