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
		<div id="profile" class="hideElement"> 
			<br><br> 
			<p><strong><span id="insertState"></span></strong></p> 
			 

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
