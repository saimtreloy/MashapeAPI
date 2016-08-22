package com.moodybugs.sam.mashapeapi;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new CallMashapeAsync().execute();
    }


    private class CallMashapeAsync extends AsyncTask<String, Integer, HttpResponse<JsonNode>> {

        protected HttpResponse<JsonNode> doInBackground(String... msg) {

            HttpResponse<JsonNode> request = null;
            try {
                request = Unirest.get("https://muslimsalat.p.mashape.com/london.json")
                        .header("X-Mashape-Key", "8FY6VJtKrUmshdZPM4VzjtLdnk4up1q1edMjsnsBnz62BLCxBI")
                        .header("Accept", "application/json")
                        .asJson();
            } catch (UnirestException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            return request;
        }

        protected void onProgressUpdate(Integer...integers) {
        }

        protected void onPostExecute(HttpResponse<JsonNode> response) {
            String answer = response.getBody().toString();
            Toast.makeText(MainActivity.this, answer, Toast.LENGTH_LONG).show();
        }
    }
}
