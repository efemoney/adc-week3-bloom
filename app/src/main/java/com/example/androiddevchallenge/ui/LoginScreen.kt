package com.example.androiddevchallenge.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.MyTheme

@Composable
fun LoginScreen(onClickLogin: ((email: String, password: String) -> Unit)? = null) {
  val (email, setEmail) = remember { mutableStateOf("") }
  val (password, setPassword) = remember { mutableStateOf("") }

  Surface(color = MaterialTheme.colors.background) {

    Column(
      modifier = Modifier.fillMaxSize(),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {

      Text(
        text = "Log in with email",
        style = MaterialTheme.typography.h1,
        modifier = Modifier.paddingFromBaseline(top = 184.dp, bottom = 16.dp),
      )

      BloomOutlinedTextField(
        value = email,
        onValueChange = setEmail,
        placeholderText = "Email address",
      )

      Spacer(modifier = Modifier.height(8.dp))

      BloomOutlinedTextField(
        value = password,
        onValueChange = setPassword,
        placeholderText = "Password (8+ characters)",
        visualTransformation = PasswordVisualTransformation(),
      )

      Text(
        text = buildAnnotatedString {
          append("By clicking below you agree to our ")
          withStyle(SpanStyle(textDecoration = TextDecoration.Underline)) {
            append("Terms of use")
          }
          append(" and consent to our ")
          withStyle(SpanStyle(textDecoration = TextDecoration.Underline)) {
            append("Privacy Policy")
          }
          append('.')
        },
        style = MaterialTheme.typography.body2,
        modifier = Modifier
          .paddingFromBaseline(top = 24.dp, bottom = 16.dp)
          .padding(horizontal = 16.dp)
          .wrapContentWidth(),
        textAlign = TextAlign.Center,
      )

      Button(
        onClick = { onClickLogin?.invoke(email, password) },
        shape = MaterialTheme.shapes.medium,
        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondary),
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
  MyTheme { LoginScreen() }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
private fun DarkPreview() {
  MyTheme(darkTheme = true) { LoginScreen() }
}
