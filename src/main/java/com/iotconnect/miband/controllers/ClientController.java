package com.iotconnect.miband.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iotconnect.miband.models.Client;
import com.iotconnect.miband.models.Heartbeat;
import com.iotconnect.miband.models.Temperature;
import com.iotconnect.miband.models.TokenUtil;
import com.iotconnect.miband.service.IClientService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
public class ClientController {
	private final TokenUtil tokenUtil;
	private IClientService serviceClient;
        

	public ClientController(IClientService sc, TokenUtil tokenUtil) {
		this.serviceClient = sc;
                this.tokenUtil = tokenUtil;

	}

	@PostMapping("/saveClient")
	public ResponseEntity<String>  saveClient(@RequestBody Client p) {
		return new ResponseEntity<>(serviceClient.add(p) , HttpStatus.OK);
	}

	// $$$$$$$$$$$$$$$$$$$$$$$$$&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&

	@PutMapping("/updateClient/{id}")
	public ResponseEntity<String> updateClient(@PathVariable("id") Long id, @RequestBody Client p) {
		 return new ResponseEntity<>(serviceClient.update(id, p), HttpStatus.OK);
	}

	// $$$$$$$$$$$$$$$$$$$$$$$$$&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&

	@GetMapping("/listClients")
	public List<Client> listClients() {
		return serviceClient.getAll();
	}

	// $$$$$$$$$$$$$$$$$$$$$$$$$&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&

	@DeleteMapping("/deleteClient/{id}")
	public ResponseEntity<String> deleteClient(@PathVariable("id") Long id) {
		return new ResponseEntity<>("the client "+serviceClient.delete(id).getNom()+" is deleted successfully " , HttpStatus.OK);
	}
	
	// $$$$$$$$$$$$$$$$$$$$$$$$$&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&

	@GetMapping("/getHeartbeatsByClient/{id}")
	public List<Heartbeat> getHeartbeatsByClient(@PathVariable Long id , @RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "2") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy) {
		return serviceClient.getHeartbeatsByClient(id , pageNo, pageSize, sortBy);
	}
        
        @GetMapping("/getTemperaturesByClient/{id}")
	public List<Temperature> getTemperaturesByClient(@PathVariable Long id , @RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "2") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy) {
		return serviceClient.getTemperaturesByClient(id , pageNo, pageSize, sortBy);
	}

	// $$$$$$$$$$$$$$$$$$$$$$$$$&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&

	@GetMapping("/getClientById/{id}")
	public Client getClientById(@PathVariable Long id) {
		return serviceClient.getClientById(id);
	}

        @PostMapping("/login")
        public ResponseEntity<Map<String, Object>> login(@RequestBody Client request) {
            String mac = request.getMac();
            String password = request.getPassword(); 
            Client client = serviceClient.getByMac(mac);

            // Create a map to store the response data
            Map<String, Object> response = new HashMap<>();

            // Check if the login is successful
            if (client != null && (client.getPassword() == null ? password == null : client.getPassword().equals(password))) {
                // Generate JWT token
                String token = generateJwtToken(mac);

                // Add the message, client object, and token to the response
                response.put("message", "Login successful");
                response.put("client", client);
                response.put("token", token);

                // Return the response with HTTP status 200 OK
                return ResponseEntity.ok(response);
            } else {
                // Add the error message to the response
                
                response.put("message", "Adresse Mac or Password Incorrect");

                // Return the response with HTTP status 401 Unauthorized
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }
        }

        private String generateJwtToken(String mac) {
        // Generate a secure key for HS256 algorithm
        byte[] keyBytes = Keys.secretKeyFor(SignatureAlgorithm.HS256).getEncoded();

        // Generate JWT token with the given MAC as the subject and the secure key
        return Jwts.builder()
                .setSubject(mac)
                // Sign the token with the secure key
                .signWith(SignatureAlgorithm.HS256, keyBytes)
                .compact();
}
        
}
