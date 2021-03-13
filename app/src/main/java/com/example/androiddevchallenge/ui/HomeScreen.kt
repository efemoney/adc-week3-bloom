package com.example.androiddevchallenge.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.CheckboxDefaults.colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
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
        .clip(MaterialTheme.shapes.small)
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
            modifier = Modifier.weight(1f)
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
  "Desert chic" to "https://www.pexels.com/photo/assorted-color-flowers-2132227/",
  "Tiny terrariums" to "https://www.pexels.com/photo/clear-glass-terrarium-jar-with-mossy-plants-1400375/",
  "Jungle vibes" to "https://www.pexels.com/photo/big-green-leaves-of-monstera-5699665/",
  "Easy care" to "https://www.pexels.com/photo/green-plant-with-long-leaves-in-pot-at-home-6208086/",
  "Statements" to "https://www.pexels.com/photo/green-leaf-plant-indoors-3511755/",
)

private val GardenItems = listOf(
  "Monstera" to "https://www.pexels.com/photo/green-swiss-cheese-plant-3097770/",
  "Aglaonema" to "https://www.pexels.com/photo/green-leaf-plant-on-white-stones-4751978/",
  "Peace lily" to "https://www.pexels.com/photo/delicate-spathiphyllum-cochlearispathum-flowers-with-fresh-green-leaves-in-garden-4425201/",
  "Fiddle leaf" to "https://www.pexels.com/photo/ficus-lyrata-with-lush-green-leaves-in-house-6208087/",
  "Snake plant" to "https://www.pexels.com/photo/photo-of-green-snake-house-plant-2123482/",
  "Pothos" to "https://www.pexels.com/photo/green-leaf-plant-1084199/",
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
