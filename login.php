<?php
session_start();

if (
    isset($_POST['submit']) &&
    !empty($_POST['email']) &&
    !empty($_POST['senha'])
) {
    require_once __DIR__ . '/conexao.php';

    $email = $_POST['email'];
    $senha = $_POST['senha'];

    // Consultar o usuário com segurança
    $sql = "SELECT email FROM usuarios WHERE email = ? AND senha = ? LIMIT 1";

    $stmt = $con->prepare($sql);

    if (!$stmt) {
        die("Não foi possível realizar a consulta.");
    }

    $stmt->bind_param("ss", $email, $senha);
    $stmt->execute();

    $result = $stmt->get_result();

    if ($result && $result->num_rows > 0) {
        session_regenerate_id(true);

        $_SESSION['email'] = $email;

        $stmt->close();
        $con->close();

        header("Location: perfil.php");
        exit;
    }

    unset($_SESSION['email']);

    $stmt->close();
    $con->close();

    echo "<script>
        alert('E-mail ou senha incorretos.');
        window.location.href = 'login.html';
    </script>";
    exit;
}

echo "<script>
    alert('Preencha todos os campos.');
    window.location.href = 'login.html';
</script>";
exit;
?>
