        package com.youssef.bosta_app.ui.gamedetails

        import androidx.compose.foundation.Image
        import androidx.compose.foundation.layout.*
        import androidx.compose.foundation.rememberScrollState
        import androidx.compose.foundation.verticalScroll
        import androidx.compose.material.icons.Icons
        import androidx.compose.material.icons.filled.ArrowBack
        import androidx.compose.material3.*
        import androidx.compose.runtime.*
        import androidx.compose.ui.Alignment
        import androidx.compose.ui.Modifier
        import androidx.compose.ui.unit.dp
        import coil.compose.rememberAsyncImagePainter
        import androidx.hilt.navigation.compose.hiltViewModel
        @OptIn(ExperimentalMaterial3Api::class)
        @Composable
        fun GameDetailsScreen(
            viewModel: GameDetailsViewModel = hiltViewModel(),
            gameId: Int,
            onBack: () -> Unit
        ) {
            val state by viewModel.state.collectAsState()

            LaunchedEffect(gameId) {
                viewModel.loadGameDetails(gameId)
            }

            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text(state.game?.name ?: "Game Details") },
                        navigationIcon = {
                            IconButton(onClick = { onBack() }) {
                                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                            }
                        }
                    )
                }
            ) { paddingValues ->
                Box(modifier = Modifier.padding(paddingValues).fillMaxSize()) {

                    state.game?.let { game ->
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp)
                                .verticalScroll(rememberScrollState()),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Card(
                                shape = MaterialTheme.shapes.medium,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(220.dp)
                                    .padding(bottom = 8.dp),
                                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
                            ) {
                                Image(
                                    painter = rememberAsyncImagePainter(game.imageUrl),
                                    contentDescription = game.name,
                                    modifier = Modifier.fillMaxSize()
                                )
                            }

                            Text("Name: ${game.name}", style = MaterialTheme.typography.titleLarge)
                            Text("Released: ${game.releasedDate}", style = MaterialTheme.typography.bodyMedium)
                            Text("Rating: ${game.rating}", style = MaterialTheme.typography.bodyMedium)
                            Spacer(modifier = Modifier.height(8.dp))
                            Text("Description: ${game.description}", style = MaterialTheme.typography.bodyMedium)
                        }
                    }

                    if (state.isLoading) {
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                    }

                    state.error?.let { error ->
                        Column(
                            modifier = Modifier.align(Alignment.Center),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text("Error: $error", style = MaterialTheme.typography.bodyMedium)
                            Spacer(modifier = Modifier.height(8.dp))
                            Button(onClick = { viewModel.retry(gameId) }) {
                                Text("Retry")
                            }
                        }
                    }
                }
            }
        }
