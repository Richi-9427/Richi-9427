package com.jsp.application;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/Logout")
public class Logout extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	// TODO Auto-generated method stub
//    	super.doGet(req, resp);
    	HttpSession session=req.getSession();
    	String name=(String)session.getAttribute("name");
    	PrintWriter writer=resp.getWriter();
    	if(name!=null)
    	{
    		writer.println("Thank You "+name+"for visiting the application");
    	}
    	else
    	{
    		writer.println("<h1 style='color:red;'>SessionTimeOut...</h1>");
    		session.invalidate();
    		System.out.println("session closed");
    	}
    }
}
