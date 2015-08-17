package com.sharedreader.app.sharedreader;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends Activity implements AsyncTaskCallBack{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final TextView textViewToChange = (TextView) findViewById(R.id.textView);
                EditText searchTextBox = (EditText) findViewById(R.id.searchTextBox);
                textViewToChange.setText("Fetching..");
                CallRetrieveBook(searchTextBox.getText().toString());
            }
        });
    }
    private void CallRetrieveBook(String query) {
        new EndpointsAsyncTask().execute(new Pair<AsyncTaskCallBack, String>(this, query));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void takeItBack(String result) {
        final TextView textViewToChange = (TextView) findViewById(R.id.textView);
        textViewToChange.setText(result);
    }

    class RetrieveBook extends AsyncTask<String, Void, String> {


        private Exception exception;

        protected void onPreExecute() {

        }

        protected String doInBackground(String... urls) {


            try {
                URL url = new URL("https://www.goodreads.com/search.xml?key=JvO2RkMBST74HI7z0famA&q=Ender%27s+Game");
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line).append("\n");
                    }
                    bufferedReader.close();
                    return stringBuilder.toString();
                }
                finally{
                    urlConnection.disconnect();
                }
            }
            catch(Exception e) {

                return e.toString();
            }
        }

        protected void onPostExecute(String response) {
            if(response == null) {
                response = "THERE WAS AN ERROR";
            }
            final TextView textViewToChange = (TextView) findViewById(R.id.textView);
            textViewToChange.setText(
                    "postexecute"+response);

        }
    }

}



