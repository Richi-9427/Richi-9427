package com.jsp.application;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/VerifyEmail")
public class VerifyEmail extends HttpServlet
{
  @Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	// TODO Auto-generated method stub
	
	  String mailid=req.getParameter("email");
	  String url="jdbc:mysql://localhost:3306?user=root&password=12345";
	  String query="select * from richi_database.user_info where user_Email=?";
	  try {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection(url);
		PreparedStatement ps=con.prepareStatement(query);
		ps.setString(1, mailid);
		ResultSet rs=ps.executeQuery();
		PrintWriter writer=resp.getWriter();
		if(rs.next())
		{
			String name=rs.getString("user_Name");
			int password=rs.getInt("user_password");
			String mail=rs.getString("user_Email");
			String mobNo=rs.getString("user_MbNo");
			System.out.println("data retrived from the database");
			HttpSession session=req.getSession();
			System.out.println("Session object acessed from jee container");
			session.setAttribute("name", name);
			session.setAttribute("pwd", password);
			session.setAttribute("mail", mail);
			session.setAttribute("mob", mobNo);
			System.out.println("user data stored in session Object");
			session.setMaxInactiveInterval(10);
			RequestDispatcher rd=req.getRequestDispatcher("VerifyPassword.html");
			rd.include(req, resp);
			writer.println("Email Verified");
		}
		else
		{
			RequestDispatcher rd=req.getRequestDispatcher("VerifyEmail.html");
			rd.include(req, resp);
			writer.println("Invalid Email");
		}
		con.close();
		} 
	  catch (Exception e)
	  {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  
}
}
