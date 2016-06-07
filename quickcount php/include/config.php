<?php
 
/**
 * Database config variables
 */

 
define ('db_name','member');
define ('mysql_user','root');
define ('mysql_pass','');
define ('server_name','Server: 127.0.0.1');


	$con = mysqli_connect($server_name, $mysql_user, $mysql_pass, $db_name);
 
if(!$con){
    echo '{"message":"Unable to connect to the database."}';
}else {
	echo  '{"message":"connection success"}';
}
?>