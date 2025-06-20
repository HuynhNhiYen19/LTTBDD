package com.example.demouthsmarttask

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.demouthsmarttask.databinding.ActivityLoginBinding // <-- Đảm bảo dòng này có
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding // <-- Đảm bảo dòng này có
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var signInLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater) // <-- Đảm bảo dòng này có
        setContentView(binding.root)

        // 1. Khởi tạo Firebase Auth
        firebaseAuth = FirebaseAuth.getInstance()

        // 2. Cấu hình Google Sign In Options (GSO)
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        // 3. Xây dựng GoogleSignInClient
        googleSignInClient = GoogleSignIn.getClient(this, gso)

        // 4. Khởi tạo ActivityResultLauncher cho kết quả đăng nhập
        signInLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            try {
                val account = task.getResult(ApiException::class.java)!!
                Log.d("LoginActivity", "firebaseAuthWithGoogle:" + account.id)
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                Log.w("LoginActivity", "Google sign in failed", e)
                Toast.makeText(this, "Đăng nhập Google thất bại: ${e.statusCode}", Toast.LENGTH_SHORT).show()
            }
        }

        // 5. Xử lý sự kiện click cho nút "SIGN IN WITH GOOGLE"
        binding.signInButtonCustom.setOnClickListener { // <-- Đảm bảo dòng này không bị lỗi
            signInGoogle()
        }
    }

    // Hàm bắt đầu quá trình đăng nhập Google - ĐÃ CẬP NHẬT ĐỂ LUÔN ĐĂNG XUẤT TRƯỚC
    private fun signInGoogle() {
        googleSignInClient.signOut().addOnCompleteListener(this) {
            val signInIntent = googleSignInClient.signInIntent
            signInLauncher.launch(signInIntent)
        }
    }

    // Hàm xác thực ID Token của Google với Firebase
    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        firebaseAuth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d("LoginActivity", "signInWithCredential:success")
                    val user = firebaseAuth.currentUser
                    updateUI(user)
                } else {
                    Log.w("LoginActivity", "signInWithCredential:failure", task.exception)
                    Toast.makeText(this, "Xác thực Firebase thất bại.", Toast.LENGTH_SHORT).show()
                    updateUI(null)
                }
            }
    }

    // Hàm chuyển sang ProfileActivity sau khi đăng nhập thành công (chỉ khi có người dùng)
    private fun updateUI(user: com.google.firebase.auth.FirebaseUser?) {
        if (user != null) {
            val intent = Intent(this, ProfileActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
            finish()
        }
    }
}