<?php

$conn = mysqli_connect('localhost', 'root', '', 'news');


$query = "SELECT * FROM reports ORDER BY date DESC";
$result=mysqli_query($link,$query);


$output = array();


foreach ($result as $row) {
 array_push($output,$row);


}

// assign to json variable
$json=json_encode($output);

//declare document type to json and output json
header("Content-Type: application/json");
echo $json;




?>
