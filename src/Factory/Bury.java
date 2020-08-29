package Factory;

import org.dreambot.api.methods.map.Area;
import prayer.MainClass;

public class Bury {
    private MainClass mainClass;
    private Factory _factory;
    public Bury(MainClass main, Factory factory) {
        mainClass = main;
        _factory = factory;
    }
    public void buryinBank(Area areana){
        if(!mainClass.getInventory().isEmpty()){
            bury();
            mainClass.sleep(500, 1500);
        }else{
            if(areana.contains(mainClass.getLocalPlayer())){
                _factory.getGetBones().banking();
            }else{
                _factory.getDoWalk().walkingtoarea(areana);
            }
        }
    }


    public void multiBury(Area areana){
        if(mainClass.getInventory().isFull()){
            bury();
            mainClass.sleep(500, 1500);
        }else{
            if(areana.contains(mainClass.getLocalPlayer())){
                if(mainClass.getPlayers().localPlayer().isMoving()){
                    mainClass.sleep(700,1000);
                }else{
                    _factory.getGetBones().TakeBones();
                }
            }else{
                _factory.getDoWalk().walkingtoarea(areana);
            }
        }
    }
    public void bury(){
        _factory.getIU().SetActivity("Bury Bones");
        while(mainClass.getInventory().contains(_factory.getChecking().checkInvertory())){
            if (mainClass.getInventory().contains(_factory.getChecking().checkInvertory())){
                mainClass.getInventory().interact(_factory.getChecking().checkInvertory(), "Bury");
                _factory.getInterfaceGraphics().setBurydBones(_factory.getInterfaceGraphics().getBurydBones() + 1);
                mainClass.sleep(800, 1300);
            }
        }
    }
}
