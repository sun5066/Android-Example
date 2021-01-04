package github.sun5066.firebasechatexample

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInResult
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.common.util.JsonUtils
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import org.json.JSONObject


class SignInActivity : AppCompatActivity(), GoogleApiClient.OnConnectionFailedListener,
    View.OnClickListener {

    companion object {
        val TAG = "SignInActivity"
    }

    private val RC_SIGN_IN: Int = 1000
    private lateinit var mFirebaseAuth: FirebaseAuth
    private lateinit var mGoogleApiClient: GoogleApiClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        mFirebaseAuth = FirebaseAuth.getInstance()
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        mGoogleApiClient = GoogleApiClient.Builder(this)
            .enableAutoManage(this, this)
            .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
            .build()

        findViewById<SignInButton>(R.id.btn_sign_in).setOnClickListener(this)
    }

    override fun onConnectionFailed(p0: ConnectionResult) {
    }

    override fun onClick(v: View?) {
        val signInIntent: Intent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient)
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val result: GoogleSignInResult? = Auth.GoogleSignInApi.getSignInResultFromIntent(data)
            Log.d(TAG, "${result!!.signInAccount}")

            if (result!!.isSuccess) {
                val account: GoogleSignInAccount? = result.signInAccount
//                val account = GoogleSignIn.getSignedInAccountFromIntent(data).getResult(ApiException::class.java)
                this.firebaseAuthWithGoogle(account)
            } else {
                Toast.makeText(this, "Google Sign-In failed", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun firebaseAuthWithGoogle(account: GoogleSignInAccount?) {
        val credential = GoogleAuthProvider.getCredential(account?.idToken, null)
        mFirebaseAuth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (!task.isSuccessful) {
                    Toast.makeText(this, "Authentication failed.", Toast.LENGTH_SHORT).show()
                } else {
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }
            }
    }
}

