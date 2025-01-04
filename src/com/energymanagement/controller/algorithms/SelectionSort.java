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
public class SelectionSort {
    
    List<EnergyTransactionModel> energySortList;

    public SelectionSort() {
        energySortList = new ArrayList<>();
    }

    
    public List<EnergyTransactionModel> sortByEnergyUnits(List<EnergyTransactionModel> energyTransactions, boolean isDesc) {
        this.energySortList.clear();
        this.energySortList.addAll(energyTransactions);

        if (energySortList == null || energySortList.isEmpty()) {
            throw new IllegalArgumentException("EnergyTransaction list cannot be null or empty.");
        }

        // Selection sort algorithm
        for (int i = 0; i < energySortList.size() - 1; i++) {
            int extremumIndex = findExtremumIndex(energySortList, i, isDesc);
            if (i != extremumIndex) {
                swap(energySortList, i, extremumIndex);
            }
        }

        return energySortList;
    }

    
    private int findExtremumIndex(List<EnergyTransactionModel> energySortList, int startIndex, boolean isDesc) {
        int extremumIndex = startIndex;

        for (int j = startIndex + 1; j < energySortList.size(); j++) {
            if (shouldSwap(energySortList.get(j).getEnergyUnits(), energySortList.get(extremumIndex).getEnergyUnits(), isDesc)) {
                extremumIndex = j;
            }
        }

        return extremumIndex;
    }

    private boolean shouldSwap(double current, double extremum, boolean isDesc) {
        return isDesc ? current > extremum : current < extremum;
    }
    
    private void swap(List<EnergyTransactionModel> energySortList, int i, int j) {
        EnergyTransactionModel temp = energySortList.get(i);
        energySortList.set(i, energySortList.get(j));
        energySortList.set(j, temp);
    }
}