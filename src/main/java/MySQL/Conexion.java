package MySQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexion {

	private String ip, port, user, pwd;
	private Connection conn;
	private boolean connected;

	public Conexion(String ip,String port, String user, String pwd) {
		this.ip = ip;
		this.port = port;
		this.user = user;
		this.pwd = pwd;
	}
	public boolean connect() {
		try {
			conn = DriverManager.getConnection("jdbc:mysql://"+ip+":"+port,user,pwd);
			connected = true;
			return true;
		}catch(Exception e) {
			connected = false;
		}	
		return false;
	}
	
	public boolean isConnected() {
		if (connected) {
			return true;
		} else {
			return false;
		}
	}
	public String createDatabaseIfNotExists() {
		try {
			Statement statement = conn.createStatement();
			String sql = "CREATE DATABASE IF NOT EXISTS SSIG";
			
			if (statement.executeUpdate(sql) == 1) {

				// Aqui ejecuto el codigo para crear tablas
				sql = "USE SSIG;";
				System.out.println("Usar ssig = "+statement.executeUpdate(sql));
						
		        sql = "CREATE TABLE usuario ("
		                    + "id INT PRIMARY KEY AUTO_INCREMENT,"
		                    + "username VARCHAR(50) NOT NULL,"
		                    + "name VARCHAR(63),"
		                    + "bio VARCHAR(150),"
		                    + "url VARCHAR(100),"
		                    + "followers VARCHAR(5),"
		                    + "following VARCHAR(5),"
		                    + "publicaciones VARCHAR(5)"
		                    + "isFollowedBy VARCHAR(5)"
		                    + "fecha DATETIME"
		                    + ")";
		        
		        System.out.println("Creacion tabla = "+statement.executeUpdate(sql));
				 
				statement.close();
				return "SSIG DATABASE CREATED";
			} else {
				statement.close();
				return "CONNECTED TO DATABASE SSIG";
			}
		} catch (SQLException e) {  
			return "ERROR CREATING DATABASE SSIG"+e.getMessage();
		}
	}
}
