package Factory;

import org.dreambot.api.methods.map.Area;
import org.dreambot.api.wrappers.interactive.GameObject;
import prayer.MainClass;

public class DoWalk {
    private MainClass mainClass;
    private Factory _factory;
    public DoWalk(MainClass main, Factory factory) {
        mainClass = main;
        _factory = factory;
    }
    public void walkingtoarea(Area Selectedarea){
        _factory.getIU().SetActivity("Walking to area!");
        mainClass.getWalking().walk(Selectedarea.getCenter().getRandomizedTile(3));
        if(mainClass.getWalking().getRunEnergy() >= 20){
            if(!(mainClass.getWalking().isRunEnabled())){
                mainClass.getWalking().toggleRun();
            }
        }
        if(mainClass.getPlayers().localPlayer().getY() > 3515 && mainClass.getPlayers().localPlayer().getY() < 3520){
            WildernessJumping();
        }
        mainClass.sleep(100, 300);
    }
    public void WildernessJumping(){
        _factory.getIU().SetActivity("Jump!");
        GameObject gap = mainClass.getGameObjects().closest("Wilderness Ditch");
        gap.interact("Cross");
        mainClass.sleep(1000, 1500);
        mainClass.getWidgets().getWidget(475).getChild(11).interact();
        mainClass.sleep(100, 250);
    }
}
