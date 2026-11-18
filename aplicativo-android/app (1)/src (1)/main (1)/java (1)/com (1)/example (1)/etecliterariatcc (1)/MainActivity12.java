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

public class MainActivity12 extends AppCompatActivity {
    public static String caminho = "", s, url;
    public static int m = -1;
    public RecyclerView recyclerView;
    String Host = "https://etecliteraria.online/pastaapp/";
    List<Item> items = new ArrayList<Item>();
    public static int px = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main12);

        recyclerView = findViewById(R.id.recyclerview);
        ImageView backButton = findViewById(R.id.imageView6); // Usando a seta com ID imageView5

        backButton.setOnClickListener(v -> finish()); // Volta para a atividade anterior

        listar();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void listar() {
        url = Host + "suashistorias.php";
        Ion.with(getBaseContext())
                .load(url)
                .setBodyParameter("autor_mail", frag1.escritox)
                .asJsonArray()
                .setCallback(new FutureCallback<JsonArray>() {
                    @Override
                    public void onCompleted(Exception e, JsonArray result) {
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
