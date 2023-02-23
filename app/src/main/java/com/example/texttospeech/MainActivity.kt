package com.example.texttospeech

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.Toast
import com.example.texttospeech.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {
    var VoiceSpeed = 2
    var Language = "US"
    private lateinit var binding: ActivityMainBinding
    private lateinit var texttospeech : TextToSpeech
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        VoiceSpeed = SharedPrefHelper.getSpeed(this)!!.toInt()
        Language = SharedPrefHelper.getLanguage(this)!!


        try {
            VoiceSpeed = intent.getIntExtra("speed",1)
            Language = intent.getStringExtra("language")!!
        }
        catch (I:java.lang.Exception) {

        }


        binding.ivSetting.setOnClickListener {
            val intent = Intent(this,SettingActivity::class.java)
            startActivity(intent)
        }


        val Lang = "Locale.${Language}"
        binding.btnMain.setOnClickListener {
            var usertext = binding.etInput.text.toString()
            texttospeech = TextToSpeech(applicationContext,TextToSpeech.OnInitListener {
                if (it == TextToSpeech.SUCCESS) {
                    texttospeech.language = Locale.forLanguageTag(Language)
                    Toast.makeText(this, "${Lang}", Toast.LENGTH_SHORT).show()
                    texttospeech.setSpeechRate(VoiceSpeed.toFloat())
                    texttospeech.speak(usertext,TextToSpeech.QUEUE_ADD,null)
                }
            })
        }
    }
}