<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Sign Up</title>
</head>
<body>
<h1>Sign Up</h1>
<form id="signup-form" action="/banking-backend/create-user" method="post">
    <label for="name">Name:</label>
    <input type="text" id="name" name="name" required><br><br>

    <label for="phone">Phone:</label>
    <input type="text" id="phone" name="phone" required><br><br>

    <label for="email">Email:</label>
    <input type="email" id="email" name="email" required><br><br>

    <label for="userName">Username:</label>
    <input type="text" id="userName" name="userName" required><br><br>

    <label for="password">Password:</label>
    <input type="password" id="password" name="password" required><br><br>

    <label for="address">Address:</label>
    <textarea id="address" name="address" rows="4" cols="50" required></textarea><br><br>

    <input type="submit" value="Sign Up">
</form>

<div id="success-message" style="display: none;">
    <p>Sign-up successful!</p>
</div>
<div id="unsuccess-message" style="display: none;">
    <p>Sign-up was not successful, please try again!</p>
</div>
<div id="conflict-message" style="display: none;">
    <p>User already exists!</p>
</div>

<script>
    document.addEventListener("DOMContentLoaded", function () {
        document.getElementById("signup-form").addEventListener("submit", function (event) {
            event.preventDefault();

            var formData = {
                name: document.getElementById("name").value,
                phone: document.getElementById("phone").value,
                email: document.getElementById("email").value,
                userName: document.getElementById("userName").value,
                password: document.getElementById("password").value,
                address: document.getElementById("address").value
            };

            fetch("/banking-backend/create-user", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(formData)
            })
                .then(response => {
                    if (response.status === 400){
                        const errorMessage = document.getElementById("unsuccess-message");
                        errorMessage.style.display = "block";
                    }
                    // else if(response.status === 409){
                    //     const conflictMessage = document.getElementById("conflict-message");
                    //     conflictMessage.style display = "block";  // corrected this line
                    // }
                    else if(response.status === 201){
                        const successMessage = document.getElementById("success-message");
                        successMessage.style.display = "block";
                    }
                    return response.json();
                })
                .then(data => {
                    // Handle the response as needed
                    console.log(data);
                })
                .catch(error => {
                    // Handle any errors
                    console.error(error);
                });
        });
    });
</script>
</body>
</html>
