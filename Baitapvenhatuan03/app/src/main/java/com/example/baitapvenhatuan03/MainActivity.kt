package com.example.baitapvenhatuan03

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.baitapvenhatuan03.ui.theme.Baitapvenhatuan03Theme
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.TextField
import androidx.compose.material3.OutlinedTextField // Import OutlinedTextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation

// THÊM CÁC IMPORT NÀY ĐỂ SỬ DỤNG CARD VÀ LAZYCOLUMN
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.ui.text.style.TextAlign // Import này cần thiết để sử dụng TextAlign.Center

// Import R để truy cập drawable resources et Icons
import com.example.baitapvenhatuan03.R
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton // Import IconButton
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.TextFieldDefaults // Import TextFieldDefaults
import androidx.compose.material3.ListItemDefaults // Import ListItemDefaults
import androidx.compose.material3.OutlinedTextFieldDefaults // Import OutlinedTextFieldDefaults


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Baitapvenhatuan03Theme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    AppNavigation()
                }
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "splash_screen") {
        composable("splash_screen") { SplashScreen(navController = navController) }
        composable("components_list") { ComponentsListScreen(navController = navController) }
        composable("text_detail") { TextDetailScreen(navController = navController) }
        composable("image_detail") { ImageDetailScreen(navController = navController) }
        composable("textfield_detail") { TextFieldDetailScreen(navController = navController) }
        composable("passwordfield_detail") { PasswordFieldScreen(navController = navController) }
        composable("column_detail") { ColumnDetailScreen(navController = navController) }
        composable("row_detail") { RowDetailScreen(navController = navController) }
        composable("box_detail") { BoxDetailScreen(navController = navController) } // THÊM DÒNG NÀY
    }
}

// CẬP NHẬT: StatusBar để hiển thị tên và MSSV ở góc phải trên cùng
@Composable
fun StatusBar(time: String = "9:41", name: String? = null, studentId: String? = null) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top
    ) {
        // Giờ
        Text(time, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.Black)

        // Các biểu tượng và Tên/MSSV
        Column(
            horizontalAlignment = Alignment.End // Căn phải
        ) {
            // Sóng di động (không có pin)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("ıllı", fontSize = 16.sp, color = Color.Black, fontWeight = FontWeight.Black)
                // Đã loại bỏ Image(painter = painterResource(id = R.drawable.battery_icon))
            }

            // Tên và MSSV (chỉ hiển thị nếu được cung cấp)
            if (name != null && studentId != null) {
                Text(name, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                Text(studentId, fontSize = 14.sp, color = Color.Gray)
            }
        }
    }
}


@Composable
fun SplashScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Gọi StatusBar với tên và MSSV
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(bottom = 8.dp)
        ) {
            // Truyền tên và MSSV vào StatusBar
            StatusBar(time = "9:41", name = "Huỳnh Đặng Yến Nhi", studentId = "060305003490")
        }

        Spacer(modifier = Modifier.weight(1f))

        Image(
            painter = painterResource(id = R.drawable.jetpack_compose),
            contentDescription = "Jetpack Compose Icon",
            modifier = Modifier.size(150.dp)
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Jetpack Compose",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Jetpack Compose is a modern UI toolkit for building native Android applications using a declarative programming approach.",
            fontSize = 16.sp,
            color = Color.Gray,
            modifier = Modifier
                .padding(horizontal = 32.dp)
                .fillMaxWidth(),
            maxLines = 4,
            softWrap = true
        )

        Spacer(modifier = Modifier.height(48.dp))

        Button(
            onClick = { navController.navigate("components_list") },
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .height(56.dp)
        ) {
            Text("I'm ready", fontSize = 20.sp)
        }

        // Loại bỏ tên và MSSV ở đây, vì đã chuyển lên StatusBar
        Spacer(modifier = Modifier.weight(1f))
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComponentsListScreen(navController: NavController) {
    val scrollState = rememberScrollState()
    val itemBlueColor = Color(0xFFCEE9F9) // Màu xanh nhạt cho các item
    val itemPinkColor = Color(0xFFFDE8ED) // Màu hồng nhạt cho "Tự tìm hiểu"

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White) // Nền trắng cho toàn màn hình
            .verticalScroll(scrollState) // Cho phép cuộn toàn bộ nội dung
            .padding(horizontal = 16.dp) // Padding ngang cho toàn bộ nội dung list
    ) {
        // Các màn hình khác chỉ hiển thị StatusBar bình thường (không tên/MSSV)
        StatusBar("9:09")

        // Tiêu đề "UI Components List"
        Text(
            text = "UI Components List",
            fontSize = 28.sp, // Tăng kích thước font để khớp ảnh
            fontWeight = FontWeight.Bold,
            color = Color.Blue,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 24.dp), // Điều chỉnh padding
            textAlign = TextAlign.Center
        )

        // ===== Display Section =====
        Text(
            "Display",
            fontSize = 20.sp, // Kích thước font cho tiêu đề section
            fontWeight = FontWeight.Bold, // In đậm
            color = Color.Black, // Màu đen
            modifier = Modifier.padding(start = 0.dp, top = 8.dp, bottom = 8.dp) // Padding sát lề trái
        )
        // Sử dụng ComponentBlockItem mới
        ComponentBlockItem("Text", "Displays text", itemBlueColor) { navController.navigate("text_detail") }
        Spacer(modifier = Modifier.height(10.dp)) // Khoảng cách giữa các item
        ComponentBlockItem("Image", "Displays an image", itemBlueColor) { navController.navigate("image_detail") }

        Spacer(modifier = Modifier.height(24.dp)) // Khoảng cách giữa các section

        // ===== Input Section =====
        Text(
            "Input",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(start = 0.dp, top = 8.dp, bottom = 8.dp)
        )
        ComponentBlockItem("TextField", "Input field for text", itemBlueColor) { navController.navigate("textfield_detail") }
        Spacer(modifier = Modifier.height(10.dp))
        ComponentBlockItem("PasswordField", "Input field for passwords", itemBlueColor) { navController.navigate("passwordfield_detail") }

        Spacer(modifier = Modifier.height(24.dp))

        // ===== Layout Section =====
        Text(
            "Layout",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(start = 0.dp, top = 8.dp, bottom = 8.dp)
        )
        ComponentBlockItem("Column", "Arranges elements vertically", itemBlueColor) { navController.navigate("column_detail") }
        Spacer(modifier = Modifier.height(10.dp))
        ComponentBlockItem("Row", "Arranges elements horizontally", itemBlueColor) { navController.navigate("row_detail") }
        Spacer(modifier = Modifier.height(10.dp)) // KHOẢNG CÁCH CHO MỤC BOX MỚI
        // ĐỔI TÊN MỤC "Tự tìm hiểu" THÀNH "Box" VÀ CHUYỂN MÀU
        ComponentBlockItem("Box", "Arranges elements on top of each other", itemBlueColor) { navController.navigate("box_detail") }

        Spacer(modifier = Modifier.height(16.dp)) // Padding cuối cùng
    }
}

// Hàm Composable tạo ra các item dạng khối có bo góc và màu nền
@Composable
fun ComponentBlockItem(title: String, description: String, bgColor: Color, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(8.dp), // Bo góc 8dp
        colors = CardDefaults.cardColors(containerColor = bgColor), // Màu nền của Card
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp) // Không có đổ bóng
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp) // Padding bên trong Card để chữ không sát viền
        ) {
            Text(
                text = title,
                fontSize = 18.sp, // Kích thước font cho tiêu đề
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Text(
                text = description,
                fontSize = 14.sp, // Kích thước font cho mô tả
                color = Color.Black
            )
        }
    }
}


// CẬP NHẬT DetailScreenHeader
@Composable
fun DetailScreenHeader(navController: NavController, title: String) {
    Column(modifier = Modifier.fillMaxWidth()) {
        StatusBar("9:09") // Chỉ gọi StatusBar không truyền tên/MSSV ở các màn hình khác
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp), // Điều chỉnh padding
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween // Căn đều các phần tử
        ) {
            // Nút Back
            IconButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier.size(24.dp) // Kích thước icon
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = Color(0xFF1E90FF) // Màu xanh tương tự hình
                )
            }

            // Tiêu đề
            Text(
                text = title,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1E90FF), // Màu xanh tương tự hình
                modifier = Modifier.weight(1f), // Chiếm phần không gian còn lại
                textAlign = TextAlign.Center // Căn giữa
            )

            // Spacer rỗng để cân bằng vị trí Text nếu không có icon bên phải
            Spacer(modifier = Modifier.width(24.dp)) // Bằng kích thước của IconButton để cân bằng
        }
    }
}

@Composable
fun TextDetailScreen(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize()) {
        DetailScreenHeader(navController = navController, title = "Text Detail")
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                buildAnnotatedString {
                    withStyle(style = SpanStyle(fontSize = 28.sp, color = Color.Black)) {
                        append("\nThe ")
                    }
                    withStyle(style = SpanStyle(fontSize = 28.sp, color = Color.Black, textDecoration = TextDecoration.LineThrough)) {
                        append("quick ") // Sửa lại để có gạch ngang
                    }
                    withStyle(style = SpanStyle(fontSize = 28.sp, color = Color(0xFFD2691E))) {
                        append("Brown")
                    }
                    withStyle(style = SpanStyle(fontSize = 28.sp, color = Color.Black)) {
                        append("fox j u m p s ")
                    }
                    withStyle(style = SpanStyle(fontSize = 28.sp, fontWeight = FontWeight.Bold, color = Color.Black)) {
                        append("over")
                    }
                    withStyle(style = SpanStyle(fontSize = 28.sp, textDecoration = TextDecoration.Underline, color = Color.Black)) {
                        append("\nthe ")
                    }
                    withStyle(style = SpanStyle(fontSize = 28.sp, fontStyle = FontStyle.Italic, color = Color.Black)) {
                        append("lazy ")
                    }
                    withStyle(style = SpanStyle(fontSize = 28.sp, color = Color.Black)) {
                        append("dog.")
                    }
                },
                lineHeight = 40.sp
            )
        }
    }
}

@Composable
fun ImageDetailScreen(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize()) {
        DetailScreenHeader(navController = navController, title = "Image Detail")
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.uth),
                contentDescription = "Ho Chi Minh City University of Transport Auditorium",
                modifier = Modifier.size(350.dp)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldDetailScreen(navController: NavController) {
    var textValue by remember { mutableStateOf("") }

    val borderColor = Color.Black // Viền ĐEN
    val darkGrayPlaceholder = Color.DarkGray // Màu đen nhạt cho placeholder

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        DetailScreenHeader(navController = navController, title = "TextField")
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f) // Chiếm không gian còn lại để đẩy nội dung xuống giữa
                .padding(horizontal = 16.dp), // Padding ngang cho nội dung chính
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center // Căn giữa nội dung theo chiều dọc
        ) {
            // Sử dụng Box để bao bọc OutlinedTextField và Placeholder tùy chỉnh
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                OutlinedTextField(
                    value = textValue,
                    onValueChange = { textValue = it },
                    // Bỏ placeholder mặc định ở đây vì chúng ta sẽ tự tạo
                    // placeholder = { Text("Thông tin nhập") },
                    label = { Text("") }, // Đặt label rỗng để không hiển thị label nổi
                    modifier = Modifier
                        .fillMaxSize(), // Chiếm toàn bộ Box cha
                    shape = RoundedCornerShape(16.dp), // Bo góc nhiều hơn: từ 8.dp lên 16.dp
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        disabledContainerColor = Color.White,
                        focusedBorderColor = borderColor, // Viền ĐEN khi focus
                        unfocusedBorderColor = borderColor, // Viền ĐEN khi không focus
                        cursorColor = Color.Black,
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black,
                        // Bỏ màu placeholder mặc định
                        // focusedPlaceholderColor = darkGrayPlaceholder,
                        // unfocusedPlaceholderColor = darkGrayPlaceholder
                    ),
                    singleLine = true // Đảm bảo chỉ nhập trên 1 dòng
                )

                // Placeholder tùy chỉnh
                if (textValue.isEmpty()) { // Chỉ hiển thị placeholder khi TextField trống
                    Text(
                        text = "Thông tin nhập",
                        modifier = Modifier
                            .fillMaxSize()
                            .wrapContentSize(Alignment.Center), // Căn giữa chữ cả chiều ngang và dọc
                        color = darkGrayPlaceholder, // Màu đen nhạt
                        fontSize = 16.sp // Kích thước font phù hợp
                    )
                }
            }


            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Tự động cập nhật dữ liệu theo textfield",
                fontSize = 16.sp,
                color = Color.Red
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordFieldScreen(navController: NavController) {
    var passwordValue by remember { mutableStateOf("") }

    // Định nghĩa màu viền và màu chữ cho TextField
    val borderColor = Color.Black // Viền ĐEN
    val textColor = Color.Black // Màu chữ nhập vào
    val labelColor = Color.DarkGray // Màu của label "Password"

    Column(modifier = Modifier.fillMaxSize()) {
        DetailScreenHeader(navController = navController, title = "PasswordField Detail")
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Enter your password:", fontSize = 18.sp)
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = passwordValue,
                onValueChange = { passwordValue = it },
                label = { Text("Password") }, // Label "Password"
                modifier = Modifier
                    .fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation(),
                shape = RoundedCornerShape(16.dp), // THÊM DÒNG NÀY ĐỂ BO GÓC
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    disabledContainerColor = Color.White,
                    focusedBorderColor = borderColor, // Viền ĐEN khi focus
                    unfocusedBorderColor = borderColor, // Viền ĐEN khi không focus
                    cursorColor = textColor,
                    focusedTextColor = textColor,
                    unfocusedTextColor = textColor,
                    focusedLabelColor = labelColor, // Màu label khi focus
                    unfocusedLabelColor = labelColor // Màu label khi không focus
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Password (hidden): ${"*".repeat(passwordValue.length)}", fontSize = 16.sp)
        }
    }
}

// Hàm Composable cho một hàng gồm 3 Box (item) - Giữ nguyên cho RowDetailScreen
@Composable
fun ThreeItemsRow(index: Int) {
    val outerBoxColor = Color(0xFFEFEFEF) // Màu xám rất nhạt cho box bao ngoài
    val lightBlue = Color(0xFFD3E0F6)
    val mediumBlue = Color(0xFF9ABAEF)
    val darkBlue = Color(0xFF6B92F2)

    val colorsForThisRow = when (index % 4) {
        0 -> listOf(lightBlue, darkBlue, mediumBlue)
        1 -> listOf(mediumBlue, darkBlue, lightBlue)
        2 -> listOf(lightBlue, darkBlue, mediumBlue)
        else -> listOf(mediumBlue, darkBlue, lightBlue)
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp, horizontal = 0.dp)
            .background(Color(0xFFEFEFEF), shape = RoundedCornerShape(8.dp))
            .padding(vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Item 1
            Box(
                modifier = Modifier
                    .width(110.dp)
                    .height(70.dp)
                    .background(outerBoxColor, shape = RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize(0.9f)
                        .background(colorsForThisRow[0], shape = RoundedCornerShape(8.dp))
                ) {
                    // No Text
                }
            }

            // Item 2
            Box(
                modifier = Modifier
                    .width(110.dp)
                    .height(70.dp)
                    .background(outerBoxColor, shape = RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize(0.9f)
                        .background(colorsForThisRow[1], shape = RoundedCornerShape(8.dp))
                ) {
                    // No Text
                }
            }

            // Item 3
            Box(
                modifier = Modifier
                    .width(110.dp)
                    .height(70.dp)
                    .background(outerBoxColor, shape = RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize(0.9f)
                        .background(colorsForThisRow[2], shape = RoundedCornerShape(8.dp))
                ) {
                    // No Text
                }
            }
        }
    }
}

// Đã đơn giản hóa ColumnItem để chỉ là khối màu xanh lá cây
@Composable
fun ColumnItem(itemColor: Color) {
    val itemWidth = 350.dp // Chiều rộng của item
    val itemHeight = 100.dp // Chiều cao của item

    Box(
        modifier = Modifier
            .width(itemWidth)
            .height(itemHeight)
            .background(itemColor, shape = RoundedCornerShape(8.dp)) // Màu xanh lá cây và bo góc
    ) {
        // Không có Text bên trong các box này
    }
}


@Composable
fun ColumnDetailScreen(navController: NavController) {
    // Định nghĩa các màu xanh lá cây dựa trên ảnh
    val lightGreen = Color(0xFFD4EAD5) // Màu xanh nhạt (hơi khác so với trước để khớp ảnh hơn)
    val darkGreen = Color(0xFF9CCC9C)   // Màu xanh đậm (hơi khác so với trước để khớp ảnh hơn)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White) // Nền trắng cho toàn màn hình
    ) {
        DetailScreenHeader(navController = navController, title = "Colum Layout")

        // KHUNG XÁM ĐẰNG SAU: Card lớn bao bọc tất cả các item màu xanh lá
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 16.dp), // Padding cho Card lớn so với lề màn hình
            colors = CardDefaults.cardColors(containerColor = Color(0xFFEFEFEF)), // Màu nền xám nhạt cho khung
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp), // Bỏ đổ bóng
            shape = RoundedCornerShape(8.dp) // Bo góc cho khung xám
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp), // Padding bên trong khung xám (trên/dưới)
                horizontalAlignment = Alignment.CenterHorizontally // Căn giữa các item xanh lá cây
            ) {
                // Item 1 (màu xanh lá cây nhạt)
                ColumnItem(itemColor = lightGreen)
                Spacer(modifier = Modifier.height(10.dp)) // Khoảng cách giữa các item

                // Item 2 (màu xanh lá cây đậm)
                ColumnItem(itemColor = darkGreen)
                Spacer(modifier = Modifier.height(10.dp)) // Khoảng cách giữa các item

                // Item 3 (màu xanh lá cây nhạt)
                ColumnItem(itemColor = lightGreen)
            }
        }
    }
}


@Composable
fun RowDetailScreen(navController: NavController) {
    // Định nghĩa các màu xanh lam khác nhau dựa trên hình ảnh 'image_e7bb78.png'
    val outerBoxColor = Color(0xFFEFEFEF) // Màu xám rất nhạt cho box bao ngoài các item
    val lightBlue = Color(0xFFD3E0F6)     // Màu xanh nhạt nhất
    val mediumBlue = Color(0xFF9ABAEF)    // Màu xanh nhạt trung bình
    val darkBlue = Color(0xFF6B92F2)      // Màu xanh đậm hơn

    val rowContainerColor = Color(0xFFEFEFEF) // Màu xám nhạt cho nền của mỗi hàng lớn
    val rowPaddingVertical = 6.dp // Khoảng cách giữa các hàng lớn

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFAF7FF)) // màu nền tím nhạt #FAF7FF
    ) {
        // Sử dụng DetailScreenHeader đã có
        DetailScreenHeader(navController = navController, title = "Row Layout") // Đổi tiêu đề thành "Row Layout" theo ảnh

        // Các item hiển thị ngang (Row)
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp), // Padding ngang cho LazyColumn
            horizontalAlignment = Alignment.CenterHorizontally, // Căn giữa các item theo chiều ngang
            contentPadding = PaddingValues(vertical = 16.dp) // Padding trên/dưới cho nội dung LazyColumn
        ) {
            items(5) { index -> // Hiển thị 5 hàng, mỗi hàng có 3 box
                val colorsForThisRow = when (index % 2) { // Thay đổi màu sắc của các box trong hàng
                    0 -> listOf(lightBlue, darkBlue, mediumBlue)
                    else -> listOf(mediumBlue, darkBlue, lightBlue)
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = rowPaddingVertical) // Padding dọc giữa các hàng lớn
                        .background(rowContainerColor, shape = RoundedCornerShape(8.dp)) // Nền xám nhạt cho hàng và bo góc
                        .padding(vertical = 8.dp) // Padding bên trong Box của hàng để các item không sát viền
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly, // Căn đều các item
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Item 1
                        Box(
                            modifier = Modifier
                                .width(110.dp) // Kích thước của box bao ngoài
                                .height(70.dp)
                                .background(outerBoxColor, shape = RoundedCornerShape(8.dp)), // Nền xám nhạt và bo góc
                            contentAlignment = Alignment.Center
                        ) {
                            // Inner Box (màu xanh)
                            Box(
                                modifier = Modifier
                                    .fillMaxSize(0.9f) // Chiếm 90% kích thước Box cha để tạo padding
                                    .background(colorsForThisRow[0], shape = RoundedCornerShape(8.dp)) // Màu xanh tương ứng
                            ) {}
                        }

                        // Item 2
                        Box(
                            modifier = Modifier
                                .width(110.dp)
                                .height(70.dp)
                                .background(outerBoxColor, shape = RoundedCornerShape(8.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            // Inner Box (màu xanh)
                            Box(
                                modifier = Modifier
                                    .fillMaxSize(0.9f)
                                    .background(colorsForThisRow[1], shape = RoundedCornerShape(8.dp)) // Màu xanh tương ứng
                            ) {}
                        }

                        // Item 3
                        Box(
                            modifier = Modifier
                                .width(110.dp)
                                .height(70.dp)
                                .background(outerBoxColor, shape = RoundedCornerShape(8.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            // Inner Box (màu xanh)
                            Box(
                                modifier = Modifier
                                    .fillMaxSize(0.9f)
                                    .background(colorsForThisRow[2], shape = RoundedCornerShape(8.dp)) // Màu xanh tương ứng
                            ) {}
                        }
                    }
                }
            }
        }
    }
}

// MÀN HÌNH MỚI: BOXDETAILSCREEN
@Composable
fun BoxDetailScreen(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize()) {
        DetailScreenHeader(navController = navController, title = "Box Layout")
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f) // Để Box nằm giữa màn hình
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Box lớn làm nền
            Box(
                modifier = Modifier
                    .size(300.dp) // Kích thước cố định cho Box lớn
                    .background(Color(0xFFEFEFEF), shape = RoundedCornerShape(12.dp)), // Nền xám nhạt, bo góc
                contentAlignment = Alignment.Center // Căn giữa nội dung mặc định
            ) {
                // Box con 1: TopStart (Màu xanh đậm)
                Box(
                    modifier = Modifier
                        .size(100.dp, 70.dp) // Kích thước nhỏ hơn
                        .background(Color(0xFF6B92F2), shape = RoundedCornerShape(8.dp)) // Màu xanh đậm
                        .align(Alignment.TopStart), // Căn TopStart
                    contentAlignment = Alignment.Center
                ) {
                    Text("TopStart", color = Color.White, fontSize = 14.sp)
                }

                // Box con 2: Center (Màu xanh trung bình)
                Box(
                    modifier = Modifier
                        .size(120.dp, 80.dp) // Kích thước lớn hơn một chút
                        .background(Color(0xFF9ABAEF), shape = RoundedCornerShape(8.dp)) // Màu xanh trung bình
                        .align(Alignment.Center), // Căn chính giữa
                    contentAlignment = Alignment.Center
                ) {
                    Text("Center", color = Color.White, fontSize = 16.sp)
                }

                // Box con 3: BottomEnd (Màu xanh nhạt)
                Box(
                    modifier = Modifier
                        .size(100.dp, 70.dp) // Kích thước nhỏ hơn
                        .background(Color(0xFFD3E0F6), shape = RoundedCornerShape(8.dp)) // Màu xanh nhạt
                        .align(Alignment.BottomEnd), // Căn BottomEnd
                    contentAlignment = Alignment.Center
                ) {
                    Text("BottomEnd", color = Color.Black, fontSize = 14.sp)
                }
            }
        }
    }
}