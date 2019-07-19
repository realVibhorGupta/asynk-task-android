package com.example.vibhor.asyntaskdemoapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView mListView;

    private String[] list_array= {"frog", "toad", "squirrel" ,
    "lorem", "ipsum","iyesrt","hsfdhiu","hnjhwgaeif",
            "huwgeiuf","giugfwga","bfadsgyugfai","biusdfaihda",
            "bkhfshgadsf","hjuigsaelkgyjfad","dshfisadf","fyastdad","jghdifsa",
            "hgjhfafda", " nbihfiwer" ,"bijkfhadkjh"

    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_PROGRESS);
        setContentView(R.layout.activity_main);
        mListView= (ListView) findViewById(R.id.listView);
        mListView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,new ArrayList<String>()));
        new MyTask().execute();
    }


    class MyTask extends AsyncTask<Void,String,Void>
    {

        private int count  = 0 ;
        private ArrayAdapter<String> stringArrayAdapter;
        @Override
        protected void onPreExecute() {
            stringArrayAdapter= (ArrayAdapter<String>) mListView.getAdapter();
            setProgressBarIndeterminate(false);
            setProgressBarVisibility(true);
    }



        /**
         * Override this method to perform a computation on a background thread. The
         * specified parameters are the parameters passed to {@link #execute}
         * by the caller of this task.
         * <p/>
         * This method can call {@link #publishProgress} to publish updates
         * on the UI thread.
         *
         * @param params The parameters of the task.
         * @return A result, defined by the subclass of this task.
         * @see #onPreExecute()
         * @see #onPostExecute
         * @see #publishProgress
         */


        @Override
        protected Void doInBackground(Void... params) {


            for (String item:list_array) {
                publishProgress(item);
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            return null;
        }
        @Override
        protected void onProgressUpdate(String... values) {
            stringArrayAdapter.add(values[0]);
            count++;
            setProgress((int)(((double)count/list_array.length)*10000));
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            setProgressBarVisibility(false);
        L.s(MainActivity.this,"All items are added successfully");
        }
    }
}
