<?php
    $con = mysqli_connect('localhost', 'cs2021mit', '2021mitt', 'cs2021mit');

    $UserId = $_POST["id"];
    $UserPwd = $_POST["pwd"];

    $statement = mysqli_prepare($con, "SELECT id FROM Member WHERE id = ?");

    mysqli_stmt_bind_param($statement, "s", $UserId);
    mysqli_stmt_execute($statement);
    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $userID);

    $response = array();
    $response["success"] = true;

    while(mysqli_stmt_fetch($statement)){
      $response["success"] = false;
      $response["id"] = $UserId;
    }

    echo json_encode($response);
?>