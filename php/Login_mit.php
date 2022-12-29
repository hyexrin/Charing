<?php
    $con = mysqli_connect("localhost", "cs2021mit", "2021mitt", "MIT");
    mysqli_query($con,'SET NAMES utf8');

    $id = $_POST["id"];
    $pwd = $_POST["pwd"];
    
    $statement = mysqli_prepare($con, "SELECT * FROM Member WHERE id = ? AND pwd = ?");
    mysqli_stmt_bind_param($statement, "ss", $id, $pwd);
    mysqli_stmt_execute($statement);


    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $id, $pwd, $name, $address, $phone, $protector, $birth);

    $response = array();
    $response["success"] = false;
 
    while(mysqli_stmt_fetch($statement)) {
        $response["success"] = true;
        $response["id"] = $id;
        $response["pwd"] = $pwd;
        $response["name"] = $name;
        $response["address"] = $address;  
        $response["phone"] = $phone;
        $response["protector"] = $protector;
        $response["birth"] = $birth;
    }

    echo json_encode($response);



?>