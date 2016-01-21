package DataHolder;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Arjun Bansil on 1/20/2016.
 */
public class DefenseList {
    Context context = null;
    private ArrayList<Defense> d_list;
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

        ArrayList<Defense> defenses = new ArrayList<Defense>();

        try{
            for(int i = 0; i < list.size(); i++){
                InputStream ims = context.getAssets().open(list.get(i) + ".png");
                Drawable d = Drawable.createFromStream(ims, null);
                Bitmap b = ((BitmapDrawable)d).getBitmap();
                Defense temp = new Defense(list.get(i), 0, 0, context);
                temp.setBitmap(b);
                defenses.add(temp);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        this.d_list = defenses;

    }

    public ArrayList<Defense> getD_list(){
        return d_list;
    }


}
