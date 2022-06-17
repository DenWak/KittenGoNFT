<?php
require "DataBase.php";
$db = new DataBase();
if (isset($_POST['name']) && isset($_POST['price']) && isset($_POST['image'])) {
    if ($db->dbConnect()) {
        if ($db->AddCat("nftchat", $_POST['name'], $_POST['price'], $_POST['image'])) {
            echo "Added the Cat";
        } else echo "Add Failed";
    } else echo "Error: Database connection";
} else echo "All fields are required";
?>
