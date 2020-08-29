package Factory;

import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.walking.path.impl.LocalPath;
import org.dreambot.api.wrappers.interactive.NPC;
import prayer.MainClass;

public class GetBone {
    private MainClass mainClass;
    private Factory _factory;
    public GetBone(MainClass main, Factory factory) {
        mainClass = main;
        _factory = factory;
    }
    public void TakeBones(){
        _factory.getIU().SetActivity("TakeBones");
        String groundItem = _factory.getChecking().GroundcheckBones();
        if(groundItem.isEmpty()){
            mainClass.log(mainClass.getGroundItems().all().get(mainClass.getGroundItems().all().size()-1).toString());
            _factory.getDoWalk().walkingtoarea(new Area());
        }else{
            ClosesItem closesItems = new ClosesItem();
            closesItems.setCost(99);
            for(int i = 0; i < mainClass.getGroundItems().all(groundItem).size(); i++)
            {
                if(closesItems.getCost() > mainClass.getWalking().getAStarPathFinder().calculate(mainClass.getLocalPlayer().getTile(),mainClass.getGroundItems().all(groundItem).get(i).getTile()).size()) {
                    closesItems.setCost(mainClass.getWalking().getAStarPathFinder().calculate(mainClass.getLocalPlayer().getTile(), mainClass.getGroundItems().all(groundItem).get(i).getTile()).size());
                    closesItems.setIndex(i);
                }
            }

            mainClass.getGroundItems().all(groundItem).get(closesItems.getIndex()).interact("Take");
        }
        mainClass.sleep(300, 1000);
    }
    //Do it




    public void banking(){
        _factory.getIU().SetActivity("Banking");
        NPC banker = mainClass.getNpcs().closest(npc -> npc != null && npc.hasAction("Bank"));
        if (banker.interact("Bank")) {
            if (mainClass.sleepUntil(() -> mainClass.getBank().open(), 9000)) {
                mainClass.getBank().withdraw(_factory.getChecking().checkBank(), 28);
                mainClass.sleep(300, 500);
                mainClass.getBank().close();
                mainClass.sleep(200, 3000);
                if(_factory.getChecking().checkBank() == null){
                    _factory.getIU().SetActivity("Bank empty!");
                    mainClass.sleep(200, 3000);
                }
            }
        }
    }
}
class ClosesItem{
    private int Cost;
    private int index;

    public ClosesItem() {
    }

    public int getCost() {
        return Cost;
    }

    public void setCost(int cost) {
        Cost = cost;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
