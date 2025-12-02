package com.driveshield.demo.Service;

import java.sql.SQLException;
import java.util.*;
import java.util.random.*;

import com.driveshield.demo.Bean.*;
import com.driveshield.demo.Dao.*;

public class Service {
	adminDao dao = new adminDao();

	public ArrayList<UserWriter> getAll() {
		return dao.getUserWriter();
	}

	// Method to add a new UserWriter
	public boolean addUserWriter(UserWriter userWriter) {
		String RandomId = randomUserIdGenrator(userWriter.getName());
		userWriter.setUnderwriterId(RandomId);
		userWriter.setPassword("Employee@12");
		return dao.insertUnderWriter(userWriter);
	}

	public String randomUserIdGenrator(String name) {
		// Ensure the name has at least 4 characters
		String prefix = name.length() >= 4 ? name.substring(0, 4) : name;
		// Generate 6 random digits
		Random random = new Random();
		int randomDigits = 100000 + random.nextInt(900000); // Ensures 6-digit random number
		// Concatenate prefix and random digits
		return prefix + randomDigits;
	}

	public boolean deleteByUID(String Uid) {
		boolean id = dao.deleteUnderWriter(Uid);
		System.out.println(Uid);
		return id;

	}

	public UserWriter searchByID(String Uid) {
		UserWriter cust = dao.getUserWriterById(Uid);
		return cust;
	}

	// public boolean getservice(String underwriterId, String password) {
	// try {
	// return dao.getdetail(underwriterId, password);
	//
	// } catch (SQLException e) {
	// e.printStackTrace();
	// }
	// return false;
	// }

	public ArrayList<Insurance> viewHistory(String vehicleNo) {
		insuranceDao dao = new insuranceDao();
		return dao.viewHistory(vehicleNo);
	}

	public Insurance searchVehicle(String vehicleNo) {
		insuranceDao dao = new insuranceDao();
		return dao.searchVehicle(vehicleNo);
	}

	public String insertInsurance(Insurance insurance) {
		insuranceDao dao = new insuranceDao();
		Random rand = new Random();
		long min = 1000000000L;
		long max = 9999999999L;
		long randomNumber = rand.nextLong(max - min + 1) + min;
		String id = String.valueOf(randomNumber);
		insurance.setInsuranceId(id);
		boolean resultFromDao = dao.insertInsurance(insurance);
		String result = "";
		if (resultFromDao == false) {
			result += "Failed to add insurance";
		} else {
			result = id;
		}
		return result;
	}

	public UserWriter login(String username, String password) {
		return dao.validateUser(username, password);
	}

	public boolean checkCurrentPassword(String underWriterId, String currentPassword) {
		adminDao dao = new adminDao();
		boolean result = false;
		result = dao.checkCurrentPassword(underWriterId, currentPassword);
		return result;
	}

	public boolean updateUnderWriterPassword(String underWriterId, String newPassword) {
		adminDao dao = new adminDao();
		boolean result = false;
		result = dao.updateUnderWriterPassword(underWriterId, newPassword);
		return result;
	}

}
