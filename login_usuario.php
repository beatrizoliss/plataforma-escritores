<?php
header('Content-Type: application/json; charset=utf-8');

// Receber os dados enviados pelo aplicativo
$email = $_POST['usuario'] ?? '';
$senha = $_POST['senha'] ?? '';

// Verificar se os campos foram preenchidos
if (empty($email) || empty($senha)) {
    echo json_encode(["status" => "error"]);
    exit;
}

// Utilizar a conexão local
require_once __DIR__ . '/conexao.php';

// Consultar o usuário com segurança
$sql = "SELECT email FROM usuarios WHERE email = ? AND senha = ? LIMIT 1";

$stmt = $con->prepare($sql);

if (!$stmt) {
    echo json_encode(["status" => "error"]);
    exit;
}

$stmt->bind_param("ss", $email, $senha);
$stmt->execute();

$result = $stmt->get_result();

if ($result && $result->num_rows > 0) {
    echo json_encode(["status" => "ok"]);
} else {
    echo json_encode(["status" => "error"]);
}

$stmt->close();
$con->close();
?>
