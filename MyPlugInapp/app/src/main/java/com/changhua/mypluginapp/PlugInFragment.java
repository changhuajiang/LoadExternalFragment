package com.changhua.mypluginapp;


import android.app.Fragment;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import example.test_interfaces.FragmentHolder;



public class PlugInFragment extends Fragment implements FragmentHolder {

    Button mSendButton1;
    TextView mText;

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {

        // Note that loading of resources is not the same as usual, because it loaded actually from another apk
        final XmlResourceParser parser = container.getContext().getPackageManager().
                getXml("com.changhua.mypluginapp", R.layout.fragment_blank, null);

        return inflater.inflate(parser, container, false);




        //return inflater.inflate(R.layout.fragment_blank, container, false);
    }

    @Override
    public Fragment getFragment() {
        return this;
    }



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        mSendButton1 = (Button) view.findViewById(R.id.button);
        mText = (TextView) view.findViewById(R.id.text);

        mSendButton1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Send a message using content of the edit text widget
                mText.setText("Click in fregment" );
            }
        });
    }
}

