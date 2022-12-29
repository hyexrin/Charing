
<?php
    $conn = mysqli_connect("localhost", "cs2021mit", "2021mitt", "MIT");

   $des_name = json_decode(file_get_contents("php://input"))->{"des_name"};  
    $sql = "SELECT * FROM Destination where des_name = '$des_name'";
   $result = mysqli_query($conn, $sql);
   if (mysqli_num_rows($result) > 0) {
     $data = array();
   while($row = mysqli_fetch_assoc($result)) {
   array_push($data, array("code"=>$row["code"], "des_name"=>$row["des_name"], "x_spot"=>$row["x_spot"], "y_spot"=>$row["y_spot"], "z_spot"=>$row["z_spot"]));
    }
             echo json_encode(array("Result"=>$data));

   }else{

   echo json_encode(array("Result"=>"there is no position"));
   }
   
   mysqli_close($conn); 
?>