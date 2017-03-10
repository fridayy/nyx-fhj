package ninja.harmless.nyx.movie

import ninja.harmless.nyx.measure.MeasureTime
import ninja.harmless.nyx.movie.model.Movie
import ninja.harmless.nyx.movie.repository.MovieRepository
import ninja.harmless.nyx.publish.PublishService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
/**
 * @author benjamin.krenn@edu.fh-joanneum.at - 8/5/16.
 */
@RestController
class MovieRestController {

    private MovieProviderService movieProviderService
    private MovieRepository movieRepository
    private PublishService publishService

    @Autowired
    MovieRestController(MovieProviderService movieProviderService, MovieRepository movieRepository, PublishService publishService) {
        this.movieProviderService = movieProviderService
        this.movieRepository = movieRepository
        this.publishService = publishService
    }

    @MeasureTime
    @RequestMapping(value = "/movies", method = RequestMethod.GET)
    public Iterable<Movie> movies() {
        return movieProviderService.provideAll()
    }

    @RequestMapping(value = "/movies/", method = RequestMethod.POST)
    public ResponseEntity<Movie> createMovie(@RequestBody Movie movie) {
        movieRepository.save(movie)
        return new ResponseEntity<Movie>(movie, HttpStatus.CREATED)
    }

    @RequestMapping(value = "/movies/{title}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public @ResponseBody
    Movie getByTitle(@PathVariable String title) {
        Movie movie =  movieProviderService.provideByTitle(title)
        publishService.publish(movie)
        return movie
    }
}
