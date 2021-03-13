package com.example.androiddevchallenge.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
internal fun BloomOutlinedTextField(
  value: String,
  onValueChange: (String) -> Unit,
  modifier: Modifier = Modifier,
  placeholderText: String? = null,
  placeholder: @Composable () -> Unit = {
    if (placeholderText != null) Text(placeholderText, style = MaterialTheme.typography.body1)
  },
  visualTransformation: VisualTransformation = VisualTransformation.None,
) {
  OutlinedTextField(
    value = value,
    onValueChange = onValueChange,
    modifier = modifier
      .padding(horizontal = 16.dp)
      .fillMaxWidth()
      .height(56.dp),
    textStyle = MaterialTheme.typography.body1,
    placeholder = placeholder,
    visualTransformation = visualTransformation,
  )
}
