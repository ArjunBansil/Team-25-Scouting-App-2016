package DataHolder;

/**
 * Created by Arjun Bansil on 1/16/2016.
 */
public class Autonomous {
    String defenses;
    int shotsMadeHigh, shotsMadeLow;
    boolean breach, pastDefense;

    public Autonomous(String d, int sL, int sH, boolean b, boolean pD){
        this.defenses = d;
        this.shotsMadeHigh = sH;
        this.shotsMadeLow = sL;
        this.breach = b;
        this.pastDefense = pD;
    }

    public String getDefenses(){
        return defenses;
    }

    public boolean getBreach(){
        return breach;
    }

    public boolean isPastDefense(){
        return pastDefense;
    }

    public int getShotsMadeHigh(){
        return shotsMadeHigh;
    }

    public int getShotsMadeLow(){
        return shotsMadeLow;
    }
}
