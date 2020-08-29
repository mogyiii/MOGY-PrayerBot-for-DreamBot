package Factory;

import prayer.MainClass;
public class Factory {
    private prayer.MainClass _main;
    private InteractionUser IU;
    private DoWalk DW;
    private Checking CK;
    private Time Time;
    private InterfaceGraphics IG;
    private AntiBan AB;
    private Areas Areas;
    private AnswareMessage message;
    private GetBone GB;
    private Bury Bury;
    public Factory(MainClass main) {
        this._main = main;
        IU = new InteractionUser(main, this);
        DW = new DoWalk(main, this);
        CK = new Checking(main, this);
        GB = new GetBone(main,this);
        Time = new Time(main, this);
        Bury = new Bury(main,this);
        IG = new InterfaceGraphics(main, this);
        AB = new AntiBan(main, this);
        Areas = new Areas(main, this);
        message = new AnswareMessage(main, this);
    }

    public InteractionUser getIU() {
        return IU;
    }

    public DoWalk getDoWalk() {return DW;}

    public Checking getChecking() {return CK;}

    public Time getTime() {
        return Time;
    }

    public InterfaceGraphics getInterfaceGraphics() {
        return IG;
    }

    public AntiBan getAntiBan() {
        return AB;
    }

    public Areas getAreas() {
        return Areas;
    }

    public MainClass get_mainClass() {
        return _main;
    }

    public AnswareMessage getMessage() {
        return message;
    }

    public GetBone getGetBones() {
        return GB;
    }

    public Bury getBury() {
        return Bury;
    }
}
