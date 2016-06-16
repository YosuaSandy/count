<?php
define('HOST','localhost');
define('USER','root');
define('PASS','');
define('DB','despilwalkot_2015');

$con = mysqli_connect(HOST,USER,PASS,DB);

if($_SERVER['REQUEST_METHOD']=='POST'){
 $kode = $_POST['kode_wilayah'];
 $tps = $_POST ['no_tps'];
$sql = "select * from tps where kd_wilayah = '$kode' and no_tps = '$tps'";
 
$res = mysqli_query($con,$sql);
 
$result = array();
 
 while($row = mysqli_fetch_array($res)){
array_push($result,
array("ID"=>$row[0],
"Laki-laki"=>$row[3],
"Perempuan"=>$row[4],
"Jumlah"=>$row[5]

));
}

if (!$res) {
    printf("Error: %s\n", mysqli_error($con));
    exit();
}
 
echo json_encode(array("Peserta"=>$result));


mysqli_close($con);
}


 ?>