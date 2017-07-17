package com.shabab.googleplace;

import org.postgis.Geometry;
import org.postgis.PGgeometry;
import org.postgis.Point;
import org.postgresql.util.PGobject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Sh-Java on 6/27/2017.
 */
public class DBHalper {
    Connection connection = null;
    public void getConnection(){



        try {

            connection = DriverManager.getConnection(
                    "jdbc:postgresql://127.0.0.1:5432/lbs_course", "postgres",
                    "Shkna1368");



            ( (org.postgresql.PGConnection)connection).addDataType("geometry", PGgeometry.class);

        } catch (SQLException e) {

            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            return;

        }

        if (connection != null) {
            System.out.println("You made it, take control your database now!");
        } else {
            System.out.println("Failed to make connection!");
        }
    }



    public void insetAllData(List<Place> places){
      //  Place p=places.get(0);



        for(Place p:places){

      /*  INSERT INTO sanandaj_place (  place_name, lat, lon,place_descryption,geo)
        VALUES
                ('SADAF',35.298900,46.990131,'مجتمع تجاری',
                        ST_SetSRID(ST_MakePoint(46.990131,35.298900), 4326));*/

        String insertTableSQL = "INSERT INTO sanandaj_place"
                + "(place_name, lat, lon,place_descryption, geo,tpes) VALUES"
                + "(?,?,?,?,ST_SetSRID(ST_MakePoint(?,?), 4326),?)";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(insertTableSQL);
            preparedStatement.setString(1, p.getPlaceName());
            preparedStatement.setDouble(2, p.getLat());
            preparedStatement.setDouble(3, p.getLon());
            preparedStatement.setString(4, p.getPlaceAddress());


            Point pointToAdd = new Point();
            pointToAdd.setX(p.getLat());
            pointToAdd.setY(p.getLon());
            preparedStatement.setDouble(5, p.getLon());
            preparedStatement.setDouble(6, p.getLat());
            preparedStatement.setString(7, p.getType());

// execute insert SQL stetement
            preparedStatement .executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    }

}





