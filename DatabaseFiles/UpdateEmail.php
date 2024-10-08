<?php

	$con=mysqli_connect("localhost", "id15534403_snapchatdatabasetest", "Password123456789!", "id15534403_snapchatdatabase");
	
	$phoneNum = $_POST["email"];
	$username = $_POST["username"];
	
	$statement = mysqli_prepare($con, "UPDATE `User` SET email = ? WHERE username = ?");
	mysqli_stmt_bind_param($statement, "ss", $email, $username);
	mysqli_stmt_execute($statement);
	mysqli_stmt_store_result($statement);
	
	$response = array();
	$response["success"] = true;
	
	echo json_encode($response);
	
?>