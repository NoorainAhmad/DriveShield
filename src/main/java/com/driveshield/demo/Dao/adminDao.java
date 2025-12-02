package com.driveshield.demo.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import com.driveshield.demo.Util.*;
import com.driveshield.demo.Bean.*;

public class adminDao {

	public boolean insertUnderWriter(UserWriter userWriter) {
		boolean result = false;
		Connection conn = UtilsDB.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement("INSERT INTO REGISTER VALUES(?,?,?,?,?,?,?,?,?)");
			ps.setString(1, userWriter.getName());
			java.sql.Date dob = new java.sql.Date(userWriter.getDate_of_birth().getTime());
			ps.setDate(2, dob);
			String gender = "";
			if (userWriter.getGender().equalsIgnoreCase("male")) {
				gender = "M";
			} else if (userWriter.getGender().equalsIgnoreCase("female")) {
				gender = "F";
			} else {
				gender = "other";
			}
			ps.setString(3, gender);

			ps.setString(4, userWriter.getAddress());
			java.sql.Date doj = new java.sql.Date(userWriter.getDateOfJoin().getTime());
			ps.setDate(5, doj);
			ps.setString(6, userWriter.getUnderwriterId());
			ps.setString(7, userWriter.getPassword());
			ps.setBoolean(8, userWriter.isAdmin());
			ps.setBoolean(9, userWriter.isIsdeleted());

			int t = ps.executeUpdate();
			if (t > 0) {
				result = true;
			}
			UtilsDB.closeAllConnection(conn, ps, null);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return result;
	}

	public boolean deleteUnderWriter(String Uid) {
		Connection conn = UtilsDB.getConnection();
		boolean UID = false;
		try {
			PreparedStatement ps = conn.prepareStatement("UPDATE Register SET isdeleted= ? WHERE UnderwriterId =?");
			ps.setBoolean(1, true);
			ps.setString(2, Uid);
			int t = ps.executeUpdate();
			if (t > 0) {

				UID = true;
			}
			UtilsDB.closeAllConnection(conn, ps, null);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return UID;
	}

	public UserWriter getUserWriterById(String uwId) {
		UserWriter userWriter = null;
		Connection conn = UtilsDB.getConnection();
		try {
			PreparedStatement ps = conn
					.prepareStatement("SELECT * FROM Register WHERE UnderwriterId = ? and isdeleted=?");
			ps.setString(1, uwId);
			ps.setBoolean(2, false);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String id = rs.getString("UnderwriterId");
				String name = rs.getString("name");
				Date dob = rs.getDate("DOB");
				String gender = rs.getString("gender");
				String address = rs.getString("address");
				Date doj = rs.getDate("DOJ");
				userWriter = new UserWriter(id, name, dob, gender, address, doj);
			}
			UtilsDB.closeAllConnection(conn, ps, rs);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return userWriter;
	}

	public UserWriter validateUser(String username, String password) {
		UserWriter userWriter = null;
		Connection conn = UtilsDB.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(
					"SELECT * FROM Register WHERE underwriterId = ? AND password = ? AND isdeleted = ?");
			ps.setString(1, username);
			ps.setString(2, password);
			ps.setBoolean(3, false);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				userWriter = new UserWriter(rs.getString("name"), rs.getString("underwriterId"),
						rs.getString("password"), rs.getBoolean("isAdmin"));
			}
			UtilsDB.closeAllConnection(conn, ps, rs);
		} catch (SQLException e) {
			System.out.println("Error.....");
			System.out.println(e.getMessage());
		}
		return userWriter;
	}

	public boolean checkCurrentPassword(String underWriterId, String currentPassword) {
		boolean result = false;
		Connection conn = UtilsDB.getConnection();

		try {
			PreparedStatement ps = conn.prepareStatement("select password from Register where underwriterId=?");
			ps.setString(1, underWriterId);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				String storedPassword = rs.getString("password");
				result = currentPassword.equals(storedPassword);
			}
			UtilsDB.closeAllConnection(conn, ps, rs);
		} catch (SQLException e) {
			System.out.println("Error.....");
			System.out.println(e.getMessage());
		}

		return result;
	}

	public boolean updateUnderWriterPassword(String underWriterId, String newPassword) {

		boolean result = false;
		Connection conn = UtilsDB.getConnection();

		try {
			PreparedStatement ps = conn.prepareStatement("update Register set password=? where underwriterId=?");
			ps.setString(1, newPassword);
			ps.setString(2, underWriterId);
			int res = ps.executeUpdate();
			if (res == 1) {
				result = true;
			}

			UtilsDB.closeAllConnection(conn, ps, null);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return result;
	}

	public ArrayList<UserWriter> getUserWriter() {
		ArrayList<UserWriter> user = new ArrayList<>();
		Connection conn = UtilsDB.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM Register");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String id = rs.getString("UnderwriterId");
				String name = rs.getString("name");
				Date dob = rs.getDate("DOB");
				String gender = rs.getString("gender");
				String address = rs.getString("address");
				Date doj = rs.getDate("DOJ");
				UserWriter userWriter = new UserWriter(id, name, dob, gender, address, doj);
				user.add(userWriter);
			}
			UtilsDB.closeAllConnection(conn, ps, rs);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return user;
	}

}
