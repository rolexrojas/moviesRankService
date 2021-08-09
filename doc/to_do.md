## MoviesRankService

These are some features that needs to be implemented before going liveÑ

Token Handling/Encryption: No real security unless the token is encrypted so information can be easily 
read by a malicious party. Also, techniques related to token expiration and token restriction based on 
user role.

ExceptionError Management: There are just to much exceptions just bouncing back to the client, this can
result in several unwanted behaviours, at least the system should have a root general exception to map
everything that goes wrong to a particular en expected message.
 
Logging Mechanism: Yeah, Logging may be a simple feature to implement, but smart logging with all the related
fields and with a format that simplifies log reading for third party apps (like Splunk) is a work to do.

Test (Unit - Integration): A good pack of unit testing making sure components work as expected on their own
and several integration test to make sure components interact as expected even when new code is added.

There are several input and output validations that are missing, this step is also another thing to watch before
going live.




 