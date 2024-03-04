package best.vikas.rentok.home.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import best.vikas.rentok.home.domain.MainViewModel
import best.vikas.rentok.util.RequestStateRender
import coil.compose.rememberAsyncImagePainter

@Composable
fun UserDetailsScreen(
    userName: String,
    mainViewModel: MainViewModel,
) {
    LaunchedEffect(key1 = Unit) {
        mainViewModel.userDetails(userName = userName)
    }

    Scaffold { paddingValues ->
        RequestStateRender(
            state = mainViewModel.userDetail.collectAsState(),
            onSuccess = { userDetail ->
                val painter = rememberAsyncImagePainter(model = userDetail.avatarUrl)
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = paddingValues.calculateTopPadding()),
                    contentAlignment = Alignment.TopCenter
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(modifier = Modifier.height(40.dp))
                        Box(
                            modifier = Modifier.size(250.dp),
                            contentAlignment = Alignment.Center,
                        ) {
                            Image(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .clip(shape = CircleShape),
                                painter = painter,
                                contentDescription = null,
                                contentScale = ContentScale.FillBounds
                            )
                        }
                        Spacer(modifier = Modifier.height(20.dp))
                        userDetail.name?.let {
                            Text(
                                text = it,
                                style = MaterialTheme.typography.headlineMedium
                            )
                        }
                        userDetail.login?.let {
                            Row(horizontalArrangement = Arrangement.Center) {
                                Text(
                                    text = "User Name: ",
                                    style = MaterialTheme.typography.titleMedium
                                )
                                Text(text = it)
                            }
                        }
                        userDetail.company?.let {
                            Row(horizontalArrangement = Arrangement.Center) {
                                Text(
                                    text = "Company: ",
                                    style = MaterialTheme.typography.titleMedium
                                )
                                Text(text = it)
                            }
                        }
                        userDetail.bio?.let {
                            Row(horizontalArrangement = Arrangement.Center) {
                                Text(text = "Bio: ", style = MaterialTheme.typography.titleMedium)
                                Text(text = it)
                            }
                        }
                        userDetail.email?.let {
                            Row(horizontalArrangement = Arrangement.Center) {
                                Text(text = "Email: ", style = MaterialTheme.typography.titleMedium)
                                Text(text = it)
                            }
                        }
                        userDetail.location?.let {
                            Row(horizontalArrangement = Arrangement.Center) {
                                Text(
                                    text = "Location: ",
                                    style = MaterialTheme.typography.titleMedium
                                )
                                Text(text = it)
                            }
                        }
                    }

                }
            },
            onLoading = {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            },
            onError = { message ->
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = message)
                }
            }
        )
    }
}