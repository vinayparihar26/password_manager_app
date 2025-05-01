package com.example.mobileiip02504.MOBILEIIP02504.presentation.activity

/**
 * File: ApiResultState.kt
 * Created by Vinay Parihar
 */
import android.view.ViewTreeObserver
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mobileiip02504.MOBILEIIP02504.model.PasswordEntity
import com.example.mobileiip02504.MOBILEIIP02504.presentation.viewModels.PasswordViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: PasswordViewModel,
) {
    var accountError by remember { mutableStateOf(false) }
    var usernameError by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()
    var showSheet by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf<PasswordEntity?>(null) }
    val passwords by viewModel.passwords.collectAsState()
    var account by remember {
        mutableStateOf("")
    }
    var username by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    selectedItem = null
                    account = ""
                    username = ""
                    password = ""
                    showSheet = true
                }, containerColor = Color(0xFF3F7DE3), modifier = Modifier.size(75.dp)
            ) {
                Icon(
                    Icons.Default.Add,
                    contentDescription = "Add",
                    modifier = Modifier.size(32.dp),
                )
            }

        }, modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 5.dp)
        ) {
            Text(
                "Password Manager",
                fontSize = 20.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp)
            )



            LazyColumn(contentPadding = paddingValues) {
                items(passwords) { item ->

                    Card(
                        shape = RoundedCornerShape(32.dp),
                        // Set your desired corner radius
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                            .clickable {
                                selectedItem = item
                                account = item.account
                                username = item.username
                                password = item.password
                                showSheet = true
                            },
                        colors = CardDefaults.cardColors(containerColor = Color.White)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {

                            Row {

                                Text(
                                    item.account,
                                    maxLines = 1,
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier
                                        .padding(end = 4.dp)
                                        .weight(1f)
                                )
                                Text(
                                    "* * * * * *",
                                    fontSize = 20.sp,
                                    color = Color.Gray,
                                    modifier = Modifier
                                        .padding(end = 2.dp)
                                        .weight(1f)
                                )
                                Spacer(modifier = Modifier.weight(1f))
                                Icon(
                                    imageVector = Icons.Filled.KeyboardArrowRight,  // Arrow icon
                                    contentDescription = "Arrow",
                                    tint = Color.Black,
                                    modifier = Modifier.size(25.dp)

                                )
                            }
                        }
                    }

                }
            }

        }

        if (showSheet) {
            ModalBottomSheet(
                onDismissRequest = {
                    showSheet = false
                    selectedItem = null
                }, sheetState = sheetState
            ) {
                val imeState = rememberImeState()
                val scrollState = rememberScrollState()

                LaunchedEffect(key1 = imeState.value) {
                    if (imeState.value) {
                        scrollState.scrollTo(scrollState.maxValue)
                    }
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .imePadding()
                        .padding(bottom = 10.dp)
                ) {

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(max = 500.dp)
                            .verticalScroll(scrollState)
                            .padding(16.dp)
                    ) {
                        if (accountError || usernameError || passwordError) {
                            Text(
                                text = "All fields are required",
                                color = Color.Red,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier
                                    .padding(top = 8.dp, bottom = 4.dp)
                                    .align(Alignment.CenterHorizontally)
                            )
                        }
                        Text(
                            text = if (selectedItem == null) " " else "Account Details",
                            fontSize = 20.sp,
                            style = MaterialTheme.typography.titleLarge,
                            modifier = Modifier.align(Alignment.CenterHorizontally),
                            fontWeight = FontWeight.Bold,
                            color = Color.Blue,

                            )

                        OutlinedTextField(
                            value = account,
                            onValueChange = {
                                account = it
                                accountError = false
                            },
                            isError = accountError,
                            label = { Text("Account") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .imePadding()
                                .padding(bottom = 10.dp)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        OutlinedTextField(
                            value = username,
                            onValueChange = {
                                username = it
                                usernameError = false
                            },
                            isError = usernameError,
                            label = { Text("Username") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .imePadding()
                                .padding(bottom = 10.dp)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        OutlinedTextField(
                            value = password,
                            onValueChange = {
                                password = it
                                passwordError = false
                            },
                            isError = passwordError,
                            label = { Text("Password") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .imePadding()
                                .padding(bottom = 10.dp)
                        )

                        Button(
                            onClick = { password = generatePassword(12) },
                            modifier = Modifier
                                .padding(top = 8.dp)
                                .fillMaxWidth(), colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF4CAF50),
                                contentColor = Color.White
                            )
                        ) {
                            Text("Generate Password", fontSize = 20.sp)
                        }

                        Spacer(
                            modifier = Modifier.height(10.dp)
                        )

                        val strength = checkPasswordStrength(password)
                        val strengthColor = when (strength) {
                            "Weak" -> Color.Red
                            "Medium" -> Color.Yellow
                            "Strong" -> Color.Green
                            else -> Color.Gray
                        }

                        val (progressValue) = when (checkPasswordStrength(password)) {
                            "Weak" -> 0.3f to Color.Red
                            "Medium" -> 0.6f to Color.Yellow
                            "Strong" -> 1.0f to Color.Green
                            else -> 0f to Color.Gray

                        }
                        val animatedProgress by animateFloatAsState(targetValue = progressValue)

                        CustomLinearProgress(progress = animatedProgress)


                        Text(
                            text = strength,
                            color = strengthColor,
                            modifier = Modifier.padding(top = 4.dp)
                        )

                        Spacer(
                            modifier = Modifier.height(10.dp)
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            if (selectedItem == null) {
                                Button(
                                    onClick = {
                                        val trimmedAccount = account.trim()
                                        val trimmedUsername = username.trim()
                                        val trimmedPassword = password.trim()

                                        if (isInputValid(
                                                trimmedAccount,
                                                trimmedUsername,
                                                trimmedPassword
                                            )
                                        ) {
                                            viewModel.addPassword(
                                                trimmedAccount,
                                                trimmedUsername,
                                                trimmedPassword
                                            )
                                            showSheet = false
                                            account = ""; username = ""; password = ""
                                            accountError = false
                                            usernameError = false
                                            passwordError = false
                                            viewModel.errorMsg.value = null
                                        } else {
                                            accountError = account.isBlank()
                                            usernameError = username.isBlank()
                                            passwordError = password.isBlank()
                                           // viewModel.errorMsg.value = "All fields are required"
                                        }
                                    },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 8.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color.Black, contentColor = Color.White
                                    )
                                ) {
                                    Text(
                                        "Add New Account",
                                        fontSize = 15.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            } else {
                                Button(
                                    onClick = {
                                        if (account.isNotBlank() && username.isNotBlank() && password.isNotBlank()) {
                                            val updated = selectedItem!!.copy(
                                                account = account,
                                                username = username,
                                                password = password
                                            )
                                            viewModel.updatePassword(updated)
                                            showSheet = false
                                            selectedItem = null
                                            accountError = false
                                            usernameError = false
                                            passwordError = false
                                            viewModel.errorMsg.value = null
                                        } else {
                                           /* viewModel.errorMsg.value =
                                                "All fields are required."*/
                                            accountError = account.isBlank()
                                            usernameError = username.isBlank()
                                            passwordError = password.isBlank()
                                        }
                                    }, modifier = Modifier.weight(1f),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color.Black,
                                        contentColor = Color.White
                                    )
                                ) {
                                    Text(
                                        "Save",
                                        fontSize = 15.sp,
                                        modifier = Modifier.padding(8.dp)
                                    )
                                }

                                Button(
                                    onClick = {
                                        viewModel.deletePassword(selectedItem!!)
                                        showSheet = false
                                        selectedItem = null
                                    }, modifier = Modifier
                                        .weight(1f)
                                        .padding(start = 10.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color(0xFFf04646),
                                        contentColor = Color.White
                                    )
                                ) {
                                    Text(
                                        "Delete",
                                        fontSize = 15.sp,
                                        modifier = Modifier.padding(8.dp)
                                    )
                                }
                            }
                        }

                    }
                }
            }
        }
        viewModel.errorMsg.value?.let {
            Text("Error: $it", color = Color.Red)
        }
    }
}

@Composable
fun CustomLinearProgress(progress: Float) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(4.dp)
            .background(Color.LightGray)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(progress)
                .height(4.dp)
                .background(Color.Blue)
        )
    }
}

@Composable
fun rememberImeState(): State<Boolean> {
    val imeState = remember {
        mutableStateOf(false)
    }

    val view = LocalView.current
    DisposableEffect(view) {
        val listener = ViewTreeObserver.OnGlobalLayoutListener {
            val isKeyboardOpen =
                ViewCompat.getRootWindowInsets(view)?.isVisible(WindowInsetsCompat.Type.ime())
                    ?: true
            imeState.value = isKeyboardOpen
        }

        view.viewTreeObserver.addOnGlobalLayoutListener(listener)
        onDispose {
            view.viewTreeObserver.removeOnGlobalLayoutListener(listener)
        }
    }
    return imeState
}

private fun isInputValid(account: String, username: String, password: String): Boolean {
    return account.trim().isNotEmpty() && username.trim().isNotEmpty() && password.trim()
        .isNotEmpty()
}

private fun checkPasswordStrength(password: String): String {
    if (password.isBlank()) return "Weak"

    return when {
        password.length < 6 -> "Weak"
        password.length >= 10 &&
                password.any { it.isDigit() } &&
                password.any { it.isUpperCase() } &&
                password.any { it.isLowerCase() } -> "Strong"

        password.length >= 6 -> "Medium"
        else -> "Weak"
    }
}


private fun generatePassword(length: Int = 12): String {
    val allowedChars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
    return (1..12).map { allowedChars.random() }.joinToString("")
}
