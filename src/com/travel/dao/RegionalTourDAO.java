//package com.travel.dao;
//
//import com.travel.bean.HomeTourBean;
//import com.travel.bean.RegionalTourBean;
//import com.travel.dbconnection.DBConnection;
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;
//
//import java.io.FileReader;
//import java.io.IOException;
//import java.io.Reader;
//import java.sql.Connection;
//import java.util.ArrayList;
//import java.util.Iterator;
//
//public class RegionalTourDAO {
//    protected Connection connection;
//
//    public RegionalTourDAO() {
//        this.connection = DBConnection.getConnect();
//    }
//
//    protected JSONArray readJSONFile(String filePath) {
//        JSONParser parser = new JSONParser();
//        JSONArray jsonArray = null;
//
//        try (Reader reader = new FileReader(filePath)) {
//            jsonArray = (JSONArray) parser.parse(reader);
//        } catch (IOException | ParseException e) {
//            e.printStackTrace();
//        }
//
//        return jsonArray;
//    }
//
//    public RegionalTourBean getRegionalTour() {
//        return null;
//    }
//
//    public ArrayList<RegionalTourBean> getRegionalTours() {
//        JSONArray jsonRegionalTours = this.readJSONFile("./web/json/tours_by_region.json");
//        ArrayList<RegionalTourBean> regionalTours = new ArrayList<>();
//
//        Iterator<JSONObject> regionalToursIterator = jsonRegionalTours.iterator();
//
//        while (regionalToursIterator.hasNext()) {
//            JSONObject jsonRegionalTour = regionalToursIterator.next();
//            JSONArray jsonHomeTours = (JSONArray) jsonRegionalTour.get("tours");
//            String region = (String) jsonRegionalTour.get("region");
//            Iterator<JSONObject> homeToursIterator = jsonHomeTours.iterator();
//            ArrayList<HomeTourBean> homeTours = new ArrayList<>();
//
//            while (homeToursIterator.hasNext()) {
//                JSONObject jsonHomeTour = homeToursIterator.next();
//                int id = (int) jsonHomeTour.get("id");
//                String title = (String) jsonHomeTour.get("top_item_title");
//                String imageUrl = (String) jsonHomeTour.get("top_item_image");
//                homeTours.add(new HomeTourBean(id, title, imageUrl));
//            }
//
//            regionalTours.add(new RegionalTourBean(region, homeTours));
//        }
//
//        return regionalTours;
//    }
//}
