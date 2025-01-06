/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.energymanagement.controller.algorithms;

import com.energymanagement.model.EnergyTransactionModel;
import java.util.ArrayList;
import java.util.List;

/**
 * This class implements the Selection Sort algorithm for sorting a list of
 * EnergyTransactionModel objects based on their energy units.
 *
 * Author: Prashidha
 */
public class SelectionSort {

    // List to store the energy transactions to be sorted
    List<EnergyTransactionModel> energySortList;

    /**
     * Constructor initializes the energySortList as an empty ArrayList.
     */
    public SelectionSort() {
        energySortList = new ArrayList<>();
    }

    /**
     * Sorts a list of EnergyTransactionModel objects based on their energy
     * units.
     *
     * @param energyTransactions The list of energy transactions to sort.
     * @param isDesc Boolean flag to indicate sorting order: - true for
     * descending order - false for ascending order
     * @return The sorted list of EnergyTransactionModel objects.
     */
    public List<EnergyTransactionModel> sortByEnergyUnits(List<EnergyTransactionModel> energyTransactions, boolean isDesc) {
        // Clear the energySortList and add all elements from the input list
        this.energySortList.clear();
        this.energySortList.addAll(energyTransactions);

        // Validate input: the list cannot be null or empty
        if (energySortList == null || energySortList.isEmpty()) {
            throw new IllegalArgumentException("EnergyTransaction list cannot be null or empty.");
        }

        // Selection Sort algorithm
        for (int i = 0; i < energySortList.size() - 1; i++) {
            // Find the index of the minimum or maximum element, depending on isDesc
            int extremumIndex = findExtremumIndex(energySortList, i, isDesc);

            // Swap the current element with the extremum element, if needed
            if (i != extremumIndex) {
                swap(energySortList, i, extremumIndex);
            }
        }

        // Return the sorted list
        return energySortList;
    }

    /**
     * Finds the index of the minimum or maximum element in the list from a
     * given start index.
     *
     * @param energySortList The list of energy transactions.
     * @param startIndex The starting index for searching.
     * @param isDesc Boolean flag to indicate whether to find the maximum (true)
     * or minimum (false).
     * @return The index of the extremum (minimum or maximum) element.
     */
    private int findExtremumIndex(List<EnergyTransactionModel> energySortList, int startIndex, boolean isDesc) {
        // Initialize extremumIndex to the starting index
        int extremumIndex = startIndex;

        // Iterate through the rest of the list to find the extremum
        for (int j = startIndex + 1; j < energySortList.size(); j++) {
            // Update extremumIndex if a better candidate is found
            if (shouldSwap(energySortList.get(j).getEnergyUnits(), energySortList.get(extremumIndex).getEnergyUnits(), isDesc)) {
                extremumIndex = j;
            }
        }

        return extremumIndex;
    }

    /**
     * Determines whether two elements should be swapped based on the sorting
     * order.
     *
     * @param current The energy units of the current element.
     * @param extremum The energy units of the current extremum element.
     * @param isDesc Boolean flag indicating sorting order: - true for
     * descending order - false for ascending order
     * @return true if the elements should be swapped, false otherwise.
     */
    private boolean shouldSwap(double current, double extremum, boolean isDesc) {
        // For descending order, swap if the current value is greater than the extremum
        // For ascending order, swap if the current value is less than the extremum
        return isDesc ? current > extremum : current < extremum;
    }

    /**
     * Swaps two elements in the list at specified indices.
     *
     * @param energySortList The list of energy transactions.
     * @param i The index of the first element.
     * @param j The index of the second element.
     */
    private void swap(List<EnergyTransactionModel> energySortList, int i, int j) {
        // Store the element at index i in a temporary variable
        EnergyTransactionModel temp = energySortList.get(i);

        // Replace the element at index i with the element at index j
        energySortList.set(i, energySortList.get(j));

        // Replace the element at index j with the stored temporary element
        energySortList.set(j, temp);
    }
}
