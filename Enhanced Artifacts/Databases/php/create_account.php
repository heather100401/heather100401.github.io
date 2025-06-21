<?php

header("Access-Control-Allow-Origin: *");
header("Content-Type: application/json");

include 'db.php';

$data = json_decode(file_get_contents("php://input"), true);
$username = $data["username"];
$password = $data["password"];


$sql = "REPLACE INTO login (username, password)
        VALUES (?, ?, ?, ?)";

$stmt = $conn->prepare($sql);
$stmt->bind_param("ssss", $username, $password);
$result = $stmt->execute();

echo json_encode(["success" => $result]);
?>
