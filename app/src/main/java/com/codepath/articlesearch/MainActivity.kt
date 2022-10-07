package com.codepath.articlesearch

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.articlesearch.databinding.ActivityMainBinding
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import kotlinx.serialization.json.Json
import okhttp3.Headers
import org.json.JSONException

fun createJson() = Json {
    isLenient = true
    ignoreUnknownKeys = true
    useAlternativeNames = false
}

private const val TAG = "MainActivity/"
//private const val SEARCH_API_KEY = "a07e22bc18f5cb106bfe4cc1f83ad8ed"//BuildConfig.API_KEY
//private const val ARTICLE_SEARCH_URL = "https://api.themoviedb.org/3/person/popular?api-key=${SEARCH_API_KEY}&language=en-US"
private const val ARTICLE_SEARCH_URL = "https://api.themoviedb.org/3/person/popular?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed"

class MainActivity : AppCompatActivity() {
    private val articles = mutableListOf<Article>()
    private lateinit var articlesRecyclerView: RecyclerView
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        articlesRecyclerView = findViewById(R.id.articles)
        // TODO: Set up ArticleAdapter with articles
        val articleAdapter = ArticleAdapter(this, articles)
        articlesRecyclerView.adapter = articleAdapter

        articlesRecyclerView.layoutManager = GridLayoutManager(this, 2)
 /*
        LinearLayoutManager(this, ).also {
            val dividerItemDecoration = DividerItemDecoration(this, it.orientation)
            articlesRecyclerView.addItemDecoration(dividerItemDecoration)
        }*/

        val client = AsyncHttpClient()
        client.get(ARTICLE_SEARCH_URL, object : JsonHttpResponseHandler() {
            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                response: String?,
                throwable: Throwable?
            ) {
                Log.e(TAG, "Failed to fetch articles: $statusCode")
            }

            override fun onSuccess(statusCode: Int, headers: Headers, json: JSON) {
                Log.i(TAG, "Successfully fetched articles: $json")
                try {
                    // TODO: Create the parsedJSON
                    val parsedJson = createJson().decodeFromString(
                        BaseResponse.serializer(),
                        json.jsonObject.toString()
                    )

                    // TODO: Do something with the returned json (contains article information)
                    parsedJson.results?.let { list ->
                        articles.addAll(list)
                    }

                    // TODO: Save the articles and reload the screen
                    articleAdapter.notifyDataSetChanged()

                } catch (e: JSONException) {
                    Log.e(TAG, "Exception: $e")
                }
            }

        })

    }
}