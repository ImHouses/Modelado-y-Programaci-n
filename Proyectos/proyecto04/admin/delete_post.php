<?php
include '../conexion.php';

$id = $_GET["id"];
$query = "DELETE FROM posts WHERE id='$id'";
if ($conn->query($query) == true) {
  echo "Post eliminado correctamente";
} else {
  echo "No se pudo eliminar el post" . $conn->error;
}
echo "<br><a href='pages/index.php'>Aceptar</a>"
 ?>
