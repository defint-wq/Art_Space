package com.example.artspace

import android.media.Image
import android.os.Bundle
import android.service.carrier.MessagePdu
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.artspace.ui.theme.ArtSpaceTheme
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.core.text.buildSpannedString

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSpaceTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ArtSpaceApp(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun ArtSpaceApp(modifier: Modifier) {
    ArtSpace()
}

@Composable
fun ArtSpace() {
    var curStep by remember { mutableIntStateOf(1) }

    val imgSrc = when(curStep) {
        1 -> R.drawable.the_kiss
        2 -> R.drawable.mona_lisa
        3 -> R.drawable.the_scream
        4 -> R.drawable.starry_night
        else -> R.drawable.girl_with_a_pearl_earring
    }

    val nameSrc = when(curStep) {
        1 -> R.string.the_kiss
        2 -> R.string.mona_lisa
        3 -> R.string.the_scream
        4 -> R.string.starry_night
        else -> R.string.girl_with_a_pearl_earring
    }

    val yearSrc = when(curStep) {
        1 -> R.string.the_kiss_year
        2 -> R.string.mona_lisa_year
        3 -> R.string.the_scream_year
        4 -> R.string.starry_night_year
        else -> R.string.girl_with_a_pearl_earring_year
    }

    val authorSrc = when(curStep) {
        1 -> R.string.the_kiss_author
        2 -> R.string.mona_lisa_author
        3 -> R.string.the_scream_author
        4 -> R.string.starry_night_author
        else -> R.string.girl_with_a_pearl_earring_author
    }

    Column(
        modifier = Modifier.padding(30.dp).fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Column (
            modifier = Modifier.shadow(elevation = 4.dp)
        ) {
            Image(
                painter = painterResource(imgSrc),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(30.dp)
                    .fillMaxWidth()
                    .height(400.dp)
            )
        }

        Spacer(modifier = Modifier.size(70.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Column(
                modifier = Modifier
                    .background(Color(220, 220, 252))
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                Text(
                    text = stringResource(nameSrc),
                    fontSize = 28.sp,
                    modifier = Modifier.padding(bottom = 5.dp)
                    )
                Row() {
                    Text(
                        text = stringResource(authorSrc),
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.size(5.dp))
                    Text(text = "(${stringResource(yearSrc)})")
                }
            }
        }


        Row() {
            Button(
                onClick ={
                    when(curStep) {
                        1 -> curStep = 5
                        2 -> curStep = 1
                        3 -> curStep = 2
                        4 -> curStep = 3
                        5 -> curStep = 4
                    }
                },
                shape = RoundedCornerShape(24.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0,0,128)
                ),
                modifier = Modifier.size(width = 180.dp, height = 50.dp)
            ) {
                Text(text = "Previous")
            }

            Spacer(modifier = Modifier.size(20.dp))

            Button(
                onClick ={
                    when(curStep) {
                        1 -> curStep = 2
                        2 -> curStep = 3
                        3 -> curStep = 4
                        4 -> curStep = 5
                        5 -> curStep = 1
                    }
                },
                shape = RoundedCornerShape(24.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0, 0, 128)
                ),
                modifier = Modifier.size(width = 200.dp, height = 50.dp)
            ) {
                Text(text = "Next")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ArtSpaceTheme {
        ArtSpaceApp(modifier = Modifier)
    }
}