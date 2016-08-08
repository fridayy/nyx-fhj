package ninja.harmless.nyx.apigateway.controller;

import ninja.harmless.nyx.apigateway.catalogue.Movie;
import ninja.harmless.nyx.apigateway.models.MovieDetails;
import ninja.harmless.nyx.apigateway.service.CatalogueService;
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


    @RequestMapping("${nyx.api.version}/movies/{title}")
    public DeferredResult<MovieDetails> movieDetails(@PathVariable String title) {
        MovieDetails details = new MovieDetails();
        Movie m = catalogueService.getMovie(title);
        details.getMovies().add(m);
        DeferredResult<MovieDetails> result = new DeferredResult<>();
        result.setResult(details);
        return result;
    }
}
