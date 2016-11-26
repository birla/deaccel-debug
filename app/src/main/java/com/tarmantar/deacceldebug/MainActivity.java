package com.tarmantar.deacceldebug;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.ToggleButton;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends AppCompatActivity {

    EditText editTextAddress, editTextPort, editTextFlashTop, editTextFlashBottom;
    Button buttonConnect, buttonPing, buttonAccelOne;
    ToggleButton toggleButtonStream;
    TextView textViewResults;
    Client myClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextAddress = (EditText) findViewById(R.id.addressEditText);
        editTextPort = (EditText) findViewById(R.id.portEditText);

        buttonConnect = (Button) findViewById(R.id.connectButton);
        buttonPing = (Button) findViewById(R.id.pingButton);
        buttonAccelOne = (Button) findViewById(R.id.accelButton);

        toggleButtonStream = (ToggleButton) findViewById(R.id.toggleButton);

        editTextFlashBottom = (EditText) findViewById(R.id.flashBottomEditText);
        editTextFlashTop = (EditText) findViewById(R.id.flashTopEditText);

        textViewResults = (TextView) findViewById(R.id.resultTextView);

        myClient = new Client(editTextAddress.getText()
                .toString(), Integer.parseInt(editTextPort
                .getText().toString()), textViewResults);

        buttonConnect.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                myClient.execute();
            }
        });

        buttonPing.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                myClient.sendMessage("ping");
            }
        });
    }
}
