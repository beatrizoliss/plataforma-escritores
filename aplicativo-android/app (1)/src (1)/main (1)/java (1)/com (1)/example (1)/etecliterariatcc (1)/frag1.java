package com.example.etecliterariatcc;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.koushikdutta.ion.Ion;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class frag1 extends Fragment {

    public static String escritox;
    private TextView txtnome;
    private ImageView profileImage;

    public frag1() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View view = inflater.inflate(R.layout.fragment_frag1, container, false);
        TextView txtnome = view.findViewById(R.id.txtnome);
        ImageView profileImage = view.findViewById(R.id.profileImage);
        ImageView favorito = view.findViewById(R.id.favorito);
        ImageView adicionado = view.findViewById(R.id.adicionado);

        txtnome.setText(MainActivity2.nomex);

        Ion.with(profileImage).placeholder ( R.drawable.vazio ).
                    error ( R.drawable.vazio ).
                load("https://etecliteraria.online/img_user/"+ MainActivity2.fotox+"");

        favorito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MainActivity11.class);
                startActivity(intent);
            }
        });

        adicionado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                escritox = MainActivity2.email;
                Intent intent = new Intent(getActivity(), MainActivity12.class);
                startActivity(intent);
            }
        });

        return view; // Retorna a view após configurar os listeners
    }
}
