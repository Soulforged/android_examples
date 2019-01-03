package io.personal.dicardo.myinstagram

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class NewPost : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_post)

        val newPostImg : ImageView = findViewById(R.id.newPostImg)

        newPostImg.setImageURI(intent.data)
    }
}
