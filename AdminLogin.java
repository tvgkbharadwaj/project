import java.io.*;
import java;
import java.util.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import DBCon.ConnectionManager;

public class AdminLogin extends HttpServlet
{
  	public void service(HttpServletRequest request,HttpServletResponse response)
		throws ServletException, IOException
	{
		HttpSession session = request.getSession();
		Connection con = null;
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();

		String username = request.getParameter("uname");
		String password = request.getParameter("pwd");
	    try
		{
			con = ConnectionManager.getConnected();
PreparedStatement ps = con.prepareStatement("select * from adminlogin where username=? and Password=?");

			ps.setString(1,username);
			ps.setString(2,password);

			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				session.setAttribute("username",username);
				response.sendRedirect("./admin_menu.jsp");

			}
			else
			{
			out.println("Authentication failed!! due to invalid login or pwd");    
		   	response.setHeader("Refresh","4;URL=./adminlogin.html");
			}
		}
		catch(Exception e)
		{
		System.out.println(e. toString());
	System.out.println("i am making modifications");
	}

	}
}
