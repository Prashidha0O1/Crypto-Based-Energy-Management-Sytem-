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
 * @author Prashidha
 */
public class InsertionSort {
    
    List<EnergyTransactionModel> energySortList;

    public InsertionSort() {
        energySortList = new ArrayList<>();
    }

    public List<EnergyTransactionModel> sortByTransactonID(List<EnergyTransactionModel> energyTransactions, boolean isDesc) {
        this.energySortList.clear();
        this.energySortList.addAll(energyTransactions);

        if (energySortList == null || energySortList.isEmpty()) {
            throw new IllegalArgumentException("Drone list cannot be null or empty.");
        }

        // Insertion sort algorithm
        for (int i = 1; i < energySortList.size(); i++) {
            EnergyTransactionModel currentDrone = energySortList.get(i);
            int j = i - 1;

            // Move elements that are greater than currentDrone (or less, based on order)
            while (j >= 0 && shouldSwap(currentDrone.getTransactionId(), energySortList.get(j).getTransactionId(), isDesc)) {
                energySortList.set(j + 1, energySortList.get(j));
                j = j - 1;
            }
            energySortList.set(j + 1, currentDrone);
        }

        return energySortList;
    }

    private boolean shouldSwap(String current, String other, boolean isDesc){
     int comparisonResult=current.compareTo(other);//lexicographical comparison
     return isDesc ? comparisonResult > 0 : comparisonResult < 0;
    }
}