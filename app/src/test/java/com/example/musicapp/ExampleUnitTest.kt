package com.example.musicapp


import android.media.MediaPlayer
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

class MainActivityUnitTest {

    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var mainActivity: MainActivity

    @Before
    fun setUp() {
        mediaPlayer = mock(MediaPlayer::class.java)
        mainActivity = MainActivity()
        mainActivity.mediaPlayer = mediaPlayer
    }

    @After
    fun tearDown() {
        mainActivity.mediaPlayer.release()
    }

    @Test
    fun playTrack_whenAlreadyPlaying_pausesMediaPlayer() {
        `when`(mediaPlayer.isPlaying).thenReturn(true)

        mainActivity.playTrack("http://testurl.com/test.mp3")

        verify(mediaPlayer).pause()
    }

    @Test
    fun playTrack_whenNotPlaying_startsMediaPlayer() {
        `when`(mediaPlayer.isPlaying).thenReturn(false)

        mainActivity.playTrack("http://testurl.com/test.mp3")

        verify(mediaPlayer).prepareAsync()
    }

    @Test
    fun stopTrack_releasesMediaPlayer() {
        mainActivity.stopTrack()

        verify(mediaPlayer).release()
    }

    @Test
    fun skipToNextTrack_whenNextTrackExists_playsNextTrack() {
        mainActivity.tracks = listOf(
            Track(name = "Track 1", audioUrl = "http://testurl.com/track1.mp3"),
            Track(name = "Track 2", audioUrl = "http://testurl.com/track2.mp3")
        )
        mainActivity.currentTrackIndex = 0

        mainActivity.skipToNextTrack()

        assert(mainActivity.currentTrackIndex == 1)
        verify(mediaPlayer).release()
    }

    @Test
    fun skipToNextTrack_whenAtLastTrack_loopsToFirstTrack() {
        mainActivity.tracks = listOf(
            Track(name = "Track 1", audioUrl = "http://testurl.com/track1.mp3"),
            Track(name = "Track 2", audioUrl = "http://testurl.com/track2.mp3")
        )
        mainActivity.currentTrackIndex = 1

        mainActivity.skipToNextTrack()

        assert(mainActivity.currentTrackIndex == 0)
        verify(mediaPlayer).release()
    }
}
