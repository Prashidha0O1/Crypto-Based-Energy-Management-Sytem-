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

    // Default Constructer
    public EnergyTransactionModel() {

    }

    /**
     * Parameterized constructor. Allows creating an instance with all
     * attributes initialized.
     *
     * @param transactionId Unique identifier for the transaction
     * @param userId Unique ID of the user involved in the transaction
     * @param energyUnits Quantity of energy units transacted
     * @param tokenType Cryptocurrency/token used for payment
     * @param paymentAmount Payment amount in the specified token
     * @param energySource Source of energy (e.g., Solar, Wind)
     * @param energyType Type of energy (e.g., Electricity, Heat)
     * @param location Geographical location of the transaction
     */
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

    /**
     * Gets the transaction ID.
     *
     * @return The unique transaction identifier.
     */
    public String getTransactionId() {
        return transactionId;
    }

    /**
     * Sets the transaction ID.
     *
     * @param transactionId The unique transaction identifier to set.
     */
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    /**
     * Gets the user ID.
     *
     * @return The user ID associated with the transaction.
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Sets the user ID.
     *
     * @param userId The user ID to set (must follow the format "0x" + unique
     * code).
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * Gets the energy units.
     *
     * @return The number of energy units transacted.
     */
    public double getEnergyUnits() {
        return energyUnits;
    }

    /**
     * Sets the energy units.
     *
     * @param energyUnits The number of energy units to set.
     */
    public void setEnergyUnits(double energyUnits) {
        this.energyUnits = energyUnits;
    }

    /**
     * Gets the token type.
     *
     * @return The type of token used for payment.
     */
    public String getTokenType() {
        return tokenType;
    }

    /**
     * Sets the token type.
     *
     * @param tokenType The type of token to set (e.g., ETH, BTC).
     */
    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    /**
     * Gets the payment amount.
     *
     * @return The payment amount in the specified token.
     */
    public double getPaymentAmount() {
        return paymentAmount;
    }

    /**
     * Sets the payment amount.
     *
     * @param paymentAmount The payment amount to set.
     */
    public void setPaymentAmount(double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    /**
     * Gets the energy source.
     *
     * @return The source of energy (e.g., Solar, Wind).
     */
    public String getEnergySource() {
        return energySource;
    }

    /**
     * Sets the energy source.
     *
     * @param energySource The source of energy to set.
     */
    public void setEnergySource(String energySource) {
        this.energySource = energySource;
    }

    /**
     * Gets the energy type.
     *
     * @return The type of energy (e.g., Electricity, Heat).
     */
    public String getEnergyType() {
        return energyType;
    }

    /**
     * Sets the energy type.
     *
     * @param energyType The type of energy to set.
     */
    public void setEnergyType(String energyType) {
        this.energyType = energyType;
    }

    /**
     * Gets the location.
     *
     * @return The geographical location of the transaction.
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets the location.
     *
     * @param location The geographical location to set.
     */
    public void setLocation(String location) {
        this.location = location;
    }
}
