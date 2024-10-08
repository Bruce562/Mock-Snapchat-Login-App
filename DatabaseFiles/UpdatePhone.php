<?php

	$con=mysqli_connect("localhost", "id15534403_snapchatdatabasetest", "Password123456789!", "id15534403_snapchatdatabase");
	
	$phoneNum = $_POST["phoneNum"];
	$username = $_POST["username"];
	
	$statement = mysqli_prepare($con, "UPDATE `User` SET phoneNum = ? WHERE username = ?");
	mysqli_stmt_bind_param($statement, "is", $phoneNum, $username);
	mysqli_stmt_execute($statement);
	mysqli_stmt_store_result($statement);
	
	$response = array();
	$response["success"] = true;
	
	echo json_encode($response);
	
?>