package com.driveshield.demo.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.driveshield.demo.Util.*;
import com.driveshield.demo.Bean.*;

public class insuranceDao {
    public boolean insertInsurance(Insurance insurance) {
        boolean result = false;
        try {
            Connection cn = UtilsDB.getConnection();

            PreparedStatement ps = cn.prepareStatement(
                    "INSERT INTO Insurance (vehicleNo, make, model, startDate, endDate, premiumAmount, insuranceId) VALUES(?,?,?,?,?,?,?)");
            ps.setString(1, insurance.getVehicleNo());
            ps.setString(2, insurance.getMake());
            ps.setString(3, insurance.getModel());
            java.sql.Date sqlstartdate = new java.sql.Date(insurance.getStartDate().getTime());
            ps.setDate(4, sqlstartdate);
            java.sql.Date sqlenddate = new java.sql.Date(insurance.getEndDate().getTime());
            ps.setDate(5, sqlenddate);
            ps.setDouble(6, insurance.getPremiumAmount());
            ps.setString(7, insurance.getInsuranceId());
            int t = ps.executeUpdate();
            if (t > 0) {
                result = true;
            }
            UtilsDB.closeAllConnection(cn, ps, null);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public Insurance searchVehicle(String vehicleNo) {
        Insurance result = null;
        try {
            Connection cn = UtilsDB.getConnection();
            PreparedStatement ps = cn.prepareStatement("SELECT * FROM Insurance WHERE insuranceId=?");
            ps.setString(1, vehicleNo);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result = new Insurance(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getDate(5), rs.getDate(6),
                        rs.getDouble(7));
            }
            UtilsDB.closeAllConnection(cn, ps, rs);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public ArrayList<Insurance> viewHistory(String vehicleNo) {
        ArrayList<Insurance> result = new ArrayList<>();
        try {
            Connection cn = UtilsDB.getConnection();
            PreparedStatement ps = cn
                    .prepareStatement("SELECT * FROM Insurance WHERE vehicleNo=? order by endDate desc");
            ps.setString(1, vehicleNo);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result.add(
                        new Insurance(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
                                rs.getDate(5), rs.getDate(6),
                                rs.getDouble(7)));
            }
            UtilsDB.closeAllConnection(cn, ps, rs);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }
}
