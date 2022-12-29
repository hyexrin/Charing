<?php
    $con = mysqli_connect("localhost", "cs2021mit", "2021mitt", "MIT");
    mysqli_query($con, "set names utf8");

    $number = $_POST["number"];

    $statement = mysqli_prepare($con, "UPDATE Rent SET how = 99 where number = ?");
    mysqli_stmt_bind_param($statement, "i", $number);
    mysqli_stmt_execute($statement);

    $response = array();
    $response["success"] = true;

    echo json_encode($response);
?>