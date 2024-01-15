package com.eureka.service;

/*
public class h {}
eureka.instance.hostname=localhost:
This property specifies the hostname that the Eureka client instance should use when registering itself with the Eureka server. In this case, it is set to "localhost," meaning that the Eureka client will register itself with the Eureka server using the hostname "localhost."

eureka.client.register-with-eureka=false:
When this property is set to false, it instructs the Eureka client not to register itself with the Eureka server. In other words, the application running this configuration will not announce its availability to the Eureka server. This is typically used when you have a service that doesn't need to be discovered by other services, such as a standalone application.

eureka.client.fetch-registry=false:
When this property is set to false, it instructs the Eureka client not to fetch the service registry information from the Eureka server. In other words, the application will not attempt to retrieve a list of available services from the Eureka server. This is used when you want to disable service discovery and don't need to find other services via Eureka.
In summary:

The first property sets the hostname for registration with Eureka, and it's set to "localhost."
The second property disables the registration of the application with Eureka.
The third property disables the fetching of the service registry from Eureka.
These configurations are useful in scenarios where you have services that are not participating in service discovery or where you are running services in a development or testing environment without a functioning Eureka server.






eureka.instance.hostname=localhost:
Imagine you have a name tag, and you write "localhost" on it. This name tag represents your computer's name. When you go to a meeting (Eureka), people there will know your computer's name as "localhost."
eureka.client.register-with-eureka=false:
Normally, when you go to a meeting, you sign in and tell others that you're there. But in this case, you decide not to sign in. So, even though you're at the meeting (Eureka), you don't tell others that you're there.
eureka.client.fetch-registry=false:
At the meeting (Eureka), there's a list of all the people and their names. Normally, you would check that list to see who's there. But in this case, you decide not to check the list. You don't care who else is at the meeting; you're just doing your own thing.
In simple terms, these configurations allow you to say, "I'm here, but I won't tell anyone, and I won't bother to see who else is here." This can be useful when you're not interested in participating in a system that keeps track of available services or when you're testing something on your own computer.










*/
