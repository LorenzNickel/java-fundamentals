package algorithms.search.astar.model;

/**
 * This class represents a node in graph specific for this implementation of the A* Algorithm
 */

public class Node{
    /** Column of the Node in the Grid (x Coordinate) */
    private int x;
    /** Row of the Node in the Grid (y Coordinate) */
    private int y;
    /** String shown in the CLI (exactly 2 chars long) */
    private String status;
    /** G Cost and F Cost combined */
    private double fCost;
    /** Heuristic: Distance to the end node */
    private double hCost;
    /** Distance to the start node */
    private double gCost;
    /** Whether the node is traversable */
    private boolean walkable;
    /** Parent (or previous) node */
    private Node previous;

    /**
     * Node constructor
     * @param x Column in the Grid
     * @param y Row in the Grid
     */

    public Node(int x, int y){
        this.x = x;
        this.y = y;
        this.status = "  ";
        this.fCost = Double.POSITIVE_INFINITY;
        this.hCost = Double.POSITIVE_INFINITY;
        this.gCost = Double.POSITIVE_INFINITY;
        this.walkable = true;
        this.previous = null;
    }

    /**
     * Checks whether a shorter path is found
     * @param openedNode Node which called this method
     * @param cost  Cost to move from the calling node to this
     * @return
     */

    public boolean checkShorterPath(Node openedNode, double cost){
        if(openedNode.getGCost() + cost < gCost) return true;
        return false;
    }

    /**
     * Calculates all Costs
     * @param to Destination node
     * @param cost Cost to move to this node from the calling node
     */

    public void calcAllCosts(Node to, double cost) {
        calcHCost(to);
        calcGCost(cost);
        fCost = gCost + hCost;
    }

    /**
     * Calculates the H cost
     * @param to Destination node
     */

    public void calcHCost(Node to){
        hCost = Math.sqrt(Math.pow(x - to.getX(), 2) +
        Math.pow(y - to.getY(), 2)) * 10;
    }

    /**
     * Calculates the G cost
     * @param cost Cost to move to this node from the calling node
     */

    public void calcGCost(double cost){
        gCost = previous.getGCost() + cost;
    }
    
    @Override
    public boolean equals(Object o){
        Node oNode = (Node) o;
        return (this.x == oNode.getX() && this.y == oNode.getY());
    }

    public Node getPrevious() {
        return this.previous;
    }

    public void setPrevious(Node previous) {
        this.previous = previous;
    }

    public boolean isWalkable() {
        return this.walkable;
    }

    public void setWalkable(boolean walkable) {
        this.walkable = walkable;
    }

    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getFCost() {
        return this.fCost;
    }

    public void setFCost(double fCost) {
        this.fCost = fCost;
    }

    public double getHCost() {
        return this.hCost;
    }

    public void setHCost(double hCost) {
        this.hCost = hCost;
    }

    public double getGCost() {
        return this.gCost;
    }

    public void setGCost(double gCost) {
        this.gCost = gCost;
    }

}