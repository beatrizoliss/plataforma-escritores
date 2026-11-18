package com.example.etecliterariatcc;

import static android.app.PendingIntent.getActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.koushikdutta.ion.Ion;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

public String p;
public static int x;
public static String titulox,conteudox,generox,capax,descricaox,autorx, escritox;

    Context context;
    List<Item> items;

    public MyAdapter(Context context, List<Item> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull  MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

       Ion.with(holder.imageView).placeholder ( R.drawable.vazio ).
                error ( R.drawable.vazio ).
                load( items.get(position).getcapa_livro().toString());


holder.imageView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        titulox=items.get(position).gettitulo_livro();
        conteudox=items.get(position).getconteudo_livro();
        generox=items.get(position).getgenero();
        capax=items.get(position).getcapa_livro();
        descricaox=items.get(position).getdescricao();
        autorx=items.get(position).getautor();
        escritox=items.get(position).getautor_mail();

        x=position;

   //   Toast.makeText(context.getApplicationContext(),
    //    "voce clicou em "+p+ "  posicao "+x, Toast.LENGTH_SHORT).show();
        Intent t=new Intent(context,MainActivity10.class);

        t.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
      v.getContext().startActivity(t);
    }
});


    }


    @Override
    public int getItemCount() {
        return items.size();
    }


}
