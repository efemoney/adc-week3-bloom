package com.example.androiddevchallenge.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.ButtonDefaults.buttonColors
import androidx.compose.material.ButtonDefaults.textButtonColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.ui.theme.pink900
import com.example.androiddevchallenge.ui.theme.white

private data class WelcomePainters(
  val bg: Painter,
  val logo: Painter,
  val illos: Painter,
)

@Composable
private fun welcomePainters(): WelcomePainters {
  val isLight = MaterialTheme.colors.isLight

  return WelcomePainters(
    bg = painterResource(id = if (isLight) R.drawable.light_welcome_bg else R.drawable.dark_welcome_bg),
    logo = painterResource(id = if (isLight) R.drawable.light_logo else R.drawable.dark_logo),
    illos = painterResource(id = if (isLight) R.drawable.light_welcome_illos else R.drawable.dark_welcome_illos),
  )
}

@Composable
fun WelcomeScreen(
  onClickCreateAccount: () -> Unit = {},
  onClickLogin: () -> Unit = {}
) {

  val painters = welcomePainters()

  Surface(color = MaterialTheme.colors.primary) {

    Image(painter = painters.bg, contentDescription = "welcome background")

    Column(
      modifier = Modifier
        .fillMaxSize(),
      horizontalAlignment = Alignment.CenterHorizontally,
    ) {

      Spacer(modifier = Modifier.height(72.dp))

      Image(
        painter = painters.illos,
        contentDescription = "welcome illos",
        modifier = Modifier
          .align(Alignment.Start)
          .wrapContentSize(unbounded = true)
          .offset(x = 88.dp)
      )

      Spacer(modifier = Modifier.height(48.dp))

      Image(
        painter = painters.logo,
        contentDescription = "Bloom Logo",
      )

      Text(
        text = "Beautiful home garden solutions",
        style = MaterialTheme.typography.subtitle1,
        modifier = Modifier.paddingFromBaseline(top = 32.dp, bottom = 40.dp),
      )

      Button(
        onClick = onClickCreateAccount,
        shape = MaterialTheme.shapes.medium,
        colors = buttonColors(backgroundColor = MaterialTheme.colors.secondary),
        modifier = Modifier
          .fillMaxWidth()
          .padding(start = 16.dp, end = 16.dp)
          .height(48.dp),
      ) {
        Text("Create account")
      }

      Spacer(modifier = Modifier.height(8.dp))

      TextButton(
        onClick = onClickLogin,
        shape = MaterialTheme.shapes.medium,
        colors = textButtonColors(contentColor = if (MaterialTheme.colors.isLight) pink900 else white),
        modifier = Modifier
          .fillMaxWidth()
          .padding(start = 16.dp, end = 16.dp)
          .height(48.dp),
      ) {
        Text("Log in")
      }
    }
  }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
private fun LightPreview() {
  MyTheme { WelcomeScreen() }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
private fun DarkPreview() {
  MyTheme(darkTheme = true) { WelcomeScreen() }
}
