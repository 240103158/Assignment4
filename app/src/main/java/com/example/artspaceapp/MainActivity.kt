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
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {
        ArtworkWall(artworkResId = R.drawable.images)

        Spacer(modifier = Modifier.height(32.dp))
        Surface(color = Color(0xFFECEBF4)) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "Mona Lisa", fontSize = 24.sp, fontWeight = FontWeight.Light)
                Text(text = buildAnnotatedString {
                    withStyle(SpanStyle(fontWeight = FontWeight.Bold)) { append("Da Vinci") }
                    append(" (2021)")
                })

            }
        }
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = {} ) { Text("Previous") }
            Button(onClick = {} ) { Text("Next") }
        }
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

@Preview(showBackground = true)
@Composable
fun ArtAppPreview() {
    ArtSpaceAppTheme {
        ArtApp()
    }
}

