 
<?php 
 
 //database constants
 define('DB_HOST', 'localhost');
 define('DB_USER', 'root');
 define('DB_PASS', '');
 define('DB_NAME', 'NFTchat');
 
 //connecting to database and getting the connection object
 $conn = new mysqli(DB_HOST, DB_USER, DB_PASS, DB_NAME);
 
 //Checking if any error occured while connecting
 if (mysqli_connect_errno()) {
 echo "Failed to connect to MySQL: " . mysqli_connect_error();
 die();
 }
 
 //creating a query
 $stmt = $conn->prepare("SELECT id, name, price, price_btc, price_eth, image FROM nftchat;");
 
 //executing the query 
 $stmt->execute();
 
 //binding results to the query 
 $stmt->bind_result($id, $name, $price, $price_btc, $price_eth, $image);
 
 $cats = array(); 
 
 //traversing through all the result 
 while($stmt->fetch()){
 $temp = array();
 $temp['id'] = $id; 
 $temp['name'] = $name; 
 $temp['price'] = $price; 
 $temp['price_btc'] = $price_btc; 
 $temp['price_eth'] = $price_eth; 
 $temp['image'] = $image; 
 array_push($cats, $temp);
 }
 
 //displaying the result in json format 
 echo json_encode($cats);