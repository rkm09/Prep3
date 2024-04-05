package gen.easy;

import java.util.*;

public class TrainMap {
    public static void main(String[] args) {
        String[] lines = {"10.0.0.1 - GET 2020-08-24", "10.0.0.1 - GET 2020-08-24", "10.0.0.2 - GET 2020-08-20"};
        System.out.println(freqIP(lines));

    }

//    Q2: Train Network
//    Rail network - consists of number of the Station objects. Stations in the map are bi-directionally connected.
//    Distance between any 2 stations is of same constant distance unit.
//    This implies that shortest distance between any 2 stations depends only on number of stations in between.
//    Implement def shortestPath(self, fromStationName, toStationName) method to find the shortest path between 2 stations.
//    Approach: Use BFS, keeping a track of predecessor paths. Maintain visited nodes to avoid cycle.
    private static void shortestPath(String[] network, String fromStationName, String toStationName) {

    }

    //    Q1: Given a log file, return IP address(es) which accesses the site most often.
    private static List<String> freqIP(String[] lines) {
        Map<String, Integer> freqMap = new HashMap<>();
        for(String line : lines) {
            String ip = line.split(" ")[0];
            freqMap.put(ip, freqMap.getOrDefault(ip, 0) + 1);
        }
        int maxValue = Collections.max(freqMap.values());
        List<String> results = new ArrayList<>();
        for(String key : freqMap.keySet()) {
            if(freqMap.get(key) == maxValue) results.add(key);
        }
        return results;
    }
}

