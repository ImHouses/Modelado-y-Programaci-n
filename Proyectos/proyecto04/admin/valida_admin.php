<?php
include '../conexion.php';
/* El usuario obtenido del formulario. */
$user = $_POST["login"];
/* La contraseña obtenida el formulario. */
$pass = $_POST["password"];
/* Consulta en los admin. */
$consulta = "SELECT * FROM admins";
/* El resultado de la consulta. */
$resultado = $conn->query($consulta);
$encontrado = false;
while($row=$resultado->fetch_assoc()) {
		$login_bd = $row["username"];
		$password_bd = $row["password"];
		if($login_bd == $user and $password_bd == $pass) {
			$encontrado=true;
			break;
	}
}
if($encontrado) {
	header("Location:menu_admin.php");
} else {
	header("Location:index.php?error=si");
}
 ?>
