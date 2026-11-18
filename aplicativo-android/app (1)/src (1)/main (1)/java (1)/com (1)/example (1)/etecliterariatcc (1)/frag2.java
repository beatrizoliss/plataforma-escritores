package com.example.etecliterariatcc;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class frag2 extends Fragment {

    public static String generox;
    public frag2() {
// Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup
            container, Bundle savedInstanceState) {
// Inflate the layout for this fragment
        View view= inflater.inflate( R.layout.fragment_frag2,
                container, false );

        ImageView img_romance = view.findViewById(R.id.img_romance);
        ImageView img_terror = view.findViewById(R.id.img_terror);
        ImageView img_filosofia = view.findViewById(R.id.img_filosofia);
        ImageView img_musical = view.findViewById(R.id.img_musical);
        ImageView img_poesia = view.findViewById(R.id.img_poesia);
        ImageView img_outros = view.findViewById(R.id.img_outros);

        img_romance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                generox = "romance";
                Intent intent = new Intent(getActivity(),  MainActivity4.class);
                startActivity(intent);
            }
        });

        img_terror.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                generox = "terror";
                Intent intent = new Intent(getActivity(),  MainActivity5.class);
                startActivity(intent);
            }
        });

        img_filosofia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                generox = "filosofia";
                Intent intent = new Intent(getActivity(),  MainActivity6.class);
                startActivity(intent);
            }
        });

        img_musical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                generox = "musica";
                Intent intent = new Intent(getActivity(),  MainActivity7.class);
                startActivity(intent);
            }
        });

        img_poesia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                generox = "poesia";
                Intent intent = new Intent(getActivity(),  MainActivity8.class);
                startActivity(intent);
            }
        });

        img_outros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                generox = "outros";
                Intent intent = new Intent(getActivity(),  MainActivity9.class);
                startActivity(intent);
            }
        });



        return view;


    }

}