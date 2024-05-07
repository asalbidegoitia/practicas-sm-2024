<!DOCTYPE html> 
<html lang="en"> 
<head> 
	<meta charset="utf-8"> 
	<meta http-equiv="X-UA-Compatible" content="IE=edge"> 
	<meta name="viewport" content="width=device-width, initial-scale=1"> 
	<title>Colleges</title> 
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"> 
	 <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
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
		
		/*
			CSS personalizado
			Ejercicio 02 (Hugo Vélez)
		*/ 
		.estiloDivError {
			border: solid 1px red;
			color: red;
		}
	</style> 
</head> 
<body> 

    <!-- Barra de Navegación -->
    <nav class="navbar navbar-expand-lg navbar-light bg-light" style= margin-left>
        	<a class="navbar-brand" href="buscador.jsp">Inicio</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
            	<!-- Añadir un nav-item por cada apartado en la barra de navegecion 
            	@params href pantalla a la que direcciona -->
                <li class="nav-item">
                    <a class="nav-link" href="CollegeInsertForm.jsp">Introducir</a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link" href="buscador.jsp">Consultar</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link disabled" href="#">Disabled</a>
                </li>
            </ul>
        </div>
    </nav>
    
	<div class="main-form" id="main-form"> 
		<section> 
			<div class="form-group"> 
				<input id="collegeName" type="text" class="form-control" placeholder="Enter college name" required="required"> 
			</div> 
			<div class="form-group"> 
				<input id="countryName" type="text" class="form-control" placeholder="Enter country name" required="required"> 
			</div> 
			<div class="form-group"> 
				<input id="provinceName" type="text" class="form-control" placeholder="Enter province" required="required"> 
			</div>
			<div class="form-group"> 
				<input id="webPage" type="text" class="form-control" placeholder="Enter college web page" required="required"> 
			</div>
			<div class="form-group"> 
				<button onclick="insertCollege()" class="btn btn-primary btn-block">Add College</button> 
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
				<p><strong><span id="insertState"></strong></span></p> 
	
			</div> 
		</section> 
	</div> 
	
	<!-- 
		Parrafo para mostrar errores  
		Ejercicio 02 (Hugo Vélez)
	-->
	<div class="profile-area hideElement" id="profile-area-error"> 
		<section class="estiloDivError"> 
			<div id="loader-error" class="hideElement"> 
				<div class="spinner-border" role="status"> 
					<span class="sr-only">Loading...</span> 
				</div> 
			</div> 
			<div id="profile-error"> 
				<br><br> 
			
				<p id="parrafoErrores"><strong>Error : </strong><span id="mensajeerror"></span></p> 
	
			</div> 
		</section> 
	</div> 
</body> 
<script> 
	function insertCollege() { 
		document.getElementById("profile-area").classList.remove("hideElement"); 
		document.getElementById("profile").classList.add("hideElement"); 

		var collegeName = document.getElementById("collegeName").value; 
		var countryName = document.getElementById("countryName").value;
		var provinceName = document.getElementById("provinceName").value; 
		var webPage = document.getElementById("webPage").value;
		
		if((collegeName != "" && collegeName != null) && (countryName != "" && countryName != null)){ 
			var xhttp = new XMLHttpRequest(); 
			xhttp.onreadystatechange = function() { 
				if (this.readyState == 4 && this.status == 200) { 
					var jsonResponse = JSON.parse(this.responseText); 
					document.getElementById("insertState").innerHTML = jsonResponse.insertState; 
					document.getElementById("profile").classList.remove("hideElement"); 
				} 
			}; 
			xhttp.open("POST", "insertCollegeDetails?countryName="+ countryName + "&collegeName=" + collegeName +
					"&provinceName=" + provinceName + "&webPage=" + webPage, true); 
			xhttp.send(); 
			console.log("done"); 
		} else { 
			console.log("Enter country name and search string to check...") 
		} 
	}
</script>
</html>