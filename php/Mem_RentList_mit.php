<?php

    $con = mysqli_connect("localhost", "cs2021mit", "2021mitt", "MIT");
    mysqli_query($con, "set names utf8");
 
    $result=mysqli_query($con, "SELECT number, rentTime, returnTime, wheelCode FROM Rent WHERE how < 99");
    $response = array();

while($row = mysqli_fetch_array($result)){
        array_push($response, array("number" => $row[0], "rentTime" => $row[1], "returnTime" => $row[2], "wheelCode" => $row[3]));
    }

    echo json_encode(array("response"=>$response));
    mysqli_close($con);

//
//
//    $con = mysqli_connect("localhost", "cs2021mit", "2021mitt", "MIT");
//    mysqli_query($con, "set names utf8");
//
//    $user = $_POST["user"];
//
//
//    $statement = mysqli_prepare($con, "SELECT number, rentTime, returnTime, wheelCode FROM Rent WHERE how < 99, user = ?");
//
//    mysqli_stmt_bind_param($statement, "s", $user);
//    mysqli_stmt_execute($statement);
//
//    $response = array();
//
//while($row = mysqli_fetch_array($result)){
//        array_push($response, array("number" => $row[0], "rentTime" => $row[1], "returnTime" => $row[2], "wheelCode" => $row[3]));
//    }
//
//    echo json_encode(array("response"=>$response));
//    mysqli_close($con);

?>


