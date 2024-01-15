package com.user.service;

public class Text {
	/*
resilience4j.ratelimiter.instances.userRateLimiter.limit-refresh-period=4s means you can check your candy supply every 4 seconds. After each 4-second period, you can start with a fresh count of candies.
resilience4j.ratelimiter.instances.userRateLimiter.limit-for-period=2 means that within each 4-second period, you're allowed to eat up to 2 candies. If you eat more than 2 candies in that time, you'll have to wait for the next 4-second period to start.
resilience4j.ratelimiter.instances.userRateLimiter.timeout-duration=2s means that if you try to eat a candy when you've already eaten 2 in the current 4-second period, you'll have to wait for a maximum of 2 seconds to see if any new candies become available. If a new candy becomes available within those 2 seconds, you can eat it; otherwise, you have to wait longer
	 

resilience4j.retry.instances.ratingHotelService.max-attempts=3 This property sets the maximum number of retry attempts that will be made when a retryable operation (such as making an HTTP request or invoking a method) fails. In this example, it's configured to allow a maximum of 3 retry attempts.
resilience4j.retry.instances.ratingHotelService.wait-duration=5s This property specifies the wait duration or delay between each retry attempt. In the event of a failure, the Retry module will wait for the specified duration before making the next retry attempt. In this example, it's set to wait for 5 seconds (5s) between retry attempts.


resilience4j.circuitbreaker.instances.ratingHotelBreaker-This configuration block defines a circuit breaker instance named ratingHotelBreaker. Multiple circuit breaker instances can be configured for different parts of your application.
registerHealthIndicator: true-This property indicates that a health indicator should be registered for this circuit breaker instance. Health indicators can be used to monitor the health of the circuit breaker and include it in health checks.
eventConsumerBufferSize: 10-Sets the size of the event consumer buffer. This buffer holds events related to the circuit breaker's state changes and can be consumed by listeners or monitoring components.
failureRateThreshold: 50-Defines the failure rate threshold as a percentage. When the failure rate (percentage of failed calls) exceeds this threshold, the circuit breaker may open to prevent further calls to a potentially failing service.
minimumNumberOfCalls: 5-Specifies the minimum number of calls required before the circuit breaker can open. This is a safeguard to ensure that the circuit breaker has enough data to make an informed decision.
automaticTransitionFromOpenToHalfOpenEnabled: true-Enables automatic transition from the "open" state to the "half-open" state after a specified duration. In the "half-open" state, a limited number of test calls are allowed to determine if the service has recovered.
waitDurationInOpenState: 6s-Sets the duration for which the circuit breaker remains in the "open" state before transitioning to the "half-open" state. In this example, it's set to 6 seconds.
permittedNumberOfCallsInHalfOpenState: 3-Specifies the maximum number of calls allowed in the "half-open" state. These calls are used to test the service's recovery.
slidingWindowSize: 10-Defines the size of the sliding window that collects data on call outcomes. The window is used to calculate failure rates and other statistics.
slidingWindowType: COUNT_BASED-Sets the type of sliding window to count-based. Other options, such as TIME_BASED, are also available and determine how the sliding window tracks calls.
	 
	 
	 */

}
