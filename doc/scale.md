###MoviesRankService

This is a very light weight solution with a very small footprint and can handle pretty good vertical powering server
host to take more request but also there is no restrictions to creating several instances, of course some load balance
technology would be needed.

This api service has no mechanism related to sessions and spring sessions so this features would be needed before load
balance deployment. Changes related to database and persistence layer will be needed to have distributed storage
while maintaining transaction atomicity.