// author : Pachalis
// REFERS TO deleteAccount.jsp
// this script is responsible for handling the error messages that may occur during the account deletion process. It checks
// the URL for any error parameters and displays an appropriate alert message to the user based on the type of error encountered.
// After displaying the alert, it clears the URL to prevent the error message from appearing on every page refresh.
const params = new URLSearchParams(window.location.search); // That takes the query string from the URL and creates a URLSearchParams object,
//  which allows us to easily access the parameters in the query string.

if (params.has('error')) { // handle errors
    const errorType = params.get('error'); // get the error type from the URL parameters
    if (errorType === 'mismatch') { // check error type and alert the user accordingly
        alert("Passwords don't match");
    } else if (errorType === 'invalid') {
        alert("Invalid Password given");
    }else if(errorType === 'delete_failed') {
        alert("An error has occurred. Please try again later.");
    }
    //Clear the URL to prevent the error message from appearing on every refresh
    window.history.replaceState({}, document.title, "index.html");
}
