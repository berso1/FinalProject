package com.udacity.gradle.build;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.sample.android_library.JokeActivity;
import com.udacity.gradle.builditbigger.AsyncResponse;
import com.udacity.gradle.builditbigger.EndpointsAsyncTask;
import com.udacity.gradle.builditbigger.R;


//paid flavor get Joke from AsyncTask and send it in intent to JokeActivity, no ads :)
public class MainActivityFragment extends Fragment {
    private ProgressBar progressBar;
    private static final String JOKE = "joke";


    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_main, container, false);
        Button button = (Button) root.findViewById(R.id.tell_joke_button);
        progressBar = (ProgressBar) root.findViewById(R.id.progressbar);

        button.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                //Load Joke from AsyncTask and send in intent to JokeActivity to display it
                EndpointsAsyncTask endpointsAsyncTask = (EndpointsAsyncTask) new EndpointsAsyncTask(new AsyncResponse() {
                    final Intent intent = new Intent(getActivity(), JokeActivity.class);

                    @Override
                    public void processFinish(String output) {
                        if (output == null) {
                            output = Integer.toString(R.string.no_data_text);
                        }
                        progressBar.setVisibility(View.GONE);
                        intent.putExtra(JOKE, output);
                        startActivity(intent);
                    }
                }).execute();
            }

        });

        return root;

    }

}
