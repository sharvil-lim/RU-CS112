package avengers;

import java.util.*;

/*
 * Directed graph for MindStoneNeighborNeurons.java class.
 */

public class NeuralNetwork {

    ArrayList<LinkedList<Neuron>> adjList;

    NeuralNetwork() {
        adjList = new ArrayList<>();
    }

    public void addNode(Neuron neuron) {
        LinkedList<Neuron> currentList = new LinkedList<>();
        currentList.add(neuron);
        adjList.add(currentList);
    }

    public void addEdge(int src, int dst) {
        LinkedList<Neuron> currentList = adjList.get(src);
        Neuron destinationNode = adjList.get(dst).get(0);
        currentList.add(destinationNode);
    }

    public boolean checkEdge(int src, int dst) {
        LinkedList<Neuron> currentList = adjList.get(src);
        Neuron destinationNode = adjList.get(dst).get(0);
        
        for (Neuron neuron : currentList) {
            if (neuron == destinationNode) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Neuron> findMindStoneConnections() {
        Neuron mindStone = null;
        ArrayList<Neuron> connectedNeurons = new ArrayList<>();

        for (LinkedList<Neuron> currentList : adjList) {
            if (currentList.size() == 1) {
                mindStone = currentList.get(0);
            }
        }

        for (LinkedList<Neuron> currentList : adjList) {
            for (Neuron neuron : currentList) {
                if (neuron.equals(mindStone) && !currentList.get(0).equals(mindStone)) {
                    connectedNeurons.add(currentList.get(0));
                }
            }
        }

        return connectedNeurons;
    }
}
