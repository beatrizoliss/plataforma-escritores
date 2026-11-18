<?php

// Verificar se o formulário foi enviado
if ($_SERVER['REQUEST_METHOD'] !== 'POST') {
    header("Location: login.html");
    exit;
}

// Receber os dados do formulário
$nome = trim($_POST['nome'] ?? '');
$tele = trim($_POST['tel'] ?? '');
$email = trim($_POST['email'] ?? '');
$senha = $_POST['senha'] ?? '';

// Verificar se todos os campos foram preenchidos
if ($nome === '' || $tele === '' || $email === '' || $senha === '') {
    echo "<script>
        alert('Preencha todos os campos.');
        window.location.href = 'login.html';
    </script>";
    exit;
}

// Validar o formato do e-mail
if (!filter_var($email, FILTER_VALIDATE_EMAIL)) {
    echo "<script>
        alert('Informe um e-mail válido.');
        window.location.href = 'login.html';
    </script>";
    exit;
}

// Conectar ao banco de dados
require_once __DIR__ . '/conexao.php';

// Verificar se o e-mail já está cadastrado
$sql = "SELECT email FROM usuarios WHERE email = ? LIMIT 1";

$stmt = $con->prepare($sql);

if (!$stmt) {
    die("Não foi possível verificar o cadastro.");
}

$stmt->bind_param("s", $email);
$stmt->execute();
$stmt->store_result();

if ($stmt->num_rows > 0) {
    $stmt->close();
    $con->close();

    echo "<script>
        alert('Este e-mail já está cadastrado.');
        window.location.href = 'login.html';
    </script>";
    exit;
}

$stmt->close();

// Inserir os dados do novo usuário
$sql = "INSERT INTO usuarios (nome, tel, email, senha)
        VALUES (?, ?, ?, ?)";

$stmt = $con->prepare($sql);

if (!$stmt) {
    die("Não foi possível realizar o cadastro.");
}

$stmt->bind_param("ssss", $nome, $tele, $email, $senha);

if ($stmt->execute()) {
    $stmt->close();
    $con->close();

    echo "<script>
        alert('Cadastro realizado com sucesso.');
        window.location.href = 'login.html';
    </script>";
    exit;
}

$stmt->close();
$con->close();

echo "<script>
    alert('Não foi possível realizar o cadastro.');
    window.location.href = 'login.html';
</script>";

exit;
