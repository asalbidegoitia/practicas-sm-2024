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
			<button onclick="saveCollege()" class="btn btn-primary btn-block">Find and Save College Details</button> 
			<button onclick="mostrarUniversidades()" class="btn btn-primary btn-block">Mostrar Universidades</button>
		</div> 

		<a href="CollegeInsertForm.jsp">College Insert Form</a>
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

<p><strong>Colleges : </strong><span id="associatedcolleges"></span></p> 
<p><strong>Webpages : </strong><span id="associatedwebpages"></span></p>
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
<div class="profile-area" id="profile-area"> 
    <section> 
        <table class="table table-striped table-bordered">
            <thead class = "table-dark">
                <tr>
                    <th>UID</th>
                    <th>Nombre</th>
                    <th>Página Web</th>
                    <th>País</th>
                    <th>Provincia/Estado</th>
                    <th>Fecha Guardado</th>
                </tr>
            </thead>
            <tbody id="universitiesTableBody"></tbody>
        </table>
         <button onclick="eliminarUniversidadSeleccionada()" class="btn btn-danger">Eliminar Universidad Seleccionada</button> <!-- Botón para eliminar universidad seleccionada -->
    </section> 
</div>

</body> 
<script> 
	function loadData() { 
		document.getElementById("profile-area").classList.remove("hideElement"); 
		document.getElementById("loader").classList.remove("hideElement"); 
		document.getElementById("profile").classList.add("hideElement");
		
		// Ocultar div
		// Ejercicio 02 (Hugo Vélez)
		document.getElementById("profile-area-error").classList.add("hideElement"); 

		var searchString = document.getElementById("searchString").value; 
		var countryName = document.getElementById("countryName").value; 

		if((searchString != "" && searchString != null) || (countryName != "" && countryName != null)) { 
			var xhttp = new XMLHttpRequest(); 
			xhttp.onreadystatechange = function() { 
				if (this.readyState == 4 && this.status == 200) { 
					var jsonResponse = JSON.parse(this.responseText); 
					document.getElementById("associatedcolleges").innerHTML = jsonResponse.associatedcolleges; 
					document.getElementById("associatedwebpages").innerHTML = jsonResponse.associatedwebpages; 
					document.getElementById("loader").classList.add("hideElement"); 
					document.getElementById("profile").classList.remove("hideElement");
					
					// Mostrar errores
					// Ejercicio 02 (Hugo Vélez)
					if(jsonResponse.parrafoErrores != null){
						document.getElementById("profile-area-error").classList.remove("hideElement"); 
						document.getElementById("mensajeerror").innerHTML = jsonResponse.parrafoErrores;
					}
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


<script> 
//Script para leer y  guardar una universidad
	function saveCollege() { 
		document.getElementById("profile-area").classList.remove("hideElement"); 
		document.getElementById("loader").classList.remove("hideElement"); 
		document.getElementById("profile").classList.add("hideElement"); 

		var searchString = document.getElementById("searchString").value; 
		var countryName = document.getElementById("countryName").value; 

		if((searchString != "" && searchString != null) || (countryName != "" && countryName != null)) { 
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
			xhttp.open("GET", "saveCollegeDetails?countryName="+ countryName + "&name=" + searchString, true); 
			xhttp.send(); 
			console.log("done"); 
		} else { 
			console.log("Enter country name and search string to check...") 
		} 
	}
</script> 
<script> 
function mostrarUniversidades() {
    var xhttp = new XMLHttpRequest(); 
    xhttp.onreadystatechange = function() { 
        if (this.readyState == 4 && this.status == 200) { 
            var jsonResponse = JSON.parse(this.responseText); 
            actualizarTablaUniversidades(jsonResponse);
        } 
    }; 
    xhttp.open("GET", "obtenerUniversidades", true); 
    xhttp.send(); 
}

function actualizarTablaUniversidades(universidades) {
    var tbody = document.getElementById("universitiesTableBody");
    tbody.innerHTML = ""; // Limpiar el contenido actual de la tabla

    // Iterar sobre cada universidad y agregar una fila a la tabla
    universidades.forEach(function(universidad) {
        var row = document.createElement("tr");
        row.id = universidad.uid; // Asignar el UID como ID de la fila

        // Agregar el listener de eventos para seleccionar la fila al hacer clic
        row.addEventListener("click", function() {
            seleccionarFila(universidad.uid);
        });

        var uidCell = document.createElement("td");
        uidCell.textContent = universidad.uid;
        row.appendChild(uidCell);

        var nombreCell = document.createElement("td");
        nombreCell.textContent = universidad.nombre;
        row.appendChild(nombreCell);

        var paginaWebCell = document.createElement("td");
        paginaWebCell.textContent = universidad.paginaWeb;
        row.appendChild(paginaWebCell);

        var paisCell = document.createElement("td");
        paisCell.textContent = universidad.pais;
        row.appendChild(paisCell);

        var provinciaEstadoCell = document.createElement("td");
        provinciaEstadoCell.textContent = universidad.provinciaEstado;
        row.appendChild(provinciaEstadoCell);

        var fechaGuardadoCell = document.createElement("td");
        fechaGuardadoCell.textContent = universidad.fechaGuardado;
        row.appendChild(fechaGuardadoCell);

        tbody.appendChild(row);
    });
}


</script>
<script>
    // Función para resaltar la fila seleccionada y almacenar su UID
    function seleccionarFila(uid) {
        // Obtener la fila seleccionada por su UID
        var filaSeleccionada = document.getElementById(uid);

        if (filaSeleccionada.classList.contains("filaSeleccionada")) {
            filaSeleccionada.classList.remove("filaSeleccionada");
            uidSeleccionado = null; // Restablecer el UID seleccionado
        } else {

            var filas = document.querySelectorAll("tbody tr");
            filas.forEach(function(fila) {
                fila.classList.remove("filaSeleccionada");
            });
            // Luego resaltar la fila actualmente seleccionada
            filaSeleccionada.classList.add("filaSeleccionada");
            uidSeleccionado = uid; // Almacenar el UID de la fila seleccionada
        }
    }

    // Función para eliminar la universidad seleccionada
    function eliminarUniversidadSeleccionada() {
	    if (uidSeleccionado !== null) {
	        var filaSeleccionada = document.getElementById(uidSeleccionado);
	        filaSeleccionada.remove();
	
	        // Enviar una solicitud al servidor para eliminar la universidad seleccionada
	        var xhttp = new XMLHttpRequest();
	        xhttp.onreadystatechange = function() {
	            if (this.readyState == 4 && this.status == 200) {
	                // La universidad se eliminó correctamente
	                console.log("Universidad eliminada de la base de datos.");
	            }
	        };
	        xhttp.open("POST", "eliminarUniversidad?id=" + uidSeleccionado, true);
	        xhttp.send();
	        
	        uidSeleccionado = null; // Restablecer el UID seleccionado
	    } else {
	        alert("Por favor, seleccione una universidad primero.");
	    }
	}
</script>
</html>