package com.example.check11

import android.R
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import com.example.check11.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    lateinit var binding : ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.login.setOnClickListener {
            val intent = Intent(this@LoginActivity, MainActivity::class.java)
            val ok = binding.userId.text.toString()
            val ok2 = binding.userPw.text.toString()
            if(ok=="admin"){
                if(ok2=="1234"){
                    Toast.makeText(this@LoginActivity,"환영합니다.ㅁ", Toast.LENGTH_SHORT ).show()
                    startActivity(intent)
                }else{
                    Toast.makeText(this@LoginActivity,"비밀번호를 잘 못 입력하셨습니다.", Toast.LENGTH_SHORT ).show()
                }
            }else{
                Toast.makeText(this@LoginActivity,"잘못된 아이디 입니다.", Toast.LENGTH_SHORT ).show()
            }
        }

        binding.signUp.setOnClickListener{
            val intent2 = Intent(this@LoginActivity, SignUpActivity::class.java)
            startActivity(intent2)
        }
    }
}