package com.and.`is`.ptn290625

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions

class DashboardActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        auth = FirebaseAuth.getInstance()
        val user: FirebaseUser? = auth.currentUser

        val profileImage = findViewById<ImageView>(R.id.profileImage)
        val welcomeText = findViewById<TextView>(R.id.welcomeText)
        val logoutButton = findViewById<Button>(R.id.logoutBtn)

        if (user != null) {
            welcomeText.text = "Welcome, ${user.displayName ?: user.email}"

            // Load photo URL using Glide
            Glide.with(this)
                .load(user.photoUrl)
                .placeholder(R.drawable.ic_person) // fallback icon
                .circleCrop()
                .into(profileImage)
        } else {
            welcomeText.text = "Welcome!"
        }

        logoutButton.setOnClickListener {
            auth.signOut()

            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).build()
            val googleSignInClient = GoogleSignIn.getClient(this, gso)
            googleSignInClient.signOut().addOnCompleteListener {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }

    }
}
