package DataHolder;

import java.io.Serializable;

public class PostGame implements Serializable{
    private String comments;

    public PostGame(String c){
        this.comments = c;
        this.comments = comments.replace(",", " ");
    }

    public String getComments(){
        return comments;
    }
}