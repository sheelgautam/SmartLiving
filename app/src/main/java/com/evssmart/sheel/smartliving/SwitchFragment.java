package com.evssmart.sheel.smartliving;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStream;


/**
 * A simple {@link Fragment} subclass.
 */
public class SwitchFragment extends android.support.v4.app.Fragment implements CompoundButton.OnCheckedChangeListener {

    Switch fan_switch;
    Switch light_switch;
    Switch ac_switch;
    Switch misc_switch;

    public SwitchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_switch, container, false);
        fan_switch = (Switch) view.findViewById(R.id.fan_switch);
        light_switch = (Switch) view.findViewById(R.id.light_switch);
        ac_switch = (Switch) view.findViewById(R.id.ac_switch);
        misc_switch = (Switch) view.findViewById(R.id.misc_switch);

        fan_switch.setOnCheckedChangeListener(this);
        light_switch.setOnCheckedChangeListener(this);
        ac_switch.setOnCheckedChangeListener(this);
        misc_switch.setOnCheckedChangeListener(this);

        return view;
    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        if(buttonView.getId() == R.id.fan_switch) {
            if (buttonView.isChecked()) {
                try {
                    MainActivity.bufferedWriter.write("001\n");
                    MainActivity.bufferedWriter.flush();
                    Toast.makeText(getActivity(), "Fan On", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    MainActivity.bufferedWriter.write("000\n");
                    MainActivity.bufferedWriter.flush();
                    Toast.makeText(getActivity(), "Fan Off", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        if(buttonView.getId() == R.id.light_switch) {
            if (buttonView.isChecked()) {
                try {
                    MainActivity.bufferedWriter.write("011\n");
                    MainActivity.bufferedWriter.flush();
                    Toast.makeText(getActivity(), "Light On", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } else {
                try {
                    MainActivity.bufferedWriter.write("010\n");
                    MainActivity.bufferedWriter.flush();
                    Toast.makeText(getActivity(), "Light Off", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        if(buttonView.getId() == R.id.ac_switch) {
            if (buttonView.isChecked()) {
                try {
                    MainActivity.bufferedWriter.write("101\n");
                    MainActivity.bufferedWriter.flush();
                    Toast.makeText(getActivity(), "AC On", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    MainActivity.bufferedWriter.write("100\n");
                    MainActivity.bufferedWriter.flush();
                    Toast.makeText(getActivity(), "AC Off", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        if(buttonView.getId() == R.id.misc_switch) {
            if (buttonView.isChecked()) {
                try {
                    MainActivity.bufferedWriter.write("111\n");
                    MainActivity.bufferedWriter.flush();
                    Toast.makeText(getActivity(), "Misc On", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    MainActivity.bufferedWriter.write("110\n");
                    MainActivity.bufferedWriter.flush();
                    Toast.makeText(getActivity(), "Misc Off", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
