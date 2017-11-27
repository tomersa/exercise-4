package com.tomer.exercise4;

public interface IAsyncTaskEvents {

    void onPostExecute();

    void onProgressUpdate(Integer count);

}
