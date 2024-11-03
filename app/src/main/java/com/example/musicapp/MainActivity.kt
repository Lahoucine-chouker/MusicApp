package com.example.musicapp

// MainActivity.kt
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var playPauseButton: FloatingActionButton
    private val songs = listOf(
        Song("1", "Death Bed", "Powfu", "https://samplesongs.netlify.app/album-arts/death-bed.jpg", "https://samplesongs.netlify.app/Death%20Bed.mp3"),
        Song("2", "Bad Liar", "Imagine Dragons", "https://samplesongs.netlify.app/album-arts/bad-liar.jpg", "https://samplesongs.netlify.app/Bad%20Liar.mp3"),
        Song("3", "Faded", "Alan Walker", "https://samplesongs.netlify.app/album-arts/faded.jpg", "https://samplesongs.netlify.app/Faded.mp3"),
        Song("4", "Hate Me", "Ellie Goulding", "https://samplesongs.netlify.app/album-arts/hate-me.jpg", "https://samplesongs.netlify.app/Hate%20Me.mp3"),
        Song("5", "Solo", "Clean Bandit", "https://samplesongs.netlify.app/album-arts/solo.jpg", "https://samplesongs.netlify.app/Solo.mp3"),
        Song("6", "Without Me", "Halsey", "https://samplesongs.netlify.app/album-arts/without-me.jpg", "https://samplesongs.netlify.app/Without%20Me.mp3")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize RecyclerView
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = SongAdapter(this, songs)

        // Set up Play/Pause button
        playPauseButton = findViewById(R.id.fabPlayPause)
        playPauseButton.setOnClickListener {
            togglePlayPause()
        }
    }

    private fun togglePlayPause() {
        // Logic to toggle play/pause
        // Update button icon based on playback state
    }
}
