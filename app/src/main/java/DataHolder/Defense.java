package DataHolder;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.team25.team25scouting.R;

import java.io.Serializable;

public class Defense implements Serializable{

    private String d_name;
    private int effectiveness = 0;
    private Bitmap bitmap = null;
    private Context context = null;

    public Defense(String d, int e, Context c){
        this.d_name = d;
        this.effectiveness = e;
        this.context = c;
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_not_interested_black_24dp );
    }

    public String getName(){
        return d_name;
    }

    public int getEffectiveness() {
        return effectiveness;
    }

    public Bitmap returnBitmap(){
        return bitmap;
    }
}