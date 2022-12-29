<?php
    $con = mysqli_connect("localhost", "cs2021mit", "2021mitt", "MIT");
    mysqli_query($con,'SET NAMES utf8');

    $userID = $_POST["UserID"];
    $userPassword = $_POST["UserPassword"];
    
    $statement = mysqli_prepare($con, "SELECT * FROM USER WHERE userID = ? AND userPassword = ?");
    mysqli_stmt_bind_param($statement, "ss", $userID, $userPassword);
    mysqli_stmt_execute($statement);


    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $userID, $userPassword, $userName, $userAge);

    $response = array();
    $response["success"] = false;
 
    while(mysqli_stmt_fetch($statement)) {
        $response["success"] = true;
        $response["UserID"] = $userID;
        $response["UserPassword"] = $userPassword;
        $response["UserName"] = $userName;
        $response["UserAge"] = $userAge;        
    }

    echo json_encode($response);



?>