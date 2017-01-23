<?php
define('HOST','localhost');
define('USER','root');
define('PASS','');
define('DB','despilwalkot_2015');

$con = mysqli_connect(HOST,USER,PASS,DB);

if($_SERVER['REQUEST_METHOD']=='POST'){
$idTps = $_POST ['id_tps'];
 $sah = $_POST['sah'];
 $rusak = $_POST ['rusak'];
 $calon1 = $_POST ['calon1'];
 $calon2 = $_POST['calon2'];
$sql = "update suara set sah ='$sah',rusak = '$rusak',calon1 = '$calon1',calon2 = '$calon2' where id_tps = '$idTps'";
 
$result = mysqli_query($con,$sql);
 

  if ($result) {
        // successfully updated
        $response["success"] = 1;
        $response["message"] = " successfully updated.";

        // echoing JSON response
        echo json_encode($response);
    } else {

    }
} else {
    // required field is missing
    $response["success"] = 0;
    $response["message"] = "Required field(s) is missing";

    // echoing JSON response
    echo json_encode($response);

mysqli_close($con);
}

 