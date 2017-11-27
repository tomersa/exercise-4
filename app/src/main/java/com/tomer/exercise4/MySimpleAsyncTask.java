package com.tomer.exercise4;

import android.os.Handler;
import android.os.Looper;

abstract public class MySimpleAsyncTask<Param> extends SimpleAsyncTask<Param> {

    private Thread mBackgroundThread;

    public void execute() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                onPreExecute();
                mBackgroundThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        doInBackground();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                onPostExecute();
                            }
                        });

                    }
                });
                mBackgroundThread.start();
            }
        });
    }

    public void runOnUiThread(Runnable runnable) {
        new Handler(Looper.getMainLooper()).post(runnable);
    }

    @Override
    protected void publishProgress(Object[] values) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                onProgressUpdate();
            }
        });

    }

    @Override
    protected void onCancelled() {
        super.cancelled = true;
    }
}
