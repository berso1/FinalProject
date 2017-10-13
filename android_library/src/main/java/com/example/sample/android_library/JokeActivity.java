package com.example.sample.android_library;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

//Activity that display the Joke
public class JokeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String joke = intent.getStringExtra("joke");
        String flavor = intent.getStringExtra("flavor");
//test flavor to show interstitialAd in free flavor or not in paid flavor
        if( flavor.equals("free")  ){
           loadAd(joke);
        }else{
            loadJoke(joke);
        }


    }

    private void loadAd(String _joke){
        final String joke= _joke;
        final InterstitialAd interstitialAd;
        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        interstitialAd.loadAd(new AdRequest.Builder().build());
        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
                interstitialAd.show();
            }
            @Override
            public void onAdFailedToLoad(int errorCode) {
                super.onAdFailedToLoad(errorCode);
                //if load fails show joke
                loadJoke(joke);
            }
            @Override
            public void onAdClosed() {
                //show joke whe ad is closed
                loadJoke(joke);
            }
        });
    }

    private void loadJoke(String joke){
        setContentView(R.layout.activity_joke);
        TextView jokeTextView = (TextView) findViewById(R.id.joke_text_view);
        jokeTextView.setText(joke);
    }
}
