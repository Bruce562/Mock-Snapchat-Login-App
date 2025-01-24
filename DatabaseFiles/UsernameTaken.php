<?php
	
	$con = mysqli_connect("localhost", "", "", "");
	
	$username = $_POST["username"];
	
	function usernameAvailable() {
		global $con, $username;
    	$statement = mysqli_prepare($connect, "SELECT * FROM User WHERE username = ?");
    	mysqli_stmt_bind_param($statement, "s", $username);
    	mysqli_stmt_execute($statement);
    	mysqli_stmt_store_result($statement);
    	$count = mysqli_stmt_num_rows($statement);
    	mysqli_stmt_close($statement);
    	if($count < 1){
    		return true;
    	}
    	else{
    		return false;
    	}
	}
	
	$response = array();
	$response["success"] = false;
	
	if(usernameAvailable()){
		$response["success"] = true;
	}
	
	echo json_encode($response);
?>
