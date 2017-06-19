<!DOCTYPE html>
<html>
<head>
	<title>Nuevo post</title>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <!-- Bootstrap Core CSS -->
    <link href="../assets/css/bootstrap.min.css" rel="stylesheet">
    <!-- Theme CSS -->
    <link href="../assets/css/clean-blog.min.css" rel="stylesheet"/>
    <!-- Custom Fonts -->
    <link href="vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href='https://fonts.googleapis.com/css?family=Lora:400,700,400italic,700italic' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800' rel='stylesheet' type='text/css'>
</head>
<body>
	<div class="container">
		<h1>Nuevo post</h1>
	  <form action="store_post.php" method="post">
		  	<div class="form-group">
          <p>Título</p>
          <input class="form-control" name="title" type="text" required="true">
		  	</div>
		  	<div class="form-group">
          <p>Subtítulo</p>
          <input class="form-control" name="subtitle" type="text">
		  	</div>
		  <div class="form-group">
          <p>Texto</p>
		  		<textarea class="form-control" name="text" type="text" required="true" cols="80" rows="30"></textarea>
		  </div>
		  <div class="form-group">
        <p>URL de la portada</p>
        <input class="form-control" name="cover_url" type="text">
		  </div>
		  <input class="btn btn-default" type="submit" value="Enviar nueva entrada">
		</form>
	</div>
</body>
</html>
