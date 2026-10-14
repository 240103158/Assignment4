package com.example.artspaceapp

import androidx.compose.foundation.Image
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspaceapp.ui.theme.ArtSpaceAppTheme
import androidx.compose.runtime.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSpaceAppTheme {
                    ArtApp()
            }
        }
    }
}



@Composable
fun ArtApp(){
    var currentArtwork by remember { mutableIntStateOf(1) }

    val imageRes: Int
    val title: String
    val artist: String
    val year: String

    when (currentArtwork) {
        1 -> {
            imageRes = R.drawable.images
            title = "Mona Lisa"
            artist = "Leonardo da Vinci"
            year = "1503"
        }
        2 -> {
            imageRes = R.drawable.images2
            title = "The Starry Night"
            artist = "Vincent van Gogh"
            year = "1889"
        }
        else -> {
            imageRes = R.drawable.images3
            title = "Crackloud"
            artist = "Anthony Frost"
            year = "2018"
        }
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        ArtworkWall(artworkResId = imageRes)
        ArtworkTitle(title = title, artist = artist, year = year)
        DisplayController(
            onPrevious = {
                currentArtwork = if (currentArtwork == 1) 3 else currentArtwork - 1
            },
            onNext = {
                currentArtwork = if (currentArtwork == 3) 1 else currentArtwork + 1
            }
        )
    }

}



@Composable
fun ArtworkWall(
    @DrawableRes artworkResId: Int,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.padding(16.dp),
        shadowElevation = 8.dp,
        color = Color.White
    ) {
        Image(
            painter = painterResource(id = artworkResId),
            contentDescription = null,
            modifier = Modifier.padding(20.dp)
        )
    }
}

@Composable
fun ArtworkTitle(
    title: String,
    artist: String,
    year: String,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        color = Color(0xFFECEBF4)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = title,
                fontSize = 24.sp,
                fontWeight = FontWeight.Light
            )
            Text(
                text = buildAnnotatedString {
                    withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                        append(artist)
                    }
                    append(" ($year)")
                },
                fontSize = 16.sp
            )
        }
    }
}

@Composable
fun DisplayController(
    onPrevious: () -> Unit,
    onNext: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth().padding(16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Button(onClick = onPrevious) { Text("Previous") }
        Button(onClick = onNext) { Text("Next") }
    }
}

@Preview(showBackground = true)
@Composable
fun ArtAppPreview() {
    ArtSpaceAppTheme {
        ArtApp()
    }
}

