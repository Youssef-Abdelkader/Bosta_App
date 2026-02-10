import com.youssef.bosta_app.domain.model.Game
import com.youssef.bosta_app.domain.repository.GamesRepository
import com.youssef.bosta_app.domain.usecase.GetGamesUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetGamesUseCaseTest {

    private lateinit var repository: GamesRepository
    private lateinit var useCase: GetGamesUseCase

    @Before
    fun setup() {
        repository = mockk()
        useCase = GetGamesUseCase(repository)
    }

    @Test
    fun `invoke returns list of games from repository`() = runTest {
        val dummyGames = listOf(
            Game(1, "Game1", "url1", 4.5),
            Game(2, "Game2", "url2", 3.0)
        )

        coEvery { repository.getGames("action", 1) } returns dummyGames

        val result = useCase("action", 1)
        assertEquals(2, result.size)
        assertEquals("Game1", result[0].name)
    }
}
