<<<<<<< HEAD
<!DOCTYPE html> 
<html lang="en"> 
<head> 
	<meta charset="utf-8"> 
	<meta http-equiv="X-UA-Compatible" content="IE=edge"> 
	<meta name="viewport" content="width=device-width, initial-scale=1"> 
	<title>Colleges</title> 
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"> 
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script> 
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script> 
	<style type="text/css"> 
		.main-form, .profile-area { 
			width: 340px; 
		} 
		.main-form { 
			margin: 50px auto 0px; 
		} 
		.profile-area { 
			margin: 10px auto; 
		} 
		.main-form section, .profile-area section { 
			margin-bottom: 15px; 
			background: #f7f7f7; 
			box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3); 
		} 
		.main-form section { 
			padding: 30px; 
		} 
		.profile-area section { 
			padding: 30px 30px 30px; 
		} 
		.profile-area section > div { 
			text-align: center; 
		} 
		.main-form h3 { 
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
		.hideElement { 
			display: none; 
		} 
	</style> 
</head> 
<body> 
<div class="main-form" id="main-form"> 
	<section> 
		<div class="form-group"> 
			<input id="searchString" type="text" class="form-control" placeholder="Enter college search name here..." required="required"> 
		</div> 
		<div class="form-group"> 
			<input id="countryName" type="text" class="form-control" placeholder="Enter country name here..." required="required"> 
		</div> 
		<div class="form-group"> 
			<button onclick="loadData()" class="btn btn-primary btn-block">Find College Details</button> 
		</div> 
	</section> 
</div> 
<div class="profile-area hideElement" id="profile-area"> 
	<section> 
		<div id="loader" class="hideElement"> 
			<div class="spinner-border" role="status"> 
				<span class="sr-only">Loading...</span> 
			</div> 
		</div> 
		<div id="profile" class="hideElement"> 
			<br><br> 
			
<p><strong>Colleges : </strong><span id="associatedcolleges"></span></p> 

			
<p><strong>Webpages : </strong><span id="associatedwebpages"></span></p> 

		</div> 
	</section> 
</div> 
</body> 
<script> 
	function loadData() { 
		document.getElementById("profile-area").classList.remove("hideElement"); 
		document.getElementById("loader").classList.remove("hideElement"); 
		document.getElementById("profile").classList.add("hideElement"); 

		var searchString = document.getElementById("searchString").value; 
		var countryName = document.getElementById("countryName").value; 

		if(searchString != "" && searchString != null && countryName != "" && countryName != null) { 
			var xhttp = new XMLHttpRequest(); 
			xhttp.onreadystatechange = function() { 
				if (this.readyState == 4 && this.status == 200) { 
					var jsonResponse = JSON.parse(this.responseText); 
					document.getElementById("associatedcolleges").innerHTML = jsonResponse.associatedcolleges; 
					document.getElementById("associatedwebpages").innerHTML = jsonResponse.associatedwebpages; 
					document.getElementById("loader").classList.add("hideElement"); 
					document.getElementById("profile").classList.remove("hideElement"); 
				} 
			}; 
			xhttp.open("GET", "getCollegeDetailsBycountryNameAndSearchString?countryName="+ countryName + "&name=" + searchString, true); 
			xhttp.send(); 
			console.log("done"); 
		} else { 
			console.log("Enter country name and search string to check...") 
		} 
	} 
</script> 
</html>
=======
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
	            // Autenticación exitosa: redirigir al usuario a la página del buscador
	            console.log("Autenticación exitosa");
	            window.location.href = "buscador.jsp"; // Redirigir a la página del buscador
	            valido = true; // Establecer la bandera de validación en true
	            return; // Salir de la función después de la redirección
	        }
	    });
	
	    // Si el flujo de control llega aquí, significa que la autenticación falló
	    if (!valido) {
	        // Se manda un mensaje de error al usuario
	        alert("Nombre de usuario o contraseña incorrectos");
	    }
	}
</script> 
</html>
>>>>>>> P2024-010-Login
