<?php
define('HOST','localhost');
define('USER','root');
define('PASS','');
define('DB','member');
 
$con = mysqli_connect(HOST,USER,PASS,DB);
 
$sql = "select * from wilayah";
 
$res = mysqli_query($con,$sql);
 
$result = array();
 
while($row = mysqli_fetch_array($res)){
array_push($result,
array('id'=>$row[0],
'name'=>$row[2],
'url'=>$row[3]
));
}
 
echo json_encode(array("result"=>$result));
 
mysqli_close($con);