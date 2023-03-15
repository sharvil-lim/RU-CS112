package avengers;

import java.util.*;

public class Timeline {
    ArrayList<LinkedList<Event>> adjList;
    ArrayList<List<Event>> chainsOfEvents;

    public Timeline() {
        this.adjList = new ArrayList<>();
        this.chainsOfEvents = new ArrayList<>();
    }

    public void addEvent(Event event) {
        LinkedList<Event> linkedList = new LinkedList<>();
        linkedList.add(event);
        adjList.add(linkedList);
    }
    
    public void initMatrix(int[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (matrix[row][col] == 1) {
                    adjList.get(row).add(adjList.get(col).getFirst());
                }
            }
        }
    }

    public void printAllPaths(Event s, Event d) {
        boolean[] visited = new boolean[adjList.size()];
        ArrayList<Event> pathList = new ArrayList<>();

        pathList.add(s);
        
        printAllPathsUtil(s, d, visited, pathList);
    }

    public void printAllPathsUtil(Event u, Event d, boolean[] isVisited, List<Event> localPathList) {
        for (int i = 0; i < adjList.size(); i++) {
            if (u.equals(adjList.get(i).getFirst())) {
                for (Event event : adjList.get(i)) {
                    if (!event.equals(u) && isVisited[event.eventNumber]) {
                        isVisited[event.eventNumber] = false;
                    }
                }
            }
        }

        if (u.equals(d)) {
            LinkedList<Event> linkedList = new LinkedList<>();
            for (Event event : localPathList) {
                linkedList.add(event);
            }
            chainsOfEvents.add(linkedList);
            return;
        }

        isVisited[u.eventNumber] = true;

        for (Event i : adjList.get(u.eventNumber)) {
            if (!isVisited[i.eventNumber]) {
                localPathList.add(i);
                printAllPathsUtil(i, d, isVisited, localPathList);

                localPathList.remove(i);
            }
        }
    }

    public void useTimeStone() {
        for (LinkedList<Event> linkedList : adjList) {
            printAllPaths(adjList.get(0).getFirst(), linkedList.getFirst());
        }
    }

    public int getTotalPossibilities() {
        return chainsOfEvents.size();
    }

    public int getEUPossibilities(int EU) {
        int ans = 0;

        for (List<Event> currentTimeline : chainsOfEvents) {
            int tempEU = 0;
            
            for (Event event : currentTimeline) {
                tempEU += event.euValue;
            }

            if (tempEU >= EU) {
                ans++;
            }
        }

        return ans;
    }
    
}