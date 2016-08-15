package ninja.harmless.nyx.apigateway.controller;

import ninja.harmless.nyx.apigateway.catalogue.Movie;
import ninja.harmless.nyx.apigateway.catalogue.MovieStatistic;
import ninja.harmless.nyx.apigateway.catalogue.MovieTrailer;
import ninja.harmless.nyx.apigateway.models.MovieDetails;
import ninja.harmless.nyx.apigateway.service.CatalogueService;
import ninja.harmless.nyx.apigateway.service.StatsService;
import ninja.harmless.nyx.apigateway.service.TrailerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * @author benjamin.krenn@edu.fh-joanneum.at - 8/8/16.
 */
@RestController
public class ApiController {

    @Autowired
    CatalogueService catalogueService;

    @Autowired
    StatsService statsService;

    @Autowired
    TrailerService trailerService;


    @RequestMapping("${nyx.api.version}/movies/{title}")
    public DeferredResult<MovieDetails> movieDetails(@PathVariable String title) {
        MovieDetails details = new MovieDetails();
        Movie m = catalogueService.getMovie(title);
        MovieStatistic s = statsService.getMovieStatistic(m.getId());
        MovieTrailer t = trailerService.getTrailer();
        details.setMovie(m);
        details.setStatistic(s);
        details.setTrailer(t);
        DeferredResult<MovieDetails> result = new DeferredResult<>();
        result.setResult(details);
        return result;
    }
}
