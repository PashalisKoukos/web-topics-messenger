// author : Pachalis Koukos
// Refers to the registerPage.html and handles form validation and error messages
// this js is responsible for validating the registration form and displaying appropriate error messages based on the URL parameters

function isValid(){
    // get the values of the input fields
    let username = document.getElementById("username").value.toString().trim();
    let password = document.getElementById("password").value.toString().trim();
    let confirmPassword = document.getElementById("confirm-password").value.toString().trim();

    if(username.length<=6){ // check if the username is at least 6 characters long
        alert("Your username must be at least 6 characters long");
        return false;
    }

    if(password!==confirmPassword){ // check if the password and confirm password fields match
        alert("Passwords must match"); // alert the user if they don't match
        return false; // prevent form submission
    }


    if(!isOK(password)){ // check if the password is valid according to the criteria defined in the isOK function
        alert("Password must be longer than 6 characters and contain both letters and digits."); // alert the user 
        return false; // prevent form submission
    }
    return true; // if all checks pass, allow form submission

}

function isOK(pass) {
    // check length of the password
    if (pass.length <= 6) return false;

    let hasLetter = false;
    let hasDigit = false;

    
    for (let i = 0; i < pass.length; i++) { // check each character in the password
        let char = pass[i];

        // if it is A-Z or a-z, set hasLetter to true
        if ((char >= 'a' && char <= 'z') || (char >= 'A' && char <= 'Z')) {
            hasLetter = true;
        }
        // if it is 0-9, set hasDigit to true
        else if (char >= '0' && char <= '9') {
            hasDigit = true;
        }
    }

    return hasLetter && hasDigit; // both must be true for the password to be valid
}

const params = new URLSearchParams(window.location.search); // get the query parameters from the URL

if (params.has('error')) { // handle errors based on the error parameter in the URL
    const errorType = params.get('error'); // get the specific error type
    if (errorType === 'user_exists') {
        alert("This username is already taken. Please choose another one."); // username already exists error
    } else if (errorType === 'db_error') {
        alert("A database error occurred. Please try again later."); // an error DB error occurred
    }

    window.history.replaceState({}, document.title, "index.html"); // remove the error parameter from the URL to prevent repeated alerts on page refresh
}
