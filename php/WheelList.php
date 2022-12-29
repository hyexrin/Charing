<?php
  
    $con = mysqli_connect("localhost", "cs2021mit", "2021mitt", "MIT");
    mysqli_query($con, "set names utf8");
 
    $result=mysqli_query($con, "SELECT * FROM Wheelchair");
    $response = array();

    while($row = mysqli_fetch_array($result)){
        array_push($response, array("code" => $row[0], "name" => $row[1], "date" => $row[2], "rent" => $row[3], "break" => $row[4]));
    }

    echo json_encode(array("response"=>$response));
    mysqli_close($con);

//    $rowCnt= mysqli_num_rows($result);
// 
//    $arr= array(); //빈 배열 생성
// 
//    for($i=0;$i<$rowCnt;$i++){
//        $row= mysqli_fetch_array($result, MYSQLI_ASSOC);
//        //각 각의 row를 $arr에 추가
//        $arr[$i]= $row;
//        
//    }
// 
//    //배열을 json으로 변환하는 함수가 있음.
//        $jsonData=json_encode($arr); //json배열로 만들어짐.
//        echo "$jsonData";
 
?>
