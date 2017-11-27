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

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);

        if(values.length > 0) {
            asyncEvent.onProgressUpdate(values[0]);
        }
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        asyncEvent.onPostExecute();
    }

    private class WorkerThread extends Thread {
        @Override
        public synchronized void start() {
            super.start();
            for(int i = 0; i < 10; i++) {
                if(isCancelled()) {
                    break;
                }

                Integer[] progress = new Integer[1];
                progress[0] = i;
                publishProgress(progress);

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}