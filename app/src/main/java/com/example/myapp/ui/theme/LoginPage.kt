package com.example.myapp.ui.theme

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusOrder
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapp.R
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.navigationBarsWithImePadding
import org.w3c.dom.Text

@Composable
fun Login()
{

    val passwordFocusRequester= FocusRequester()
    val focusManager: FocusManager = LocalFocusManager.current

    ProvideWindowInsets {
        Column(
            Modifier
                .background(if (isSystemInDarkTheme()) Color.Black else Color.Green)
                .navigationBarsWithImePadding()
                .padding(24.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp, alignment = Alignment.Bottom),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.treeanimation),
                contentDescription = "null",
                Modifier.size(80.dp),

                )
            TextInput(Inputtype.Name, keyboardActions = KeyboardActions(onNext = {
                passwordFocusRequester.requestFocus()
            }))
            TextInput(Inputtype.Password,keyboardActions = KeyboardActions(onDone = {
                focusManager.clearFocus()
            }), focusRequester = passwordFocusRequester)
            Button(onClick = { }, modifier = Modifier.fillMaxWidth()) {
                Text("SIGN IN", Modifier.padding(vertical = 8.dp))
            }
            Divider(
                color = Color.White.copy(alpha = 0.3f),
                thickness = 1.dp,
                modifier = Modifier.padding(top = 48.dp)
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Don't have an account?", color = Color.White)
                TextButton(onClick = { }) {
                    Text(text = "SIGN UP")
                }
            }
            Button(onClick = {  }) {
                Text("Sign in With Google")
            }
        }
    }

}
sealed class Inputtype(
    val label:String,
    color: Color,
    val icon: ImageVector,
    val keyboardOptions: KeyboardOptions,
    val visualTransformation: VisualTransformation
){
    object Name:Inputtype(
        label="Username",
        Color.Black,
        icon= Icons.Default.Person,
        keyboardOptions = KeyboardOptions(imeAction= ImeAction.Next),
        visualTransformation= VisualTransformation.None
    )
    object Password:Inputtype(
        label="Password",
        Color.Black,
        icon= Icons.Default.Lock,
        keyboardOptions= KeyboardOptions(imeAction= ImeAction.Done, keyboardType = KeyboardType.Password),
        visualTransformation= PasswordVisualTransformation()
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextInput(inputtype: Inputtype,
              focusRequester: FocusRequester?=null,
              keyboardActions: KeyboardActions
) {
    var value by remember {
        mutableStateOf("")
    }
    TextField(value = value,
        onValueChange = { value =it },
        modifier = Modifier
            .fillMaxWidth()
            .focusOrder(focusRequester ?: FocusRequester()),
        leadingIcon = { Icon(imageVector = inputtype.icon,null) },
        label = { Text(text=inputtype.label) },
        colors= TextFieldDefaults.textFieldColors(
            containerColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        singleLine = true,
        keyboardOptions = inputtype.keyboardOptions,
        visualTransformation = inputtype.visualTransformation,
        keyboardActions=keyboardActions
    )
}
