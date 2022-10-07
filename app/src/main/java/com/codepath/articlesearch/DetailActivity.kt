package com.codepath.articlesearch

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

private const val TAG = "DetailActivity"

class DetailActivity : AppCompatActivity() {
    private lateinit var mediaImageView: ImageView
    private lateinit var titleTextView: TextView
    private lateinit var bylineTextView: TextView
    private lateinit var abstractTextView: TextView
    private lateinit var genderTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // TODO: Find the views for the screen
        mediaImageView = findViewById(R.id.mediaImage)
        titleTextView = findViewById(R.id.mediaTitle)
        bylineTextView = findViewById(R.id.mediaByline)
        abstractTextView = findViewById(R.id.mediaAbstract)
        genderTextView = findViewById(R.id.genderView)

        // TODO: Get the extra from the Intent
        val article = intent.getSerializableExtra(ARTICLE_EXTRA) as Article

        // TODO: Set the title, byline, and abstract information from the article
        titleTextView.text = article.name
        bylineTextView.text = if (article.adult) "Adult Actor" else "Non-Adult Actor"
        abstractTextView.text = "Known for: " + article.known_for_department
        genderTextView.text = if (article.gender == "1") "Female" else "Male"

        // TODO: Load the media image
        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500/" + article.profile_path)
            .into(mediaImageView)
    }
}