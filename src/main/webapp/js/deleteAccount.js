// author : Pachalis
// REFERS TO deleteAccount.jsp

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
