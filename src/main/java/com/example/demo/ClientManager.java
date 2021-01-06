package com.example.demo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.demo.dto.Clients;
import com.example.demo.dto.User;

import connection.DbConnection;

@WebServlet("/client-manager")
public class ClientManager extends HttpServlet{
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		int userAction=Integer.parseInt(request.getParameter("userAction"));
		
		switch(userAction) {
		case 1:
			saveUser(request, response);
			break;
		
		case 2:
			validateUser(request, response);
			break;
			
		case 3:
			getUserHistory(request, response);
			break;
		case 4:
			saveComposeSms(request, response);
			break;
		}
		
		
	}
	
	private void saveComposeSms(HttpServletRequest request, HttpServletResponse response) {
		int userId = Integer.parseInt(request.getParameter("user_id"));
		String senderId = request.getParameter("sender_id");
		String contact = request.getParameter("contact");
		String message = request.getParameter("message");
		
		Clients c = new Clients();
		c.setUserId(userId);
		c.setSender_details(senderId);
		c.setMessage(message);
		
		saveIntoDb(c);
		
		
	}

	private void getUserHistory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String userId = request.getParameter("user_id");
		List<Clients> list = getAllUserData(userId);
		
		request.setAttribute("list", list);
		
		request.getRequestDispatcher("/UserHistory.jsp").forward(request, response);
	}

	private List<Clients> getAllUserData(String userId) {
		List<Clients> list = new ArrayList<Clients>();
		Connection conn=null;
		Statement pstmt=null;
		ResultSet rs = null;
		try {
			conn = DbConnection.getInstance().getConnection();
			String sql = "select * from clients where user_id="+Integer.parseInt(userId);
			pstmt = conn.createStatement();
			rs = pstmt.executeQuery(sql);
			while(rs.next()) {
				Clients c = new Clients();
				c.setId(rs.getInt("id"));
				c.setSender_details(rs.getString("sender_details"));
				c.setMessage(rs.getString("message"));
				c.setStatus(rs.getString("status"));
				
				list.add(c);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(conn!=null) {
					conn.close();
				}if(rs!=null) {
					rs.close();
				}if(pstmt!=null) {
					pstmt.close();
				}
			} catch (Exception e2) {
				// TODO: handle exception
			e2.printStackTrace();
			}
			return list;
		} 

		
	}

	private void validateUser(HttpServletRequest request, HttpServletResponse response) {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		User u = validate(email, password);
		request.getSession().setAttribute("user", u);
		if(u.getName() == null) {
			// user is not exist
		} else {
			//move to dashboad
		}
		
		
		
	}
	
	public User validate(String email, String password) {
		User u = new User();
		Connection conn=null;
		Statement pstmt=null;
		ResultSet rs = null;
		try {
			conn = DbConnection.getInstance().getConnection();
			String sql = "select * from user where email = '"+email+"' and password = '"+password+"'  ";
			pstmt = conn.createStatement();
			rs = pstmt.executeQuery(sql);
			while(rs.next()) {
				
				u.setId(rs.getInt("id"));
				u.setName(rs.getString("name"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(conn!=null) {
					conn.close();
				}if(rs!=null) {
					rs.close();
				}if(pstmt!=null) {
					pstmt.close();
				}
			} catch (Exception e2) {
				// TODO: handle exception
			e2.printStackTrace();
			}
			return u;
		}
		
	}

	private void saveUser(HttpServletRequest request, HttpServletResponse response) {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String mobile = request.getParameter("mobile");
		String password = request.getParameter("password");
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		try {
			Calendar cal = Calendar.getInstance();
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
	        String submission_time = String.valueOf(sdf.format(cal.getTime()));
	        System.out.println("submission_time=>>"+submission_time);
	        
			conn = DbConnection.getInstance().getConnection();
			String sql = "insert into user (name, email, mobile, password, submission_date) values (?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, email);
			pstmt.setString(3, mobile);
			pstmt.setString(4, password);
			pstmt.setString(5, submission_time);
			pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(conn!=null) {
					conn.close();
				}if(rs!=null) {
					rs.close();
				}if(pstmt!=null) {
					pstmt.close();
				}
			} catch (Exception e2) {
				// TODO: handle exception
			e2.printStackTrace();
			}
			}
		
	}

	public void saveIntoDb(Clients c) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		try {
			Calendar cal = Calendar.getInstance();
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
	        String submission_time = String.valueOf(sdf.format(cal.getTime()));
	        System.out.println("submission_time=>>"+submission_time);
	        
			conn = DbConnection.getInstance().getConnection();
			String sql = "insert into clients (sender_details, message, status, submission_date, user_id) values (?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, c.getSender_details());
			pstmt.setString(2, c.getMessage());
			pstmt.setString(3, "1");
			pstmt.setString(4, submission_time);
			pstmt.setInt(4, c.getUserId());
			pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(conn!=null) {
					conn.close();
				}if(rs!=null) {
					rs.close();
				}if(pstmt!=null) {
					pstmt.close();
				}
			} catch (Exception e2) {
				// TODO: handle exception
			e2.printStackTrace();
			}finally {
				try {
					if(conn!=null) {
						conn.close();
					}if(rs!=null) {
						rs.close();
					}if(pstmt!=null) {
						pstmt.close();
					}
				} catch (Exception e2) {
					// TODO: handle exception
				e2.printStackTrace();
				}
				}
		} 
		
	}
	
	public List<Clients> getPendingMessages() {
		List<Clients> clientList = new ArrayList<Clients>();
		Connection conn=null;
		Statement pstmt=null;
		ResultSet rs = null;
		try {
			conn = DbConnection.getInstance().getConnection();
			String sql = "select * from clients where status = 1";
			pstmt = conn.createStatement();
			rs = pstmt.executeQuery(sql);
			while(rs.next()) {
				Clients c = new Clients();
				c.setSender_details(rs.getString("sender_details"));
				c.setMessage(rs.getString("message"));
				
				clientList.add(c);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(conn!=null) {
					conn.close();
				}if(rs!=null) {
					rs.close();
				}if(pstmt!=null) {
					pstmt.close();
				}
			} catch (Exception e2) {
				// TODO: handle exception
			e2.printStackTrace();
			}
			return clientList;
		} 
	}

}
