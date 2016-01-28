package DataHolder;

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
    private static String Key_TowerBreach = "Tower Breach";
    private static String Key_TowerClimb = "Tower Climb";
    private static String Key_Comments = "Comments";
    private static String comma = ",";

    public DatabaseWriter(Team t){
        this.team = t;
        this.auto = team.getAuto();
        this.tele = team.getTeleOp();
        this.intro = team.getIntro();
        this.post = team.getPG();
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

    public void write(){
        try{
            File directory = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), "Scouting App");

            if(!directory.exists()){
                Log.i("tag", "Directory doesn't exist");
                boolean b = directory.mkdir();
                if(b){
                    Log.i("tag", "Directory created");
                }else if(!b){
                    Log.i("tag", "Directory not CREATED");
                }
            }

            File file = new File(directory, "ScoutingInfo.csv");
            Log.i("tag", "File is created");
            writer = new FileWriter(file, true);
            Log.i("tag", "We created all of the stuff");

            ArrayList<Defense> list = tele.getList();
            String info="";
            for(int i = 0; i < list.size(); i++){
                String addition = list.get(i).getName() + " (BC:" + list.get(i).getBreachCount() + ", E:" + list.get(i).getEffectiveness();
                info+=addition;
            }

            if(file.length()==0){
                writer.write(Key_Team + comma + Key_Num + comma + Key_S_Name + comma + Key_Def + comma + Key_DefAuto + comma + Key_ReachDef + comma + Key_ShotsHiAuto + comma
                        +Key_ShotsLowAuto + comma + Key_ShotsHiTele + comma + Key_ShotsHiTele + comma + Key_ShotsLowTele + comma + Key_DefStatus + comma +
                Key_DefStatus + comma + Key_TowerBreach + comma + Key_TowerClimb + comma + Key_Comments);
                Log.i("tag", "We created the header");
            }

            writer.write(intro.getTeamNum() + comma + intro.getMatchNum() + comma + intro.getScoutName() + comma + auto.getDefenses()
            +comma + auto.getBreach() + comma + auto.isPastDefense() + comma + auto.getShotsMadeHigh() + comma + auto.getShotsMadeLow()
                    + comma + tele.getShotsHigh() + comma + tele.getShotsLow() + comma + info + comma + tele.getTowerBreach() + comma + tele.getTowerClimb()
                + comma + post.getComments());

            




            writer.flush();
            writer.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
