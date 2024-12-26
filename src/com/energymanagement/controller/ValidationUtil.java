package com.energymanagement.controller;

/**
 *
 * @author Prashidha
 */
import java.awt.Color;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ValidationUtil {

    private static final Pattern ALPHA_NUMERIC_PATTERN = Pattern.compile("^[a-zA-Z0-9]+$");
    private static final Pattern ALPHABET_PATTERN = Pattern.compile("^[a-zA-Z\\s]+$"); // Only alphabets and spaces
    private static final Pattern PAYMENTAMOUNT_VOLUME_PATTERN = Pattern.compile("^\\d+(\\.\\d+)?$"); // Numeric with optional decimals
    /**
     * Validates if a string is alphanumeric (letters and numbers only).
     * @param input the string to validate.
     * @return true if the input is alphanumeric, false otherwise.
     */
    public static boolean isAlphanumeric(String input) {
        return input != null && input.matches("^[a-zA-Z0-9]+$");
    }
    
    private static boolean isNullOrEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }
    
    public static boolean isValidNumericValue(String value, JLabel lblError) {
        if (isNullOrEmpty(value)) {
            lblError.setText("Value cannot be empty.");
            lblError.setForeground(Color.RED);
            return false;
        }
        if (!PAYMENTAMOUNT_VOLUME_PATTERN.matcher(value).matches()) {
            lblError.setText("Value must be a valid numeric.");
            lblError.setForeground(Color.RED);
            return false;
        }
        double number = Double.parseDouble(value);
        if (number < 0) {
            lblError.setText("Value cannot be negative.");
            lblError.setForeground(Color.RED);
            return false;
        }
        return true;
    }
    /**
     * Validates the User ID.
     * @param userId the User ID to validate
     * @param lblError the JLabel to display error messages
     * @return true if the User ID is valid, false otherwise
     */
    public static boolean isValidUserId(String userId, JLabel lblError) {
        if (isNullOrEmpty(userId)) {
            lblError.setText("User ID cannot be empty.");
            lblError.setForeground(Color.WHITE);
            return false;
        }
        if (!userId.startsWith("0x") || !ALPHA_NUMERIC_PATTERN.matcher(userId.substring(2)).matches() || userId.length() != 10) {
            lblError.setText("User ID must start with '0x', be alphanumeric, exactly 10 characters.");
            lblError.setForeground(Color.RED);
            return false;
        }
        lblError.setText(""); // Clear error message if valid
        return true;
    }

    
    /**
     * Validates the Transaction ID.
     * @param transactionId the Transaction ID to validate
     * @param lblError the JLabel to display error messages
     * @return true if the Transaction ID is valid, false otherwise
     */
    public static boolean isValidTransactionId(String transactionId, JLabel lblError) {
        if (transactionId == null || transactionId.trim().isEmpty()) {
            lblError.setText("Transaction ID cannot be empty.");
            lblError.setForeground(Color.RED);
            return false;
        }
        if (!ALPHA_NUMERIC_PATTERN.matcher(transactionId).matches() || transactionId.length() != 5) {
            lblError.setText("Transaction ID must be alphanumeric, exactly 5 character.");
            lblError.setForeground(Color.RED);
            return false;
        }
        lblError.setText(""); // Clear error message if valid
        return true;
    }
    
    /**
     * Validates the energy amount.
     * @param amount the energy amount to validate.
     * @param lblError the JLabel to display error messages.
     * @return true if the energy amount is valid, false otherwise.
     */
    public static boolean isValidEnergyAmount(String amount, JLabel lblError) {
        if (amount == null || amount.trim().isEmpty()) {
            lblError.setText("Energy amount cannot be empty.");
            lblError.setForeground(Color.RED);
            return false;
        }
        if (!amount.matches("^[0-9]+(\\.[0-9]{1,2})?$")) {
            lblError.setText("Energy amount must be a number with up to two decimal places.");
            lblError.setForeground(Color.RED);
            return false;
        }
        lblError.setText(""); // Clear error message if valid
        return true;
    }
    
    /**
     * Validates if the payment method is one of the accepted methods.
     * @param paymentMethod the payment method to validate.
     * @param lblError the JLabel to display error messages.
     * @return true if the payment method is valid, false otherwise.
     */
    public static boolean validatePaymentMethod(String paymentMethod, JLabel lblError) {
        if (paymentMethod == null || paymentMethod.trim().isEmpty()) {
            lblError.setText("Payment method cannot be empty.");
            lblError.setForeground(Color.RED);
            return false;
        }
        if (!(paymentMethod.equals("SOL") || paymentMethod.equals("ETH") || 
              paymentMethod.equals("BTC") || paymentMethod.equals("SLC") || 
              paymentMethod.equals("TRX"))) {
            lblError.setText("Invalid payment method. Accepted methods: SOL, ETH, BTC, SLC, TRX.");
            lblError.setForeground(Color.RED);
            return false;
        }
        lblError.setText(""); // Clear error message if valid
        return true;
    }
    
    public static boolean isValidLocation(String location, JLabel lblError) {
        if (isNullOrEmpty(location)) {
            lblError.setText("Location cannot be empty.");
            lblError.setForeground(Color.RED);
            return false;
        }
        if (!ALPHABET_PATTERN.matcher(location).matches() || location.split("\\s+").length > 2) {
            lblError.setText("Location must contain alphabets and a maximum of 2 words.");
            lblError.setForeground(Color.RED);
            return false;
        }
        return true;
    }
        /**
     * Validates if the given energy source is one of the accepted sources.
     * Updates the lblError with an error message if validation fails.
     *
     * @param energySource The energy source to validate.
     * @param lblError The JLabel to display error messages.
     * @return true if the energy source is valid, false otherwise.
     */
    public static boolean isValidEnergySource(String energySource, JLabel lblError) {
        if (energySource == null || energySource.trim().isEmpty()) {
            lblError.setText("Energy Source cannot be empty.");
            lblError.setForeground(Color.RED);
            return false;
        }
        List<String> validSources = Arrays.asList("Solar", "Wind", "Hydro", "Geothermal", "Biomass");
        if (!validSources.contains(energySource)) {
            lblError.setText("Invalid Energy Source. Accepted values: Solar, Wind, Hydro, Geothermal, Biomass.");
            lblError.setForeground(Color.RED);
            return false;
        }
        lblError.setText(""); // Clear error if valid
        return true;
    }
    /**
     * Validates if the given energy type is one of the accepted types.
     * Updates the lblError with an error message if validation fails.
     *
     * @param energyType The energy type to validate.
     * @param lblError The JLabel to display error messages.
     * @return true if the energy type is valid, false otherwise.
     */
    public static boolean isValidEnergyType(String energyType, JLabel lblError) {
        if (energyType == null || energyType.trim().isEmpty()) {
            lblError.setText("Energy Type cannot be empty.");
            lblError.setForeground(Color.RED);
            return false;
        }
        List<String> validTypes = Arrays.asList("Electricity", "Thermal", "Mechanical");
        if (!validTypes.contains(energyType)) {
            lblError.setText("Invalid Energy Type. Accepted values: Electricity, Thermal, Mechanical.");
            lblError.setForeground(Color.RED);
            return false;
        }
        lblError.setText(""); // Clear error if valid
        return true;
    }
  
}
