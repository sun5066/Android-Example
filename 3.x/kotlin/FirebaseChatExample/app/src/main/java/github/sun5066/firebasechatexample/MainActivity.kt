package github.sun5066.firebasechatexample

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import de.hdodenhof.circleimageview.CircleImageView


class MainActivity : AppCompatActivity(), GoogleApiClient.OnConnectionFailedListener {
    companion object {
        val TAG = "MainActivity"
        val MESSAGES = "messages"
    }

    private lateinit var mFirebaseAdapter: FirebaseRecyclerAdapter<ChatMessage, MessageViewHolder>
    private lateinit var mFirebaseDatabaseReference: DatabaseReference
    private lateinit var mMsgEditText: EditText

    private lateinit var mUsername: String
    private lateinit var mPhotoUrl: String

    private var mFireBaseAuth: FirebaseAuth? = null
    private var mFireBaseUser: FirebaseUser? = null

    private lateinit var mMessageRecyclerView: RecyclerView
    private lateinit var mGoogleApiClient: GoogleApiClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mFirebaseDatabaseReference = FirebaseDatabase.getInstance().reference
        mMsgEditText = findViewById(R.id.message_edit)
        mMessageRecyclerView = findViewById(R.id.message_recycler_view)

        findViewById<Button>(R.id.btn_send).setOnClickListener {
            val chatMessage =
                ChatMessage(text = "${mMsgEditText.text}", name = mUsername, photo = mPhotoUrl)
            mFirebaseDatabaseReference.child(MESSAGES)
                .push() // ChatMessage 에 자동으로 id가 생성됨
                .setValue(chatMessage)

            mMsgEditText.setText("")
        }

        mGoogleApiClient = GoogleApiClient.Builder(this)
            .enableAutoManage(this, this)
            .addApi(Auth.GOOGLE_SIGN_IN_API)
            .build()

        mFireBaseAuth = FirebaseAuth.getInstance()
        mFireBaseUser = mFireBaseAuth!!.currentUser
        if (mFireBaseUser == null) {
            startActivity(Intent(this, SignInActivity::class.java))
            finish()
            return
        } else {
            mUsername = mFireBaseUser!!.displayName!!
            if (mFireBaseUser!!.photoUrl != null) {
                mPhotoUrl = mFireBaseUser!!.photoUrl.toString()
            }
        }

        val query = mFirebaseDatabaseReference.child(MESSAGES)
        val options: FirebaseRecyclerOptions<ChatMessage> =
            FirebaseRecyclerOptions.Builder<ChatMessage>()
                .setQuery(query, ChatMessage::class.java)
                .build()

        mFirebaseAdapter =
            object : FirebaseRecyclerAdapter<ChatMessage, MessageViewHolder>(options) {
                override fun onCreateViewHolder(
                    parent: ViewGroup,
                    viewType: Int
                ): MessageViewHolder {
                    val view = LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_message, parent, false)
                    return MessageViewHolder(view)
                }

                override fun onBindViewHolder(
                    holder: MessageViewHolder,
                    position: Int,
                    model: ChatMessage
                ) {
                    Log.d(TAG, "------------------------------------------------")
                    holder.messageTextView.text = model.text
                    holder.nameTextView.text = model.name

                    Log.d(TAG, model.text)
                    if (model.photo == null) {
                        holder.photoImageView.setImageDrawable(
                            ContextCompat.getDrawable(
                                this@MainActivity,
                                R.drawable.ic_account_circle_black_24dp
                            )
                        )
                    } else {
                        Glide.with(applicationContext)
                            .load(model.photo)
                            .into(holder.photoImageView)
                    }
                }
            }

        mMessageRecyclerView.layoutManager = LinearLayoutManager(this)
        mMessageRecyclerView.adapter = mFirebaseAdapter
    }

    override fun onStart() {
        super.onStart()
        mFirebaseAdapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        mFirebaseAdapter.stopListening()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.sign_out_menu -> {
                mFireBaseAuth?.signOut()
                Auth.GoogleSignInApi.signOut(mGoogleApiClient)
                mUsername = ""
                startActivity(Intent(this, SignInActivity::class.java))
                true
            }
            else -> false
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onConnectionFailed(p0: ConnectionResult) {

    }

    inner class MessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        var messageImageView: ImageView = itemView.findViewById(R.id.messageImageView)
        var photoImageView: CircleImageView = itemView.findViewById(R.id.photoImageView)
        var messageTextView: TextView = itemView.findViewById(R.id.messageTextView)
    }
}
