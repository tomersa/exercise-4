package com.tomer.exercise4;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AsyncTaskActivity extends AppCompatActivity implements IAsyncTaskEvents {

    TextView terminalTextView;
    Button createButton, startButton, cancelButton;

    CounterAsyncTask currentTask = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);
        terminalTextView = findViewById(R.id.terminal_tv);

        createButton = findViewById(R.id.create_button);
        startButton = findViewById(R.id.start_button);
        cancelButton = findViewById(R.id.cancel_button);

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentTask = new CounterAsyncTask();
            }
        });

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentTask != null) {
                    IAsyncTaskEvents[] events = new IAsyncTaskEvents[1];
                    events[0] = AsyncTaskActivity.this;
                    currentTask.execute(events);
                }
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentTask.cancel(true);
                currentTask = new CounterAsyncTask();
            }
        });
    }

    @Override
    public void onPostExecute() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                terminalTextView.setText(R.string.terminal_finished);
            }
        });
        currentTask = new CounterAsyncTask();
    }

    @Override
    public void onProgressUpdate(final Integer count) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                terminalTextView.setText(Integer.toString(count));
            }
        });
    }
}
