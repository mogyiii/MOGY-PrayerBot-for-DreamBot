package Factory;

import org.dreambot.api.methods.skills.Skill;
import prayer.MainClass;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

import static java.lang.Math.toIntExact;

public class InterfaceGraphics {
    private MainClass mainClass;
    private Factory _factory;
    private long second2;
    private int i = 0;
    private String dot = "...";
    private int burydBones = 0;
    private Image mainpaint = getImage("https://i.imgur.com/9NAEERw.png");
    public InterfaceGraphics(MainClass main, Factory factory) {
        mainClass = main;
        _factory = factory;
    }
    public void Drawn(Graphics graphics, GUI.JWindow window){
        graphics.drawImage(mainpaint, 2, 341, null);
        Font font = new Font("Arial", Font.PLAIN, 12);
        graphics.setFont(font);
        graphics.setColor(new Color(0, 120, 242));

        graphics.drawString("Buried Bones: " + burydBones, 10, 355);
        graphics.drawString("Time running: " + _factory.getTime().eclapsedtime(_factory.getTime().getStartTime()), 10, 370);
        graphics.drawString("Prayer level: " + mainClass.getSkills().getRealLevel(Skill.PRAYER) + " (" + (mainClass.getSkills().getRealLevel(Skill.PRAYER) - mainClass.getSkillTracker().getStartLevel(Skill.PRAYER)) + ")", 10, 385);
        graphics.drawString("Bury/hour: " + burydBones * (int) (3600D / _factory.getTime().eclapsedsec(_factory.getTime().getStartTime())), 10, 400);
        graphics.drawString("Estimated Xp/hour: " + mainClass.getSkillTracker().getGainedExperiencePerHour(Skill.PRAYER), 10, 415);
        graphics.drawString("Next level: " + mainClass.getSkillTracker().getTimeToLevel(Skill.PRAYER) / 60000 + "(Minutes)", 10, 430);
        graphics.drawString("Xp gained: " + (mainClass.getSkillTracker().getGainedExperience(Skill.PRAYER)), 10, 445);
        graphics.drawString("Xp remaining: " + (mainClass.getSkills().getExperienceToLevel(Skill.PRAYER)), 10, 460);
        graphics.drawString(_factory.getIU().getActivity() + dot(), 230, 472);
    }
    private Image getImage(String url){
        try{
            return ImageIO.read(new URL(url));
        }catch(IOException e){
            return null;
        }
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

    public int getBurydBones() {
        return burydBones;
    }

    public void setBurydBones(int burydBones) {
        this.burydBones = burydBones;
    }
}
