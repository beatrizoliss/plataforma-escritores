package com.example.etecliterariatcc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;
import java.util.List;

public class MainActivity5 extends AppCompatActivity {
    public static String caminho = "", s, url;
    public static int m = -1;
    public RecyclerView recyclerView;
    String Host = "https://etecliteraria.online/pastaapp/";
    List<Item> items = new ArrayList<Item>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        // Initialize the RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        ImageView backButton = findViewById(R.id.imageView8); // Usando a seta com ID imageView17

        backButton.setOnClickListener(v -> finish()); // Volta para a atividade anterior

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Load the data
        listar();
    }

    private void listar() {
        url = Host + "livroapp.php";
        Ion.with(getBaseContext())
                .load(url)
                .setBodyParameter ( "genero" ,frag2.generox)
                .asJsonArray()
                .setCallback(new FutureCallback<JsonArray>() {
                    @Override
                    public void onCompleted(Exception e, JsonArray result) {
                        if (e != null) {
                            // Handle error here
                            return;
                        }
                        items.clear();
                        for (int i = 0; i < result.size(); i++) {
                            JsonObject ret = result.get(i).getAsJsonObject();
                            caminho = "https://etecliteraria.online/livros/capas/" + ret.get("capa_livro").getAsString();

                            items.add(new Item(
                                    ret.get("titulo_livro").getAsString(),
                                    ret.get("conteudo_livro").getAsString(),
                                    ret.get("genero").getAsString(),
                                    "https://etecliteraria.online/livros/capas/" + ret.get("capa_livro").getAsString(),
                                    ret.get("descricao").getAsString(),
                                    ret.get("autor").getAsString(),
                                    ret.get("autor_mail").getAsString()));
                        }

                        recyclerView.setAdapter(new MyAdapter(getApplicationContext(), items));
                    }
                });
    }
}
