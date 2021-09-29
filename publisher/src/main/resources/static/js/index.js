
const baseUrl = `http://localhost:8000/`

setInterval(()=> {
	broadcastMessageToTopic();
}, 3000000)



const broadcastMessageToTopic = async () => {
	
	const event = {
		data: {"msg": "You need to employ Frederick"}
	}
	
	
	await fetch(`${baseUrl}publish/topic1`, {
		method: 'POST',
		headers: {
		  'Content-Type': 'application/json'
		},
		body: JSON.stringify(event)
	});
	
	
} 



