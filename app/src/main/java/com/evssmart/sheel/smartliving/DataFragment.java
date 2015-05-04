package com.evssmart.sheel.smartliving;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.IOException;


/**
 * A simple {@link Fragment} subclass.
 */
public class DataFragment extends android.support.v4.app.Fragment {

    TextView temp_tview, humidity_tview, conc_tview;
    receiveSocketData getTheData;

    public DataFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_data, container, false);

        temp_tview = (TextView) view.findViewById(R.id.temp_tview);
        humidity_tview = (TextView) view.findViewById(R.id.humidity_tview);
        conc_tview = (TextView) view.findViewById(R.id.conc_tview);
        getTheData = new receiveSocketData();
        getTheData.start();

        return view;
    }


    class receiveSocketData extends Thread {

        String data = null;

        @Override
        public void run() {

            while(control.running) {
                try {
                    data = MainActivity.bufferedReader.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if(data != null) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if(data.matches("00" + ".*")) {
                                data = data.replaceFirst("00", "");
                                temp_tview.setText(data + " C");
                            }

                            if(data.matches("01" + ".*" )) {
                                data = data.replaceFirst("01", "");
                                humidity_tview.setText(data + " %");
                            }

                            if(data.matches("10" + ".*" )) {
                                data = data.replaceFirst("10", "");
                                conc_tview.setText(data + " ppm");
                            }
                        }
                    });
                }
            }
        }
    }
}
