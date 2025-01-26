/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.energymanagement.controller.algorithms;

import com.energymanagement.model.EnergyTransactionModel;
import java.util.ArrayList;
import java.util.List;

/**
 * This class implements the Insertion Sort algorithm to sort a list of
 * EnergyTransactionModel objects. The sorting can be done based on the
 * "Transaction ID" field in either ascending or descending order.
 *
 * Author: Prashidha
 */
public class InsertionSort {

    // List to store energy transactions to be sorted
    List<EnergyTransactionModel> energySortList;

    /**
     * Constructor initializes the energySortList as an empty ArrayList.
     */
    public InsertionSort() {
        energySortList = new ArrayList<>();
    }

    /**
     * Sorts a list of EnergyTransactionModel objects based on Transaction ID.
     *
     * @param energyTransactions The list of energy transactions to sort.
     * @param isDesc Boolean flag to indicate sorting order: - true for
     * descending order - false for ascending order
     * @return The sorted list of EnergyTransactionModel objects.
     */
    public List<EnergyTransactionModel> sortByTransactonID(List<EnergyTransactionModel> energyTransactions, boolean isDesc) {
        // Clear any previous list and add all elements from the input list
        this.energySortList.clear();
        this.energySortList.addAll(energyTransactions);

        // Validate input: the list cannot be null or empty
        if (energySortList == null || energySortList.isEmpty()) {
            throw new IllegalArgumentException(" list cannot be null or empty.");
        }

        // Insertion Sort algorithm
        for (int i = 1; i < energySortList.size(); i++) {
            // The current element (EnergyTransactionModel) to be inserted
            EnergyTransactionModel currentTransaction = energySortList.get(i);

            // The index of the previous element for comparison
            int j = i - 1;

            // Move elements that are greater than currentTransaction (or less, based on order)
            while (j >= 0 && shouldSwap(currentTransaction.getTransactionId(), energySortList.get(j).getTransactionId(), isDesc)) {
                // Shift element to the right to make space for the current element
                energySortList.set(j + 1, energySortList.get(j));
                j = j - 1; // Move the index to the left
            }

            // Insert the current element in the correct position
            energySortList.set(j + 1, currentTransaction);
        }

        // Return the sorted list
        return energySortList;
    }

    /**
     * Determines whether two Transaction IDs should be swapped based on sorting
     * order.
     *
     * @param current The current Transaction ID.
     * @param other The other Transaction ID to compare with.
     * @param isDesc Boolean flag to indicate sorting order: - true for
     * descending order - false for ascending order
     * @return true if the elements should be swapped, false otherwise.
     */
    private boolean shouldSwap(String current, String other, boolean isDesc) {
        // Perform lexicographical comparison between current and other Transaction IDs
        int comparisonResult = current.compareTo(other);

        // If sorting in descending order, swap if current > other
        // If sorting in ascending order, swap if current < other
        return isDesc ? comparisonResult > 0 : comparisonResult < 0;
    }
}
