package Config;

import java.util.ArrayList;

public class Time {

    static ArrayList<String> timePerHour;


    public static   void  initTimePerHour(){

        timePerHour= new ArrayList<String>();
        for(int i=0;i<24;i++){
            if(i<10){
                timePerHour.add("0"+i+":"+Const.headAppointment);
            }else{
                timePerHour.add(i+":"+Const.headAppointment);
            }
        }
    }

    public static ArrayList<String> getTimePerHour() {
        initTimePerHour();
        return timePerHour;
    }

}
