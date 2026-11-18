package com.example.etecliterariatcc;

public class Item {

    String titulo_livro, conteudo_livro, genero, capa_livro, descricao, autor, autor_mail;

    public Item( String titulo_livro, String conteudo_livro, String genero, String capa_livro, String descricao, String autor, String autor_mail) {
        this.titulo_livro = titulo_livro;
        this.conteudo_livro = conteudo_livro;
        this.genero = genero;
        this.capa_livro = capa_livro;
        this.descricao = descricao;
        this.autor = autor;
        this.autor_mail = autor_mail;
    }

    // Novo construtor para 7 argumentos


    public String gettitulo_livro() { return titulo_livro; }
    public String getconteudo_livro() { return conteudo_livro; }
    public String getgenero() { return genero; }
    public String getcapa_livro() { return capa_livro; }
    public String getdescricao() { return descricao; }
    public String getautor() { return autor; }
    public String getautor_mail() { return autor_mail; }


    public void settitulo_livro(String titulo_livro) { this.titulo_livro = titulo_livro; }
    public void setnome(String conteudo_livro) { this.conteudo_livro = conteudo_livro; }
    public void setgenero(String genero) { this.genero = genero; }
    public void setcapa_livro(String capa_livro) { this.capa_livro = capa_livro; }
    public void setdescricao(String descricao) { this.descricao = descricao; }
    public void setautor(String autor) { this.autor = autor; }
    public void setautor_mail(String autor_mail) { this.autor_mail = autor_mail; }
}
