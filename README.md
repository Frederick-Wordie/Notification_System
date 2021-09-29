# Notification_System
Notification system (Publisher subscriber example) 


This project is made up of two applications, a publisher and a subscriber.
The publisher is a server that keeps track of topics -> subscribers where a topic is a
string and a subscriber is an HTTP endpoint. When a message is published on a topic, it is forwarded to all subscriber endpoints. The publisher runs on port 8000
A user interface has been created. On load it initiates a javascript event publishing to publish events to a topic. (http://localhost:8000/)

**Clone and Run project as spring boot project**

**Publisher server end points**

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

Automated sending of messages to a topic can be iniitiated using javascript by visting http:localhost:8000/ in your browser

2. **Payload sent to subscribers**


_Request_: 

Example
```json
{
  "topic": "String",  
  "data": "object"
 }
``` 

**Subscribing server end points**

The applicaton runs on port 9000. It has an end point to receive the sent data and also a GET end point to monitor the events being sent. This is done using Server sent event. The message is streamed unto the web page using event source in javascript

2. **End points to receive published events**

`POST /test1` and `POST /test2`

_Received data structure_: 

Example
```json
{
	"topic": "String"
	"data": "object",     		
 }
``` 
_Response_:
HTTP response code 

**Visit http:localhost:9000/ in your browser to see the events being sent**

