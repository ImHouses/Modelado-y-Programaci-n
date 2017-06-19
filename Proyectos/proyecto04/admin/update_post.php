<?php
include '../conexion.php';

$title = $_POST["title"];
$subtitle = $_POST["subtitle"];
$text = $_POST["text"];
$cover_url = $_POST["cover_url"];
$id = $_POST["id"];
date_default_timezone_set('America/Mexico_City');
$date_time = date("Y-m-d H:i:s");
if ($cover_url == "") {
  $cover_url = "assets/images/cover.jpeg";
}
$query = "UPDATE posts
  SET title='$title', text='$text', updated_at='$date_time', cover_url='$cover_url', sub_title='$subtitle'
  WHERE id='$id'";
if ($conn->query($query) == true) {
  echo "Post modificado correctamente.";
} else {
  echo "No se pudo publicar el post." . $conn->error;
}
echo $date_time;
echo "<a href='menu_admin.php'>Aceptar</a>";
 ?>
