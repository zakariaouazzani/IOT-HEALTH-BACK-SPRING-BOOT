/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iotconnect.miband.service;

import com.iotconnect.miband.dao.TemperatureRepository;
import com.iotconnect.miband.models.Client;
import com.iotconnect.miband.models.Temperature;
import com.iotconnect.miband.models.TemperatureClient;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author zakar
 */

@Transactional
@Service
public class TemperatureServiceImpl implements ITemperatureService {
    
        private TemperatureRepository  temperatureRepository;
	
	private IClientService clientService;

    public TemperatureServiceImpl(TemperatureRepository hbr , IClientService cs) {
                this.temperatureRepository = hbr;
		this.clientService = cs;
    }
        
        @Override
	public Temperature add(Temperature c) {
		return this.temperatureRepository.save(c);
	}
    
    
        @Override
	public Temperature addTemperatureClient(TemperatureClient p) {
		if(p.getMac() != null && !p.getMac().strip().isBlank() && p.getData1() != null  && !p.getData1().strip().isBlank()) {
			System.out.println("Mac : " + p.getMac());
			Client c = clientService.getByMac(p.getMac());
			if(c != null ) {
			System.out.println("Client : " + c);
			Temperature t = new Temperature();
			t.setData1(p.getData1());
			t.setDate_prelevement(p.getDate_prelevement());
			t = add(t);
			c.addTemperature(t);
			clientService.add(c);
			return t;
			}
		}
		System.out.println("Null ");
		return null;
	}

    @Override
	public List<Temperature> getAll() {
		return temperatureRepository.findAll();
	}
}
