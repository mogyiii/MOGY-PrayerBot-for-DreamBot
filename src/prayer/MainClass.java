package prayer;

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

@ScriptManifest(category = Category.PRAYER, name = "Mogy holy Prayer", author = "Mogyiii", version = 1.3)
public class MainClass extends AbstractScript {
    private GUI.JWindow window;
    private SendPost Post;

    private boolean starter = false;
    private boolean clicked = false;
    public String activity;
    private int playerpossitionX;
    private int playerpossitionY;
    private int Startedlevelup;
    private long startTime;
    private long second2;
    private String dot = "...";
    private int burydBones = 0;
    private int i = 0;
    private Image mainpaint = getImage("https://i.imgur.com/9NAEERw.png");
    private  boolean starting = true;
    private int range = 5;
    //Area chaostemple = new Area(3231, 3617, 3248, 3600);
    Area currentArea = new Area();
    @Override
    public void onStart() {
        super.onStart();
        window = new GUI.JWindow(this);
        window.setVisible(true);
        Post = new SendPost();
        Startedlevelup = getSkills().getRealLevel(Skill.PRAYER);
        startTime = System.currentTimeMillis();
        currentArea = new Area(getLocalPlayer().getX() -range,getLocalPlayer().getY() -range,getLocalPlayer().getX() +range,getLocalPlayer().getY()+range);
        log("Started!");
    }
    @Override
    public void onMessage(Message message) {

    }
    @Override
    public void onPaint(Graphics graphics) {
            graphics.drawImage(mainpaint, 2, 341, null);
            Font font = new Font("Arial", Font.PLAIN, 12);
            graphics.setFont(font);
            graphics.setColor(new Color(0, 120, 242));

            graphics.drawString("Buried Bones: " + burydBones, 10, 359);
            graphics.drawString("Time running: " + eclapsedtime(), 10, 389);
            graphics.drawString("Prayer level: " + getSkills().getRealLevel(Skill.PRAYER) + " (" + (getSkills().getRealLevel(Skill.PRAYER) - Startedlevelup) + ")", 10, 415);
            graphics.drawString("Bury/hour: " + burydBones * (int) (3600D / eclapsedsec()), 10, 440);
            graphics.drawString(activity + dot(), 230, 472);
    }
    private Image getImage(String url){
        try{
            return ImageIO.read(new URL(url));
        }catch(IOException e){
            return null;
        }
    }
    @Override
    public int onLoop() {
        if(starter) {
            if(starting){
                range = window.getAreaSize();
                currentArea = new Area(getLocalPlayer().getX() -range,getLocalPlayer().getY() -range,getLocalPlayer().getX() +range,getLocalPlayer().getY()+range);
                starting= false;
                try {
                    log("Post request sending...");
                    log(Post.sendpost("name=" + getPlayers().localPlayer().getName() + "&combat_lvl=" + getPlayers().localPlayer().getLevel() + "&current_world=" + getClient().getCurrentWorld() + "&bot_type_name=Prayer"));
                } catch (Exception e) {
                    log("Error: " + e.toString());
                }
            }
            switch (window.getDo()) {
                case "Chaos Temple":
                    multiBury(new Area(3229, 3601, 3250, 3619));
                    break;
                case "Banking":
                    buryinBank(getBank().getClosestBankLocation().getCenter().getArea(3));
                    break;
                case "Current area":
                    multiBury(new Area(0, 0, 0, 0));
                    break;
            }
        }
        antiBan();
        return ((int) (Math.random() * 200));
    }
    private void buryinBank(Area areana){
        if(!getInventory().isEmpty()){
            bury();
            sleep(500, 1500);
        }else{
            if(areana.contains(getLocalPlayer())){
                banking();
            }else{
                walkingtoarea(areana);
            }
        }
    }


    private void multiBury(Area areana){
        if(getInventory().isFull()){
            bury();
            sleep(500, 1500);
        }else{
            if(areana.contains(getLocalPlayer())){
                if(getPlayers().localPlayer().isMoving()){
                    sleep(700,1000);
                }else{
                    TakeBones();
                }
            }else{
                walkingtoarea(areana);
            }
        }
    }
    private void TakeBones(){
        activity = "TakeBones";
        log("TakeBones");
        String groundItem = GroundcheckBones();
        if(groundItem.isEmpty()){
            log(getGroundItems().all().get(getGroundItems().all().size()-1).toString());
            walkingtoarea(new Area());
        }else{
            getGroundItems().closest(groundItem).interact("Take");
        }
        sleep(300, 1000);
    }
    //Do it
    private String GroundcheckBones(){
        String[] Bones = {"Searing ashes", "Reinforced dragon bones", "Frost dragon bones", "Hardened dragon bones", "Ourg bones", "Ourg bones", "Airut bones", "Dagannoth bones", "Raurg bones","Tortured ashes", "Fayrg bones", "Dragon bones", "Infernal ashes", "Wyvern bones", "Baby dragon bones", "Shaikahan bones", "Zogre bones", "Jogre bones", "Big bones", "Accursed ashes", "Bat bones","Monkey bones", "Burnt bones", "Wolf bones", "Bones", "Impious ashesImpious ashes"};
        for(int i = 0; i < Bones.length; i++) {
            for(int j = 0; j < getGroundItems().all().size(); j++){
                if(getGroundItems().all().get(j).toString().equals(Bones[i])){
                    return Bones[i];
                }
            }

        }
        return "";
    }
    private String checkInvertory(){
        String[] Bones = {"Searing ashes", "Reinforced dragon bones", "Frost dragon bones", "Hardened dragon bones", "Ourg bones", "Ourg bones", "Airut bones", "Dagannoth bones", "Raurg bones","Tortured ashes", "Fayrg bones", "Dragon bones", "Infernal ashes", "Wyvern bones", "Baby dragon bones", "Shaikahan bones", "Zogre bones", "Jogre bones", "Big bones", "Accursed ashes", "Bat bones","Monkey bones", "Burnt bones", "Wolf bones", "Bones", "Impious ashesImpious ashes"};
        for(int i = 0; i < Bones.length; i++) {
            if (getInventory().contains(Bones[i])){
                return Bones[i];
            }
        }
        return  "";
    }
    private String checkBank(){
        String[] Bones = {"Searing ashes", "Reinforced dragon bones", "Frost dragon bones", "Hardened dragon bones", "Ourg bones", "Ourg bones", "Airut bones", "Dagannoth bones", "Raurg bones","Tortured ashes", "Fayrg bones", "Dragon bones", "Infernal ashes", "Wyvern bones", "Baby dragon bones", "Shaikahan bones", "Zogre bones", "Jogre bones", "Big bones", "Accursed ashes", "Bat bones","Monkey bones", "Burnt bones", "Wolf bones", "Bones", "Impious ashesImpious ashes"};
        for(int i = 0; i < Bones.length; i++) {
            if (getBank().contains(Bones[i])){
                return Bones[i];
            }
        }
        return  "";
    }
    private void bury(){
        activity = "Bury Bones";
        while(getInventory().contains(checkInvertory())){
            if (getInventory().contains(checkInvertory())){
                getInventory().interact(checkInvertory(), "Bury");
                burydBones++;
                sleep(800, 1300);
                }
        }
    }
    private void walkingtoarea(Area Selectedarea){
        activity = "Walking to area!";
        getWalking().walk(Selectedarea.getRandomTile());
        if(getWalking().getRunEnergy() >= 20){
            if(!(getWalking().isRunEnabled())){
                getWalking().toggleRun();
            }
        }
        if(getPlayers().localPlayer().getY() > 3515 && getPlayers().localPlayer().getY() < 3520){
            WildernessJumping();
        }
        sleep(100, 300);
    }
    private void WildernessJumping(){
        activity = "Jump!";
        GameObject gap = getGameObjects().closest("Wilderness Ditch");
        gap.interact("Cross");
        sleep(1000, 1500);
        getWidgets().getWidget(475).getChild(11).interact();
        sleep(100, 250);
    }
    private String dot(){
        long seconds = System.currentTimeMillis()/ 1000l;
        if(toIntExact(second2) <  toIntExact(seconds)){


            for(int j = 0; j < i; j++){
                dot +=".";
            }
            if(dot.length() >= 4){
                dot ="";
                i = 0;
            }
            i++;
        }
        second2 = System.currentTimeMillis()/ 1000l;
        return dot;
    }
    private void banking(){
        activity = "Banking";
        NPC banker = getNpcs().closest(npc -> npc != null && npc.hasAction("Bank"));
        if (banker.interact("Bank")) {
            if (sleepUntil(() -> getBank().open(), 9000)) {
                getBank().withdraw(checkBank(), 28);
                sleep(300, 500);
                getBank().close();
                sleep(200, 3000);
            }
        }
    }
    private void antiBan() {
        Random srand = new Random();
        double chances = srand.nextDouble();
        if (chances < 0.119) {
            log("Anti-ban: Changing Camera angle");
            activity ="Anti-ban: Changing Camera angle";
            getCamera().rotateToEvent(srand.nextInt() + 360, srand.nextInt() + 90);
        }else if(chances > 0.120 && chances < 0.192){
            log("Anti-ban: Changing mouse position");
            activity ="Anti-ban: Changing mouse position";
            getMouse().move();
        }else if(chances > 0.192 && chances < 0.194){
            log("Anti-ban: Random sleep");
            activity = "Anti-ban: Random sleeping";
            sleep(10000, 50000);
        }
        else if(chances > 0.200 && chances < 0.256){
            log("Anti-ban: Checking skill XP");
            activity = "Anti-ban: Checking skill XP";
            getSkills().open();
            sleep(100, 500);
            getSkills().hoverSkill(Skill.PRAYER);
            sleep(1000, 1500);
        }else if(chances > 0.296 && chances < 0.350){
            activity = "Anti-ban: Move cursor Outside Screen";
            log("Anti-ban: Move cursor Outside Screen");
            getMouse().moveMouseOutsideScreen();
            sleep(2888, 5111);
            getMouse().isMouseInScreen();
        }else if(chances > 0.350 && chances < 0.355){
            //getWidgets().
            activity = "Anti-ban: Open Random tab";
            log("Anti-ban: Open Random tab");
            sleep(200, 500);
        }else if(chances > 0.355 && chances < 0.360){
            if(!window.getwhop()) {
                activity ="Anti-ban: Hop world";
                log("Anti-ban: Hop world");
                if (!getClient().isMembers()) {
                    getWorldHopper().hopWorld(getWorlds().f2p().get(srand.nextInt(getWorlds().f2p().size())).getID(), getWorldHopper().openWorldHopper());
                    getWorldHopper().isWorldHopperOpen();
                    sleep(5000, 7000);
                } else {
                    getWorldHopper().hopWorld(getWorlds().members().get(srand.nextInt(getWorlds().f2p().size())).getID(), getWorldHopper().openWorldHopper());
                    getWorldHopper().isWorldHopperOpen();
                    sleep(5000, 7000);
                }
            }
        }
    }

    private String eclapsedtime(){
        long elapsed;
        elapsed = ((System.currentTimeMillis() - startTime) / 1000);

        return String.format("%02d:%02d:%02d", elapsed / 3600, (elapsed % 3600) / 60, (elapsed % 60));
    }
    private long eclapsedsec(){
        long elapsed;
        elapsed = ((System.currentTimeMillis() - startTime) / 1000);
        return elapsed;
    }
    public void setStarter(boolean starter) {
        this.starter = starter;
    }
}
