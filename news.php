<?php
// Firstly, perform the database connection
$conn = mysqli_connect('localhost', 'root', '', 'news');

// Then prepare the query statement
$query = $conn->prepare("SELECT title, reporter, `desc`, DATE_FORMAT(date, '%W %M %d %Y') FROM news");

// Then execute the query
$query->execute();

// Then bind the query results to variables
$query->bind_result($title, $reporter, $desc, $date);

// Prepare an array to hold the query results
$contents = array();

// Loop through the rows and assign values to the 'data' variable
while ($query->fetch()) {
    $data = array();
    $data['title'] = $title;
    $data['reporter'] = $reporter;
    $data['desc'] = $desc;
    $data['date'] = $date;

    // Push the array to the contents array
    array_push($contents, $data);
}

// Finally, send the data as a JSON object
echo json_encode($contents);
?>