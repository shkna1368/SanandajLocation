package com.shabab.googleplace;

/**
 * Created by Sh-Java on 6/27/2017.
 */
public class Place {
    private double lat,lon;
    private  String placeName,placeDescrption,
            placeAddress,type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getPlaceDescrption() {
        return placeDescrption;
    }

    public void setPlaceDescrption(String placeDescrption) {
        this.placeDescrption = placeDescrption;
    }

    public String getPlaceAddress() {
        return placeAddress;
    }

    public void setPlaceAddress(String placeAddress) {
        this.placeAddress = placeAddress;
    }


    public String toString(){

        return   lat+"-"+lon+
         "-"+ placeName+"-"+placeDescrption+"-"+"-"+
                placeAddress;

    }
}
