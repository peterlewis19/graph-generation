public class Edge{
    private Node[] aToB;
    private Node node1;
    private Node node2;

    public Edge(Node[] edge){
        this.node1 = edge[0];
        this.node2 = edge[1];
    }

    public boolean isOverlapping(Edge otherEdge){
        Node otherNode1 = otherEdge.getFirstNode();
        Node otherNode2 = otherEdge.getSecondNode();

        /*System.out.println(node1 + " to "+ node2);
        System.out.println(otherNode1 + " to "+ otherNode2);
        System.out.println();
        */

        int[] currentXMinAndMax = new int[2];
        int[] otherXMinAndMax = new int[2];

        double currentGradient;
        double otherGradient;

        /*
        * y-y_1 = m(x-x_1)
        * y-y_1 = (m * x) - (m * x_1) ---> take out y and x, as they are unknown => y_1 - (m * x_1)
        * y = y_1 + m(x-x_1)               this is the isolatedSection
        *
        * SAME FOR OTHER EDGE
        * resulting in:
        *
        * (isolated1 - isolated0) / (m0 - m1) = x
        *
        * */

        //works out gradient
        int currentChangeInX;
        int currentChangeInY;

        //smallest x is node 1
        if (node1.getCoords()[0] > node2.getCoords()[0]) {
            currentChangeInX = node1.getCoords()[0] - node2.getCoords()[0];
            currentChangeInY = node1.getCoords()[1] - node2.getCoords()[1];
        }else{
            currentChangeInX = node2.getCoords()[0] - node1.getCoords()[0];
            currentChangeInY = node2.getCoords()[1] - node1.getCoords()[1];
        }

        if (currentChangeInX != 0) {
            currentGradient = currentChangeInY / currentChangeInX;
        } else{
            currentGradient = 0;
        }


        //gets the smaller and bigger X in order  e.g (8,4) and (4,6)
        if (node1.getCoords()[0] < node2.getCoords()[0]) {
            currentXMinAndMax = new int[]{node1.getCoords()[0], node2.getCoords()[0]};
        }else{
            currentXMinAndMax = new int[]{node2.getCoords()[0], node1.getCoords()[0]};
        }

        //use only node1 for easiness
        double currentIsolated = node1.getCoords()[1] - (currentGradient * node1.getCoords()[0]);

        //for the second line
        int otherChangeInX;
        int otherChangeInY;

        //smallest x is node 1
        if (otherNode1.getCoords()[0] > otherNode2.getCoords()[0]) {
            otherChangeInX = otherNode1.getCoords()[0] - otherNode2.getCoords()[0];
            otherChangeInY = otherNode1.getCoords()[1] - otherNode2.getCoords()[1];
        }else{
            otherChangeInX = otherNode2.getCoords()[0] - otherNode1.getCoords()[0];
            otherChangeInY = otherNode2.getCoords()[1] - otherNode1.getCoords()[1];
        }

        if (otherChangeInX != 0) {
            otherGradient = otherChangeInY / otherChangeInX;
        } else{
            otherGradient = 0;
        }

        if (otherNode1.getCoords()[0] < otherNode2.getCoords()[0]) {
            otherXMinAndMax = new int[]{otherNode1.getCoords()[0], otherNode2.getCoords()[0]};
        }else{
            otherXMinAndMax = new int[]{otherNode2.getCoords()[0], otherNode1.getCoords()[0]};
        }

        double otherIsolated = otherNode1.getCoords()[1] - (otherGradient * otherNode1.getCoords()[0]);

        double xToCheck;

        //if gradients are equal then they cant coincide
        if (currentGradient-otherGradient != 0) {
            xToCheck = (otherIsolated - currentIsolated) / (currentGradient - otherGradient);
        }else{
            //not possible to be in valid range, so will return that they do not overlap
            xToCheck = -1;
        }


        if (xToCheck > currentXMinAndMax[0] && xToCheck < currentXMinAndMax[1] && xToCheck > otherXMinAndMax[0] && xToCheck < otherXMinAndMax[1]){
        //if (xToCheck > 0){
            return true;
        }else{
            return false;
        }

    }

    public Node getFirstNode(){
        return node1;
    }

    public Node getSecondNode(){
        return node2;
    }
}
