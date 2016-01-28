package DataHolder;

import java.io.Serializable;

/**
 * Created by Arjun Bansil on 1/13/2016.
 */
public class Intro implements Serializable {
    int matchNum, teamNum;
    String scoutName;

    public Intro(int m, int t, String s){
        this.scoutName = s;
        this.matchNum = m;
        this.teamNum = t;
    }

    public int getMatchNum(){
        return matchNum;
    }

    public int getTeamNum(){
        return teamNum;
    }

    public String getScoutName(){return scoutName;}
}
