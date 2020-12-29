package github.sun5066.firebasechatexample

data class ChatMessage(
    val id: String,
    val text: String,
    val name: String,
    val photo: String,
    val imageUrl: String
)