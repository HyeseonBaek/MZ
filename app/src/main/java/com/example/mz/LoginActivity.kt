package com.example.mz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.mz.databinding.ActivityLoginBinding
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.kakaoLoginBtn.setOnClickListener {
            val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
                if (error != null) {
                    Log.e("fail", "카카오계정으로 로그인 실패", error)
                } else if (token != null) {
                    Log.i("fail", "카카오계정으로 로그인 성공 ${token.accessToken}")
                }
            }
            // kakao login에는 카카오톡, 카카오계정 로그인 두가지
            // 카카오톡 설치되어 있으면 이를 우선으로
            if(UserApiClient.instance.isKakaoTalkLoginAvailable(this)){
                UserApiClient.instance.loginWithKakaoTalk(this){token, error ->
                    if(error != null){
                        Log.e("fail", "카카오톡으로 로그인 실패",error)
                        //에러의 원인이 사용자의 취소라면 의도적 취소이므로 바로 로그인 취소
                        if(error is ClientError && error.reason == ClientErrorCause.Cancelled){
                            return@loginWithKakaoTalk
                        }
                        //위의 원인 아니면 카카오톡 자체에 등록된 계정이없다는뜻, 카카오계정으로 로그인 시도
                        UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
                    }
                    else if(token != null){
                        // 에러 없고 토큰 정상
                        Log.i("Success", "카카오톡으로 로그인 성공")
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                        finish()
                    }
                }

            } else {
                UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
            }

        }





    }
}