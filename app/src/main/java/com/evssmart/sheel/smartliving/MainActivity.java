package com.evssmart.sheel.smartliving;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;


public class MainActivity extends ActionBarActivity {

    private EditText port;
    private EditText address;
    private Button connect;
    String response = "";
    public static Socket socketClient = null;
    public static OutputStream outputStream = null;
    public static OutputStreamWriter outputStreamWriter = null;
    public static BufferedWriter bufferedWriter = null;
    public static InputStream inputStream = null;
    public static InputStreamReader inputStreamReader = null;
    public static BufferedReader bufferedReader = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        port = (EditText) findViewById(R.id.port);
        address = (EditText) findViewById(R.id.address);
        connect = (Button) findViewById(R.id.connect);

        checkValidation();

        address.addTextChangedListener(mWatcher);
        port.addTextChangedListener(mWatcher);

    }

    private void checkValidation() {
        // TODO Auto-generated method stub

        if ((TextUtils.isEmpty(address.getText()))
                || (TextUtils.isEmpty(port.getText())))
            connect.setEnabled(false);
        else
            connect.setEnabled(true);

    }

    TextWatcher mWatcher = new TextWatcher() {

        @Override
        public void onTextChanged(CharSequence s, int start, int before,
                                  int count) {
            // TODO Auto-generated method stub
            checkValidation();
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {
            // TODO Auto-generated method stub

        }

        @Override
        public void afterTextChanged(Editable s) {
            // TODO Auto-generated method stub

        }
    };

    public void onConnectPress(View v) {
        connectToSocket connectToSocket = new connectToSocket(address.getText().toString(), Integer.parseInt(port.getText().toString()));
        connectToSocket.execute();
        connect.setEnabled(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    class connectToSocket extends AsyncTask<Void, Void, Void> {
        String Address;
        Integer Port;

        connectToSocket(String Address, Integer Port) {
            this.Address = Address;
            this.Port = Port;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(getBaseContext(), "Connecting", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected Void doInBackground(Void... param) {
            try {
                socketClient = new Socket(Address, Port);
            } catch (IOException e) {
                e.printStackTrace();
                response = "";
                response = e.toString();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            if (!(response.equals(""))) {
                Toast.makeText(getBaseContext(), response, Toast.LENGTH_LONG).show();
                Toast.makeText(getBaseContext(), "Connection Failed!", Toast.LENGTH_SHORT).show();
                connect.setEnabled(true);
            } else {
                if (socketClient == null) {
                    Toast.makeText(getBaseContext(), "Connection Failed!", Toast.LENGTH_SHORT).show();
                    connect.setEnabled(true);
                } else {
                    Intent intent = new Intent(MainActivity.this, control.class);
                    startActivity(intent);
                    try {
                        outputStream = socketClient.getOutputStream();
                        outputStreamWriter = new OutputStreamWriter(outputStream);
                        bufferedWriter = new BufferedWriter(outputStreamWriter);
                        inputStream = socketClient.getInputStream();
                        inputStreamReader = new InputStreamReader(inputStream);
                        bufferedReader = new BufferedReader(inputStreamReader);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}