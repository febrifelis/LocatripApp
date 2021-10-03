package com.fbrproject.locatripapp.home.setting

import android.os.Bundle
import android.text.InputType
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.fbrproject.locatrip.R
import com.fbrproject.locatrip.utils.Preferences
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_edit_profile.*

class EditProfileActivity : AppCompatActivity() {
    private lateinit var preferences: Preferences
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        preferences = Preferences(this)
        database = FirebaseDatabase.getInstance().getReference("User")
            .child(preferences.getValues("user") ?: "")

        imageView3.setOnClickListener { onBackPressed() }

        Glide.with(this)
            .load(preferences.getValues("url"))
            .apply(RequestOptions.circleCropTransform())
            .into(ivProfile)

        et_nama.setText(preferences.getValues("nama"))
        et_username.setText(preferences.getValues("user"))
        et_username.inputType = InputType.TYPE_NULL
        et_email.setText(preferences.getValues("email"))

        btn_submit.setOnClickListener {
            val nama = et_nama.text.trim().toString()
            val email = et_email.text.trim().toString()
            val password = et_password.text.trim().toString()

            if (nama.isEmpty() || email.isEmpty()) {
                Toast.makeText(this, "Nama dan email tidak boleh kosong!", Toast.LENGTH_LONG).show()
            } else {
                submit(nama, email, password)
            }
        }
    }

    private fun submit(nama: String, email: String, password: String) {
        if (nama.isNotEmpty())
            database.child("nama").setValue(nama)
                .addOnSuccessListener {
                    preferences.setValues("nama", nama)
                    Toast.makeText(this, "Berhasil mengganti nama!", Toast.LENGTH_SHORT).show()
                }

        if (email.isNotEmpty())
            database.child("email").setValue(email)
                .addOnSuccessListener {
                    preferences.setValues("email", email)
                    Toast.makeText(this, "Berhasil mengganti email!", Toast.LENGTH_SHORT).show()
                }

        if (password.isNotEmpty())
            database.child("password").setValue(password)
                .addOnSuccessListener {
                    Toast.makeText(this, "Berhasil mengganti password!", Toast.LENGTH_SHORT).show()
                }
    }
}