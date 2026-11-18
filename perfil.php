<?php
session_start();

// Verificar se o usuário está autenticado
if (!isset($_SESSION['email'])) {
    unset($_SESSION['email']);
    header("Location: login.html");
    exit;
}

require_once __DIR__ . '/conexao.php';

// Buscar os dados do usuário
$sql = "SELECT nome, tel, email FROM usuarios WHERE email = ? LIMIT 1";

$stmt = $con->prepare($sql);

if (!$stmt) {
    die("Não foi possível consultar os dados do perfil.");
}

$stmt->bind_param("s", $_SESSION['email']);
$stmt->execute();

$stmt->bind_result($nomeUsuario, $telefoneUsuario, $emailUsuario);

if (!$stmt->fetch()) {
    $stmt->close();
    $con->close();

    unset($_SESSION['email']);
    header("Location: login.html");
    exit;
}

// Proteger os dados antes de exibi-los na página
$nomeUsuario = htmlspecialchars($nomeUsuario ?? '', ENT_QUOTES, 'UTF-8');
$telefoneUsuario = htmlspecialchars($telefoneUsuario ?? '', ENT_QUOTES, 'UTF-8');
$emailUsuario = htmlspecialchars($emailUsuario ?? '', ENT_QUOTES, 'UTF-8');

$stmt->close();
$con->close();
?>

<!DOCTYPE html>
<html lang="pt-BR">

<head>
    <meta charset="utf-8">
    <title>Página de Perfil</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">

    <script src="https://code.jquery.com/jquery-3.6.3.min.js"
        integrity="sha256-Uv9jxBenhLL1+qzfUyxk5wrP+L5qvhwLTP60qjzTpL8="
        crossorigin="anonymous"></script>

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css">

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/assets/owl.carousel.min.css">

    <link href="../assets/css/petfil.css" rel="stylesheet">
</head>

<body id="page-top">

    <section id="about" class="about-mf sect-pt4 route">
        <div class="container">
            <div class="row">
                <div class="col-sm-12">
                    <div class="box-shadow-full">

                        <div class="row">

                            <div class="col-md-6">
                                <div class="row">

                                    <div class="col-sm-6 col-md-5">
                                        <div class="about-img">
                                            <img src="../assets/img/cv.png"
                                                class="img-fluid rounded b-shadow-a" alt="Foto de perfil">
                                        </div>
                                    </div>

                                    <div class="col-sm-6 col-md-7">
                                        <div class="about-info">

                                            <p>
                                                <span class="title-s">Nome: </span>
                                                <span><?= $nomeUsuario ?></span>
                                            </p>

                                            <p>
                                                <span class="title-s">Email: </span>
                                                <span><?= $emailUsuario ?></span>
                                            </p>

                                            <p>
                                                <span class="title-s">Telefone: </span>
                                                <span><?= $telefoneUsuario ?></span>
                                            </p>

                                        </div>
                                    </div>

                                </div>
                            </div>

                            <div class="col-md-6">
                                <div class="about-me pt-4 pt-md-0">

                                    <div class="title-box-2">
                                        <h5 class="title-left">
                                            Perfil
                                        </h5>
                                    </div>

                                    <p class="lead">
                                        Esta é a página dos seus dados pessoais.
                                        Ao voltar para a página inicial, você poderá
                                        explorar nosso projeto de várias maneiras,
                                        aproveitando os serviços listados abaixo.
                                        Tenha uma excelente experiência!
                                    </p>

                                </div>
                            </div>

                            <div class="row">
                                <div class="col-sm-12">
                                    <div class="title-box text-center">

                                        <br>
                                        <h1 class="title-a">
                                            Serviços
                                        </h1>

                                        <div class="line-mf"></div>

                                    </div>
                                </div>
                            </div>

                            <div class="row">

                                <div class="col-md-4">
                                    <div class="service-box">

                                        <img src="../assets/img/quill-drawing-a-line.png"
                                            width="150" class="img1" style="text-align: center;" alt="Escrita">

                                        <div class="service-content">
                                            <h2 class="s-title">Escrever</h2>
                                        </div>

                                    </div>
                                </div>

                                <div class="col-md-4">
                                    <div class="service-box">

                                        <img src="../assets/img/logotiposemfundo.png"
                                            width="150" class="img1" style="text-align: center;" alt="ETECLITERARIA">

                                        <div class="service-content">
                                            <h2 class="s-title">ETECLITERARIA</h2>
                                        </div>

                                    </div>
                                </div>

                                <div class="col-md-4">
                                    <div class="service-box">

                                        <img src="../assets/img/reading-book.png"
                                            width="150" class="img1" style="text-align: center;" alt="Leitura">

                                        <div class="service-content">
                                            <h2 class="s-title">LEITURA</h2>
                                        </div>

                                    </div>
                                </div>

                            </div>

                            <div class="button-container-wrapper">
                                <div class="button-container">

                                    <a href="sair.php" class="btn mt-4">Sair</a>

                                    <a href="index.html" class="btn mt-4">
                                        Voltar ao Início
                                    </a>

                                </div>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <script src="../assets/javascript/perfil.js"></script>

</body>
</html>
