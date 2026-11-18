<?php
session_start();

// Limpar os dados da sessão
$_SESSION = [];

// Encerrar a sessão
session_destroy();

// Redirecionar para a página de login
header("Location: login.html");
exit;