package github.sun5066.contentresolver.adapter

import android.media.MediaPlayer
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import github.sun5066.contentresolver.R
import github.sun5066.contentresolver.model.MusicVO
import kotlinx.android.synthetic.main.item_recycler.view.*
import java.text.SimpleDateFormat

class MusicRecyclerAdapter : RecyclerView.Adapter<MusicRecyclerAdapter.Holder>() {
    var musicList = mutableListOf<MusicVO>()
    var mediaPlayer: MediaPlayer? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder =
        Holder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_recycler, parent, false)
        )

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val music = musicList[position]
        holder.setMusic(music)
    }

    override fun getItemCount(): Int = musicList.size

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var musicUri: Uri? = null
        init {
            itemView.setOnClickListener {
                if (mediaPlayer != null) {
                    mediaPlayer?.release()
                    mediaPlayer = null
                }
                mediaPlayer = MediaPlayer.create(itemView.context, musicUri)
                mediaPlayer?.start()
            }
        }

        fun setMusic(musicVO: MusicVO) {
            itemView.imageAlbum.setImageURI(musicVO.getAlbumId())
            itemView.textArtist.text = musicVO.artist
            itemView.textTitle.text = musicVO.title

            val duration = SimpleDateFormat("mm:ss").format(musicVO.duration)
            itemView.textDuration.text = duration
            this.musicUri = musicVO.getMusicUri()
        }
    }
}

