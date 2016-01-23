package DataHolder;

import java.io.Serializable;
import java.util.ArrayList;

public class TeleOp implements Serializable{
    private int shotsHigh, shotsLow;
    private ArrayList<Defense> list;
    private boolean towerBreach, towerClimb;
    private String scoring_loc;


    public TeleOp(int sH, int sL, ArrayList<Defense> l, boolean tB, boolean tC, String bS){
        this.shotsHigh = sH;
        this.shotsLow = sL;
        this.list = l;
        this.towerBreach = tB;
        this.towerClimb = tC;
        this.scoring_loc = bS;
    }

    public int getShotsHigh(){
        return shotsHigh;
    }

    public int getShotsLow(){
        return shotsLow;
    }

    public ArrayList<Defense> getList(){
        return list;
    }

    public boolean getTowerBreach(){
        return towerBreach;
    }

    public boolean getTowerClimb(){
        return towerClimb;
    }

    public String getScoringLoc(){
        return scoring_loc;
    }
}