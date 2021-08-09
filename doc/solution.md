## MoviesRankService


This solution is a Rest API service, it has to main controllers:
 UserAuthController: This Controller handles all auth process, signUp, signInMethod and generate token for all
 subsequent requests. 
  MovieRankController: This controller has the 3 main use cases of the application: 
  Find a movie by title and find out if the movie won best picture ('/findNomineeByTitle');
  Rate a movie: I chose to develop this functionality internally to my api not using any rating data from external
  service so this Api would be a great idea to compare how movies rates in perception over time against the all time rate
  that you can find on imdb. (/rateMovie)
  Get top ten movies by internal rating and then consuming external client to get detailed information.
  ('/topTenMoviesByBoxOffice')
 
There is also an embedded batch job used to load all the CSV data to a postgresSQL database.
There were many mechanisms to load the data from the file but I chose to go for a batch job as it would allow time 
controlled execution if needed and data transformation operation with job execution listeners, it can come in handy when
data change over a determined period of time.


 







