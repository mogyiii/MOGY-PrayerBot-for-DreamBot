package Route;

import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.map.Tile;
import prayer.MainClass;
import java.util.Random;
public class RouteAlgorithm {
    private Area _CurrentArea;
    private Terrain[] _Terrain;
    private prayer.MainClass mainClass;
    private boolean Running;
    private boolean Stopped = false;
    private Tile MainTerrain;
    private Tile PlayerRefleshedTile;
    public Tile PlayerMomentaryPosition;
    private Tile FlagPosition;
    public RouteAlgorithm(Area currentArea, MainClass main) {
        _CurrentArea = currentArea;
        this.mainClass = main;
        Running = true;
    }
    public boolean isRunning() {
        return Running;
    }
    public void setRunning(boolean running) {
        Running = running;
    }
    public Tile getPlayerMomentaryPosition() {
        return PlayerMomentaryPosition;
    }
    public void setPlayerMomentaryPosition(Tile playerMomentaryPosition) {
        PlayerMomentaryPosition = playerMomentaryPosition;
    }
    public Tile getMainTerrain() {
        return MainTerrain;
    }
    public void setMainTerrain(Tile mainTerrain) {
        MainTerrain = mainTerrain;
    }

    public void Learn(){
        int indexTerrain = 0;
        _Terrain = new Terrain[_CurrentArea.getPolygonArea().getBounds().width * _CurrentArea.getPolygonArea().getBounds().height];
        outerloop:{
        for(int i = 0; i < _CurrentArea.getPolygonArea().getBounds().width - 2; i++){//Xcor horizontal
            for(int j = 0; j < _CurrentArea.getPolygonArea().getBounds().height - 2; j++){//YCor vertical
                _Terrain[indexTerrain] = new Terrain((int)_CurrentArea.getPolygonArea().getBounds().getX() + i, (int)_CurrentArea.getPolygonArea().getBounds().getY() + j);
                mainClass.log("X: " + _Terrain[indexTerrain].getXcor() + " Y: " + _Terrain[indexTerrain].getYcor());
                BuildPlayerPositionTile();
                Sleep(100, 201);
                BuildTile(_Terrain[indexTerrain].getXcor(), _Terrain[indexTerrain].getYcor());
                while(true){//Walk start Position(MainTerrain)
                    CameraPosition();
                    if(CheckArrived(_Terrain[indexTerrain].getXcor(), _Terrain[indexTerrain].getYcor(), "Arrived")){
                        break;
                    }
                    if(!mainClass.getMap().canReach(MainTerrain)){
                        _Terrain[indexTerrain].setAvailable(false);
                        mainClass.log("unAvailable");
                        break;
                    }
                    CheckMissClick(_Terrain[indexTerrain].getXcor(), _Terrain[indexTerrain].getYcor(), "Go to the terrain");
                    BuildPlayerPositionTile();
                    Sleep(200, 300);
                    if(/*mainClass.isExit() ||*/ Stopped){
                        mainClass.log("Exit");
                        Stopped = true;
                        break outerloop;
                    }
                }
                CheckNeighbor(_Terrain[indexTerrain]);
                indexTerrain++;
            }
        }
        }
        Running = false;
    }
    private void BuildPlayerPositionTile(){
        PlayerMomentaryPosition = new Tile();
        PlayerMomentaryPosition.setX(mainClass.getLocalPlayer().getX());
        PlayerMomentaryPosition.setY(mainClass.getLocalPlayer().getY());
    }
    private void BuildTile(int x, int y){
        MainTerrain = new Tile();
        MainTerrain.setX(x);
        MainTerrain.setY(y);
        MainTerrain.setZ(0);
        GoTo(MainTerrain);
    }
    private void GoTo(Tile go){
        mainClass.getWalking().walkOnScreen(go);
    }
    private void CheckNeighbor(Terrain terrain){
        NeighborTerrain[] NeighborArray = new NeighborTerrain[]{terrain.getNorthTerrain(), terrain.getNorthEastTerrain(), terrain.getEastTerrain(), terrain.getSouthEastTerrain(), terrain.getSouthTerrain(), terrain.getSouthWestTerrain(), terrain.getWestTerrain(), terrain.getNorthWestTerrain()};
        outerloop:{
            for(int i = 0; i < NeighborArray.length; i++){
                BuildPlayerPositionTile();
                PlayerRefleshedTile = PlayerMomentaryPosition;
                Sleep(600, 700);
                BuildTile(NeighborArray[i].getXcor(),NeighborArray[i].getYcor());
                NeighborArray[i].setCost(1);
                while(true){//Check position available
                    CheckMissClick(NeighborArray[i].getXcor(), NeighborArray[i].getYcor(), "Go to the terrain");
                    if(mainClass.getLocalPlayer().isMoving() && (mainClass.getLocalPlayer().getX() != NeighborArray[i].getXcor() || mainClass.getLocalPlayer().getY() != NeighborArray[i].getYcor()) && MainTerrain.getArea(1).contains(FlagPosition)) {//Cost
                        if(!(PlayerRefleshedTile.toString().equals(PlayerMomentaryPosition.toString()))){
                            mainClass.log("Refleshed: " + PlayerRefleshedTile.toString() + " Momentary" + PlayerMomentaryPosition.toString() + (PlayerRefleshedTile.toString().equals(PlayerMomentaryPosition.toString())));
                            NeighborArray[i].setCost(NeighborArray[i].getCost() + 1);
                            mainClass.log("Cost: " + NeighborArray[i].getCost());
                            PlayerRefleshedTile = PlayerMomentaryPosition;
                        }
                    }
                    CameraPosition();
                    if(/*mainClass.isExit()|| */mainClass.isPaused() || Stopped){
                        mainClass.log("Exit");
                        Stopped = true;
                        break outerloop;
                    }
                    if(CheckArrived(NeighborArray[i].getXcor(), NeighborArray[i].getYcor(), "Neighbor Arrived")){
                        break;
                    }
                    if(!mainClass.getMap().canReach(MainTerrain)){
                        NeighborArray[i].setCost(4);
                        mainClass.log("I Can't");
                        Sleep(400, 500);
                        break;
                    }
                    BuildPlayerPositionTile();
                }
                MoveBackTerrain(terrain);
            }
        }
    }
    private boolean CheckArrived(int x, int y, String msg){
        if(mainClass.getLocalPlayer().getX() == x && mainClass.getLocalPlayer().getY() == y){
            mainClass.log(msg);
            Sleep(400, 550);
            return true;
        }
        return false;
    }
    private void MoveBackTerrain(Terrain terrain){
        if(terrain.isAvailable()){//ResetMainTerrain
            BuildTile(terrain.getXcor(),terrain.getYcor());
            while (mainClass.getLocalPlayer().getX() != terrain.getXcor() && mainClass.getLocalPlayer().getY() != terrain.getYcor()){
                Sleep(500, 650);
            }
        }
    }
    private void CheckMissClick(int x, int y, String msg){
        if(mainClass.getWalking().getDestination() != null){
            FlagPosition = mainClass.getWalking().getDestination();
        }
        if(!mainClass.getLocalPlayer().isMoving() && (mainClass.getLocalPlayer().getX() != x || mainClass.getLocalPlayer().getY() != y) || !MainTerrain.getArea(1).contains(FlagPosition)) {
            mainClass.log("Miss clicked Destination" + mainClass.getWalking().getDestination() + " MainTerrain " + MainTerrain);
            Sleep(200, 201);
            BuildTile(x,y);
            mainClass.log(msg);
        }
    }
    private void CameraPosition(){
        if((mainClass.getCamera().getPitch() < 225 || mainClass.getCamera().getPitch() > 230) || mainClass.getCamera().getYaw() != 0){//Camera position
            mainClass.getWidgets().getWidget(548).getChild(10).interact();
        }
    }
    private void Sleep(int min, int max){
        Random r = new Random();
        int time = r.nextInt(max) + min;
        try {

            Thread.sleep(time);
        } catch(InterruptedException e) {
            System.out.println("got interrupted!");
        }
    }
}
