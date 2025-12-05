package com.driveshield.demo.Bean;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

public class Insurance {

    private String vehicleNo;
    private String make;
    private String model;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date startDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date endDate;

    private double premiumAmount;
    private String insuranceId;

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public double getPremiumAmount() {
        return premiumAmount;
    }

    public void setPremiumAmount(double premiumAmount) {
        this.premiumAmount = premiumAmount;
    }

    public String getInsuranceId() {
        return insuranceId;
    }

    public void setInsuranceId(String id) {
        this.insuranceId = id;
    }

    // Default no-argument constructor (required for JSON deserialization)
    public Insurance() {
    }

    public Insurance(String insuranceId, String vehicleNo, String make, String model, Date startDate, Date endDate,
            double premiumAmount) {
        super();
        this.vehicleNo = vehicleNo;
        this.make = make;
        this.model = model;
        this.startDate = startDate;
        this.endDate = endDate;
        this.premiumAmount = premiumAmount;
        this.insuranceId = insuranceId;
    }

}
