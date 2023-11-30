<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<title>Registro</title>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<link href="/PW-web/css/base.css" rel="stylesheet" type="text/css">
</head>

<body>
<script type="text/javascript">
function mostrarContraseña(){
  var checkBox = document.getElementById('ver');
  var texto = document.getElementById('pass');
  if (checkBox.checked == true){
	texto.type = 'text';
  } else{
	texto.type = 'password';
  }
}
</script>


	<div class="row justify-content-center align-items-center" style="height: 70vh">
		<div class="center">
			<div class="container bg-dark text-white text-center">
				<div class="card-body">
					<h1>Crear una cuenta</h1>
					<form id="form" method="post" action="/PW-web/mvc/controller/registroController.jsp">
						<label for="nombre">Nombre y apellidos</label> 
						<input type="text" name=nombre placeholder="Fernando Alonso Díaz" required>
						<br> 
						<label for="email">Correo electrónico</label> 
						<input type="email" name=correo id="correo" placeholder="ejemplo@email.com" required> 
						<br> 
						<label for="password">Contraseña</label>
						<input type="password" name=pass id="pass" placeholder="Password" required> 
						<br>
						<input type="checkbox" id="ver" onclick="mostrarContraseña()">
						<label for="mostrarContraseña">Mostrar contraseña</label>
						<br>
						<label for="fechaNacimiento">Fecha de nacimiendo</label> 
						<input type="date" name=fechaNacimiendo required> 
						<br><br> 
						<input type="reset" class="btn btn-light btn-lg" value="Borrar"/> 
						<input type="submit" class="btn btn-light btn-lg" value="Enviar"/>
					</form>
					<form method="post" action="/PW-web">
						<br> 
						<input type="submit" class="btn btn-light btn-lg" value="Volver">
					</form>
				</div>
			</div>
		</div>
	</div>
	
</body>
</html>