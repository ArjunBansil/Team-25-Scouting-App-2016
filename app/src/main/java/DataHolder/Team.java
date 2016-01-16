package DataHolder;

import java.io.Serializable;

import DataHolder.Intro;
import DataHolder.TeleOp;
import DataHolder.Autonomous;



public class Team implements Serializable{
    private Autonomous auto;
    private Intro intro;
    private PostGame pg;
    private TeleOp teleOp;

    public Team(Intro i, Autonomous a, TeleOp t, PostGame p){
        this.intro = i;
        this.auto = a;
        this.teleOp = t;
        this.pg = p;
    }

    public Autonomous getAuto(){
        return auto;
    }

    public Intro getIntro(){
        return intro;
    }

    public TeleOp getTeleOp(){
        return teleOp;
    }

    public PostGame getPG(){
        return pg;
    }

}