<?php
	class dbConnect {
    private $conn;
 
    // Connecting to database
    public function connect() {
        require_once 'include/config.php';
         
        // Connecting to mysql database
        $this->conn = new mysqli(HOST, USER, PASS, DB);
	     
         
        // return database handler
        return $this->conn;
    }
}
