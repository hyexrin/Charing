<?php
  
    $con = mysqli_connect("localhost", "cs2021mit", "2021mitt", "MIT");
    mysqli_query($con, "set names utf8");
 
    $sql= "SELECT number, wheelCode, rentTime, reuturnTime FROM Rent";
    $result=mysqli_query($con, $sql);
 
    $rowCnt= mysqli_num_rows($result);
 
    $arr= array(); //빈 배열 생성
 
    for($i=0;$i<$rowCnt;$i++){
        $row= mysqli_fetch_array($result, MYSQLI_ASSOC);
        //각 각의 row를 $arr에 추가
        $arr[$i]= $row;
        
    }
 
    //배열을 json으로 변환하는 함수가 있음.
        $jsonData=json_encode($arr); //json배열로 만들어짐.
        echo "$jsonData";
 
    mysqli_close($con);
 
?>