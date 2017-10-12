package com.udacity.gradle.builditbigger;


import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.widget.ProgressBar;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class NonEmptyStringTest {


    @Test
    public void nonEmptyStringTest() {
        ProgressBar progressBar=null;
        EndpointsAsyncTask endpointsAsyncTask = (EndpointsAsyncTask) new EndpointsAsyncTask(new AsyncResponse() {
            @Override
            public void processFinish(String output) {
                Assert.assertTrue(!output.equals(""));
            }
        },progressBar).execute();
    }
}
