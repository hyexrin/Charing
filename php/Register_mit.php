<?php 
    $con = mysqli_connect("localhost", "cs2021mit", "2021mitt", "MIT");
    mysqli_query($con,'SET NAMES utf8');

    $id = $_POST["id"];
    $pwd = $_POST["pwd"];
    $name = $_POST["name"];
    $address = $_POST["address"];
    $phone = $_POST["phone"];
    $protector = $_POST["protector"];
    $birth = $_POST["birth"];

    $statement = mysqli_prepare($con, "INSERT INTO Member VALUES (?,?,?,?,?,?,?)");
    mysqli_stmt_bind_param($statement, "ssssssi", $id, $pwd, $name, $address, $phone, $protector, $birth);
    mysqli_stmt_execute($statement);


    $response = array();
    $response["success"] = true;
 
   
    echo json_encode($response);



?>