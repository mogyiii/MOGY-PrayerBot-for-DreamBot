package Factory;

import prayer.MainClass;

public class Checking {
    private Factory _factory;
    private MainClass mainClass;
    public Checking(MainClass main, Factory factory) {
        mainClass = main;
        _factory = factory;
    }
    public String GroundcheckBones(){
        String[] Bones = {"Searing ashes", "Reinforced dragon bones", "Frost dragon bones", "Hardened dragon bones", "Ourg bones", "Ourg bones", "Airut bones", "Dagannoth bones", "Raurg bones","Tortured ashes", "Fayrg bones", "Dragon bones", "Infernal ashes", "Wyvern bones", "Baby dragon bones", "Shaikahan bones", "Zogre bones", "Jogre bones", "Big bones", "Accursed ashes", "Bat bones","Monkey bones", "Burnt bones", "Wolf bones", "Bones", "Impious ashesImpious ashes"};
        for(int i = 0; i < Bones.length; i++) {
            for(int j = 0; j < mainClass.getGroundItems().all().size(); j++){
                if(mainClass.getGroundItems().all().get(j).toString().equals(Bones[i])){
                    return Bones[i];
                }
            }

        }
        return "";
    }
    public String checkInvertory(){
        String[] Bones = {"Searing ashes", "Reinforced dragon bones", "Frost dragon bones", "Hardened dragon bones", "Ourg bones", "Ourg bones", "Airut bones", "Dagannoth bones", "Raurg bones","Tortured ashes", "Fayrg bones", "Dragon bones", "Infernal ashes", "Wyvern bones", "Baby dragon bones", "Shaikahan bones", "Zogre bones", "Jogre bones", "Big bones", "Accursed ashes", "Bat bones","Monkey bones", "Burnt bones", "Wolf bones", "Bones", "Impious ashesImpious ashes"};
        for(int i = 0; i < Bones.length; i++) {
            if (mainClass.getInventory().contains(Bones[i])){
                return Bones[i];
            }
        }
        return  "";
    }
    public String checkBank(){
        String[] Bones = {"Searing ashes", "Reinforced dragon bones", "Frost dragon bones", "Hardened dragon bones", "Ourg bones", "Ourg bones", "Airut bones", "Dagannoth bones", "Raurg bones","Tortured ashes", "Fayrg bones", "Dragon bones", "Infernal ashes", "Wyvern bones", "Baby dragon bones", "Shaikahan bones", "Zogre bones", "Jogre bones", "Big bones", "Accursed ashes", "Bat bones","Monkey bones", "Burnt bones", "Wolf bones", "Bones", "Impious ashesImpious ashes"};
        for(int i = 0; i < Bones.length; i++) {
            if (mainClass.getBank().contains(Bones[i])){
                return Bones[i];
            }
        }
        return  "";
    }
}
