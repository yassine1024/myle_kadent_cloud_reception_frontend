package Config;

import java.util.HashMap;

public class Const {

    public static final String cabinetId= "576-40-1283";

    public static final String headAppointment="30";
    public static final String TIME_BUNCH= "30";
    public static  HashMap<String, String> ACTE_CELLULE;

    public  Const(){
        ACTE_CELLULE = new HashMap<String, String>();
        ACTE_CELLULE.put("ODF","red");
        ACTE_CELLULE.put("ENDO","green");
        ACTE_CELLULE.put("Restoration","yellow");
        ACTE_CELLULE.put("Fix Prostetics","blue");
        ACTE_CELLULE.put("Movible Prostetics","Orange");
        ACTE_CELLULE.put("Implant","purple");
        ACTE_CELLULE.put("Tooth Extraction","brown");
        ACTE_CELLULE.put("Surgery","grey");
        ACTE_CELLULE.put("Perio","white");
        ACTE_CELLULE.put("Consultation","black");

    }


}
