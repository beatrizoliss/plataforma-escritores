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

public class MainActivity11 extends AppCompatActivity {

    public static String caminho = "", s, url;
    public static int m = -1;
    public RecyclerView recyclerView;
    String Host = "https://etecliteraria.online/pastaapp/";
    List<Item> items = new ArrayList<Item>();
    public static int px = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main11);

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.recyclerView3);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listar();
    }

    private void listar() {
        url = Host + "paginafavoritos.php"; // Alterado para o script PHP correto
        Ion.with(getBaseContext())
                .load(url)
                .setBodyParameter("usuario_id", MainActivity2.usuariox) // Mantido como estava
                .asJsonArray()
                .setCallback(new FutureCallback<JsonArray>() {
                    @Override
                    public void onCompleted(Exception e, JsonArray result) {
                        items.clear();
                        for (int i = 0; i < result.size(); i++) {
                            JsonObject ret = result.get(i).getAsJsonObject();
                            caminho = "https://etecliteraria.online/livros/capas/" + ret.get("capa_livro").getAsString();

                            // Adiciona cada item à lista
                            items.add(new Item(
                                    ret.get("titulo_livro").getAsString(),
                                    ret.get("conteudo_livro").getAsString(),
                                    ret.get("genero").getAsString(),
                                    "https://etecliteraria.online/livros/capas/" + ret.get("capa_livro").getAsString(), // Mantido como estava
                                    ret.get("descricao").getAsString(),
                                    ret.get("autor").getAsString(),
                                    ret.get("autor_mail").getAsString()));
                        }

                        // Define o adaptador para o RecyclerView
                        recyclerView.setAdapter(new MyAdapter(getApplicationContext(), items));
                    }
                });
    }
}
