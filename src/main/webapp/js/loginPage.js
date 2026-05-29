// author : Paschalis Koukos
// JS FOR index.html file

const params = new URLSearchParams(window.location.search); // Create a URLSearchParams object to access query parameters from the URL
// if our parameters from the form have some kind of error, we detect it and alert the user about that error.
if (params.has('error')) {
    alert("Wrong Username or password. Please try again."); // error detected , alert the user
    window.history.replaceState({}, document.title, "index.html"); // Remove the error parameter from the URL to prevent repeated alerts on page refresh
}


// Add an event listener to the form submission to validate the input fields before submitting the form
// We do don want the user to submit the form without typing anything in the username or password fields,
// so we check if those fields are empty and if they are, we alert the user to fill them in and prevent the form from being submitted.
document.querySelector('form').addEventListener('submit', function(event) {

    // get params from the form 
    const user = document.getElementById('username').value.trim();
    const pass = document.getElementById('password').value.trim();

    if (user === "" || pass === "") { // Check if either the username or password fields are empty
        alert("Please fill in both username and password fields."); // empty parameter found! Alert the user to fill in both fields
        event.preventDefault(); // Prevent the form from being submitted if either field is empty
        // this will return back to the login page without submitting the form,
        // allowing the user to correct their input and try again.
    }
});