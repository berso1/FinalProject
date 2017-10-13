package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
    private static final String JOKE = "joke";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().
                add(R.id.fragment, new MainActivityFragment()).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //star AsyncTask to get Joke and when finish send it to JokeActivity in intent extra
    public void tellJoke(View view) {
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        final Intent intent = new Intent(this, JokeActivity.class);
        TextView flavor_text_view = (TextView) findViewById(R.id.flavor);
        final String flavor = flavor_text_view.getText().toString();
        progressBar.setVisibility(View.VISIBLE);

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
        }).execute();
    }
}


