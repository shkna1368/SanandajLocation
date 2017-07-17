package com.shabab.googleplace;

import java.util.List;

/**
 * Created by Sh-Java on 6/27/2017.
 */
public class MainCl {
    public static void main(String[] args) {
        CallWS callWS=new CallWS();
       // callWS.call("library");
        String [] types=CallWS.placeType;
        int size=types.length;
        DBHalper dbHalper=new DBHalper();
        dbHalper.getConnection();

        for(int i=0;i<size;i++){
            System.out.println("akharin i="+i);
     //  List<Place> places=callWS.getJSON("library",15000);
            List<Place> places=callWS.getJSON(types[i],15000);

        dbHalper.insetAllData(places);}




    }
}
