/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.energymanagement.util;

/**
 *
 * @author Prashidha
 */
import java.util.regex.Pattern;
import javax.swing.JTextField;

public class ValidationUtil {

    /**
     * Validates if a string is alphanumeric (letters and numbers only).
     * @param input the string to validate.
     * @return true if the input is alphanumeric, false otherwise.
     */
    public static boolean isAlphanumeric(String input) {
        return input != null && input.matches("^[a-zA-Z0-9]+$");
    }
    
    public static boolean isInputValid(JTextField transactionIdField, 
                                       JTextField userIdField, 
                                       JTextField energyUnitsField, 
                                       JTextField paymentAmountField, 
                                       JTextField locationField) {
        // Check for empty fields
        if (transactionIdField.getText().trim().isEmpty() ||
            userIdField.getText().trim().isEmpty() ||
            energyUnitsField.getText().trim().isEmpty() ||
            paymentAmountField.getText().trim().isEmpty() ||
            locationField.getText().trim().isEmpty()) {
            return false; // If any field is empty, return false
        }

        // Check if energy units and payment amount are valid numbers
        try {
            Double.parseDouble(energyUnitsField.getText().trim());
            Double.parseDouble(paymentAmountField.getText().trim());
        } catch (NumberFormatException e) {
            return false; // If parsing fails, return false
        }

        return true; // All fields are valid
    }
}

    /**
     * Validates if a UserID starts with "0x" followed by 8 alphanumeric characters.
     * @param userId the UserID to validate.
     * @return true if the UserID is valid, false otherwise.
     */
    public static boolean isValidUserID(String userId) {
        return userId != null && userId.matches("^0x[a-zA-Z0-9]{8}$");
    }

    /**
     * Validates if the transaction ID is alphanumeric.
     * @param transactionId the transaction ID to validate.
     * @return true if the transaction ID is valid, false otherwise.
     */
    public static boolean isValidTransactionID(String transactionId) {
        return isAlphanumeric(transactionId);
    }

    /**
     * Validates if the energy amount is a positive number (e.g., "100", "5.5").
     * @param amount the energy amount to validate.
     * @return true if the amount is valid, false otherwise.
     */
    public static boolean isValidEnergyAmount(String amount) {
        return amount != null && Pattern.matches("^[0-9]+(\\.[0-9]{1,2})?$", amount);
    }

    /**
     * Validates if the date format is correct (e.g., "yyyy-MM-dd").
     * @param date the date string to validate.
     * @return true if the date format is valid, false otherwise.
     */
    public static boolean isValidDate(String date) {
        return date != null && Pattern.matches("^\\d{4}-\\d{2}-\\d{2}$", date);
    }

    /**
     * Validates if the payment method is one of the accepted methods (e.g., "SOLANA", "ETH", "USDT").
     * @param paymentMethod the payment method to validate.
     * @return true if the payment method is valid, false otherwise.
     */
    public static boolean isValidPaymentMethod(String paymentMethod) {
        return paymentMethod != null && (paymentMethod.equals("SOL") || paymentMethod.equals("ETH") || paymentMethod.equals("BTC") || paymentMethod.equals("SLC") || paymentMethod.equals("TRX"));
    }
}
