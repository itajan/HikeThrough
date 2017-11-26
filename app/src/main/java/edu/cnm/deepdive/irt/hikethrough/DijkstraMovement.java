package edu.cnm.deepdive.irt.hikethrough;


import edu.cnm.deepdive.irt.hikethrough.unused.Hex;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class DijkstraMovement {

//  public boolean solve(hexCenter origin, hexCenter destination) {
//    pool = new HashSet<>();
//    for (Iterator iter = graph.nodes(); iter.hasNext(); ) {
//      pool.add((Hex) iter.next());
//    }
//    visited = new HashMap<>();
//    visited.put(origin, 0);
//    predecessors = new HashMap<>();
//    while (!pool.isEmpty()) {
//      Hex nearestHex = null;
//      int bestDistance = Integer.MAX_VALUE;
//      for (Hex hex : pool) {
//        if (visited.containsKey(hex)) {
//          int testDistance = visited.get(hex);
//          if (nearestHex == null
//              || testDistance < bestDistance) {
//            nearestHex = hex;
//            bestDistance = testDistance;
//          }
//        }
//      }
//      if (nearestHex == null) {
//        return false;
//      }
//      pool.remove(nearestHex);
//      if (nearestHex == destination) {
//        return true;
//      }
//      for (Iterator iter = nearestHex.edges(); iter.hasNext(); ) {
//        Edge edge = (Edge) iter.next();
//        int testDistance = bestDistance + edge.getInt("distance");
//        Hex nextHex = edge.getAdjacentHex(nearestHex);
//        if (!visited.containsKey(nextHex)
//            || testDistance < visited.get(nextHex)) {
//          visited.put(nextHex, testDistance);
//          predecessors.put(nextHex, nearestHex);
//        }
//      }
//    }
//    return true;
//  }


}
