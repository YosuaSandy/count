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
$t = "select * from suara where id_tps = '$idTps'";
$result =mysqli_query ($con,$t);

 if ($result && mysqli_num_rows($result) > 0 ) {
 $sql = "update suara set sah ='$sah',rusak = '$rusak',calon1 = '$calon1',calon2 = '$calon2' where id_tps = '$idTps'";
 $res = mysqli_query($con,$sql);
echo "data suara sudah terupdate";
}else {
$sql = "insert into suara (id_tps,sah,rusak,calon1,calon2) values ('$idTps','$sah','$rusak','$calon1','$calon2')";
 $res = mysqli_query($con,$sql);
 echo "data berhasil diinputkan";
 }
 
$result = array();
 
 
mysqli_close($con);
}