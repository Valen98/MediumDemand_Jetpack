package com.example.mediumdemand_jetpack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mediumdemand_jetpack.ui.theme.MediumDemand_JetpackTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MediumDemand_JetpackTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colorScheme.background)
                    ) {

                        MainApp()

                    }
                }
            }
        }
    }
}

@Composable
fun MainApp() {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val open: () -> Unit = { scope.launch { drawerState.open() } }
    val close: () -> Unit = { scope.launch { drawerState.close() } }
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                ModalDrawerSheet(modifier = Modifier.width(200.dp)) {
                    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
                        IconButton(
                            onClick = {
                                scope.launch {
                                    drawerState.apply {
                                        close()
                                    }
                                }
                            },
                        ) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Close Menu",
                                modifier = Modifier.size(32.dp)
                            )
                        }
                        NavigationDrawerItem(
                            label = { Text("Page 1") },
                            selected = false,
                            onClick = { /*TODO*/ })
                        NavigationDrawerItem(
                            label = { Text("Page 2") },
                            selected = false,
                            onClick = { /*TODO*/ })
                        NavigationDrawerItem(
                            label = { Text("Page 3") },
                            selected = false,
                            onClick = { /*TODO*/ })
                    }
                }
            }, gesturesEnabled = true,
            content = {
                CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {

                    Column(modifier = Modifier.fillMaxSize()) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(MaterialTheme.colorScheme.background),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Medium Demand",
                                fontSize = 32.sp,
                                modifier = Modifier
                                    .padding(start = 8.dp, top = 16.dp, end = 8.dp)
                            )
                            IconButton(
                                onClick = {
                                    scope.launch {
                                        drawerState.apply {
                                            if (isClosed) open() else close()
                                        }
                                    }
                                }, modifier = Modifier.padding(top = 16.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Menu,
                                    contentDescription = "Menu",
                                    modifier = Modifier.size(48.dp)
                                )
                            }
                        }
                        RowScroll()
                        ColumnScroll()
                    }
                }
            }
        )
    }
}


@Composable
fun RowScroll() {
    LazyRow(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        items(20) { content ->
            ContentRow(content)
        }
    }
}

@Composable
fun ContentRow(id: Int) {
    val image: Painter = if (id % 2 == 0) {
        painterResource(R.drawable.photo1)
    } else {
        painterResource(id = R.drawable.photo2)
    }

    Column(
        modifier = Modifier
            .width(100.dp)
            .height(100.dp)
            .padding(end = 16.dp)
            .background(MaterialTheme.colorScheme.onSecondary),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(modifier = Modifier.padding(bottom = 8.dp, top = 8.dp)) {
            Image(
                painter = image,
                contentDescription = "Row Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(64.dp)
            )
        }
        Row {
            Text(text = "Post Text")
        }

    }
}

@Composable
fun ColumnScroll() {
    LazyColumn() {
        items(20) { item ->
            ContentColumn(id = item)
        }
    }
}

@Composable
fun ContentColumn(id: Int) {
    val string: String
    val image: Painter

    if (id % 2 == 0) {
        image = painterResource(R.drawable.photo1)
        string = "This is my bedroom"
    } else {
        image = painterResource(id = R.drawable.photo2)
        string = "This is my Garden"
    }

    Column(modifier = Modifier.padding(8.dp)) {
        Image(painter = image, contentDescription = "Colummn Image")
        Row(modifier = Modifier.padding(top = 4.dp)) {
            Icon(imageVector = Icons.Default.Favorite, contentDescription = "Like status")
            Icon(
                imageVector = Icons.Default.Share,
                contentDescription = "Comment",
                modifier = Modifier.padding(start = 8.dp)
            )
        }
        Column {
            Text(text = string)
            Text(
                text = "Published 27/1-24 10:04",
                fontSize = 8.sp
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun ContentColumnPreview() {
    ColumnScroll()
}

@Preview(showBackground = true)
@Composable
fun ContentRowPreview() {
    ContentRow(1)
}


@Preview(showBackground = true)
@Composable
fun RowScrollPreview() {
    RowScroll()
}

@Preview(showBackground = true)
@Composable
fun FullAppReview() {
    Column {
        RowScroll()
        ColumnScroll()
    }
}