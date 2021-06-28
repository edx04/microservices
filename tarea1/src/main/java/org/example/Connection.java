package org.example;




import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;

public class Connection {

    private static final String HTTPS_URL = "https://jsonmock.hackerrank.com/api/movies/search/";
     HttpURLConnection connection = null;
    private String movie = "";


    Connection(String movie){
        this.movie = movie;
    }


    private void ConnectionHttp(int page){
        URL url = null;
        try {
            url = new URL(HTTPS_URL +"?Title="+ movie + "&page="+page);
            connection = (HttpURLConnection)url.openConnection();
        } catch (MalformedURLException e) {
            e.printStackTrace();

        }
        catch (IOException e) {
            e.printStackTrace();
        }


    }


    private void ConnectionHttp(){
        URL url = null;

        try {
            url = new URL(HTTPS_URL +"?Title="+ movie);
            connection = (HttpURLConnection)url.openConnection();
        } catch (MalformedURLException e) {
            e.printStackTrace();

        }
        catch (IOException e) {
            e.printStackTrace();
        }


    }


    private BufferedReader bufferedReader(){
        BufferedReader br = null;

        try {
            InputStream inputStream = connection.getInputStream();
            InputStreamReader isr= new InputStreamReader(inputStream);
            br =  new BufferedReader(isr);


        } catch (IOException e) {
            e.printStackTrace();
        }

        return br;
    }



    public void obtain_titles() {
        ConnectionHttp();

        try {

            BufferedReader br = bufferedReader();

            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject) parser.parse(br);
            Long totalPages = (Long) obj.get("total_pages");
            ArrayList<JSONObject> data = (ArrayList<JSONObject>) obj.get("data");


            int actual_page = 2;
            while (actual_page <= totalPages){

                ConnectionHttp(actual_page);
                br  =bufferedReader();
                obj = (JSONObject) parser.parse(br);
                ArrayList<JSONObject> temp = (ArrayList<JSONObject>) obj.get("data");
                data.addAll(temp);
                actual_page ++;
            }

            data.forEach(a -> System.out.println(a.get("Title")));



        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }
}
