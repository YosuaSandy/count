<?php
define('HOST','localhost');
define('USER','root');
define('PASS','');
define('DB','despilwalkot_2015');

$con = mysqli_connect(HOST,USER,PASS,DB);

if($_SERVER['REQUEST_METHOD']=='POST'){
 $kode = $_POST['kode_wilayah'];
$sql = "select * from tps where kd_wilayah = '$kode' ";
 
$res = mysqli_query($con,$sql);
 
$result = array();
 
 while($row = mysqli_fetch_array($res)){
array_push($result,
array("id"=>$row[0],
"no_tps"=>$row[2],
"alamat"=>$row[7]
));
}

if (!$res) {
    printf("Error: %s\n", mysqli_error($con));
    exit();
}
 
echo json_encode(array("Tempat"=>$result));


mysqli_close($con);
}


 ?>