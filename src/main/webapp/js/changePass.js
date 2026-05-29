// author : Paschalis Koukos
// JavaScript for the change password page,validates the new password and checks if the three
// new password fields match and if the new password is different from the old one
// refers to the changePassword.jsp page and is included in that page using a script tag
function isValid(){
    // Get the values of the old password and the three new password fields
    let oldPass = document.getElementById("oldPassword"); 
    let newPass1 = document.getElementById("newPassword1");
    let newPass2 = document.getElementById("newPassword2");
    let newPass3 = document.getElementById("newPassword3");

    if (newPass1.value !== newPass2.value || newPass2.value !== newPass3.value || newPass1.value!==newPass3.value) {
        alert("The three new password fields must match!"); // check if the three new password fields match, 
        // if not, alert the user and return false to prevent form submission
        return false;
    }

    if(newPass1.value === oldPass.value) { // check if the new password is different from the old one, if not alert the user and return false to prevent form submission
        alert("The new password must be different from the existing one!");
        return false;
    }

    if(!isOK(newPass1.value)){ // check if the new password is valid according to the criteria (longer than 6 characters and contain both letters and digits),
        alert("Password must be longer than 6 characters and contain both letters and digits."); // if not, alert the user and return false to prevent form submission
        return false;
    }
    return true; // everything is ok, return true to allow form submission
}

function isOK(pass) {

    // check if the password is longer than 6 characters, if not return false
    if (pass.length <= 6) return false;

    let hasLetter = false;
    let hasDigit = false;

    // check if the password contains both letters and digits, if not return false
    for (let i = 0; i < pass.length; i++) { // iterate through each character of the password and check if it is a letter or a digit,
    // if it is a letter set hasLetter to true, if it is a digit set hasDigit to true
        let char = pass[i];
        // check if the character is a letter (either uppercase or lowercase)
        // or a digit, and set the corresponding flags accordingly
        if ((char >= 'a' && char <= 'z') || (char >= 'A' && char <= 'Z')) { 
            hasLetter = true;
        }
        else if (char >= '0' && char <= '9') {
            hasDigit = true;
        }
    }

    // check if both conditions are met and are true, if so return true, otherwise return false
    return hasLetter && hasDigit;
}

const params = new URLSearchParams(window.location.search); // That takes the query string from the URL and creates a URLSearchParams object,
//  which allows us to easily access the parameters in the query string.

if (params.has('error')) { // handle errors
    const errorType = params.get('error'); // get the error type from the URL parameters
    if (errorType === 'wrongPass') { // check error type and alert the user accordingly
        alert("Wrong old password entered");
    } else{
        alert("An unknown error occurred");
    }
    //Clear the URL to prevent the error message from appearing on every refresh
    window.history.replaceState({}, document.title, "changePassword.jsp"); // This line uses the History API to replace the current history entry with a new
    // one that has the same title but a different URL (without the query parameters). This effectively clears the URL parameters, so if the user refreshes the page, they won't see the error message again.
}
