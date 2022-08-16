package com.example.mz

import android.app.Application
import com.kakao.sdk.common.KakaoSdk

class GlobalApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        KakaoSdk.init(this,"aae8f56c41bde35d0a4db86fd0abbf78")
    }

}