/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.energymanagement.controller.algorithms;
import com.energymanagement.model.EnergyTransactionModel;
import java.util.ArrayList;
import java.util.List;

public class BinarySearch {

    private List<EnergyTransactionModel> energyTransactions;

    public BinarySearch() {
        this.energyTransactions = new ArrayList<>();
    }

    /**
     * Searches for a transaction in the sorted list using Binary Search.
     *
     * @param energyTransactions The sorted list of energy transactions.
     * @param transactionId      The transaction ID to search for.
     * @return The EnergyTransactionModel object if found, or null if not found.
     */
    public EnergyTransactionModel searchTransactionById(List<EnergyTransactionModel> energyTransactions, String transactionId) {
        this.energyTransactions.clear();
        this.energyTransactions.addAll(energyTransactions);

        if (energyTransactions == null || energyTransactions.isEmpty()) {
            throw new IllegalArgumentException("Energy transaction list cannot be null or empty.");
        }

        // Perform binary search
        int low = 0;
        int high = energyTransactions.size() - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            String midTransactionId = energyTransactions.get(mid).getTransactionId();

            int comparison = midTransactionId.compareTo(transactionId);

            if (comparison == 0) {
                // Transaction found
                return energyTransactions.get(mid);
            } else if (comparison < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        // Transaction not found
        return null;
    }
}
