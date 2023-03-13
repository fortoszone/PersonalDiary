package com.fortoszone.personaldiary.ui.auth.register

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.fortoszone.personaldiary.databinding.ActivityRegisterBinding
import com.fortoszone.personaldiary.model.local.User
import com.fortoszone.personaldiary.model.remote.response.RegisterResponse
import com.fortoszone.personaldiary.model.remote.retrofit.DiaryService
import com.fortoszone.personaldiary.model.remote.retrofit.RetrofitClient
import com.fortoszone.personaldiary.ui.auth.login.LoginActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.btnCreate.setOnClickListener {
            val email = binding.tilEmail.text.toString().trim()
            val username = binding.tilUsername.text.toString().trim()
            val password = binding.tilPassword.text.toString().trim()
            val passwordConfirm = binding.tilConfirmPassword.text.toString().trim()

            if (email.isEmpty()) {
                binding.tilEmail.error = "Email required"
                binding.tilEmail.requestFocus()
            }

            if (!(email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches())) {
                binding.tilEmail.error = "Email is not valid"
                binding.tilEmail.requestFocus()
                return@setOnClickListener
            }

            if (username.isEmpty()) {
                binding.tilPassword.error = "Username required"
                binding.tilPassword.requestFocus()
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                binding.tilPassword.error = "Password required"
                binding.tilPassword.requestFocus()
                return@setOnClickListener
            }

            if (passwordConfirm.isEmpty()) {
                binding.tilConfirmPassword.error = "Password confirmation required"
                binding.tilConfirmPassword.requestFocus()
                return@setOnClickListener
            } else if (password != passwordConfirm) {
                binding.tilConfirmPassword.error = "Password doesn't match"
                binding.tilConfirmPassword.requestFocus()
                return@setOnClickListener
            }

            val apiService = RetrofitClient.getInstance().create(DiaryService::class.java)
            apiService.registerUser(email, username, password, passwordConfirm).enqueue(object :
                Callback<RegisterResponse> {
                override fun onResponse(
                    call: Call<RegisterResponse>,
                    response: Response<RegisterResponse>
                ) {
                    if (response.code() == 201) {
                        Toast.makeText(this@RegisterActivity, "Account Created", Toast.LENGTH_SHORT)
                            .show()
                        val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                        startActivity(intent)
                    } else if (response.code() == 422) {
                        Toast.makeText(
                            this@RegisterActivity, response.message(),
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Toast.makeText(
                            this@RegisterActivity,
                            response.message().toString(),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                    Toast.makeText(this@RegisterActivity, t.message, Toast.LENGTH_LONG).show()
                }
            })
        }
    }
}