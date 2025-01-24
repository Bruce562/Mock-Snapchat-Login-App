<?php

	$con=mysqli_connect("localhost", "", "", "");
	
	$password = $_POST["password"];
	$username = $_POST["username"];
	
	$statement = mysqli_prepare($con, "SELECT * FROM User WHERE username = ? AND password = ?");
	mysqli_stmt_bind_param($statement, "ss", $username, $password);
	mysqli_stmt_execute($statement);
	
	mysqli_stmt_store_result($statement);
	mysqli_stmt_bind_result($statement, $name, $birthday, $username, $password);
	
	$response = array();
	$response["success"] = false;
	
	while(mysqli_stmt_fetch($statement)){
		$response["success"] = true;
		$response["name"] = $name;
		$response["birthday"] = $birthday;
		$response["username"] = $username;
		$response["password"] = $password;
	}
	
	echo json_encode($response);
	
?>
