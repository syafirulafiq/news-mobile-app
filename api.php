<?php
// api.php

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    // Database connection
    $conn = mysqli_connect('localhost', 'root', '', 'news');

    // Check if the connection is successful
    if (!$conn) {
        die("Connection failed: " . mysqli_connect_error());
    }

    // Handle incoming POST data
    $name = $_POST['name'];
    $email = $_POST['email'];
    $reports = $_POST['reports'];

    // Perform database insertion
    $query = "INSERT INTO reports (name, email, reports) VALUES ('$name', '$email', '$reports')";
    $result = mysqli_query($conn, $query);

    // Check if insertion was successful
    if (!$result) {
        echo mysqli_error($conn);
    } else {
        echo "Data inserted successfully";
    }

    // Close the database connection
    mysqli_close($conn);
}
?>