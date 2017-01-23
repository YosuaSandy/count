<?php
$respon = array();
 
 require_once('config.php');
 
 if (isset ($_GET ["id"])){
	$id = $_GET ['id'];
	$result = mysqli_query("SELECT member FROM tps WHERE id =$id");
	
	if (!empty($result)) {
		if (mysqli_num_rows($result) > 0){
			$result =mysqli_fetch_array($result);
			
			$Wilayah = array();
			$Wilayah ["id"] = result ["id"];
			$Wilayah ["no_tps"] = result ["no_tps"];
			
			$respon ["success"] = 1;
			
			$respon["tps"] = array();
			
			array_push($respon ["tps"], $tps);
			
			echo json_encode($respon);
		}else {
			$respon ["success"] = 0;
			$respon["message"] = "tps tidak ditemukan";
			
			echo json_encode($respon);

			}
	}else {
		$respon["success"] = 0;
		$respon["message"] = "tps tidak ditemukan";
		
		echo json_encode ($respon);
	}
 }else {
	$respon["success"] = 0;
	$respon ["message"] = "data tidak ditemukan";
	
	echo json_encode($respon);
 }