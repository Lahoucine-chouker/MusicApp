package com.example.musicapp


// SongAdapter.kt
import android.content.Context
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class SongAdapter(private val context: Context, private val songs: List<Song>) :
    RecyclerView.Adapter<SongAdapter.SongViewHolder>() {

    private var mediaPlayer: MediaPlayer? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_song, parent, false)
        return SongViewHolder(view)
    }

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        val song = songs[position]
        holder.bind(song)
    }

    override fun getItemCount(): Int = songs.size

    inner class SongViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val songTitle: TextView = itemView.findViewById(R.id.songTitle)
        private val songArtist: TextView = itemView.findViewById(R.id.songArtist)
        private val songArtwork: ImageView = itemView.findViewById(R.id.songArtwork)
        private val playButton: ImageButton = itemView.findViewById(R.id.playButton)

        fun bind(song: Song) {
            songTitle.text = song.title
            songArtist.text = song.artist
            Glide.with(context).load(song.artworkUrl).into(songArtwork)

            // Set up play button functionality
            playButton.setOnClickListener {
                playPauseSong(song)
            }
        }

        private fun playPauseSong(song: Song) {
            mediaPlayer?.release()  // Release any previous MediaPlayer instance

            mediaPlayer = MediaPlayer().apply {
                setDataSource(song.songUrl)
                prepare()
                start()
            }

            playButton.setImageResource(R.drawable.pause) // Change to pause icon while playing

            mediaPlayer?.setOnCompletionListener {
                playButton.setImageResource(R.drawable.next) // Revert to play icon when finished
            }
        }
    }
}
