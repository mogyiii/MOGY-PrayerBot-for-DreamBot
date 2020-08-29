package Factory;

import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.map.Tile;
import prayer.MainClass;

//https://explv.github.io/
public class Areas {
    private MainClass mainClass;
    private Factory _factory;
    Area currentArea = new Area();

    public Areas(MainClass main, Factory factory) {
        mainClass = main;
        _factory = factory;
    }
    private Area Chaos_Temple = new Area(
            new Tile[] {
                    new Tile(3234, 3622, 0),
                    new Tile(3245, 3616, 0),
                    new Tile(3249, 3609, 0),
                    new Tile(3246, 3600, 0),
                    new Tile(3237, 3599, 0),
                    new Tile(3231, 3603, 0),
                    new Tile(3229, 3612, 0)
            }
    );
    Area BoneYard = new Area(
            new Tile[] {
                    new Tile(3199, 3743, 0),
                    new Tile(3226, 3755, 0),
                    new Tile(3249, 3755, 0),
                    new Tile(3268, 3742, 0),
                    new Tile(3293, 3722, 0),
                    new Tile(3285, 3713, 0),
                    new Tile(3261, 3720, 0),
                    new Tile(3246, 3731, 0),
                    new Tile(3213, 3724, 0),
                    new Tile(3197, 3729, 0)
            }
    );
    Area SouthGraveYard = new Area(
    new Tile[] {
        new Tile(3155, 3601, 0),
                new Tile(3178, 3604, 0),
                new Tile(3184, 3589, 0),
                new Tile(3178, 3580, 0),
                new Tile(3159, 3579, 0),
                new Tile(3151, 3591, 0)
    }
);
    public Area getCurrentArea() {
        return currentArea;
    }

    public void setCurrentArea(Area currentArea) {
        this.currentArea = currentArea;
    }

    public Area getChaos_Temple() {
        return Chaos_Temple;
    }

    public Area getBoneYard() {
        return BoneYard;
    }

    public Area getSouthGraveYard() {
        return SouthGraveYard;
    }
}
