# Notification_System
Notification system (Publisher subscriber example) 

This project is made up of two applications, a publisher and a subscriber.
The publisher is a server that keeps track of topics -> subscribers where a topic is a
string and a subscriber is an HTTP endpoint. When a message is published on a topic, it is forwarded to all subscriber endpoints. The publisher runs on port 8000
A user interface has been created. On load it initiates a javascript event publishing to publish events to a topic. (http://localhost:8000/)

1. **To Create a Subscription**

`POST /subscribe/{topic}`

_Request_: 

Example
```json
{
	"url": "http://localhost:9000/test1",     		
 }
``` 
_Response_:
```json
{
   
    "url": "http://localhost:9000/test1",
    "topic": "String"
}
``` 

2. **Publish message to topic**

`POST /publish/{topic}`

_Request_: 

Example
```json
{
	[key: String]: any,     		
 }
``` 
_Response_:
HTTP response code 

Automated sending of messages to a topic can be iniitiated using jacascript by visting http:localhost:8000/ in your browser

2. **Payload sent to subscribers**


_Request_: 

Example
```json
{
	topic: String],  
  data: object
 }
``` 


