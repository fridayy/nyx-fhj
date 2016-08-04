package ninja.harmless.nyx.movie.service

import ninja.harmless.nyx.movie.MovieRemoteAcquisitionService
import ninja.harmless.nyx.movie.dto.Movie
import ninja.harmless.nyx.movie.repository.MovieRepository
import ninja.harmless.nyx.testcategories.annotation.UnitTest
import org.junit.experimental.categories.Category
import spock.lang.Specification
import spock.lang.Unroll

import java.util.concurrent.CompletableFuture

/**
 * @author benjamin.krenn@edu.fh-joanneum.at - 8/4/16.
 */
@UnitTest
class MovieProviderServiceTest extends Specification {

    MovieRemoteAcquisitionService mockedRemoteAcquisition
    MovieRepository               mockedRepository
    MovieProviderServiceImpl      classUnderTest
    Movie                         mockedMovie

    void setup() {
        mockedRemoteAcquisition = Mock(MovieRemoteAcquisitionService)
        mockedRepository        = Mock(MovieRepository)
        mockedMovie             = new Movie()
        classUnderTest          = new MovieProviderServiceImpl(mockedRepository, mockedRemoteAcquisition)
    }


    def "movie is not in database"() {
        given:
            mockedRepository.findByTitleIgnoreCase(_) >> null
            mockedRemoteAcquisition.acquireByTitle(_) >> mockedMovie
        when:
            def result = classUnderTest.provideByTitle("test")
        then:
            1 * mockedRemoteAcquisition.acquireByTitle(_)
    }


    def "movie exists already in database"() {
        given:
            mockedRepository.findByTitleIgnoreCase(_) >> CompletableFuture.completedFuture(mockedMovie)
            mockedRemoteAcquisition.acquireByTitle(_) >> null
        when:
            def result = classUnderTest.provideByTitle("test")
        then:
            0 * mockedRemoteAcquisition.acquireByTitle(_)
            result == mockedMovie
    }
}
