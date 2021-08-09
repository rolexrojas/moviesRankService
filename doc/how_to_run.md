## moviesRankService

This application will be delivered in a zip file named moviesrankservice-{version}.zip

Simply extract the image from zip file, resulting in a jar file.

Database setup is needed before using this app.

### Docker deployment

To build the image with Docker just execute:

```
docker build --build-arg JAR_FILE=build/libs/*.jar -t movierankservice . 
```

Then to run the container exposing port 8582
```
docker run -p 8582:8582 movierankservice
```




