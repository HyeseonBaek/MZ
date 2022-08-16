package com.example.mz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kakao.sdk.common.util.Utility
import android.util.Log
import com.example.mz.databinding.ActivityMainBinding

// 여름 프로젝트 청년정책 활용정보 제공 어플리케이션 220809~
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val keyHash = Utility.getKeyHash(this)//onCreate 안에 입력해주자
        Log.d("Hash", keyHash)
    }
}

