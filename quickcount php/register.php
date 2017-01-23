<?php
error_reporting(0);
require "config.php";
 
$usernaname = $_POST["Username"];
$password = $_POST["Password"];

 
$username = "admin";
$password = "admin";
 
$sql = "INSERT INTO `admin` (`id`,`username`, `password`) VALUES (Null, '".$username."', '".$password."');";
if(!mysqli_query($con, $sql)){
    echo '{"message":"Unable to save the data to the database."}';
}else {
	echo 'success save data ';
}
 
?>