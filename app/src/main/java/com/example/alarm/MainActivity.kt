package com.example.alarm

import android.os.Build
import android.app.NotificationManager
import android.app.NotificationChannel
import android.content.Context
import androidx.core.app.NotificationCompat
import android.widget.Button
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var button = findViewById(R.id.button) as Button
        button.setOnClickListener {
            var builder = NotificationCompat.Builder(this, "MY_channel")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("알림 제목")
                .setContentText("알림 내용")

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) { // 오레오 버전 이후에는 알림을 받을 때 채널이 필요
                val channel_id = "MY_channel" // 알림을 받을 채널 id 설정
                val channel_name = "채널이름" // 채널 이름 설정
                val descriptionText = "설명글" // 채널 설명글 설정
                val importance = NotificationManager.IMPORTANCE_DEFAULT // 알림 우선순위 설정
                val channel = NotificationChannel(channel_id, channel_name, importance).apply {
                    description = descriptionText
                }

                // 만든 채널 정보를 시스템에 등록
                val notificationManager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                notificationManager.createNotificationChannel(channel)

                // 알림 표시: 알림의 고유 ID(ex: 1002), 알림 결과
                notificationManager.notify(1002, builder.build())
            }
        }
    }
}