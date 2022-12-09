package com.vickycodes.richtexteditor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.getSelectedText
import androidx.compose.ui.text.input.getTextBeforeSelection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vickycodes.richtexteditor.ui.theme.RichTextEditorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RichTextEditorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center) {
                        var text by remember {
                            mutableStateOf(TextFieldValue("This is the text I want to check edit"))
                        }
                        BasicTextField(
                            value = text,
                            onValueChange = {
                                text = text.copy(it.annotatedString, selection = it.selection, composition = null)
                            },
                            modifier = Modifier
                                .height(300.dp)
                                .padding(24.dp),
                        )

                        Button(onClick = { text = makeBold(text) }) {
                            Text(text = "Make Bold")
                        }
                    }


                }
            }
        }
    }

    fun makeBold(textFVal: TextFieldValue): TextFieldValue {
        val txtAnnotatedBuilder = AnnotatedString.Builder()
        val realStartIndex = textFVal.getTextBeforeSelection(textFVal.text.length).length
        val endIndex = realStartIndex + textFVal.getSelectedText().length
        txtAnnotatedBuilder.append(textFVal.annotatedString)
        val myStyle = SpanStyle(
            color = Color.Green,
            fontWeight = FontWeight.Bold
        )
        txtAnnotatedBuilder.addStyle(myStyle, realStartIndex, endIndex)
        return textFVal.copy(annotatedString = txtAnnotatedBuilder.toAnnotatedString())

    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RichTextEditorTheme {
        Greeting("Android")
    }
}

