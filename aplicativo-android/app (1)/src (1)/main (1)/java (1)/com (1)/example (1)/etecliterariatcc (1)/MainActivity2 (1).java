package com.example.etecliterariatcc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

public class MainActivity2 extends AppCompatActivity {
    private EditText txtemail, txtsenha;
    private ImageView imgShowPassword;  // ImageView para mostrar/ocultar a senha
    private TextView txtcadastro, txtesqueceu;

    public static String fotox, nomex, usuariox;
    private String Host = "https://etecliteraria.online/pastaapp/"; // Substitua pelo URL correto do seu PHP
    String url;
    String ret;

    public static String email, senha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // Inicializa as variáveis
        txtemail = findViewById(R.id.txtemail);
        txtsenha = findViewById(R.id.txtsenha);
        txtcadastro = findViewById(R.id.txtcadastro);
        txtesqueceu = findViewById(R.id.txtesqueceu);
        imgShowPassword = findViewById(R.id.imgShowPassword);  // ImageView para mostrar/ocultar a senha

        // Configura o clique do botão de login
        findViewById(R.id.button3).setOnClickListener(v -> logar());

        // Configura o clique do link para recuperar senha
        txtesqueceu.setOnClickListener(v -> {
            String url1 = "https://etecliteraria.online/RecuperarSenha.php";
            openWebsite(url1);
        });

        // Configura o clique do link para cadastro
        txtcadastro.setOnClickListener(v -> {
            String url2 = "https://etecliteraria.online/login.html";
            openWebsite(url2);
        });

        // Adiciona a funcionalidade para o ImageView mostrar/ocultar senha
        imgShowPassword.setOnClickListener(v -> {
            if (txtsenha.getTransformationMethod() instanceof PasswordTransformationMethod) {
                // Se a senha estiver oculta, mostra a senha
                txtsenha.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                imgShowPassword.setImageResource(R.drawable.olho);  // Altere para o ícone de "olho aberto"
            } else {
                // Se a senha estiver visível, oculta a senha
                txtsenha.setTransformationMethod(PasswordTransformationMethod.getInstance());
                imgShowPassword.setImageResource(R.drawable.olho);  // Altere para o ícone de "olho fechado"
            }
        });
    }

    // Método para abrir o navegador com a URL fornecida
    private void openWebsite(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    // Método para realizar o login
    public void logar() {
        url = Host + "login_usuario.php";
        Ion.with(MainActivity2.this)
                .load(url)
                .setBodyParameter("usuario", txtemail.getText().toString())
                .setBodyParameter("senha", txtsenha.getText().toString())
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        ret = result.get("status").getAsString();
                        if (ret.equals("ok")) {
                            // Recupera os dados do usuário
                            fotox = result.get("foto").getAsString();
                            nomex = result.get("nome").getAsString();
                            usuariox = result.get("ID").getAsString();

                            // Salva as credenciais
                            email = txtemail.getText().toString();
                            senha = txtsenha.getText().toString();

                            // Redireciona para a próxima tela (MainActivity3)
                            Intent trocar = new Intent(MainActivity2.this, MainActivity3.class);
                            startActivity(trocar);
                        } else {
                            Toast.makeText(getApplicationContext(), "Login ou senha incorretos", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}
