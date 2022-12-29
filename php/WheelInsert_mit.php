<?php 
    $con = mysqli_connect("localhost", "cs2021mit", "2021mitt", "MIT");
    mysqli_query($con,'SET NAMES utf8');


//    $code = $_POST["code"];
    $name = $_POST["name"];
    $date = $_POST["date"];
    $rent = $_POST["rent"];
    $break = $_POST["break"];


    $statement = mysqli_prepare($con, "INSERT INTO Wheelchair (name, date, rent, break) VALUES (?,?,?,?)");
    mysqli_stmt_bind_param($statement, "ssss", $name, $date, $rent, $break);
    mysqli_stmt_execute($statement);


    $response = array();
    $response["success"] = true;
 
   
    echo json_encode($response);

?>