<?php
	
	$con = mysqli_connect("localhost", "", "", "");
	
	$email = $_POST["email"];
	
	function emailAvailable() {
		global $con, $email;
    	$statement = mysqli_prepare($con, "SELECT * FROM User WHERE email = ?");
    	mysqli_stmt_bind_param($statement, "s", $email);
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
	
	if(emailAvailable()){
		$response["success"] = true;
	}
	
	echo json_encode($response);
?>
