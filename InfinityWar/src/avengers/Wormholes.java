package avengers;

import java.util.*;

/*
 * Undirected, weighted graph for the LocateTitan.java class.
 */

public class Wormholes {

    ArrayList<WormholeEntry> nodes;
    int[][] matrix;
    int size;

    public Wormholes (int size) {
        matrix = new int[size][size];
        nodes = new ArrayList<>();
        this.size = size;
    }

    public void addNode(WormholeEntry node) {
        nodes.add(node);
    }

    public void addEdge(int src, int dst, int weight) {
        WormholeEntry srcNode = nodes.get(src);
        WormholeEntry dstNode = nodes.get(dst);

        double dividend = srcNode.getFunctionalityValue() * dstNode.getFunctionalityValue();
        weight = (int) (weight / dividend);
        
        matrix[src][dst] = weight;
    }
    
    public boolean checkEdge(int src, int dst) {
        if (matrix[src][dst] == 0) {
            return false;
        } else {
            return true;
        }
    }

    public ArrayList<Integer> getAdj(int src) {
        ArrayList<Integer> adjNodeIndex = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            if (checkEdge(src, i)) {
                adjNodeIndex.add(i);
            }
        }

        return adjNodeIndex;
    }
    
    public int DijkstraAlgorithm(int targetIndex) {
        int[] minCost = new int[size];
        boolean[] set = new boolean[size];

        for (int i = 0; i < size; i++) {
            if (i == 0) {
                minCost[i] = 0;
            } else {
                minCost[i] = Integer.MAX_VALUE;
            }
        }

        for (int i = 0; i < size - 1; i++) {
            int currentSource = 0;

            for (int j = 0; j < size; j++) {
                int temp = minCost[j];
                
                if (temp < minCost[currentSource] && !set[j]) {
                    currentSource = j;
                } else if (set[currentSource]) {
                    currentSource++;
                }
            }

            set[currentSource] = true;
            ArrayList<Integer> adjNodeIndex = getAdj(currentSource);

            for (Integer index : adjNodeIndex) {
                if (!set[index]) {
                    int totalCost = minCost[currentSource] + matrix[currentSource][index];
                    minCost[index] = Integer.min(totalCost, minCost[index]);
                }
            }
        }

        return minCost[targetIndex];
    }
}