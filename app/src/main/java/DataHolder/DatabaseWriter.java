package DataHolder;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Arjun Bansil on 1/26/2016.
 */
public class DatabaseWriter {

    private Team team;
    private FileWriter writer;
    private Autonomous auto;
    private Intro intro;
    private TeleOp tele;
    private PostGame post;

    private static String Key_Team = "Team Number";
    private static String Key_Num = "Match Number";
    private static String Key_S_Name = "Scout Name";
    private static String Key_Def = "Name of Defense";
    private static String Key_DefAuto = "Defense Breached (Autonomous)";
    private static String Key_ReachDef = "Defenses Reached (Autonomous)";
    private static String Key_ShotsHiAuto = "Shots Made High (Autonomous)";
    private static String Key_ShotsLowAuto = "Shots Made Low (Autonomous)";
    private static String Key_ShotsHiTele = "Shots Made High (TeleOp)";
    private static String Key_ShotsLowTele = "Shots Made Low (TeleOp)";
    private static String Key_DefStatus = "Defenses Breached/Effectiveness (TeleOp)";
    private static String cheval_B = "Cheval de Frise Breach Count";
    private static String cheval_E = "Cheval de Frise Effectiveness";
    private static String d_bridge_B = "Drawbridge Breach Count";
    private static String d_bridge_E = "Drawbridge Effectiveness";
    private static String l_bar_B = "Low Bar Breach Count";
    private static String l_bar_E = "Low Bar Effectiveness";
    private static String moat_b = "Moat Breach Count";
    private static String moat_e = "Moat Effectiveness";
    private static String p_culli_b = "Portculli Breach Count";
    private static String p_culli_e = "Portculli Effectiveness";
    private static String rampart_b = "Ramparts Breach Count";
    private static String rampart_e = "Ramparts Effectiveness";
    private static String r_wall_b = "Rock Wall Breach Count";
    private static String r_wall_e = "Rock Wall Effectiveness";
    private static String r_terrain_b = "Rough Terrain Breach Count";
    private static String r_terrain_e = "Rough Terrain Effectiveness";
    private static String s_port_b = "Sally Port Breach Count";
    private static String s_port_e = "Sally Port Effectiveness";
    private static String Key_DefPresent = "Defenses Present on the Field";
    private static String Key_ScoringLoc = "Scoring Locations";
    private static String Key_TowerBreach = "Tower Breach";
    private static String Key_TowerClimb = "Tower Climb";
    private static String Key_Comments = "Comments";
    private static String comma = ",";
    private Context context = null;

    public DatabaseWriter(Team t, Context c){
        this.team = t;
        this.auto = team.getAuto();
        this.tele = team.getTeleOp();
        this.intro = team.getIntro();
        this.post = team.getPG();
        this.context = c;
    }

    private void newLine(){
        try{
            writer.write(System.lineSeparator());
        }catch(FileNotFoundException e){
            Log.i("tag", "FileNotFoundException occurred at New Line ");
        }catch(IOException e){
            Log.i("tag", "IOException Occurred at New Line");
        }
    }
    /*
    public void write(){
        try{
            File directory = new File(Environment.getExternalStorageDirectory().getAbsolutePath(),"Scouting App");
            if(!directory.exists()){
                directory.mkdir();
                Log.i("tag", "Directoy has been created holmes");
            }else {
                Log.i("tag", "Directory is already here fam");
            }
            File file = new File(directory, "ScoutingInfo.csv");
            Log.i("tag", "File is created");
            writer = new FileWriter(file, true);
            Log.i("tag", "We created all of the stuff");

            ArrayList<Defense> list = tele.getList();
            String info="";
            for(int i = 0; i < list.size(); i++){
                if(list.get(i).getEffectiveness() != 0 && list.get(i).getBreachCount() != 0){
                    String name = list.get(i).getName();
                    name.replace(" ", "-");
                    String addition = name + "-BC:" + list.get(i).getBreachCount() + " E:" + list.get(i).getEffectiveness()+" ";
                    info+=addition;
                }
            }

            String f ="";
            for(int i = 0; i<list.size(); i++){
                int j = list.size()-i;
                String name = list.get(i).getName();
                name.replace(" ", "-");
                if(j == 1){
                    f+=name;
                }else {
                    f+=name + " ";
                }
            }

            if(file.length()==0){
                writer.write(Key_Team + comma + Key_Num + comma + Key_S_Name + comma + Key_Def + comma + Key_DefAuto + comma + Key_ReachDef + comma + Key_ShotsHiAuto + comma
                        +Key_ShotsLowAuto + comma + Key_ShotsHiTele + comma + Key_ShotsLowTele + comma + Key_DefStatus + comma +
                        Key_DefPresent + comma + Key_ScoringLoc + comma + Key_TowerBreach + comma +Key_TowerClimb +
                        comma + Key_Comments);
                Log.i("tag", "We created the header");
                newLine();
            }

            writer.write(intro.getTeamNum() + comma + intro.getMatchNum() + comma + intro.getScoutName() + comma + auto.getDefenses()
            +comma + auto.getBreach() + comma + auto.isPastDefense() + comma + auto.getShotsMadeHigh() + comma + auto.getShotsMadeLow()
                    + comma + tele.getShotsHigh() + comma + tele.getShotsLow() + comma + info + comma + f + comma
                    +tele.getScoringLoc() +  comma + tele.getTowerBreach() + comma + tele.getTowerClimb()
                + comma + post.getComments());
            newLine();


            writer.flush();
            writer.close();

        }catch (Exception e){
            e.printStackTrace();
            Log.e("tag", "Something went horribly wrong +\n " + e.toString());
        }
    }
    */

    public void write(){
        try{
            File directory = new File(Environment.getExternalStorageDirectory().getAbsolutePath(),"Scouting App");
            if(!directory.exists()){
                directory.mkdir();
                Log.i("tag", "Directoy has been created holmes");
            }else {
                Log.i("tag", "Directory is already here fam");
            }
            File file = new File(directory, "ScoutingInfo.csv");
            Log.i("tag", "File is created");
            writer = new FileWriter(file, true);
            Log.i("tag", "We created all of the stuff");

            ArrayList<Defense> list = tele.getList();

            ArrayList<Defense> f_list = new ArrayList<Defense>();
            f_list.add(new Defense("Cheval de Frise", 0,0,context));
            f_list.add(new Defense("Drawbridge", 0,0,context));
            f_list.add(new Defense("Moat", 0,0,context));
            f_list.add(new Defense("Portculli",0,0,context ));
            f_list.add(new Defense("Ramparts",0,0, context));
            f_list.add(new Defense("Rock Wall", 0,0, context));
            f_list.add(new Defense("Rough Terrain",0,0,context));
            f_list.add(new Defense("Sally Port",0,0,context));
            f_list.add(new Defense("Low Bar",0,0,context));

            for(int i = 0; i< f_list.size();i++){
                Defense temp = f_list.get(i);
                for(int j = 0; j < list.size(); j++){
                    if(list.get(j).getName().equals(temp.getName())){
                        f_list.get(i).setEffectiveness(list.get(j).getEffectiveness());
                        f_list.get(i).setBreachCount(list.get(j).getBreachCount());
                    }
                }
            }

            String info = "";

            for(int i = 0;i<f_list.size();i++){
                int bc = f_list.get(i).getBreachCount();
                int e = f_list.get(i).getEffectiveness();
                info+=bc+","+e+",";
            }

            if(file.length()==0){
                writer.write(Key_Team + comma + Key_Num + comma + Key_S_Name + comma + Key_Def + comma + Key_DefAuto + comma + Key_ReachDef +
                        comma + Key_ShotsHiAuto + comma + Key_ShotsLowAuto + comma + Key_ShotsHiTele + comma + Key_ShotsLowTele + comma +
                        cheval_B + comma + cheval_E + comma + d_bridge_B + comma + d_bridge_E + comma
                        + moat_b + comma + moat_e + comma + p_culli_b + comma + p_culli_e + comma + rampart_b + comma + rampart_e + comma +
                        r_wall_b + comma + r_wall_e + comma + r_terrain_b + comma + r_terrain_e + comma + s_port_b + comma + s_port_e + comma +
                        l_bar_B + comma + l_bar_E + comma + Key_ScoringLoc + comma + Key_TowerBreach + comma +Key_TowerClimb + comma +
                        Key_Comments);
                newLine();
            }


            writer.write(intro.getTeamNum() + comma + intro.getMatchNum() + comma + intro.getScoutName() + comma + auto.getDefenses() + comma
                    + auto.getBreach() + comma +  auto.isPastDefense() + comma + auto.getShotsMadeHigh() + comma + auto.getShotsMadeLow() + comma
                    + tele.getShotsHigh() + comma + tele.getShotsLow() + comma +info + tele.getScoringLoc() + comma +
                    option(tele.getTowerBreach()) + comma + option(tele.getTowerClimb()) + comma + post.getComments());

            newLine();

            writer.flush();
            writer.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private String option(boolean b){
        if(b){
            return "Yes";
        }else{
            return "no";
        }
    }
}
