package Factory;

import org.dreambot.api.methods.skills.Skill;
import prayer.MainClass;

import java.util.Random;

public class AntiBan {
    private MainClass mainClass;
    private Factory _factory;
    public AntiBan(MainClass main, Factory factory) {
        mainClass = main;
        _factory = factory;
    }
    public void antiBan() {
        Random srand = new Random();
        double chances = srand.nextDouble();
        if (chances < 0.119) {
            _factory.getIU().SetActivity("Anti-ban: Changing Camera angle");
            mainClass.getCamera().rotateToEvent(srand.nextInt() + 360, srand.nextInt() + 90);
        }else if(chances > 0.120 && chances < 0.192){
            _factory.getIU().SetActivity("Anti-ban: Changing mouse position");
            mainClass.getMouse().move();
        }else if(chances > 0.192 && chances < 0.194){
            _factory.getIU().SetActivity("Anti-ban: Random sleeping");
            mainClass.sleep(10000, 50000);
        }
        else if(chances > 0.200 && chances < 0.256){
            _factory.getIU().SetActivity("Anti-ban: Checking skill XP");
            mainClass.getSkills().open();
            mainClass.sleep(100, 500);
            mainClass.getSkills().hoverSkill(Skill.PRAYER);
            mainClass.sleep(1000, 1500);
        }else if(chances > 0.296 && chances < 0.350){
            _factory.getIU().SetActivity("Anti-ban: Move cursor Outside Screen");
            mainClass.getMouse().moveMouseOutsideScreen();
            mainClass.sleep(2888, 5111);
            mainClass.getMouse().isMouseInScreen();
        }else if(chances > 0.350 && chances < 0.355){
            //getWidgets().
            _factory.getIU().SetActivity("Anti-ban: Open Random tab");
            mainClass.sleep(200, 500);
        }else if(chances > 0.355 && chances < 0.360){
            if(!mainClass.getWindow().getWhopper()) {
                _factory.getIU().SetActivity("Anti-ban: Hop world");

                if (!mainClass.getClient().isMembers()) {
                    mainClass.getWorldHopper().hopWorld(mainClass.getWorlds().f2p().get(srand.nextInt(mainClass.getWorlds().f2p().size())).getID(), mainClass.getWorldHopper().openWorldHopper());
                    mainClass.getWorldHopper().isWorldHopperOpen();
                    mainClass.sleep(5000, 7000);
                } else {
                    mainClass.getWorldHopper().hopWorld(mainClass.getWorlds().members().get(srand.nextInt(mainClass.getWorlds().f2p().size())).getID(), mainClass.getWorldHopper().openWorldHopper());
                    mainClass.getWorldHopper().isWorldHopperOpen();
                    mainClass.sleep(5000, 7000);
                }
            }
        }
    }
}
