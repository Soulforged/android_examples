package io.personal.dicardo.notes

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.view.View

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener {
            openAddNote(null)
        }

        var storage = getSharedPreferences(javaClass.`package`.name, Context.MODE_PRIVATE)
        var notesListString = storage.getString(NOTES_LIST, "[]")
        var notesList :Array<Note> = emptyArray()
        if (notesListString != null) {
            notesList = Json.instance.fromJson(notesListString, Array<Note>::class.java)
        }

        var notesListView : RecyclerView = findViewById(R.id.notesView)
        notesListView.adapter = NotesAdapter(notesList, View.OnClickListener {
            val position = it.tag as Int
            val note = notesList[position]
            openAddNote(note)
        })
        notesListView.layoutManager = LinearLayoutManager(this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    fun openAddNote(note :Note?): Boolean {
        var addNoteIntent = Intent(applicationContext, AddNote::class.java)
        addNoteIntent.putExtra(SELECTED_NOTE, note)
        startActivity(addNoteIntent)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            R.id.action_add_note -> openAddNote(null)
            else -> super.onOptionsItemSelected(item)
        }
    }
}
