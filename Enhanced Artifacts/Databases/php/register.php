<?php
header("Access-Control-Allow-Origin: *");
header("Content-Type: application/json");

include 'db.php';

$input = json_decode(file_get_contents("php://input"), true);

if (!$input || !isset($input["username"]) || !isset($input["password"])) {
    echo json_encode(["success" => false, "message" => "Invalid input"]);
    exit;
}

$username = $input["username"];
$password = $input["password"];

// Check if username already exists
$checkQuery = "SELECT * FROM users WHERE username = ?";
$stmt = $conn->prepare($checkQuery);
$stmt->bind_param("s", $username);
$stmt->execute();
$result = $stmt->get_result();

if ($result->num_rows > 0) {
    echo json_encode(["success" => false, "message" => "Username already taken"]);
} else {
    // Register user (Consider hashing password in production)
    $insertQuery = "INSERT INTO users (username, password) VALUES (?, ?)";
    $stmt = $conn->prepare($insertQuery);
    $stmt->bind_param("ss", $username, $password);

    if ($stmt->execute()) {
        $newUserID = $conn->insert_id;  // Get the new user's ID
        echo json_encode([
            "success" => true,
            "userID" => $newUserID
        ]);
    } else {
        echo json_encode(["success" => false, "message" => "Registration failed"]);
    }
}
$stmt->close();
$conn->close();
?>
