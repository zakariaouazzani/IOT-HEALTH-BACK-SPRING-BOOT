/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.iotconnect.miband.service;

import com.iotconnect.miband.models.Temperature;
import com.iotconnect.miband.models.TemperatureClient;
import java.util.List;



public interface ITemperatureService {
                Temperature add(Temperature c);

	  	Temperature addTemperatureClient(TemperatureClient p);
                List<Temperature> getAll();

	
	 
}
