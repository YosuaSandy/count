<?php

 define('HOST','localhost');
define('USER','root');
define('PASS','');
define('DB','despilwalkot_2015');
 
$con = mysqli_connect(HOST,USER,PASS,DB);
$sql = "select * from wilayah where level_wilayah = 3";
 
$res = mysqli_query($con,$sql);
 
$result = array();
 
while($row = mysqli_fetch_array($res)){
array_push($result,
array("id"=>$row[0],
"kode_wilayah"=>$row[1],
"nama"=>$row[2]
));
}
 
echo json_encode(array("wilayah"=>$result));
 
mysqli_close($con);

 ?>