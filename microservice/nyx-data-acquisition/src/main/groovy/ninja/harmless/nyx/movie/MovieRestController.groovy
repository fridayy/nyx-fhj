package ninja.harmless.nyx.movie

import ninja.harmless.nyx.movie.dto.Movie
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

/**
 * @author benjamin.krenn@edu.fh-joanneum.at - 8/5/16.
 */
@RestController
class MovieRestController {

    MovieProviderService movieProviderService

    @Autowired
    MovieRestController(MovieProviderService movieProviderService) {
        this.movieProviderService = movieProviderService
    }

    @RequestMapping(value = "/title", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public @ResponseBody Movie getByTitle(@RequestParam(value = "title") String title) {
        return movieProviderService.provideByTitle(title);
    }
}
