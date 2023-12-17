package com.jsp.application;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.PreparedStatement;
@WebServlet("/VerifyPassword")
public class VerifyPassword extends HttpServlet
{
   @Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	// TODO Auto-generated method stub
	//super.doPost(req, resp);
	String password=req.getParameter("pass");
	Integer pass=Integer.parseInt(password);
	System.out.println("user password="+pass);
	//Acessess session object
	HttpSession session=req.getSession();
	//
	Integer sessionPassword=(Integer)session.getAttribute("pwd");
	System.out.println("Db password="+sessionPassword);
	PrintWriter writer=resp.getWriter();
	if(pass.equals(sessionPassword))
	{
		
		RequestDispatcher rd=req.getRequestDispatcher("Portal.html");
		rd.include(req, resp);
		writer.println("<center><h1> welcome"+(String)session.getAttribute("name")+"</h1>");
		writer.println("<h1 style='color:green;'>LoginSuccessfully...</h1></center>");
		
	}
	else
	{
		RequestDispatcher rd=req.getRequestDispatcher("VerifyEmail.html");
		rd.include(req, resp);
		writer.println("<h1 style=color:red;'>InvalidPassword...</h1>");
		
		
	}
	if(sessionPassword==null)
	{
		writer.println("<h1 style='color:red;'>please Enter valid Password...</h1>");
	}
	
	   
}
}
