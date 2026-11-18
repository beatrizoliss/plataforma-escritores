package com.example.etecliterariatcc;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
public class frag3 extends Fragment {
    public Button b1;
    public frag3() {
// Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup
            container,
                             Bundle savedInstanceState) {
        final View
                view=inflater.inflate(R.layout.fragment_frag3, container,
                false);
        b1=view.findViewById(R.id.button);
        b1.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText( getActivity(), "cliquei no botão", Toast.LENGTH_SHORT ).show();
            }
        } );
// Inflate the layout for this fragment
        return view;
    }
}