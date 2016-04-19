package DataHolder;

import java.io.Serializable;

public class PostGame implements Serializable{
    private String comments, status;

    public PostGame(String c, String s){
        this.comments = c;
        this.comments = comments.replace(",", " ");
        this.status = s;
    }

    public String getComments(){
        return comments;
    }

    public String getStatus(){
        return status;
    }
}