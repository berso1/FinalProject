package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.sample.android_library.JokeActivity;
import com.udacity.gradle.build.MainActivityFragment;


public class MainActivity extends AppCompatActivity {

    private static final String FLAVOR = "flavor";
    private ProgressBar progressBar;
    private static String JOKE = "joke";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().
                add(R.id.fragment, new MainActivityFragment()).commit();

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


    public void tellJoke(View view) {
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        final Intent intent = new Intent(this, JokeActivity.class);
        TextView flavor_text_view = (TextView) findViewById(R.id.flavor);
        final String flavor = flavor_text_view.getText().toString();
        EndpointsAsyncTask endpointsAsyncTask = (EndpointsAsyncTask) new EndpointsAsyncTask(new AsyncResponse() {
            @Override
            public void processFinish(String output) {
                if (output == null) {
                    output = Integer.toString(R.string.no_data_text);
                }
                progressBar.setVisibility(View.GONE);
                intent.putExtra(JOKE, output);
                intent.putExtra(FLAVOR,flavor);
                startActivity(intent);
            }
        }, progressBar).execute();
    }
}


