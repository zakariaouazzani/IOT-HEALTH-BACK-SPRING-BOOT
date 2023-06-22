/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iotconnect.miband.controllers;

import com.iotconnect.miband.models.Temperature;
import com.iotconnect.miband.models.TemperatureClient;
import com.iotconnect.miband.service.ITemperatureService;
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author zakar
 */

@CrossOrigin(origins = "*")
@RestController
public class TemperatureController {
    private ITemperatureService temperatureService ;

    public TemperatureController(ITemperatureService temperatureService) {
        this.temperatureService = temperatureService;
    }
    
        @PostMapping("/addTemperatureClient")
	public Temperature addTemperatureClient(@RequestBody TemperatureClient t) {
		return temperatureService.addTemperatureClient(t);
	}
        
        
        @GetMapping("/listTemperatures")
	public List<Temperature> listTemperatures() {
		return temperatureService.getAll();
	}
    
    
}
