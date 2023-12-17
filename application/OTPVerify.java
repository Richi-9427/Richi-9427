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
@WebServlet("/OTPVerify")
public class OTPVerify extends HttpServlet
{
     @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
    	 String temp=req.getParameter("otp");
    	 Integer num=Integer.parseInt(temp);
    	 System.out.println("user enter otp"+num);
    	 HttpSession session=req.getSession();
    	 Integer otp=(Integer)session.getAttribute("otp");
    	 System.out.println("actual otp number "+otp);
     	 String name=(String)session.getAttribute("name");
     	 String mobNo=(String)session.getAttribute("mbno");
     	//String otpno=(String)session.getAttribute("otp");
     	PrintWriter writer=resp.getWriter();
     	if(num.equals(otp))
     	{
     		writer.println("Thank You "+name+" and "+mobNo+" OTP verfication Successfully");
     	}
     	else
     	{
     		writer.println("<h1 style='color:red;'>SessionTimeOut...</h1>");
     		session.invalidate();
     		RequestDispatcher rd=req.getRequestDispatcher("Mobile.html");
     		rd.include(req, resp);
     		System.out.println("session closed");
     	}
    }
}
