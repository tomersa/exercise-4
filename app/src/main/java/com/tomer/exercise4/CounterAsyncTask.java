package com.tomer.exercise4;

import android.os.AsyncTask;

public class CounterAsyncTask extends AsyncTask<IAsyncTaskEvents, Integer, Void> {

    IAsyncTaskEvents asyncEvent;

    @Override
    protected Void doInBackground(IAsyncTaskEvents... iAsyncTaskEvents) {
        asyncEvent = iAsyncTaskEvents[0];

        WorkerThread workerThread = new WorkerThread();
        workerThread.start();
        try {
            workerThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    private class WorkerThread extends Thread {
        @Override
        public synchronized void start() {
            super.start();
            for(int i = 0; i < 10; i++) {
                asyncEvent.onProgressUpdate(i);

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            asyncEvent.onPostExecute();
        }
    }
}