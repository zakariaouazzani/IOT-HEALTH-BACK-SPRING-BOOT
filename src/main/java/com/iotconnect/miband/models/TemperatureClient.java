/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iotconnect.miband.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;

/**
 *
 * @author zakar
 */

@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class TemperatureClient {
    @NotNull
	private String mac;
	
	@NotNull
	private String data1;
        
        @NotNull
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss[.SSS]")
	private LocalDateTime date_prelevement;

        public String getMac() {
            return mac;
        }

        public void setMac(String mac) {
            this.mac = mac;
        }

        public String getData1() {
            return data1;
        }

        public void setData1(String data1) {
            this.data1 = data1;
        }

        public LocalDateTime getDate_prelevement() {
            return date_prelevement;
        }

        public void setDate_prelevement(LocalDateTime date_prelevement) {
            this.date_prelevement = date_prelevement;
        }
        
        
}
