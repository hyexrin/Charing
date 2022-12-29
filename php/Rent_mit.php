<?php 
    $con = mysqli_connect("localhost", "cs2021mit", "2021mitt", "MIT");
    mysqli_query($con,'SET NAMES utf8');

//    $number = $_POST["number"];
    $rentTime = $_POST["rentTime"];
//    $returnTime = $_POST["returnTime"];
    $user = $_POST["user"];
    $wheelCode = $_POST["wheelCode"];
    $rentPlace = $_POST["rentPlace"];
    $returnplace = $_POST["returnPlace"];
    $time = $_POST["time"];
    $how = $_POST["how"];


    $statement = mysqli_prepare($con, "INSERT INTO Rent (rentTime, returnTime, user, wheelCode, rentPlace, returnPlace, time, how) VALUES (NOW(),DATE_ADD(NOW(), INTERVAL 2 HOUR),?,?,?,?,?,?)");
    mysqli_stmt_bind_param($statement, "siiisi", $user, $wheelCode, $rentPlace, $returnplace, $time, $how);
    mysqli_stmt_execute($statement);


    $response = array();
    $response["success"] = true;
 
   
    echo json_encode($response);



?> 