package com.energymanagement.model;

public class EnergyTransactionModel {
    private String transactionId;  // Unique identifier for transaction
    private String userId;         // User ID (starts with "0x" + 8-digit unique code)
    private double energyUnits;    // Energy Units being transacted
    private String tokenType;      // Token used for payment (e.g., ETH, BTC)
    private double paymentAmount;  // Payment amount in specified token
    private String energySource;   // Source of energy (e.g., Solar, Nuclear, Wind)
    private String energyType;     // Type of energy (e.g., Electricity, Heat)
    private String location;       // Location of transaction
    
    public EnergyTransactionModel(){
    
    }
    // Constructor
    public EnergyTransactionModel(String transactionId, String userId, double energyUnits, 
                             String tokenType, double paymentAmount, String energySource, 
                             String energyType, String location) {
        this.transactionId = transactionId;
        this.userId = userId;
        this.energyUnits = energyUnits;
        this.tokenType = tokenType;
        this.paymentAmount = paymentAmount;
        this.energySource = energySource;
        this.energyType = energyType;
        this.location = location;
    }

    // Getters and Setters
    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public double getEnergyUnits() {
        return energyUnits;
    }

    public void setEnergyUnits(double energyUnits) {
        this.energyUnits = energyUnits;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getEnergySource() {
        return energySource;
    }

    public void setEnergySource(String energySource) {
        this.energySource = energySource;
    }

    public String getEnergyType() {
        return energyType;
    }

    public void setEnergyType(String energyType) {
        this.energyType = energyType;
    }


    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
