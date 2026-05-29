// author : Paschalis Koukos
// This JavaScript file contains the logic for saving a new topic when the user clicks the "Save Topic"
// button on the createNewTopic.html page.
// It sends a POST request to the CreateNewTopicServlet with the topic name and description, and then displays 
// the response message from the server.
// refers to the createNewTopic.html page and is included in that page using a script tag

async function saveTopic() {
    const data = { // Create a data object that contains the topic name and description, which are retrieved from the input fields in the HTML form
        name: document.getElementById('topicName').value, // Get the value of the topic name input field
        description: document.getElementById('topicDesc').value // Get the value of the topic description textarea
    };

    const response = await fetch('CreateNewTopicServlet', { // Send a POST request to the CreateNewTopicServlet API endpoint
        method: 'POST', // Set the HTTP method to POST
        headers: { 'Content-Type': 'application/json' }, // Set the content type to JSON
        body: JSON.stringify(data) // Convert the data object to a JSON string and include it in the request body
    });

    const result = await response.json(); // Parse the JSON response from the server
    document.getElementById('responseMessage').innerText = result.message; // Display the response message from the server in the responseMessage paragraph element
    
    // If the response message indicates that the topic was created successfully,
    // clear the input fields for the topic name and description
    if (result.message.includes("successfully")) {
        document.getElementById("topicName").value = "";
        document.getElementById("topicDesc").value = "";
        document.getElementById("btnReturn").style.display = "block"; // Show the "Return Home" button
    }
}