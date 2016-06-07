<?php
$con=mysqli_connect("localhost","root","","member");

if (mysqli_connect_errno($con))
{
   echo "Failed to connect to MySQL: " . mysqli_connect_error();
}
if($_SERVER['REQUEST_METHOD']=='GET'){
$result = mysqli_query($con,"SELECT * FROM admin where Username='$username' and Password='$password'");
$row = mysqli_fetch_array($result);
$data = $row[0];

if($data){
echo $data;
}
mysqli_close($con);

}
?>