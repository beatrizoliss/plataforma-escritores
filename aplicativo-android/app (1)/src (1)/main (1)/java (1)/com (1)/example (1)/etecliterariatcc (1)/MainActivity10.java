package com.example.etecliterariatcc;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

public class MainActivity10 extends AppCompatActivity {
    public TextView lbltitulo, lblautor, lbldescricao;
    public ImageView lblcapa;
    public CheckBox favoriteCheckbox;



    public static String caminho = "", s, url, idx, ret;
    String Host = "https://etecliteraria.online/pastaapp/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main10);

        lbltitulo = findViewById(R.id.lbltitulo);
        lblautor = findViewById(R.id.lblautor);
        lbldescricao = findViewById(R.id.lbldescricao);
        lblcapa = findViewById(R.id.lblcapa);
        favoriteCheckbox = findViewById(R.id.favoriteCheckbox);
        // Definindo os valores das variáveis com base nos dados da classe MyAdapter
        lbltitulo.setText(MyAdapter.titulox);
        lblautor.setText(MyAdapter.autorx);
        lbldescricao.setText(MyAdapter.descricaox);
        procurar();

        // Carregar a imagem de capa
        Ion.with(lblcapa)
                .placeholder(R.drawable.vazio)
                .error(R.drawable.vazio)
                .load(MyAdapter.capax + "");

        // Listener para voltar à tela anterior
        ImageView backButton = findViewById(R.id.imageView69);
        backButton.setOnClickListener(v -> finish()); // Volta para a atividade anterior
        //findViewById(R.id.favoriteCheckbox).setOnClickListener(new View.OnClickListener() {
        favoriteCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b)
            {
                if (favoriteCheckbox.isChecked()) {
                    inserir(); // Chama a função inserir quando o checkbox é marcado
                } else {
                    deletar(); // Chama a função deletar quando o checkbox é desmarcado
                }
            }
        });
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent trocar=new Intent(MainActivity10.this, MainActivity13.class);
                MainActivity10.this.startActivity(trocar);
            }
        });

    }


    private void procurar() {
        url = Host + "procurarId.php";
        Ion.with(getBaseContext())
                .load(url)
                .setBodyParameter ( "titulo_livro" ,MyAdapter.titulox)
                .asJsonArray()
                .setCallback(new FutureCallback<JsonArray>() {
                    @Override
                    public void onCompleted(Exception e, JsonArray result) {
                        for (int i = 0; i < result.size(); i++) {
                            JsonObject ret = result.get(i).getAsJsonObject();


                                 idx=   ret.get("id_livro").getAsString();
                        }


                    }
                });
    }


    public void inserir()
    {
        url = Host + "favoritar.php";
        Ion.with (MainActivity10.this)
                .load ( url )
                .setBodyParameter ( "id_livro" , idx)
                .setBodyParameter ( "id_usuario", MainActivity2.usuariox)
                .asJsonObject ()
                .setCallback ( new FutureCallback<JsonObject> () {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
               /*         ret=result.get("status").getAsString ();
                        if(ret.equals ("ok"))
                        {
                           Toast.makeText(getApplicationContext(),
                       " incluido com sucesso",   Toast.LENGTH_LONG).show();

                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),
                                    " não existe login ou senha ",   Toast.LENGTH_LONG).show();

                        }*/


                    }
                } );

    }
    public void deletar()
    {
        url = Host + "desfavoritar.php";
        Ion.with (MainActivity10.this)
                .load ( url )
                .setBodyParameter ( "id_livro" , idx)
                .setBodyParameter ( "id_usuario", MainActivity2.usuariox)
                .asJsonObject ()
                .setCallback ( new FutureCallback<JsonObject> () {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
               /*         ret=result.get("status").getAsString ();
                        if(ret.equals ("ok"))
                        {
                           Toast.makeText(getApplicationContext(),
                       " incluido com sucesso",   Toast.LENGTH_LONG).show();

                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),
                                    " não existe login ou senha ",   Toast.LENGTH_LONG).show();

                        }*/


                    }
                } );

    }
}
