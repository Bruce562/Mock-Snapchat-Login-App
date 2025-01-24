<?php
	$con = mysqli_connect("localhost", "", "", "");
	
	$name = $_POST["name"];
	$birthday = $_POST["birthday"];
	$password = $_POST["password"];
	$username = $_POST["username"];

	$statement = mysqli_prepare($con, "INSERT INTO User (name, birthday, username, password) VALUES (?, ?, ?, ?) ");
	mysqli_stmt_bind_param($statement, "ssss", $name, $birthday, $username, $password);
	mysqli_stmt_execute($statement);
	
	$response = array();
	$response["success"] = true;
	
	echo json_encode($response);
?>
