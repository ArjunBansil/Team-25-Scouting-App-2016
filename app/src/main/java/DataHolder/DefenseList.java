package DataHolder;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by Arjun Bansil on 1/20/2016.
 */
public class DefenseList {
    Context context = null;
    public DefenseList(Context c){
        this.context = c;
        setUp();
    }

    public void setUp(){
        ArrayList<String> list = new ArrayList<String>();
        list.add("Cheval de Frise");
        list.add("Drawbridge");
        list.add("Moat");
        list.add("Portculli");
        list.add("Ramparts");
        list.add("Rock Wall");
        list.add("Rough Terrain");
        list.add("Sally Port");



    }


}
