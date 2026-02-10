import com.youssef.bosta_app.domain.model.GameDetails
import com.youssef.bosta_app.domain.usecase.GetGameDetailsUseCase
import com.youssef.bosta_app.ui.gamedetails.GameDetailsViewModel
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
class GameDetailsViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    private lateinit var useCase: GetGameDetailsUseCase
    private lateinit var viewModel: GameDetailsViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)

        useCase = mockk()
        viewModel = GameDetailsViewModel(useCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `loadGameDetails updates state with details`() = runTest {
        val details = GameDetails(1, "Game1", "url1", "2026-01-01", 4.5, "Desc")
        coEvery { useCase(1) } returns details

        viewModel.loadGameDetails(1)
        testDispatcher.scheduler.advanceUntilIdle()

        val state = viewModel.state.value
        assertEquals(details, state.game)
        assertEquals(false, state.isLoading)
    }

    @Test
    fun `loadGameDetails sets error when exception occurs`() = runTest {
        coEvery { useCase(1) } throws RuntimeException("Failed to load")

        viewModel.loadGameDetails(1)
        testDispatcher.scheduler.advanceUntilIdle()

        val state = viewModel.state.value
        assertEquals("Failed to load", state.error)
        assertEquals(false, state.isLoading)
    }
}
