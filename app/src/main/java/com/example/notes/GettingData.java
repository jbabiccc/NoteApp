package com.example.notes;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class GettingData extends AsyncTask<Void,Void,Void> {
    String data = "";
    String dataParsing="";
    String Parsing="";


    @Override
    protected Void doInBackground(Void... voids) {
        try {
            URL url = new URL("https://api.myjson.com/bins/vqkjg");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String lines = "";
            while (lines != null) {
                lines = bufferedReader.readLine();
                data += lines;
            }
            JSONArray jsonArray = new JSONArray(data);
            for(int i=0; i<jsonArray.length(); i++){
                JSONObject JO = (JSONObject) jsonArray.get(i);
                Parsing = "name:" + JO.get("name")+ "\n"+
                        "country:" + JO.get("country") + "\n" +
                        "department:" + JO.get("department") + "\n";
                dataParsing +=  Parsing;
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        Networking.data.setText(this.dataParsing);
    }
}
