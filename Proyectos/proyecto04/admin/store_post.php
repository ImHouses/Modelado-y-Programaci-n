<?php
include '../conexion.php';
/* El título. */
$title = $_POST["title"];
/* El subtítulo. */
$subtitle = $_POST["subtitle"];
/* El texto principal del post. */
$text = $_POST["text"];
/* La URL de la portada. */
$cover_url = $_POST["cover_url"];
if ($cover_url == "") {
  $cover_url = "assets/images/cover.jpeg";
}
date_default_timezone_set('America/Mexico_City');
/* Timestamp. */
$date_time = date("Y-m-d H:i:s");
$query = "INSERT INTO posts(title, text, created_at, updated_at, cover_url, sub_title) VALUES ('$title', '$text', '$date_time', '$date_time', '$cover_url', '$subtitle')";
if ($conn->query($query) == true) {
  echo "Post publicado correctamente.";
} else {
  echo "No se pudo publicar el post." . $conn->error;
}
echo "<br><a href='menu_admin.php'>Aceptar</a>";
 ?>
