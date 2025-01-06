/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.energymanagement.controller.algorithms;

import com.energymanagement.model.EnergyTransactionModel;
import javax.swing.JOptionPane;
import java.util.List;

/**
 * This class provides a generic binary search implementation for searching
 * within a list of EnergyTransactionModel objects.
 */
public class BinarySearch {

    /**
     * Performs a binary search on a sorted list of EnergyTransactionModel
     * objects based on a specific field.
     *
     * @param searchValue The value to search for.
     * @param searchColumn The column/field to search by (e.g., "Transaction
     * ID", "User ID").
     * @param transactionList The sorted list of EnergyTransactionModel objects
     * to search.
     * @param left The left boundary index of the search range.
     * @param right The right boundary index of the search range.
     * @return The EnergyTransactionModel that matches the search value, or null
     * if not found.
     */
    public EnergyTransactionModel searchByField(String searchValue, String searchColumn, List<EnergyTransactionModel> transactionList, int left, int right) {
        // Base case: If the right index is less than the left, the element is not found
        if (right < left) {
            return null; // Element not found
        }

        // Calculate the middle index of the current search range
        int mid = (left + right) / 2;

        // Retrieve the field value of the middle element based on the specified column
        String midValue = getFieldValue(transactionList.get(mid), searchColumn);

        // Handle numeric comparisons specifically for "Transaction ID" field
        if (searchColumn.equals("Transaction ID")) {
            // Extract the numeric part of the transaction IDs
            String searchNumericPart = searchValue.replaceAll("[^0-9]", ""); // Extract digits from the search value
            String midNumericPart = midValue.replaceAll("[^0-9]", ""); // Extract digits from the mid value

            try {
                // Convert extracted numeric parts to integers
                int searchInt = Integer.parseInt(searchNumericPart);
                int midInt = Integer.parseInt(midNumericPart);

                // Compare the numeric parts
                if (searchInt == midInt) {
                    return transactionList.get(mid); // Return the matching element
                } else if (searchInt < midInt) {
                    // Recursively search the left half
                    return searchByField(searchValue, searchColumn, transactionList, left, mid - 1);
                } else {
                    // Recursively search the right half
                    return searchByField(searchValue, searchColumn, transactionList, mid + 1, right);
                }
            } catch (NumberFormatException e) {
                // Handle invalid input for numeric conversion
                JOptionPane.showMessageDialog(null, "Invalid Transaction ID format. Please check your input.", "Error", JOptionPane.ERROR_MESSAGE);
                return null; // Return null in case of an error
            }
        }

        // Handle string comparisons for non-numeric fields
        int comparison = searchValue.compareToIgnoreCase(midValue); // Compare search value with mid value (case-insensitive)
        if (comparison == 0) { // If a match is found
            return transactionList.get(mid); // Return the matching element
        } else if (comparison < 0) { // If the search value is lexicographically smaller
            // Recursively search the left half
            return searchByField(searchValue, searchColumn, transactionList, left, mid - 1);
        } else { // If the search value is lexicographically larger
            // Recursively search the right half
            return searchByField(searchValue, searchColumn, transactionList, mid + 1, right);
        }
    }

    /**
     * Helper method to retrieve the value of a specific field from an
     * EnergyTransactionModel object.
     *
     * @param transaction The EnergyTransactionModel object.
     * @param columnName The name of the field/column to retrieve.
     * @return The value of the specified field as a string.
     */
    private String getFieldValue(EnergyTransactionModel transaction, String columnName) {
        // Determine the field to retrieve based on the column name
        switch (columnName) {
            case "Transaction ID": // Return the transaction ID
                return transaction.getTransactionId();
            case "User ID": // Return the user ID
                return transaction.getUserId();
            case "Energy Units": // Return the energy units as a string
                return String.valueOf(transaction.getEnergyUnits());
            case "Payment Amount": // Return the payment amount as a string
                return String.valueOf(transaction.getPaymentAmount());
            case "Location": // Return the location
                return transaction.getLocation();
            case "Token Type": // Return the token type
                return transaction.getTokenType();
            case "Energy Source": // Return the energy source
                return transaction.getEnergySource();
            case "Energy Type": // Return the energy type
                return transaction.getEnergyType();
            default: // Handle unsupported columns
                return ""; // Return an empty string for invalid or unsupported column names
        }
    }
}
