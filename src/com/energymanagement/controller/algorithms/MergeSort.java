/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.energymanagement.controller.algorithms;

import com.energymanagement.model.EnergyTransactionModel;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Suman Lama
 */
public class MergeSort {
    
    // Sort a list of EnergyTransactionModel objects based on Location (String) in ascending or descending order
    public List<EnergyTransactionModel> sortByLocation(List<EnergyTransactionModel> energyTransactions, boolean isDesc) {
        if (energyTransactions == null || energyTransactions.isEmpty()) {
            throw new IllegalArgumentException("Energy Transactions list cannot be null or empty.");
        }

        // Convert the List to an array for merge sort (MergeSort works with arrays)
        List<EnergyTransactionModel> sortedList = new ArrayList<>(energyTransactions);
        mergeSort(sortedList, 0, sortedList.size() - 1, isDesc);
        return sortedList;
    }

    // The mergeSort function recursively splits the list and merges it back
    private void mergeSort(List<EnergyTransactionModel> list, int left, int right, boolean isDesc) {
        if (left < right) {
            int mid = (left + right) / 2;
            
            mergeSort(list, left, mid, isDesc);   // Sort the left half
            mergeSort(list, mid + 1, right, isDesc); // Sort the right half
            
            merge(list, left, mid, right, isDesc); // Merge the sorted halves
        }
    }

    // The merge function combines two sorted halves of the list
    private void merge(List<EnergyTransactionModel> list, int left, int mid, int right, boolean isDesc) {
        // Temporary arrays for the two halves
        List<EnergyTransactionModel> leftList = new ArrayList<>(list.subList(left, mid + 1));
        List<EnergyTransactionModel> rightList = new ArrayList<>(list.subList(mid + 1, right + 1));
        
        int i = 0, j = 0, k = left;
        
        // Merge the two halves based on Location (String comparison)
        while (i < leftList.size() && j < rightList.size()) {
            if (shouldSwap(leftList.get(i).getLocation(), rightList.get(j).getLocation(), isDesc)) {
                list.set(k++, leftList.get(i++));
            } else {
                list.set(k++, rightList.get(j++));
            }
        }
        
        // Copy any remaining elements from the left half
        while (i < leftList.size()) {
            list.set(k++, leftList.get(i++));
        }
        
        // Copy any remaining elements from the right half
        while (j < rightList.size()) {
            list.set(k++, rightList.get(j++));
        }
    }

    // Determine whether to swap based on the sorting order (ascending/descending)
    private boolean shouldSwap(String leftLocation, String rightLocation, boolean isDesc) {
        return isDesc ? leftLocation.compareTo(rightLocation) > 0 : leftLocation.compareTo(rightLocation) < 0;
    }
}
