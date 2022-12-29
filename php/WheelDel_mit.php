<?php
    $con = mysqli_connect("localhost", "cs2021mit", "2021mitt", "MIT");
    mysqli_query($con, "set names utf8");

    $name = $_POST["name"];

    $statement = mysqli_prepare($con, "DELETE FROM Wheelchair WHERE name = ?");
    mysqli_stmt_bind_param($statement, "s", $name);
    mysqli_stmt_execute($statement);

    $response = array();
    $response["success"] = true;

    echo json_encode($response);
?>