package io.personal.dicardo.myinstagram

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.parse.ParseQuery
import com.parse.ParseUser

class UserList : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var viewAdapter: RecyclerView.Adapter<*>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_list)

        viewManager = LinearLayoutManager(this)

        recyclerView = findViewById<RecyclerView>(R.id.userList).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
        }

        ParseQuery.getQuery(ParseUser::class.java).whereNotEqualTo("username", ParseUser.getCurrentUser().username)
            .findInBackground { result, e ->
            if (e != null) {
                Toast.makeText(applicationContext, e.localizedMessage, Toast.LENGTH_SHORT).show()
            }
            viewAdapter = UserAdapter(result)
            recyclerView.adapter = viewAdapter
        }
    }
}

class UserAdapter(private val dataset: List<ParseUser>) : RecyclerView.Adapter<UserAdapter.ParseUserHolder>() {
    class ParseUserHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ParseUserHolder {
        val textView = LayoutInflater.from(parent.context)
            .inflate(android.R.layout.simple_list_item_1, parent, false) as TextView
        return ParseUserHolder(textView)
    }

    override fun getItemCount() = dataset.size

    override fun onBindViewHolder(holder: ParseUserHolder, pos: Int) {
        holder.textView.text = dataset[pos].username
    }

}
