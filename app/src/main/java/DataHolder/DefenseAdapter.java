package DataHolder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.team25.team25scouting.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arjun Bansil on 1/22/2016.
 */
public class DefenseAdapter extends RecyclerView.Adapter<DefenseAdapter.DefenseViewHolder> {

    public List<Defense> list;

    public DefenseAdapter(List<Defense> l){
        this.list = l;
    }

    @Override
    public int getItemCount(){
        return list.size();
    }

    public List<Defense> getList(){
        return list;
    }

    @Override
    public void onBindViewHolder(DefenseViewHolder dH, int i){
        final int count = i;
        final Defense d = list.get(i);
        final DefenseViewHolder holder = dH;

        ArrayList<String> ratingList = new ArrayList<String>();
        ratingList.add("0");
        ratingList.add("1");
        ratingList.add("2");
        ratingList.add("3");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(dH.context,
                android.R.layout.simple_spinner_item, ratingList);
        holder.sp.setAdapter(adapter);
        holder.sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();
                list.get(count).setEffectiveness(Integer.parseInt(selectedItem));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        holder.title.setText(d.getName());
        holder.pic.setImageBitmap(d.returnBitmap());
        holder.inc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int b = d.getBreachCount();
                list.get(count).setBreachCount(b+1);
                holder.b_count.setText(list.get(count).getBreachCount() + "");
                if(holder.sp.getSelectedItem().toString().equals("0") && list.get(count).getBreachCount() == 1){
                    holder.sp.setSelection(1, true);
                }else if(holder.sp.getSelectedItem().toString().equals("1") && list.get(count).getBreachCount() == 2){
                    holder.sp.setSelection(2, true);
                }else if(holder.sp.getSelectedItem().toString().equals("2") && list.get(count).getBreachCount() == 3){
                    holder.sp.setSelection(3, true);
                }
            }

        });

        holder.dec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int b = d.getBreachCount();
                if(b>0){
                    list.get(count).setBreachCount(b-1);
                }
                holder.b_count.setText(list.get(count).getBreachCount()+"");
                if(Integer.parseInt(holder.sp.getSelectedItem().toString()) > 0 && list.get(count).getBreachCount() == 0 ){
                    holder.sp.setSelection(0, true);
                }
            }
        });


    }

    @Override
    public DefenseViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.defense_card, viewGroup, false);
        return new DefenseViewHolder(itemView);
    }


    public static class DefenseViewHolder extends RecyclerView.ViewHolder {
        protected TextView title, b_count;
        protected Button inc, dec;
        protected Spinner sp;
        protected ImageView pic;
        private Context context;

        public DefenseViewHolder(View v){
            super(v);
            context = v.getContext();
            title = (TextView)v.findViewById(R.id.def_title);
            pic = (ImageView)v.findViewById(R.id.defense_pic);
            b_count = (TextView)v.findViewById(R.id.breachCount);
            inc = (Button)v.findViewById(R.id.incBCount);
            dec = (Button)v.findViewById(R.id.decBCount);
            sp = (Spinner)v.findViewById(R.id.b_rating);

        }
    }

}
