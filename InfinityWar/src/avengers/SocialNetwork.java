package avengers;

import java.util.*;

/* 
 * Undirected graph for PredictThanosSnap.java class.
 */

public class SocialNetwork {
    
    public class Lifeform {
        int number;

        Lifeform(int number) {
            this.number = number;
        }
    }

    ArrayList<LinkedList<Lifeform>> adjList;
    int size;
    ArrayList<Lifeform> toDelete;

    SocialNetwork(int size) {
        this.size = size;
        adjList = new ArrayList<>();
    }

    public void addNodes(int size) {
        for (int i = 0; i < size; i++) {
            Lifeform lifeform = new Lifeform(i);
            LinkedList<Lifeform> linkedList = new LinkedList<>();
            linkedList.add(lifeform);
            adjList.add(linkedList);
        }
    }

    public void addEdges(int[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (matrix[row][col] == 1) {
                    adjList.get(row).add(adjList.get(col).getFirst());
                }
            }
        }
    }

    public void snap(long seed) {
        StdRandom.setSeed(seed);
        toDelete = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            if (StdRandom.uniform() <= 0.5) {
                toDelete.add(adjList.get(i).getFirst());
            }
        }

        for (int i = 0; i < toDelete.size(); i++) {
            deleteNodes(toDelete.get(i));
        }
    }

    public void deleteNodes(Lifeform lifeform) {    
        for (int i = 0; i < adjList.size(); i++) {
            if (adjList.get(i).getFirst().equals(lifeform)) {
                adjList.remove(i);
            }
        }

        for (LinkedList<Lifeform> linkedList : adjList) {
            linkedList.remove(lifeform);
        }
    } 
    
    public boolean connectivityTest() {
        boolean[] visited = new boolean[size];

        for (Lifeform lifeform : toDelete) {
            visited[lifeform.number] = true;
        }

        Stack<Integer> stack = new Stack<>();

        stack.push(adjList.get(0).getFirst().number);
        visited[adjList.get(0).getFirst().number] = true;

        while(!stack.isEmpty()) {
            Integer index = stack.pop();
            List<Lifeform> neighbors = adjList.get(index);

            for (Lifeform lifeform : neighbors) {
                if (!visited[lifeform.number]) {
                    stack.push(lifeform.number);
                    visited[lifeform.number] = true;
                }
            }
        }

        for (int i = 0; i < visited.length; i++) {
            if (visited[i]) {
                return false;
            }
        }

        return true;
    }
}