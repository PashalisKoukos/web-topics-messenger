// author : Paschalis Koukos
// refers to createNewTopic.jsp
// this function is called when the user clicks the "Save Topic" button in the createNewTopic.jsp page
// it sends a POST request to the CreateNewTopicServlet with the topic name and description entered by the user
// it then processes the response from the server and updates the page accordingly
function saveTopic() {
    const data = { // Collect the topic name and description from the input fields
        name: document.getElementById('topicName').value,
        description: document.getElementById('topicDesc').value
    };

    // Send a POST request to the CreateNewTopicServlet
    fetch('CreateNewTopicServlet', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(data)
    })
    // Convert the response to JSON
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        return response.json();
    })
    // Handle the parsed JSON result
    .then(result => {
        // Display the response message from the server
        document.getElementById('responseMessage').innerText = result.message;

        // If the topic was created successfully, clear the input fields and show the "Return Home" button
        if (result.message && result.message.includes("successfully")) {
            document.getElementById("topicName").value = "";
            document.getElementById("topicDesc").value = "";
            
            const returnBtn = document.getElementById("btnReturn");
            if (returnBtn) {
                returnBtn.style.display = "block";
            }
        }
    })
    // Catch and log any errors that occur during the fetch process
    .catch(error => {
        console.error('Error:', error);
        document.getElementById('responseMessage').innerText = "An error occurred while saving the topic.";
    });
}