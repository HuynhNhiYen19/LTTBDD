package com.example.baitapvenhatuan02

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.baitapvenhatuan02.ui.theme.Baitapvenhatuan02Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Baitapvenhatuan02Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UserInfoScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserInfoScreen() {
    var nameInput by remember { mutableStateOf("") }
    var ageInput by remember { mutableStateOf("") }
    var resultMessage by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Tiêu đề
        Text(
            text = "THỰC HÀNH 01",
            fontSize = 20.sp,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 0.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                // Trường nhập liệu Họ và tên
                OutlinedTextField(
                    value = nameInput,
                    onValueChange = { newValue ->
                        nameInput = newValue
                        resultMessage = ""
                    },
                    label = { Text("Họ và tên") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Trường nhập liệu Tuổi
                OutlinedTextField(
                    value = ageInput,
                    onValueChange = { newValue ->
                        if (newValue.all { it.isDigit() } || newValue.isEmpty()) {
                            ageInput = newValue
                        }
                        resultMessage = ""
                    },
                    label = { Text("Tuổi") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // --- PHẦN THAY ĐỔI MODIFIER CỦA BUTTON ---
        Button(
            onClick = {
                val name = nameInput.trim()
                val age = ageInput.toIntOrNull()

                if (name.isEmpty()) {
                    resultMessage = "Họ và tên không được để trống."
                } else if (age == null || age < 0) {
                    resultMessage = "Tuổi không hợp lệ."
                } else {
                    val ageCategory = when {
                        age > 65 -> "Người già"
                        age in 6..65 -> "Người lớn"
                        age in 2..5 -> "Trẻ em"
                        age < 2 -> "Em bé"
                        else -> "Không xác định"
                    }
                    resultMessage = "$name - $age tuổi - là $ageCategory"
                }
            },
            modifier = Modifier
                .width(200.dp) // Đặt chiều rộng cố định, ví dụ 200.dp. Bạn có thể điều chỉnh số này.
            // .wrapContentWidth() // Hoặc dùng cái này nếu bạn muốn nó tự co lại theo nội dung
            // .align(Alignment.CenterHorizontally) // Cần trong một Row, nhưng ở đây đã có Column centerHorizontally
        ) {
            Text("Kiểm tra")
        }
        // --- KẾT THÚC PHẦN THAY ĐỔI MODIFIER CỦA BUTTON ---

        Spacer(modifier = Modifier.height(16.dp))

        // Hiển thị kết quả
        Text(
            text = resultMessage,
            fontSize = 17.sp,
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewUserInfoScreen() {
    Baitapvenhatuan02Theme {
        UserInfoScreen()
    }
}