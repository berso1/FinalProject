package com.udacity.gradle.builditbigger;


import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class NonEmptyStringTest {

    //test the result of AsyncTask is not empty using interface AsyncResponse
    @Test
    public void nonEmptyStringTest() {
        EndpointsAsyncTask endpointsAsyncTask = (EndpointsAsyncTask) new EndpointsAsyncTask(new AsyncResponse() {
            @Override
            public void processFinish(String output) {
                Assert.assertFalse(output.isEmpty());
            }
        }).execute();
    }
}
