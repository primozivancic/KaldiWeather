package software.ivancic.geo.ui.components

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import software.ivancic.core.ui.theme.KaldiWeatherTheme
import software.ivancic.geo.ui.R

@Composable
fun SearchField(
    label: String,
    query: TextFieldValue,
    modifier: Modifier = Modifier,
    onSearchActionClicked: () -> Unit = {},
    onClearClick: () -> Unit = {},
    onQueryChanged: (TextFieldValue) -> Unit,
) {
    var showClearButton by remember { mutableStateOf(query.text.isNotEmpty()) }

    OutlinedTextField(
        modifier = modifier
            .onFocusChanged { focusState ->
                showClearButton = (focusState.isFocused)
            },
        value = query,
        onValueChange = onQueryChanged,
        label = { Text(text = label) },
        singleLine = true,
        trailingIcon = {
            if (showClearButton) {
                IconButton(onClick = onClearClick) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = stringResource(R.string.clear)
                    )
                }
            }
        },
        keyboardActions = KeyboardActions(onSearch = {
            onSearchActionClicked()
        }),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search,
            keyboardType = KeyboardType.Text
        )
    )
}

private class SearchQueryTextProvider : PreviewParameterProvider<String> {
    override val values: Sequence<String>
        get() = sequenceOf("", "some search query")
}

@Preview
@Composable
fun TextFieldWithSuggestionsPreview(
    @PreviewParameter(SearchQueryTextProvider::class) text: String,
) {
    KaldiWeatherTheme {
        SearchField(
            query = TextFieldValue(text),
            onQueryChanged = {},
            onSearchActionClicked = {},
            label = "label"
        )
    }
}
