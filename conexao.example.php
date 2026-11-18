<?php

$servername = "localhost";
$database = "NOME_DO_BANCO";
$username = "USUARIO_DO_BANCO";
$password = "SUA_SENHA_AQUI";

$con = mysqli_connect(
    $servername,
    $username,
    $password,
    $database
);

if (!$con) {
    die("Não foi possível conectar ao banco de dados.");
}

mysqli_set_charset($con, "utf8mb4");

?>
