
#####To Test this system first we need is to call the signUp method to have a registration on account


*/userSignUp* [POST]

+ Body Request
```
{
    "username":"sampleUser",
    "password":"samplePassword"
}  
```


+ Response 200 (json)
```
SignUpDTO{username='sampleUser', password='**********'}
```

Then we will need to authenticate against the system to obtain a JSON web token, this token would be requested as
a header in all subsequent requests.

*/userSignIn* [POST]

+ Body Request
```
{
    "username":"sampleUser",
    "password":"samplePassword"
}  
```

+ Body Response
```
Headers:
     token: eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0MjoyMDIwMTE4OCIsImV4cCI6MTYyODQ4OTQ3MX0.qfvExSsC2kncJNt9ivm7KgDELeiL869XPYGExyAke5M
```




This endpoint will diplay result of title of movie request and would also include if the picture won the best picture 
award.

*/findNomineeByTitle/{title}* [GET]

+ Body Response
```
{
    "wonBestPicture": "NO",
    "Title": "Matrix",
    "Year": "1993",
    "Rated": "N/A",
    "Released": "01 Mar 1993",
    "Genre": "Action, Drama, Fantasy",
    "Director": "N/A",
    "Writer": "Grenville Case",
    "Actors": "Nick Mancuso, Phillip Jarrett, Carrie-Anne Moss",
    "Awards": "1 win",
    "imdbRating": "7.9",
    "BoxOffice": null,
    "Production": null,
    "imdbID": "tt0106062"
}
```

This endpoint wil allow you to rate a movie based on the movie unique identifier extracted from open imdb, a user
will be able to rate a movie just once and this rating can't be higher than 10;


  [jwt token is needed as a header]
*/rateMovie* [POST] 
{
    "movieIdentifier":"tt0499549",
    "rating":9.8
}

+ Response
{
    HttpResult 201 Created.
}


This endpoint will display the top ten rated movies (based on our internal rating system) and then it will sort and
display those movies based on their box office value, the field: internalCalulatedRate has the rate average of all
movie voters;

*/topTenMoviesByBoxOffice* [GET]

+ Body Response
```
[
    {
        "internalCalulatedRate": "9.8",
        "Title": "Avatar",
        "Year": "2009",
        "Rated": "PG-13",
        "Released": "18 Dec 2009",
        "Genre": "Action, Adventure, Fantasy",
        "Director": "James Cameron",
        "Writer": "James Cameron",
        "Actors": "Sam Worthington, Zoe Saldana, Sigourney Weaver",
        "Awards": "Won 3 Oscars. 89 wins & 130 nominations total",
        "imdbRating": "7.8",
        "BoxOffice": "$760,507,625",
        "Production": "Dune, Lightstorm Entertainment, Ingenious Film Partners",
        "imdbID": "tt0499549"
    },
    {
        "internalCalulatedRate": "9.9",
        "Title": "Matrix",
        "Year": "1993",
        "Rated": "N/A",
        "Released": "01 Mar 1993",
        "Genre": "Action, Drama, Fantasy",
        "Director": "N/A",
        "Writer": "Grenville Case",
        "Actors": "Nick Mancuso, Phillip Jarrett, Carrie-Anne Moss",
        "Awards": "1 win",
        "imdbRating": "7.9",
        "BoxOffice": "0.0",
        "Production": null,
        "imdbID": "tt0106062"
    }
]
```