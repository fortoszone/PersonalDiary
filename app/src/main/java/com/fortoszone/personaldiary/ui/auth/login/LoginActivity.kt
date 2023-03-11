package com.fortoszone.personaldiary.ui.auth.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.fortoszone.personaldiary.databinding.ActivityLoginBinding
import com.fortoszone.personaldiary.model.remote.request.LoginRequest
import com.fortoszone.personaldiary.model.remote.response.LoginResponse
import com.fortoszone.personaldiary.model.remote.retrofit.DiaryService
import com.fortoszone.personaldiary.model.remote.retrofit.RetrofitClient
import com.fortoszone.personaldiary.ui.auth.register.RegisterActivity
import com.fortoszone.personaldiary.ui.home.HomeActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.tvGoToRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        binding.btnLogin.setOnClickListener {
            val request = LoginRequest()

            request.email = binding.tilEmail.text.toString().trim()
            request.password = binding.tilPassword.text.toString().trim()

            if (request.email?.isEmpty() == true) {
                binding.tilEmail.error = "Email required"
                binding.tilEmail.requestFocus()
                return@setOnClickListener
            }

            if (request.password?.isEmpty() == true) {
                binding.tilPassword.error = "Password required"
                binding.tilPassword.requestFocus()
                return@setOnClickListener
            }

            val intent = Intent(this@LoginActivity, HomeActivity::class.java)
            startActivity(intent)

//            val apiService = RetrofitClient.getInstance().create(DiaryService::class.java)
//            apiService.loginUser(request).enqueue(object :
//                Callback<LoginResponse> {
//                override fun onResponse(
//                    call: Call<LoginResponse>,
//                    response: Response<LoginResponse>
//                ) {
//                    Toast.makeText(
//                        this@LoginActivity,
//                        response.code().toString(),
//                        Toast.LENGTH_SHORT
//                    ).show()
//
//                    if (response.code() == 200) {
//                        Toast.makeText(
//                            this@LoginActivity,
//                            "Login Succeed, username: ${response.body()?.user}",
//                            Toast.LENGTH_SHORT
//                        )
//                            .show()
//
//                        val intent = Intent(this@LoginActivity, HomeActivity::class.java)
//                        startActivity(intent)
//                    }
//                }
//
//                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
//                    Toast.makeText(this@LoginActivity, t.message, Toast.LENGTH_LONG).show()
//                }
//            })
        }
    }
}