package com.jsp.application;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/VerifyMobile")
public class VerifyMobile extends HttpServlet
{
 @Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	// TODO Auto-generated method stub
	//super.doPost(req, resp);
	 String mbno=req.getParameter("mob");
	 //
	 String url="jdbc:mysql://localhost:3306?user=root&password=12345";
	 String query="select * from richi_database.user_info where user_MbNo=?";
	 try {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection(url);
		PreparedStatement ps=con.prepareStatement(query);
		ps.setString(1, mbno);
		ResultSet rs=ps.executeQuery();
		PrintWriter writer=resp.getWriter();
		if(rs.next())
		{
			String name=rs.getString("user_Name");
			String mobNo=rs.getString("user_MbNo");
			Random ran=new Random();
			int num=ran.nextInt(10000);
			System.out.println("number is :"+num);
			if(num<1000)
			{
				num+=1000;
			}
			
			HttpSession session=req.getSession();
			session.setAttribute("name", name);
			session.setAttribute("mbno", mobNo);
			session.setAttribute("otp", num);
			System.out.println("user data stored in session Object");
			session.setMaxInactiveInterval(10);
			RequestDispatcher rd=req.getRequestDispatcher("OTP.html");
			rd.include(req, resp);
			writer.println("Otp Number ="+num);
			writer.println("");
			writer.println("Mobile Number Verified");
			
			
		}
		else
		{
			RequestDispatcher rd=req.getRequestDispatcher("Mobile.html");
			rd.include(req, resp);
			writer.println("Mobile Number is Invalid");
			
		}
		con.close();
	} catch (ClassNotFoundException | SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
