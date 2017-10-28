package com.shapedhorizon.teymur.epiknovel;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    public String title;
    //private TextView mTextMessage;
    ArrayList<Seri> seris;
    public SeriRVAdapter adapter = new SeriRVAdapter(this, seris);;
    Elements els;
    RecyclerView rv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        RecyclerView rvContacts = (RecyclerView) findViewById(R.id.seri_recycle);

        // Initialize contacts
        seris = Seri.addSeri("","");
        // Create adapter passing in the sample user data
        //SeriRVAdapter adapter = new SeriRVAdapter(this, seris);
        // Attach the adapter to the recyclerview to populate items
        rvContacts.setAdapter(adapter);
        // Set layout manager to position the items
        rvContacts.setLayoutManager(new LinearLayoutManager(this));
        rv = (RecyclerView) findViewById(R.id.seri_recycle);

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    //mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    //mTextMessage.setText(R.string.title_books);
                    AsyncInternet task = new AsyncInternet();
                    task.execute();

            }
            return false;
        }

    };
    private class AsyncInternet extends AsyncTask<Void, Void, String>{


        @Override
        protected String doInBackground(Void... params) {
            Document doc= null;
            try {
                doc = Jsoup.connect("https://www.epiknovel.com/seri-listesi?sorting=views&sorting-type=DESC").get();
            } catch (IOException e) {
                e.printStackTrace();
            }
            els = doc.getElementsByTag("h3");
            title = "null";
            //if (!(els.first() == null)) {
              //  title = "";
                //for(Element el : els){
                  //  title+="\n"+el.text();
                  //  seris.addAll(Seri.addSeri(el.text(),""));
                  //  adapter.notifyItemInserted(0);
                //}
            //}
            return title;
        }

        @Override
        protected void onPreExecute() {

            //mTextMessage.setText("Loading..");
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {

            //mTextMessage.setText(title);
            try {
                Log.i("Adapter", adapter.toString());
                if (!(els == null)) {
                    Log.i("Elements", els.toString());
                    if (!(els.first() == null)) {
                        title = "";
                        for (Element el : els) {
                            title += "\n" + el.text();
                            seris.addAll(Seri.addSeri(el.text(), ""));
                            adapter.notifyItemInserted(seris.size());
                        }
                        Log.i("Title", title);
                    }
                } else {
                    System.out.print("els null");
                }
            }catch (NullPointerException ne){
                ne.printStackTrace();
            }
            super.onPostExecute(s);
        }
    }

}
