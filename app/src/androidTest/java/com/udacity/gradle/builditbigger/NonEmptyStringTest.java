package com.udacity.gradle.builditbigger;


import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class NonEmptyStringTest {

    @Test
    public void  nonEmptyString() {
        final CountDownLatch signal = new CountDownLatch(1);
        EndpointsAsyncTask endpointsAsyncTask = (EndpointsAsyncTask) new EndpointsAsyncTask(new AsyncResponse() {
            @Override
            public void processFinish(String output) {
                Assert.assertFalse(output.isEmpty());
                signal.countDown();
            }
        }).execute();
        try {
            signal.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
