package DataHolder;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.team25.team25scouting.R;

import java.util.List;

/**
 * Created by Arjun Bansil on 1/22/2016.
 */
public class DefenseAdapter extends RecyclerView.Adapter<DefenseAdapter.DefenseViewHolder> {

    private List<Defense> list;

    public DefenseAdapter(List<Defense> l){
        this.list = l;
    }

    @Override
    public int getItemCount(){
        return list.size();
    }

    @Override
    public void onBindViewHolder(DefenseViewHolder dH, int i){
        Defense d = list.get(i);
        dH.title.setText(d.getName());
        dH.pic.setImageBitmap(d.returnBitmap());
    }

    @Override
    public DefenseViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.defense_card, viewGroup, false);
        return new DefenseViewHolder(itemView);
    }


    public static class DefenseViewHolder extends RecyclerView.ViewHolder {
        protected TextView title, count;
        protected Button inc, dec;
        protected Spinner sp;
        protected ImageView pic;

        public DefenseViewHolder(View v){
            super(v);
            title = (TextView)v.findViewById(R.id.def_title);
            pic = (ImageView)v.findViewById(R.id.defense_pic);

        }
    }

}
