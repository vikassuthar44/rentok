package best.vikas.rentok.home.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import best.vikas.rentok.R
import best.vikas.rentok.home.data.UserListResponse
import best.vikas.rentok.home.domain.MainViewModel
import best.vikas.rentok.util.RequestStateRender
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter

@Composable
fun UserListScreenUI(
    mainViewModel: MainViewModel,
    onClick: (String) -> Unit
) {
    Scaffold { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White.copy(alpha = 0.9f))
                .padding(top = paddingValues.calculateTopPadding())
        ) {
            RequestStateRender(
                state = mainViewModel.userList.collectAsState(),
                onSuccess = { userData ->
                    if (userData.isNotEmpty()) {
                        LazyColumn(
                            verticalArrangement = Arrangement.spacedBy(space = 10.dp),
                        ) {
                            items(userData) { item ->
                                SingleUserUI(userData = item) {
                                    onClick(it)
                                }
                            }
                        }
                    } else {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = "No Data Found")
                        }
                    }
                },
                onError = { message ->
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = message)
                    }
                },
                onLoading = {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
            )
        }
    }
}

@Composable
fun SingleUserUI(
    userData: UserListResponse,
    onClick: (String) -> Unit,
) {
    val painter = rememberAsyncImagePainter(model = userData.avatarUrl)
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
            .clip(shape = RoundedCornerShape(size = 10.dp))
            .clickable {
                onClick(userData.login)
            }
            .background(color = Color.LightGray)
            .padding(horizontal = 16.dp, vertical = 10.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(70.dp)
                    .clip(shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painter,
                    contentDescription = null
                )
            }
            Spacer(modifier = Modifier.width(20.dp))
            Column {
                Text(text = userData.login, style = MaterialTheme.typography.headlineMedium.copy(
                    color = Color.DarkGray
                ))
            }
        }
    }
}