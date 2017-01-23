<?php
 if($_SERVER['REQUEST_METHOD']=='GET'){
 
 $username = $_GET['username'];
 
 require_once('dbConnect.php');
 
 $sql = "SELECT member FROM admin  WHERE username='".$username."'";
 
 $r = mysqli_query($conn,$sql);
 
 $res = mysqli_fetch_array($r);
 
 $result = array();
 
 array_push($result,array(
 "username"=>$res['username'],
 "password"=>$res['password']
 )
 );
 
 echo json_encode(array("result"=>$result));
 
 mysqli_close($con);
 
 }