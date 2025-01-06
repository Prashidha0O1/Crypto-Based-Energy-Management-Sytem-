/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.energymanagement.controller.algorithms;

import com.energymanagement.model.EnergyTransactionModel;
import java.util.ArrayList;
import java.util.List;

/**
 * This class implements the Merge Sort algorithm to sort a list of
 * EnergyTransactionModel objects. Sorting is performed based on the "Location"
 * field (String) in ascending or descending order.
 *
 * Author: Prashidha
 */
public class MergeSort {

    /**
     * Sorts a list of EnergyTransactionModel objects based on their Location
     * (String).
     *
     * @param energyTransactions The list of energy transactions to sort.
     * @param isDesc Boolean flag to indicate sorting order: - true for
     * descending order - false for ascending order
     * @return The sorted list of EnergyTransactionModel objects.
     */
    public List<EnergyTransactionModel> sortByLocation(List<EnergyTransactionModel> energyTransactions, boolean isDesc) {
        // Input validation: Ensure the list is not null or empty
        if (energyTransactions == null || energyTransactions.isEmpty()) {
            throw new IllegalArgumentException("Energy Transactions list cannot be null or empty.");
        }

        // Convert the list to a new list for sorting to avoid modifying the original list
        List<EnergyTransactionModel> sortedList = new ArrayList<>(energyTransactions);

        // Call the mergeSort function to sort the list
        mergeSort(sortedList, 0, sortedList.size() - 1, isDesc);
        return sortedList;
    }

    /**
     * Recursively splits the list into halves and sorts each half using the
     * Merge Sort algorithm.
     *
     * @param list The list of EnergyTransactionModel objects to sort.
     * @param left The starting index of the current sublist.
     * @param right The ending index of the current sublist.
     * @param isDesc Boolean flag to indicate sorting order (true for
     * descending, false for ascending).
     */
    private void mergeSort(List<EnergyTransactionModel> list, int left, int right, boolean isDesc) {
        // Base case: Continue splitting until a single element remains
        if (left < right) {
            // Calculate the middle index of the current sublist
            int mid = (left + right) / 2;

            // Recursively sort the left half
            mergeSort(list, left, mid, isDesc);

            // Recursively sort the right half
            mergeSort(list, mid + 1, right, isDesc);

            // Merge the two sorted halves
            merge(list, left, mid, right, isDesc);
        }
    }

    /**
     * Merges two sorted halves of the list into a single sorted segment.
     *
     * @param list The list of EnergyTransactionModel objects to merge.
     * @param left The starting index of the left half.
     * @param mid The ending index of the left half and starting index of the
     * right half.
     * @param right The ending index of the right half.
     * @param isDesc Boolean flag to indicate sorting order (true for
     * descending, false for ascending).
     */
    private void merge(List<EnergyTransactionModel> list, int left, int mid, int right, boolean isDesc) {
        // Create temporary lists for the left and right halves
        List<EnergyTransactionModel> leftList = new ArrayList<>(list.subList(left, mid + 1));
        List<EnergyTransactionModel> rightList = new ArrayList<>(list.subList(mid + 1, right + 1));

        // Initialize indices for iterating through the left, right, and main list
        int i = 0, j = 0, k = left;

        // Merge the left and right lists based on Location comparison
        while (i < leftList.size() && j < rightList.size()) {
            if (shouldSwap(leftList.get(i).getLocation(), rightList.get(j).getLocation(), isDesc)) {
                // If left element should come first, add it to the main list
                list.set(k++, leftList.get(i++));
            } else {
                // Otherwise, add the right element to the main list
                list.set(k++, rightList.get(j++));
            }
        }

        // Add any remaining elements from the left list to the main list
        while (i < leftList.size()) {
            list.set(k++, leftList.get(i++));
        }

        // Add any remaining elements from the right list to the main list
        while (j < rightList.size()) {
            list.set(k++, rightList.get(j++));
        }
    }

    /**
     * Determines whether two Location strings should be swapped based on the
     * sorting order.
     *
     * @param leftLocation The Location of the left element.
     * @param rightLocation The Location of the right element.
     * @param isDesc Boolean flag to indicate sorting order: - true for
     * descending order - false for ascending order
     * @return true if the elements should be swapped, false otherwise.
     */
    private boolean shouldSwap(String leftLocation, String rightLocation, boolean isDesc) {
        // Perform lexicographical comparison between leftLocation and rightLocation
        return isDesc ? leftLocation.compareTo(rightLocation) > 0 : leftLocation.compareTo(rightLocation) < 0;
    }
}
