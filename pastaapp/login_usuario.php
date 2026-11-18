<?php
$email = $_POST["usuario"];
$senha = $_POST["senha"];

require_once __DIR__ . '/../conexao.php';

$comando = "SELECT email FROM usuarios WHERE email = ? AND senha = ? LIMIT 1";

$stmt = mysqli_prepare($con, $comando);

$dados = array("status" => "-");

if ($stmt) {
    mysqli_stmt_bind_param($stmt, "ss", $email, $senha);
    mysqli_stmt_execute($stmt);
    mysqli_stmt_bind_result($stmt, $emailEncontrado);

    if (mysqli_stmt_fetch($stmt)) {
        $dados = array("status" => "ok", "email" => $emailEncontrado);
    }

    mysqli_stmt_close($stmt);
}

$close = mysqli_close($con);

echo json_encode($dados);
?>
