package github.sun5066.firebasechatexample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import de.hdodenhof.circleimageview.CircleImageView

class MainActivity : AppCompatActivity(), GoogleApiClient.OnConnectionFailedListener {
    private lateinit var mUsername: String
    private lateinit var mPhotoUrl: String

    inner class MessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        private var messageImageView: ImageView = itemView.findViewById(R.id.messageImageView)
        private var messageTextView: TextView = itemView.findViewById(R.id.messageTextView)
        private var photoImageView: CircleImageView = itemView.findViewById(R.id.photoImageView)
    }

    private var mFireBaseAuth: FirebaseAuth? = null
    private var mFireBaseUser: FirebaseUser? = null

    private lateinit var mMessageRecyclerView: RecyclerView
    private lateinit var mGoogleApiClient: GoogleApiClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mMessageRecyclerView = findViewById(R.id.message_recycler_view)

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
    }

    override fun onConnectionFailed(p0: ConnectionResult) {

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
}
