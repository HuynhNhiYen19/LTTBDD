package com.example.baithuchanh02

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.baithuchanh02.ui.theme.BaiThucHanh02Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BaiThucHanh02Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    EmailValidationScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailValidationScreen() {
    var emailInput by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") } // Đây là thông báo lỗi/thành công
    var isError by remember { mutableStateOf(false) } // State để xác định có phải lỗi hay không, để đổi màu

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Tiêu đề
        Text(
            text = "Thực hành 02",
            fontSize = 20.sp,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        // Trường nhập liệu Email
        OutlinedTextField(
            value = emailInput,
            onValueChange = { newValue ->
                emailInput = newValue
                // Khi người dùng nhập lại, xóa thông báo và reset trạng thái lỗi
                message = ""
                isError = false
            },
            label = { Text("Email") },
            placeholder = { Text("nhập email của bạn") },
            isError = isError, // Gắn trạng thái lỗi vào TextField
            modifier = Modifier.fillMaxWidth()
        )

        // Spacer nhỏ giữa TextField và thông báo
        Spacer(modifier = Modifier.height(4.dp)) // Tạo khoảng cách nhỏ

        // --- ĐÂY LÀ PHẦN THAY ĐỔI VỊ TRÍ: Đặt Text thông báo TRƯỚC Button ---
        // Hiển thị thông báo lỗi/thành công
        if (message.isNotEmpty()) { // Chỉ hiển thị khi có thông báo
            Text(
                text = message,
                color = if (isError) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary, // Đổi màu dựa vào isError
                modifier = Modifier
                    .align(Alignment.Start) // Căn lề trái giống trong hình
                    .padding(horizontal = 4.dp) // Thêm padding ngang cho đẹp
            )
        }
        // --- KẾT THÚC PHẦN THAY ĐỔI VỊ TRÍ ---

        Spacer(modifier = Modifier.height(16.dp)) // Spacer giữa thông báo và nút

        // Nút Kiểm tra
        Button(
            onClick = {
                if (emailInput.isNullOrEmpty()) {
                    message = "Email không hợp lệ"
                    isError = true
                } else if (!emailInput.contains("@")) {
                    message = "Email không đúng định dạng"
                    isError = true
                } else {
                    message = "Bạn đã nhập email hợp lệ"
                    isError = false
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Kiểm tra")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewEmailValidationScreen() {
    BaiThucHanh02Theme {
        EmailValidationScreen()
    }
}