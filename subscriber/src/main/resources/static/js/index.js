
const baseUrl = `http://localhost:9000/`
const eventSource = new EventSource(`${baseUrl}subscribe/topic1`)
const messageContainer = document.querySelector("#output-original")

	
eventSource.onopen = function(e) {
	console.log('OPENED: => ', e)
} 


eventSource.addEventListener("topic1", function(e) {
  subscribeToTopic(e.data);
})


eventSource.onerror = function(e) {
	console.log('ERROR: => ', e)
} 

eventSource.close = function(e) {
	console.log('CLOSED: => ', e)
} 


const subscribeToTopic = message => {
   messageContainer.insertAdjacentHTML('beforeend',message)
    $("#output-processed").text(message)
}

