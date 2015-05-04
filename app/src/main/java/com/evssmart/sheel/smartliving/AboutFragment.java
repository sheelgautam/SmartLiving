package com.evssmart.sheel.smartliving;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;


/**
 * A simple {@link Fragment} subclass.
 */
public class AboutFragment extends android.support.v4.app.Fragment {

    Button disconnect_button;


    public AboutFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_about, container, false);

        disconnect_button = (Button) view.findViewById(R.id.disconnect_button);
        disconnect_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    MainActivity.socketClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Toast.makeText(getActivity(), "Connection Closed!", Toast.LENGTH_SHORT).show();
                control.running = false;
                getActivity().finish();
            }
        });

        return view;
    }


}
