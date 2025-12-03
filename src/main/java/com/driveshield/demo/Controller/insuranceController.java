package com.driveshield.demo.Controller;

import com.driveshield.demo.Service.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import com.google.gson.Gson;
import com.driveshield.demo.Bean.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/vehicle")
public class insuranceController {

    private final Service service = new Service();
    private final Gson gson = new Gson();

    // Get vehicle by InsuranceId
    @RequestMapping(value = "/getVehicle/{InsuranceId}", method = RequestMethod.GET)
    public String getVehicleById(@PathVariable String InsuranceId) {
        Insurance insurance = service.searchVehicle(InsuranceId);
        return gson.toJson(insurance);
    }

    // Get vehicle history by vehicle number
    @RequestMapping(value = "/getHistory/{vehicleNo}", method = RequestMethod.GET)
    public String getVehicleHistory(@PathVariable String vehicleNo) {
        ArrayList<Insurance> history = service.viewHistory(vehicleNo);
        return gson.toJson(history);
    }

    // Add a new vehicle insurance
    @RequestMapping(value = "/addInsurance", method = RequestMethod.POST)
    public String addInsurance(@RequestBody Insurance insurance) {
        // Debug logging
        System.out.println("Received Insurance Data:");
        System.out.println("Vehicle No: " + insurance.getVehicleNo());
        System.out.println("Make: " + insurance.getMake());
        System.out.println("Model: " + insurance.getModel());
        System.out.println("Start Date: " + insurance.getStartDate());
        System.out.println("End Date: " + insurance.getEndDate());
        System.out.println("Premium Amount: " + insurance.getPremiumAmount());

        String result = service.insertInsurance(insurance);
        String message = result.equalsIgnoreCase("Failed to add insurance") ? "Failed to add insurance" : result;
        return gson.toJson(message);
    }

}
