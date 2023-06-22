/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iotconnect.miband.models;

/**
 *
 * @author zakar
 */

public class LoginResponse {
    private Client client;
    private String message;

    public LoginResponse(Client client, String message) {
        this.client = client;
        this.message = message;
    }
    

    // Getters and setters for the client and message
}
