package Route;

public class NeighborTerrain {
    private int Xcor;
    private int Ycor;
    private int Cost;

    public NeighborTerrain(int xcor, int ycor, int cost) {
        Xcor = xcor;
        Ycor = ycor;
        Cost = cost;
    }
    public int getXcor() {
        return Xcor;
    }

    public void setXcor(int xcor) {
        Xcor = xcor;
    }

    public int getYcor() {
        return Ycor;
    }

    public void setYcor(int ycor) {
        Ycor = ycor;
    }

    public int getCost() {
        return Cost;
    }

    public void setCost(int cost) {
        Cost = cost;
    }


}
