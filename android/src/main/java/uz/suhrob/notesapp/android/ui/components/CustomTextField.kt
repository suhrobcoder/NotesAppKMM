package uz.suhrob.notesapp.android.ui.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uz.suhrob.notesapp.android.ui.theme.NotesAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(
    value: String,
    onChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    singLine: Boolean = false,
    placeholder: String? = null,
    textStyle: TextStyle = TextStyle.Default,
) {
    val interactionsSource: MutableInteractionSource = remember { MutableInteractionSource() }
    BasicTextField(
        value = value,
        onValueChange = onChange,
        modifier = modifier.fillMaxWidth(),
        textStyle = textStyle,
        decorationBox = @Composable { innerTextField ->
            TextFieldDefaults.TextFieldDecorationBox(
                value = value,
                visualTransformation = VisualTransformation.None,
                enabled = true,
                innerTextField = innerTextField,
                interactionSource = interactionsSource,
                singleLine = singLine,
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    errorIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                ),
                placeholder = {
                    if (placeholder != null) {
                        Text(text = placeholder, style = textStyle)
                    }
                },
                contentPadding = PaddingValues(horizontal = 0.dp, vertical = 8.dp),
            )
        }
    )
}

@Preview
@Composable
fun CustomTextField_Preview() {
    var text by remember {
        mutableStateOf("")
    }
    NotesAppTheme {
        CustomTextField(
            value = text,
            onChange = { text = it },
            singLine = true,
        )
    }
}