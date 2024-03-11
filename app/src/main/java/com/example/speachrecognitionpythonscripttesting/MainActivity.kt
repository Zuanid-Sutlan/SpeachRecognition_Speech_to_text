package com.example.speachrecognitionpythonscripttesting

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.assemblyai.api.AssemblyAI
import com.assemblyai.api.resources.transcripts.types.Transcript
import com.assemblyai.api.resources.transcripts.types.TranscriptOptionalParams
import com.assemblyai.api.resources.transcripts.types.TranscriptStatus


//external fun transcribeAudio(audioFilePath: String): String

class MainActivity : AppCompatActivity() {

    private var backgroundThread: Thread? = null

    private val API_KEY = "1383af52681541bab250ba0170aef336"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
       /* ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/


        /*val audioFilePath = "path/to/your/audio/file"
        val transcription = transcribeAudio(audioFilePath)*/

        findViewById<Button>(R.id.button2).setOnClickListener {
            startActivity(Intent(this, AudioRecorderActivity::class.java))
        }

        findViewById<Button>(R.id.button3).setOnClickListener {
            startActivity(Intent(this, MicRecognitionActivity::class.java))
        }

//        lateinit var transcript: Transcript

        backgroundThread = Thread{
            val client = AssemblyAI.builder()
                .apiKey(API_KEY)
                .build()

            val audioUrl =
                "https://storage.googleapis.com/aai-web-samples/5_common_sports_injuries.mp3"

            val params = TranscriptOptionalParams.builder()
                .speakerLabels(true)
                .build()

           val transcript = client.transcripts().transcribe(audioUrl, params)

            Log.i("onCreate: ", transcript.text.toString())

            this.runOnUiThread {
                findViewById<Button>(R.id.button).setOnClickListener {
                    findViewById<TextView>(R.id.text).text = transcript.text.toString()
                }

            }

        }.apply { start() }








    }

    override fun onDestroy() {
        super.onDestroy()
        backgroundThread?.interrupt()
    }

    /*companion object {
        init {
            System.loadLibrary("transcriber")
        }
    }*/
}