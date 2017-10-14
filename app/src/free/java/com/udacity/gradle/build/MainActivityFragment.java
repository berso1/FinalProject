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
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.udacity.gradle.builditbigger.AsyncResponse;
import com.udacity.gradle.builditbigger.EndpointsAsyncTask;
import com.udacity.gradle.builditbigger.R;


//free flavor loads add in fragment
public class MainActivityFragment extends Fragment {

    private ProgressBar progressBar;
    private static final String JOKE = "joke";


    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_main, container, false);
        AdView mAdView = (AdView) root.findViewById(R.id.adView);
        Button button = (Button) root.findViewById(R.id.tell_joke_button);
        progressBar = (ProgressBar) root.findViewById(R.id.progressbar);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);


        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                final InterstitialAd interstitialAd;
                interstitialAd = new InterstitialAd(getActivity());
                interstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
                interstitialAd.loadAd(new AdRequest.Builder().build());
                interstitialAd.setAdListener(new AdListener() {
                    @Override
                    public void onAdLoaded() {
                        interstitialAd.show();
                    }

                    @Override
                    public void onAdFailedToLoad(int errorCode) {
                        super.onAdFailedToLoad(errorCode);
                        loadJoke();
                    }

                    @Override
                    public void onAdClosed() {
                        loadJoke();
                    }
                });
            }


        });
        return root;

    }

    private void loadJoke() {
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


}
