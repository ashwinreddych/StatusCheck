package com.ash.statuscheck.checkstatus.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Category;

import com.ash.statuscheck.jdbc.JDBCConnection;
import com.ash.statuscheck.mail.SendMail;
import com.ash.statuscheck.pojo.ProfileDetails;


public class RegisterStatus {
	
	  final Category logger = Category.getInstance(this.getClass().getName());
	 
	public boolean sendConfirmationEmail(ProfileDetails profileDetails) {
		boolean value = false;
		
		value = checkRegistrationStatus(profileDetails);
		return value;
	}
	
	private boolean checkRegistrationStatus(ProfileDetails profileDetails){
		
		boolean value = false;

		JDBCConnection db = new JDBCConnection();
		SendMail sendMail = new SendMail();

		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName(db.DriverDetails());
			conn = DriverManager.getConnection(db.URLDetails(), db.user(),
					db.pwd());
			stmt = conn.createStatement();

			String sql = " select id, email, name,status,verify_code  from ashdb.register where status = '0'"; 
			
			logger.debug(sql);
			
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				value = true;
				profileDetails.setUserId(rs.getInt("id")); 
				profileDetails.setName(rs.getString("name")); ;
				profileDetails.setStatus(rs.getString("status"));
				profileDetails.setEmail(rs.getString("email"));
				profileDetails.setVerifyCode(rs.getString("verify_code"));
				
				boolean verificationStatus =  sendMail.sendVerificationEmail(profileDetails);
				
				if(verificationStatus){
					updateStatus(profileDetails);
				}
			}
			rs.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					conn.close();
			} catch (SQLException se) {
			}// do nothing
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}// end finally try
		}// end try
		System.out.println( "Done");
		return value;
		
		
	}

	private void updateStatus(ProfileDetails profileDetails) {

		JDBCConnection db = new JDBCConnection();
		
		 Connection conn = null;
		 Statement stmt = null;
		 try{
		    Class.forName(db.DriverDetails());
		    conn = DriverManager.getConnection(db.URLDetails(), db.user(), db.pwd());
		    stmt = conn.createStatement();
		    String sql = "update ashdb.register " +
		                 "set status = '1', comments = 'Email Sent' where email = '"+profileDetails.getEmail()+"'";
		    int column = stmt.executeUpdate(sql);
		    
//		    if(column==1){
//		    	conn.commit();
//		    }else{
//		    	conn.rollback();
//		    }
//		    
		    System.out.println("Update query status : " +column);
		 }catch(SQLException se){
		    //Handle errors for JDBC
		    se.printStackTrace();
		 }catch(Exception e){
		    //Handle errors for Class.forName
		    e.printStackTrace();
		 }finally{
		    //finally block used to close resources
		    try{
		       if(stmt!=null)
		          conn.close();
		    }catch(SQLException se){
		    }// do nothing
		    try{
		       if(conn!=null)
		          conn.close();
		    }catch(SQLException se){
		       se.printStackTrace();
		    }//end finally try
		 }//end try
	}
}
