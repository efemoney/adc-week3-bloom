/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Card
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults.colors
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.MyTheme
import dev.chrisbanes.accompanist.glide.GlideImage

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen() {

  Scaffold(
    modifier = Modifier.fillMaxSize(),
    bottomBar = { HomeScreenNavBar() }
  ) { paddingValues ->

    Column(
      Modifier
        .verticalScroll(rememberScrollState())
        .padding(paddingValues)
    ) {

      BloomOutlinedTextField(
        value = "",
        onValueChange = { /* no op */ },
        modifier = Modifier.padding(top = 40.dp),
        placeholder = {
          Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Default.Search, "Search", Modifier.size(18.dp))
            Spacer(modifier = Modifier.width(4.dp))
            Text("Search", style = MaterialTheme.typography.body1)
          }
        }
      )

      Text(
        text = "Browse themes",
        style = MaterialTheme.typography.h1,
        modifier = Modifier
          .paddingFromBaseline(top = 32.dp)
          .padding(horizontal = 16.dp)
          .wrapContentWidth(),
      )

      Spacer(modifier = Modifier.height(16.dp))

      Themes()

      Row(
        modifier = Modifier
          .paddingFromBaseline(top = 40.dp)
          .padding(horizontal = 16.dp)
      ) {
        Text(
          text = "Design your home garden",
          style = MaterialTheme.typography.h1,
          modifier = Modifier.wrapContentWidth(),
        )

        Spacer(Modifier.weight(1f))

        Icon(
          imageVector = Icons.Filled.FilterList,
          contentDescription = "Filter",
          modifier = Modifier.size(24.dp)
        )
      }

      Spacer(Modifier.height(16.dp))

      GardenItems.forEachIndexed { index, (name, image) ->
        GardenPlant(name, image, selected = index == 0)
        if (index != GardenItems.lastIndex) Spacer(Modifier.height(8.dp))
      }
    }
  }
}

@Composable
fun GardenPlant(name: String, image: String, selected: Boolean) {

  val contentColor = LocalContentColor.current

  Row(
    Modifier
      .drawBehind {
        drawLine(
          contentColor,
          start = Offset(x = 88.dp.toPx(), size.height),
          end = Offset(x = size.width - 16.dp.toPx(), size.height),
        )
      }
      .padding(horizontal = 16.dp)
      .wrapContentHeight()
  ) {

    GlideImage(
      data = image,
      contentDescription = name,
      modifier = Modifier
        .size(64.dp)
        .clip(MaterialTheme.shapes.small),
      contentScale = ContentScale.Crop,
    )

    Spacer(Modifier.width(16.dp))

    Column(
      Modifier
        .weight(1f)
        .height(64.dp)
    ) {

      Text(
        text = name,
        style = MaterialTheme.typography.h2,
        modifier = Modifier.paddingFromBaseline(top = 24.dp),
      )

      Text(
        text = "This is a description",
        style = MaterialTheme.typography.body1,
        modifier = Modifier.paddingFromBaseline(bottom = 24.dp),
      )
    }

    Checkbox(
      checked = selected,
      onCheckedChange = null,
      modifier = Modifier
        .size(24.dp)
        .offset(x = -16.dp, y = 16.dp),
      colors = colors(
        checkedColor = MaterialTheme.colors.secondary,
        checkmarkColor = MaterialTheme.colors.onSecondary,
      )
    )
  }
}

@Composable
private fun Themes() {
  Row(
    Modifier
      .horizontalScroll(rememberScrollState())
      .padding(horizontal = 16.dp)
  ) {

    ThemeItems.forEachIndexed { i, (label, image) ->

      Card(
        modifier = Modifier.size(136.dp),
        shape = MaterialTheme.shapes.small,
        elevation = 1.dp
      ) {
        Column {

          GlideImage(
            data = image,
            contentDescription = "label",
            modifier = Modifier.weight(1f),
            contentScale = ContentScale.Crop,
          )

          Text(
            text = label,
            modifier = Modifier
              .height(40.dp)
              .fillMaxWidth()
              .padding(start = 16.dp)
              .wrapContentHeight(),
            style = MaterialTheme.typography.h2,
          )
        }
      }

      if (i != ThemeItems.lastIndex) Spacer(modifier = Modifier.width(8.dp))
    }
  }
}

private val ThemeItems = listOf(
  "Desert chic" to "https://images.pexels.com/photos/2132227/pexels-photo-2132227.jpeg?cs=srgb&dl=pexels-quang-nguyen-vinh-2132227.jpg&fm=jpg",
  "Tiny terrariums" to "https://images.pexels.com/photos/1400375/pexels-photo-1400375.jpeg?cs=srgb&dl=pexels-katarzyna-modrzejewska-1400375.jpg&fm=jpg",
  "Jungle vibes" to "https://images.pexels.com/photos/5699665/pexels-photo-5699665.jpeg?cs=srgb&dl=pexels-volkan-vardar-5699665.jpg&fm=jpg/",
  "Easy care" to "https://images.pexels.com/photos/6208086/pexels-photo-6208086.jpeg?cs=srgb&dl=pexels-%D0%B2%D0%BB%D0%B0%D0%B4%D0%B8%D0%BC%D0%B8%D1%80-%D0%B3%D0%BB%D0%B0%D0%B4%D0%BA%D0%BE%D0%B2-6208086.jpg&fm=jpg",
  "Statements" to "https://images.pexels.com/photos/3511755/pexels-photo-3511755.jpeg?cs=srgb&dl=pexels-sidnei-maia-3511755.jpg&fm=jpg/",
)

private val GardenItems = listOf(
  "Monstera" to "https://images.pexels.com/photos/3097770/pexels-photo-3097770.jpeg?cs=srgb&dl=pexels-huy-phan-3097770.jpg&fm=jpg/",
  "Aglaonema" to "https://images.pexels.com/photos/4751978/pexels-photo-4751978.jpeg?cs=srgb&dl=pexels-karolina-grabowska-4751978.jpg&fm=jpg/",
  "Peace lily" to "https://images.pexels.com/photos/4425201/pexels-photo-4425201.jpeg?cs=srgb&dl=pexels-melvin-vito-4425201.jpg&fm=jpg/",
  "Fiddle leaf" to "https://images.pexels.com/photos/6208087/pexels-photo-6208087.jpeg?cs=srgb&dl=pexels-%D0%B2%D0%BB%D0%B0%D0%B4%D0%B8%D0%BC%D0%B8%D1%80-%D0%B3%D0%BB%D0%B0%D0%B4%D0%BA%D0%BE%D0%B2-6208087.jpg&fm=jpg/",
  "Snake plant" to "https://images.pexels.com/photos/2123482/pexels-photo-2123482.jpeg?cs=srgb&dl=pexels-fabian-stroobants-2123482.jpg&fm=jpg/",
  "Pothos" to "https://images.pexels.com/photos/1084199/pexels-photo-1084199.jpeg?cs=srgb&dl=pexels-faraz-ahmad-1084199.jpg&fm=jpg/",
)

@Composable
private fun HomeScreenNavBar() {

  val (selected, setSelected) = remember { mutableStateOf(0) }

  BottomNavigation(
    backgroundColor = MaterialTheme.colors.primary,
    modifier = Modifier.height(56.dp)
  ) {

    NavigationItems.forEachIndexed { i, (label, icon) ->

      BottomNavigationItem(
        selected = i == selected,
        onClick = { setSelected(i) },
        icon = { Icon(icon, contentDescription = label, Modifier.size(24.dp)) },
        label = { Text(label, style = MaterialTheme.typography.caption) }
      )
    }
  }
}

private val NavigationItems = listOf(
  "Home" to Icons.Filled.Home,
  "Favorites" to Icons.Outlined.FavoriteBorder,
  "Profile" to Icons.Filled.AccountCircle,
  "Cart" to Icons.Filled.ShoppingCart,
)

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
private fun LightPreview() {
  MyTheme { HomeScreen() }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
private fun DarkPreview() {
  MyTheme(darkTheme = true) { HomeScreen() }
}
