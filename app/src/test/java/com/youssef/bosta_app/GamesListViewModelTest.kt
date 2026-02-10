import com.youssef.bosta_app.domain.model.Game
import com.youssef.bosta_app.domain.usecase.GetGamesUseCase
import com.youssef.bosta_app.ui.gameslist.GamesListViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GamesListViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    private lateinit var getGamesUseCase: GetGamesUseCase
    private lateinit var viewModel: GamesListViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)

        getGamesUseCase = mockk()
        viewModel = GamesListViewModel(getGamesUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `loadGames updates state with games`() = runTest {
        val dummyGames = listOf(
            Game(1, "Game1", "url1", 4.5),
            Game(2, "Game2", "url2", 3.0)
        )

        coEvery { getGamesUseCase("action", 1) } returns dummyGames

        viewModel.loadGames()

        testDispatcher.scheduler.advanceUntilIdle()

        val state = viewModel.state.value
        assertEquals(2, state.games.size)
        assertEquals("Game1", state.games[0].name)
        assertEquals(false, state.isLoading)
    }

    @Test
    fun `loadGames sets error when exception occurs`() = runTest {
        coEvery { getGamesUseCase("action", 1) } throws RuntimeException("Network failed")

        viewModel.loadGames()
        testDispatcher.scheduler.advanceUntilIdle()

        val state = viewModel.state.value
        assertEquals("Network failed", state.error)
        assertEquals(false, state.isLoading)
    }
}
