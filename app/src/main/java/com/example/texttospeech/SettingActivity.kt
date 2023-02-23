package com.example.texttospeech

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.Toast
import com.example.texttospeech.databinding.ActivitySettingBinding

class SettingActivity : AppCompatActivity() {
    var VoiceSpeed = 2
    var Language = "US"
    private lateinit var binding: ActivitySettingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.seekBar.progress = SharedPrefHelper.getSpeed(this)!!.toInt()
        binding.speed.text = binding.seekBar.progress.toString()

        var Lang = binding.dropdownField.text.toString()
        Lang = SharedPrefHelper.getLanguage(this)!!



        saveData()

        var items = listOf("US","CANADA","CHINA","ENGLISH","FRENCH","GERMAN")

        var adapter = ArrayAdapter(this,R.layout.list_item,items)
        binding.dropdownField.setAdapter(adapter)





        binding.seekBar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                Log.d("Seekbar","${p1}")
                VoiceSpeed = p1
                binding.speed.text = p1.toString()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }

        })

        binding.button2.setOnClickListener {
            saveData()
            val intent =  Intent(this@SettingActivity,MainActivity::class.java)
            intent.putExtra("speed",VoiceSpeed)
            intent.putExtra("language",Language)
            startActivity(intent)

        }
    }
    fun saveData() {
        SharedPrefHelper.saveLanguage(this,Language)
        SharedPrefHelper.saveSpeed(this,VoiceSpeed.toString())
    }
}