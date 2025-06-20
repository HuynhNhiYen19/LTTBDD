package com.example.demouthsmarttask

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.demouthsmarttask.databinding.ActivityProfileBinding
import com.google.firebase.auth.FirebaseAuth

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. Khởi tạo Firebase Auth
        firebaseAuth = FirebaseAuth.getInstance()

        // 2. Lấy thông tin người dùng hiện tại từ Firebase
        val currentUser = firebaseAuth.currentUser

        // 3. Hiển thị thông tin người dùng lên UI
        if (currentUser != null) {
            // Sử dụng dữ liệu từ Firebase (nếu có) hoặc đặt cố định như trong hình
            binding.nameEditText.setText(currentUser.displayName ?: "Melissa Peters")
            binding.emailEditText.setText(currentUser.email ?: "melpeters@gmail.com")

            // Load ảnh profile bằng Glide, fallback về ảnh cố định nếu không có
            Glide.with(this)
                .load(currentUser.photoUrl ?: R.drawable.profile_pic_melissa) // Ảnh cố định nếu không có photoUrl
                .placeholder(R.drawable.profile_pic_melissa)
                .error(R.drawable.profile_pic_melissa)
                .into(binding.profileImageView)
        } else {
            // Nếu không có người dùng, đặt dữ liệu cố định và thông báo
            binding.nameEditText.setText("Melissa Peters")
            binding.emailEditText.setText("melpeters@gmail.com")
            Glide.with(this)
                .load(R.drawable.profile_pic_melissa)
                .into(binding.profileImageView)
            Toast.makeText(this, "Không có người dùng đăng nhập, hiển thị dữ liệu mẫu.", Toast.LENGTH_SHORT).show()
        }

        // 4. Đặt ngày sinh cố định như trong hình
        binding.dobEditText.setText("23/05/1995")

        // 5. Xử lý sự kiện click cho nút Back trên Top Bar
        binding.backButton.setOnClickListener {
            returnToLoginActivity()
        }

        // 6. Nút dưới cùng là "Back"
        binding.profileBackButton.text = "Back"
        binding.profileBackButton.setOnClickListener {
            returnToLoginActivity()
        }

        // 7. Xử lý sự kiện click cho Camera Icon trên ảnh profile
        binding.cameraIcon.setOnClickListener {
            Toast.makeText(this, "Chức năng đổi ảnh chưa được triển khai!", Toast.LENGTH_SHORT).show()
            // TODO: Thêm logic để mở camera hoặc chọn ảnh từ thư viện
        }
    }

    // Xử lý nút Back của hệ thống
    override fun onBackPressed() {
        returnToLoginActivity()
    }

    // Hàm chung để quay về LoginActivity
    private fun returnToLoginActivity() {
        val intent = Intent(this, LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        finish()
        Toast.makeText(this, "Đã quay lại màn hình đăng nhập", Toast.LENGTH_SHORT).show()
    }
}