import java.io.Serializable;

public class Team implements Serializable{
    private Auto auto;
    private Intro intro;
    private PostGame pg;
    private TeleOp teleOp;

    public Team(Intro i, Auto a, TeleOp t, PostGame p){
        this.intro = i;
        this.auto = a;
        this.teleOp = t;
        this.pg = p;
    }

    public Auto getAuto(){
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