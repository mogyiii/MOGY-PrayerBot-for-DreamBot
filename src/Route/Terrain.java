package Route;

public class Terrain {
    private int Xcor;
    private int Ycor;
    private boolean Available = true;
    private NeighborTerrain NorthTerrain;
    private NeighborTerrain NotrtWestTerrain;
    private NeighborTerrain NorthEastTerrain;
    private NeighborTerrain WestTerrain;
    private NeighborTerrain EastTerrain;
    private NeighborTerrain SouthEastTerrain;
    private NeighborTerrain SouthWestTerrain;
    private NeighborTerrain SouthTerrain;


    public Terrain(int Xcor,int Ycor) {
       NorthTerrain = new NeighborTerrain(Xcor, Ycor + 1, 0);
       NotrtWestTerrain = new NeighborTerrain(Xcor - 1, Ycor + 1, 0);
       NorthEastTerrain = new NeighborTerrain(Xcor + 1, Ycor + 1, 0);
       WestTerrain = new NeighborTerrain(Xcor - 1, Ycor, 0);
       EastTerrain = new NeighborTerrain(Xcor + 1, Ycor, 0);
       SouthEastTerrain = new NeighborTerrain(Xcor + 1, Ycor - 1, 0);
       SouthWestTerrain = new NeighborTerrain(Xcor - 1, Ycor - 1, 0);
       SouthTerrain = new NeighborTerrain(Xcor , Ycor - 1, 0);
       setXcor(Xcor);
       setYcor(Ycor);
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

    public boolean isAvailable() {
        return Available;
    }

    public void setAvailable(boolean available) {
        Available = available;
    }

    //NeighborTerrain
    public NeighborTerrain getNorthTerrain() {
        return NorthTerrain;
    }

    public void setNorthTerrain(NeighborTerrain northTerrain) {
        NorthTerrain = northTerrain;
    }

    public NeighborTerrain getNorthWestTerrain() {
        return NotrtWestTerrain;
    }

    public void setNorthWestTerrain(NeighborTerrain notrtWestTerrain) {
        NotrtWestTerrain = notrtWestTerrain;
    }

    public NeighborTerrain getNorthEastTerrain() {
        return NorthEastTerrain;
    }

    public void setNorthEastTerrain(NeighborTerrain northEastTerrain) {
        NorthEastTerrain = northEastTerrain;
    }

    public NeighborTerrain getWestTerrain() {
        return WestTerrain;
    }

    public void setWestTerrain(NeighborTerrain westTerrain) {
        WestTerrain = westTerrain;
    }

    public NeighborTerrain getEastTerrain() {
        return EastTerrain;
    }

    public void setEastTerrain(NeighborTerrain eastTerrain) {
        EastTerrain = eastTerrain;
    }

    public NeighborTerrain getSouthEastTerrain() {
        return SouthEastTerrain;
    }

    public void setSouthEastTerrain(NeighborTerrain southEastTerrain) {
        SouthEastTerrain = southEastTerrain;
    }

    public NeighborTerrain getSouthWestTerrain() {
        return SouthWestTerrain;
    }

    public void setSouthWestTerrain(NeighborTerrain southWestTerrain) {
        SouthWestTerrain = southWestTerrain;
    }

    public NeighborTerrain getSouthTerrain() {
        return SouthTerrain;
    }

    public void setSouthTerrain(NeighborTerrain southTerrain) {
        SouthTerrain = southTerrain;
    }
}
