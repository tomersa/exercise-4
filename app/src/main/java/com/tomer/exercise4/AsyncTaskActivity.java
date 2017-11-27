package com.tomer.exercise4;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class AsyncTaskActivity extends AppCompatActivity implements IAsyncTaskEvents{

    TextView terminalTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);
        terminalTextView = findViewById(R.id.terminal_tv);
    }

    @Override
    public void onPostExecute() {
        terminalTextView.setText(Resources.getSystem().getText(R.string.terminal_finished));
    }

    @Override
    public void onProgressUpdate(Integer count) {
        terminalTextView.setText(Resources.getSystem().getText(count));
    }
}
