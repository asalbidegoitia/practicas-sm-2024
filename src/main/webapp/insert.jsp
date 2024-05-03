<!DOCTYPE html> 
<html lang="en"> 
<head> 
    <meta charset="utf-8"> 
    <meta http-equiv="X-UA-Compatible" content="IE=edge"> 
    <meta name="viewport" content="width=device-width, initial-scale=1"> 
    <title>Insertar</title> 
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
    <!-- Barra de Navegación -->
    
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand" href="index.jsp">Inicio</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item active">
                    <a class="nav-link" href="insert.jsp">Introducir</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="index.jsp">Consultar</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link disabled" href="index.jsp">Disabled</a>
                </li>
            </ul>
        </div>
    </nav>
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
