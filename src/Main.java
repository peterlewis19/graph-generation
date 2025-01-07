import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class Main {
    public static void main(String[] args) {

        ///CONSTANTS
        int NUM_OF_POINTS = 100;
        int CLOSEST_N = 4;
        //TODO:
        // 1.Generate N random nodes ----- tick
        // 2.Recognise the n closest nodes ----- tick

        ArrayList<Node>[] allNeighbours = new ArrayList[NUM_OF_POINTS];

        //generates 50 random nodes
        Node[] graph = generatePoints(NUM_OF_POINTS);

        //for each node recognise the 5 closest neighbours
        Node[] fiveClosest = new Node[CLOSEST_N];

        for (int j=0; j < CLOSEST_N; j++) {
            //finds the 5 closest nodes to the current node, and randomly chooses from those to make neighbours
            for (int k = 0; k < graph.length; k++) {
                Node current = graph[k];
                //sort the other nodes by distance to current node using bubble sort
                Node[] sortedForCurrentGraph = graph.clone();

                Arrays.sort(sortedForCurrentGraph, Comparator.comparingDouble(current::distanceTo));


                //adds the CLOSEST_N closest points to fiveClosest
                for (int i = 1; i < CLOSEST_N + 1; i++) {
                    fiveClosest[i - 1] = sortedForCurrentGraph[i];
                }

                //adds the closest neighbour
                if (j == 0) {
                    current.addNeighbour(fiveClosest[0]);
                } else {
                    // add the second closest neighbour if the confirmed neighbours arent connected to it, etc
                    boolean isNeighbour = false;

                    for (Node neighbour: current.getNeighbours()){
                        if (fiveClosest[j].isNeighbour(neighbour)){
                            isNeighbour = true;
                        }
                    }

                    if (!isNeighbour){
                        current.addNeighbour(fiveClosest[j]);
                    }
                }


                //System.out.println("CURRENT NODE: " + current);
                //System.out.println(current.getNeighbours().size() + " NEIGHBOURS: ");

                //arraylist containing Node, neighbour1, neighbour2... etc to add to allNeigbours at the end
                ArrayList<Node> nodeAndNeighbours = new ArrayList<>();
                nodeAndNeighbours.add(current);


                for (Node neighbour: current.getNeighbours()){
                    System.out.println(neighbour);

                    nodeAndNeighbours.add(neighbour);
                }

                allNeighbours[k] = nodeAndNeighbours;
                //System.out.println();
            }

            //frane.drawFrame(allNeighbours);
        }



        //using allNeighbours, draw the graph
        NewFrame frame = new NewFrame();
        frame.drawFrame(allNeighbours);

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

        return allNodes;
    }
}