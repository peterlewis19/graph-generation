import java.util.ArrayList;

public class Node {
    private int[] coords;
    private ArrayList<Node> neighbours;

    public Node(int[] coords){
        this.coords = coords;
        this.neighbours = new ArrayList<>();
    }

    public void addNeighbour(Node neighbour){
        neighbours.add(neighbour);
    }

    public ArrayList<Node> getNeighbours(){
        return neighbours;
    }

    public boolean isNeighbour(Node neighbour){
        boolean isNeighbour = false;
        ArrayList<Node> neighboursOfNeighbour = neighbour.getNeighbours();

        //checks if the current node is a neighbour of neighbour
        for (Node neighbourino: neighboursOfNeighbour){
            if (neighbourino.getCoords().equals(getCoords())){
                isNeighbour = true;
            }
        }

        return isNeighbour;

    }

    public int[] getCoords(){
        return coords;
    }

    public String toString(){
        int[] coords = getCoords();
        int x = coords[0];
        int y = coords[1];

        String strx = Integer.toString(x);
        String stry = Integer.toString(y);

        return "["+strx + "," + stry+"]";
    }

    //returns the distance to another node
    public double distanceTo(Node matilda){
        double distance;

        int changeInX = Math.abs(coords[0] - matilda.getCoords()[0]);
        int changeInY = Math.abs(coords[1] - matilda.getCoords()[1]);

        //use pythagorean theorem to work out the distance diagonally
        distance = Math.sqrt(Math.pow(changeInX,2) + Math.pow(changeInY,2));

        return distance;
    }


}
