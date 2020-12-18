package github.sun5066.contentresolver.model

import android.net.Uri
import android.provider.MediaStore

class MusicVO(
    var id: String,
    var title: String?,
    var artist: String?,
    var albumId: String?,
    var duration: Long?
) {
    fun getMusicUri(): Uri = Uri.withAppendedPath(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, id)

    fun getAlbumId(): Uri = Uri.parse("content://media/external/audio/albumart/$albumId")
}