<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title></title>
  </head>
  <body>
    <form action="validandoAdmin.php" method="POST">

    <table id="tabla">
    	<p id="ins"> Ingresa tus datos </p>
    	<tr>
    		<td>
    			Login
    		</td>
    		<td>
    			<input id="user" type="text" name="login" required="true">
    		</td>

    	</tr>
    	<tr>
    		<td>
    			Contraseña
    		</td>
    		<td>
    			<input type="password" name="password" required="true">
    		</td>

    	</tr>
    	<tr>
    		<td>

    		</td>
    		<td>
    			<input type="submit" value="Enviar">
    		</td>

    	</tr>


    </table>

    <?php

		$e = $_GET["error"];
		if($e == "si") {
			echo "<p id='e'>Credenciales incorrectas</p>";
		}
    ?>
  </body>
</html>
