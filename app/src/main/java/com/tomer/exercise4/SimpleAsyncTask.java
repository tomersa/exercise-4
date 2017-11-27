package com.tomer.exercise4;

public abstract class SimpleAsyncTask<Param> {

    protected boolean cancelled = false;

    abstract protected void doInBackground();

    abstract protected void onPreExecute();

    protected void onPostExecute() {
        publishProgress();
    }

    protected void onProgressUpdate() {}

    abstract protected void onCancelled();

    abstract protected void publishProgress(final Param... values);
}
