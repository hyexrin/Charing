<?php

    $con = mysqli_connect("localhost", "cs2021mit", "2021mitt", "MIT");



    $z_spot  = $_GET["z_spot"];
 

        $statement = mysqli_prepare($con, "SELECT * FROM Destination where z_spot = ?");

    mysqli_stmt_bind_param($statement, "s", $z_spot);

    $result = mysqli_stmt_execute($statement);


    mysqli_stmt_store_result($statement);

    mysqli_stmt_bind_result($statement, $code, $des_name, $x_spot,  $y_spot, $z_spot);


    $response = array();

    $response["success"] = false1;


    while(mysqli_stmt_fetch($statement)) {

        $response["success"] = true;

        $response["code"] = $code;

        $response["x_spot"] = $x_spot;

        $response["y_spot"] = $y_spot;

        $response["z_spot"] = $z_spot;        

    }


  
    echo json_encode($response);
 
echo $result;






?>