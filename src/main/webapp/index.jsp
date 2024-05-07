<!DOCTYPE html> 
<html lang="en"> 
<head> 
    <meta charset="utf-8"> 
    <meta http-equiv="X-UA-Compatible" content="IE=edge"> 
    <meta name="viewport" content="width=device-width, initial-scale=1"> 
    <title>Login</title> 
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"> 
    <style type="text/css"> 
        .login-form { 
            width: 340px; 
            margin: 50px auto 0px; 
        } 
        .login-form section { 
            margin-bottom: 15px; 
            background: #f7f7f7; 
            box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3); 
            padding: 30px; 
        } 
        .login-form h3 { 
            margin: 0 0 15px; 
        } 
        .form-control, .btn { 
            min-height: 38px; 
            border-radius: 2px; 
        } 
        .btn { 
            font-size: 15px; 
            font-weight: bold; 
        } 
    </style> 
</head> 
<body> 
    <div class="login-form"> 
        <section> 
            <h3>Login</h3> 
            <div class="form-group"> 
                <input id="username" type="text" class="form-control" placeholder="Username" required="required"> 
            </div> 
            <div class="form-group"> 
                <input id="password" type="password" class="form-control" placeholder="Password" required="required"> 
            </div> 
            <div class="form-group"> 
                <button onclick="recogerusuarios()" class="btn btn-primary btn-block">Login</button> 
            </div> 
        </section> 
    </div> 
</body> 
<script> 
	function recogerusuarios(){
	    var xhttp = new XMLHttpRequest();
	    xhttp.open("GET", "obtenerUsuarios", true);
	    xhttp.onreadystatechange = function () {
	        if (this.readyState == 4 && this.status == 200) {
	            var jsonResponse = JSON.parse(this.responseText);
	            console.log(JSON.stringify(jsonResponse));
	            login(jsonResponse);
	        }
	    };
	    xhttp.send();
	}
	
	function login(usuarios) { 
	    var valido = false;
	    var username = document.getElementById("username").value; 
	    var password = document.getElementById("password").value; 
	    
	    // Iterar sobre cada usuario y comparar con los datos introducidos por el usuario
	    usuarios.forEach(function (usuario) {
	        if (usuario.login === username && usuario.password === password) {
	            // Autenticaci�n exitosa: redirigir al usuario a la p�gina del buscador
	            console.log("Autenticaci�n exitosa");
	            window.location.href = "buscador.jsp"; // Redirigir a la p�gina del buscador
	            valido = true; // Establecer la bandera de validaci�n en true
	            return; // Salir de la funci�n despu�s de la redirecci�n
	        }
	    });
	
	    // Si el flujo de control llega aqu�, significa que la autenticaci�n fall�
	    if (!valido) {
	        // Se manda un mensaje de error al usuario
	        alert("Nombre de usuario o contrase�a incorrectos");
	    }
	}
</script> 
</html>