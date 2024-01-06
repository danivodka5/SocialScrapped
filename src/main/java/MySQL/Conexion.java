package MySQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
				
				sql = "USE SSIG;";
				statement.executeUpdate(sql);

				// CRUD
		        sql = "CREATE TABLE personas("
		        		+ "idp INT PRIMARY KEY,"
		        		+ "nombre VARCHAR(50)"
		        		+ ");";
		        
		        statement.executeUpdate(sql);
		        
		        // Users Table
		        sql = "CREATE TABLE usuarios ("
		                    + "idp INT NOT NULL,"
		                    + "username VARCHAR(50) NOT NULL,"
		                    + "name VARCHAR(63),"
		                    + "bio VARCHAR(150),"
		                    + "url VARCHAR(100),"
		                    + "followers VARCHAR(5),"
		                    + "following VARCHAR(5),"
		                    + "publicaciones VARCHAR(5),"
		                    + "isFollowedBy VARCHAR(5),"
		                    + "fecha DATETIME,"
		                    + "FOREIGN KEY (id) REFERENCES personas(idp)"
		                    + ")";
		        
		        statement.executeUpdate(sql);
		        
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
	
	public boolean doesUserExists(String username) {
		System.out.println("---- Iniciando el metodo doesUserExists() ----");
		
		String sql = "SELECT IF( EXISTS( SELECT * FROM usuarios WHERE username = ?),1,0);";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				System.out.println("1 existe 0 no ="+rs.getInt(1));
				if (rs.getInt(1) == 0) {
					return false;
				} else {
					return true;
				}
			}
		} catch (SQLException e) { e.printStackTrace(); }
		
		return false;
	}
	public String createUser(String username) {
		System.out.println("---- Iniciando el metodo createUser() ----");
		
		String sql = "INSERT INTO usuarios(username) values(?)";
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			
			int res = ps.executeUpdate();
			
			return "cu1 Valor = "+res;

		} catch (SQLException e) { 
			e.printStackTrace(); 
			return e.getMessage(); 
		}
	}	
}
