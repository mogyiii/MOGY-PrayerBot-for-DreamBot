package prayer;

import Factory.Factory;
import GUI.JWindow;
import org.dreambot.api.methods.item.GroundItems;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.skills.Skill;
import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.api.wrappers.interactive.NPC;
import org.dreambot.api.wrappers.items.GroundItem;
import org.dreambot.api.wrappers.items.Item;
import org.dreambot.api.wrappers.widgets.message.Message;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.Math.toIntExact;

@ScriptManifest(category = Category.PRAYER, name = "Mogy holy Prayer", author = "Mogyiii", version = 1.4)
public class MainClass extends AbstractScript {
    private GUI.JWindow window;
    private boolean starter = false;
    private boolean clicked = false;
    private int range = 5;
    private Factory _factory;

    @Override
    public void onStart() {
        super.onStart();
        this._factory = new Factory(this);
        SetWindow(new GUI.JWindow(this));
        getWindow().setVisible(true);
        GetFactory().getTime().setStartTime(System.currentTimeMillis());
        _factory.getAreas().setCurrentArea(new Area(getLocalPlayer().getX() -range,getLocalPlayer().getY() -range,getLocalPlayer().getX() +range,getLocalPlayer().getY()+range));
        log("Started!");
    }
    @Override
    public void onMessage(Message message) {
        if(message.getMessage().contains("You get some")){
        }else if(message.getMessage().contains("I can't reach that!")){
            if(getGameObjects().closest("Wilderness Ditch").exists()){
                GetFactory().getIU().SetActivity( "Jump!");
                GetFactory().getDoWalk().WildernessJumping();
            }
        }else{
            GetFactory().getMessage().Answer(message, window);
        }
    }
    @Override
    public void onPaint(Graphics graphics) {
        GetFactory().getInterfaceGraphics().Drawn(graphics, getWindow());
    }

    @Override
    public int onLoop() {
        if(isStarter()) {
            if(_factory.getIU().isStarting()){
                range = window.getAreaSize();
                _factory.getAreas().setCurrentArea(new Area(getLocalPlayer().getX() -range,getLocalPlayer().getY() -range,getLocalPlayer().getX() +range,getLocalPlayer().getY()+range));
                getSkillTracker().start(Skill.PRAYER);
                _factory.getIU().setStarting(false);
            }
            switch (getWindow().getDo()) {
                case "Chaos Temple":
                    _factory.getBury().multiBury(_factory.getAreas().getChaos_Temple());
                    break;
                case "Bone Yard":
                    _factory.getBury().multiBury(_factory.getAreas().getBoneYard());
                    break;
                case "South GraveYard":
                    _factory.getBury().multiBury(_factory.getAreas().getSouthGraveYard());
                    break;
                case "From Bank":
                    _factory.getBury().buryinBank(getBank().getClosestBankLocation().getCenter().getArea(3));
                    break;
                case "Current area":
                    _factory.getBury().multiBury(_factory.getAreas().getCurrentArea());
                    break;
            }
            GetFactory().getAntiBan().antiBan();
        }
        return ((int) (Math.random() * 200));
    }



    public void setStarter(boolean starter) {
        this.starter = starter;
    }
    public JWindow getWindow() {
        return window;
    }
    private void SetWindow(GUI.JWindow _window){
        window = _window;
    }
    public boolean isStarter() {
        return starter;
    }

    public Factory GetFactory() {
        return _factory;
    }
}
