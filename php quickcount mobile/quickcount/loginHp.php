<?php
define('HOST','localhost');
define('USER','root');
define('PASS','');
define('DB','member');

$con = mysqli_connect(HOST,USER,PASS,DB);

if($_SERVER['REQUEST_METHOD']=='POST'){
 $no = $_POST['no_hp'];
$sql = "select * from `user` where `no_hp`='$no' ";
 $result = array();
 
 $res = mysqli_query($con,$sql);
 while($row = mysqli_fetch_array($res)){
array_push($result,
array("id"=>$row[0],
"nama"=>$row[1],
"username"=>$row[2]
));
}
 
echo json_encode(array("user"=>$result));

$check = mysqli_fetch_array(mysqli_query($con,$sql));

if(isset($check)){
 echo ("success");
 }else{
 echo ("Invalid Username or Password");
 }
 
 }else{
 echo ("error try again");
 }
mysqli_close($con);

?>