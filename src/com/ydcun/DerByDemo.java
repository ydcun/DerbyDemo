package com.ydcun;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author ydcun
 *Derby数据库   操作的demo
 */
public class DerByDemo {
	private static String driver = "org.apache.derby.jdbc.EmbeddedDriver";//加载驱动
	private static String protocol = "jdbc:derby:";		
	static String dbName = DerByDemo.class.getResource("/fri").getPath().substring(1);  //fri数据库文件夹位置path

	static void loadDriver() {
		try {
			Class.forName(driver).newInstance();
			System.out.println("Loaded the appropriate driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void doIt() {
		Connection conn = null;
		Statement s = null;
		ResultSet rs = null;
		System.out.println("starting");
		try {
			conn = DriverManager.getConnection(protocol + dbName+";create=true");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Connected to and created database " + dbName);
		try {
			s = conn.createStatement();
			rs = s.executeQuery("select * from fir");
			while (rs.next()) {
				System.out.println("id:"+rs.getInt(1));
				System.out.println("name:"+rs.getString(2));
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {
			conn.close();
			conn = null;
			s.close();
			s = null;
			rs.close();
			rs = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		DerByDemo t = new DerByDemo();
		t.loadDriver();
		t.doIt();
		System.out.println("数据库路径：="+DerByDemo.class.getResource("/fri").getPath().substring(1));
	}
}