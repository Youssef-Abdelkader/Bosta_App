package com.youssef.bosta_app
import com.youssef.bosta_app.domain.model.GameDetails
import com.youssef.bosta_app.domain.repository.GamesRepository
import com.youssef.bosta_app.domain.usecase.GetGameDetailsUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetGameDetailsUseCaseTest {

    private lateinit var repository: GamesRepository
    private lateinit var useCase: GetGameDetailsUseCase

    @Before
    fun setup() {
        repository = mockk()
        useCase = GetGameDetailsUseCase(repository)
    }

    @Test
    fun `invoke returns game details from repository`() = runTest {
        val dummyDetails = GameDetails(
            id = 1,
            name = "Game1",
            imageUrl = "url1",
            releasedDate = "2026-01-01",
            rating = 4.5,
            description = "Description"
        )

        coEvery { repository.getGameDetails(1) } returns dummyDetails

        val result = useCase(1)
        assertEquals(dummyDetails, result)
        assertEquals("Game1", result.name)
        assertEquals(4.5, result.rating, 0.0)
    }

    @Test(expected = RuntimeException::class)
    fun `invoke throws exception when repository fails`() = runTest {
        coEvery { repository.getGameDetails(1) } throws RuntimeException("Network error")

        useCase(1)
    }
}
