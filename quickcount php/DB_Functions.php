<?php

class DB_Functions {
 
    private $conn;
 
    // constructor
    function __construct() {
        require_once 'config.php';
        // connecting to database
        $db = new config();
        $this->conn = $db->connect();
    }
 
    // destructor
    function __destruct() {
         
    }
 
    /**
     * Storing new user
     * returns user details
     */
    public function storeUser($username,$password) {
        
 
        $stmt = $this->conn->prepare("INSERT INTO admin( Username,Password) VALUES( ?, ?)");
        $stmt->bind_param("ss", $username,$password);
        $result = $stmt->execute();
        $stmt->close();
 
        // check for successful store
        if ($result) {
            $stmt = $this->conn->prepare("SELECT * FROM admin WHERE Username = ?");
            $stmt->bind_param("s", $username);
            $stmt->execute();
            $user = $stmt->get_result()->fetch_assoc();
            $stmt->close();
 
            return $admin;
        } else {
            return false;
        }
    }
 
    /**
     * Get user by email and password
     */
    public function getUserByUsernameAndPassword($username, $password) {
 
        $stmt = $this->conn->prepare("SELECT * FROM admin WHERE Username = ?");
 
        $stmt->bind_param("s", $username);
 
        if ($stmt->execute()) {
            $user = $stmt->get_result()->fetch_assoc();
            $stmt->close();
 
            // verifying user password
            $password = $user['Password'];
            $hash = $this->checkhashSSHA($password);
            // check for password equality
            if ($password == $hash) {
                // user authentication details are correct
                return $admin;
            }
        } else {
            return NULL;
        }
    }
 
    /**
     * Check user is existed or not
     */
    public function isUserExisted($username) {
        $stmt = $this->conn->prepare("SELECT member from admin WHERE username = ?");
 
        $stmt->bind_param("s", $username);
 
        $stmt->execute();
 
        $stmt->store_result();
 
        if ($stmt->num_rows > 0) {
            // user existed 
            $stmt->close();
            return true;
        } else {
            // user not existed
            $stmt->close();
            return false;
        }
    }
 
    /**
     * Encrypting password
     * @param password
     * returns salt and encrypted password
     */
   
 
}
 
?>