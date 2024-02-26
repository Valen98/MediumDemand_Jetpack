package com.example.mediumdemand_jetpack

import android.annotation.SuppressLint
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
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mediumdemand_jetpack.ui.theme.MediumDemand_JetpackTheme

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
                    Column(modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background)) {
                        TopBar()

                        RowScroll()

                        ColumnScroll()
                    }
                }
            }
        }
    }
}

@SuppressLint("PrivateResource")
@Composable
fun TopBar() {
    var menuExpanded by remember {
        mutableStateOf(false)
    }

    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(start = 8.dp, top = 16.dp, end = 8.dp)
        .background(MaterialTheme.colorScheme.background),
        horizontalArrangement = Arrangement.SpaceBetween) {
        Text(
            text = "Medium Demand",
            fontSize = 32.sp,
        )
        Column {
            IconButton(
                onClick = {menuExpanded = !menuExpanded}) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Menu",
                    modifier = Modifier.size(48.dp)
                )
            }

            //Dropdown menu under hamburger menu
            DropdownMenu(
                expanded = menuExpanded,
                onDismissRequest = { menuExpanded = false }) {
                DropdownMenuItem(text = { Text("Page 1") }, onClick = { /*TODO*/ })
                DropdownMenuItem(text = {Text("Page 2")}, onClick = { /*TODO*/ })
                DropdownMenuItem(text = { Text("Page 3") }, onClick = { /*TODO*/ })
            }
        }
    }
}


@Composable
fun RowScroll() {
    LazyRow(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween) {
        items(20) {content ->
            ContentRow(content)
        }
    }
}

@Composable
fun ContentRow(id: Int) {
    val image: Painter = if(id % 2 == 0) {
        painterResource(R.drawable.photo1)
    }
    else {
        painterResource(id = R.drawable.photo2)
    }

    Column(modifier = Modifier
        .width(100.dp)
        .height(100.dp)
        .padding(end = 16.dp)
        .background(MaterialTheme.colorScheme.onSecondary),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        Row(modifier = Modifier.padding(bottom = 8.dp)) {
            Image(
                painter = image,
                contentDescription = "Row Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)
            )
        }
        Row() {
            Text(text = "Post Text")
        }

    }
}

@Composable
fun ColumnScroll() {
    LazyColumn() {
        items(20) {item ->
            ContentColumn(id = item)
        }
    }
}

@Composable
fun ContentColumn(id: Int) {
    val string: String
    val image: Painter

    if(id % 2 == 0 ) {
        image = painterResource(R.drawable.photo1)
        string = "This is my bedroom"
    }else {
        image = painterResource(id = R.drawable.photo2)
        string = "This is my Garden"
    }

    Column(modifier = Modifier.padding(8.dp)) {
        Image(painter = image, contentDescription = "Colummn Image")
        Row(modifier = Modifier.padding(top = 4.dp)) {
            Icon(imageVector = Icons.Default.Favorite, contentDescription = "Like status" )
            Icon(imageVector = Icons.Default.Share, contentDescription = "Comment", modifier = Modifier.padding(start = 8.dp))
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
fun TopBarReview() {
    TopBar()
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
        TopBar()
        RowScroll()
        ColumnScroll()
    }
}