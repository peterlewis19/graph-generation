import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class Main {
    public static void main(String[] args) {

        ///CONSTANTS
        int NUM_OF_POINTS = 100;   //roughly 100:1 ratio has best results
        int CLOSEST_N = 8; //HAS A CLOSEST_N! impact on runtime about

        // TODO:
        /// -add another generation after, of points with less than e.g 3 neighbours, to fill it out even more
        /// - this should close the gaps, and leave less islands
        
        //generates points and writes them to map.txt
        Node[] graph = generatePoints(NUM_OF_POINTS);

        ArrayList<Node>[] allNeighbours = new ArrayList[NUM_OF_POINTS];

        //generates 50 random nodes
        //Node[] graph = FileHandler.readFromFile("map.txt");

        //for each node recognise the 5 closest neighbours
        Node[] nClosest = new Node[CLOSEST_N];

        for (int j=0; j < CLOSEST_N; j++) {
            //finds the 5 closest nodes to the current node, and randomly chooses from those to make neighbours
            for (Node current : graph) {
                //sort the other nodes by distance to current node using built in method
                Node[] sortedForCurrentGraph = graph.clone();

                Arrays.sort(sortedForCurrentGraph, Comparator.comparingDouble(current::distanceTo));


                //adds the CLOSEST_N closest points to nClosest
                for (int i = 1; i < CLOSEST_N + 1; i++) {
                    nClosest[i - 1] = sortedForCurrentGraph[i];
                }

                //adds the closest neighbour
                if (j == 0) {
                    current.addNeighbour(nClosest[0]);
                } else {
                    // add the second closest neighbour if the confirmed neighbours arent connected to it, etc
                    boolean isNeighbour = false;

                    for (Node neighbour : current.getNeighbours()) {
                        if (nClosest[j].isNeighbour(neighbour)) {
                            isNeighbour = true;
                        }
                    }

                    if (!isNeighbour) {
                        current.addNeighbour(nClosest[j]);
                    }
                }

            }

        }

        int count=0;

        for (Node node: graph){
            allNeighbours[count] = node.getNeighbours();
            count++;
        }

        //go through allNeighbours, for each node sort by distance to start, then do first 50 of those. this is def way faster!!!!
        // O(n^2), for each node, iterate through entire list, to intersect test all possibilities

        //THIS IS WILDLY INEFFICIENT IT NEEDS REFACTORING DESPERATELY
        Node toRemoveFromNode;
        ArrayList<Node> nodeToRemoveFrom = new ArrayList<>();

        ArrayList<Node[]> alreadyDeleted = new ArrayList<>(); //[0] = node1, [1] = node2, [2] = neighbour1, [3] = neighbour2  AND [0,1] = neighbours, [2,3] = nodes FOR COPY

        for (Node node : graph) {
            for (Node value : graph) {
                Edge currentEdge = null;
                Edge otherEdge = null;

                for (Node neighbour : node.getNeighbours()) {
                    currentEdge = new Edge(new Node[]{node, neighbour});

                    for (Node neighbour2 : value.getNeighbours()) {
                        otherEdge = new Edge(new Node[]{value, neighbour2});

                        if (currentEdge.isOverlapping(otherEdge)) {
                            toRemoveFromNode = otherEdge.getSecondNode();

                            //finds the second node and removes the overlapping neighbour from it
                            //nodeToRemoveFrom = allNeighbours.get(allNeighbours.indexOf(value.getNeighbours())); ///GOES TO -1 OUT OF BOUNDS HERE
                            if (!alreadyDeleted.contains(new Node[]{node, neighbour, value, neighbour2}) && !alreadyDeleted.contains(new Node[]{value,neighbour2, node, neighbour})) {

                                for (ArrayList<Node> neighors : allNeighbours) {
                                    if (neighors.get(0) == value) {
                                        nodeToRemoveFrom = neighors;

                                        nodeToRemoveFrom.remove(toRemoveFromNode);

                                        alreadyDeleted.add(new Node[]{node, neighbour, value, neighbour2});
                                        alreadyDeleted.add(new Node[]{value, neighbour2, node, neighbour});
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        //NewFrame frame = new NewFrame();
        //frame.drawFrame(allNeighbours);

    }

    public static Node[] generatePoints(int numberOfPoints){
        Random random = new Random();
        int randomX = 0;
        int randomY = 0;
        //int[] coords = {randomX, randomY};
        Node[] allNodes = new Node[numberOfPoints];

        for (int i = 0; i < numberOfPoints; i++) {
            randomX = random.nextInt(0, 1820);
            randomY = random.nextInt(0,980);

            //adds the coordinates to instantiate the Node
            int[] coords = {randomX, randomY};
            allNodes[i] = new Node(coords); //Node is added to allNodes
        }

        //write allNodes to a file to use a consistent data set
        FileHandler.writeFromList("map.txt", allNodes, true);


        return allNodes;
    }
}