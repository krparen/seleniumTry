package com.example.demo;

import lombok.Data;

@Data
public class TicketInfo {
    private String departureDateTime;
    private String arrivalDateTime;
    private String flightLength;
    private String airline;
    private String cost;
}
