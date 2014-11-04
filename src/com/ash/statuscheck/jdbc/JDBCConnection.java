package com.ash.statuscheck.jdbc;

//26383
public class JDBCConnection {
	
	public String DriverDetails(){
		return "com.mysql.jdbc.Driver";
	}
	public String URLDetails(){
			return "jdbc:mysql://127.0.0.1:3306/ashDB";
	}
	public String user(){
		return "a_usr";
	}
	public String pwd(){
		return "ashdeeee";
}

}
