<?php
function getPostById($id) {
  include 'conexion.php';
  $query = "SELECT * FROM posts WHERE id=$id";
  $res = $conn->query($query);
  $post = $res->fetch_assoc();
  return $post;
}

function getPostList() {
  include 'conexion.php';
  $query = "SELECT * FROM posts";
  $res = $conn->query($query);
  $posts = array();
  $i = 0;
  while ($post = $res->fetch_assoc()) {
    $posts[$i] = $post;
    $i++;
  }
  return array_reverse($posts);
}
 ?>
