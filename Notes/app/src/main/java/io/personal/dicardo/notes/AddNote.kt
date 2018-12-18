package io.personal.dicardo.notes

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.text.InputType
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

import kotlinx.android.synthetic.main.activity_add_note.*
import java.util.*

class AddNote : AppCompatActivity() {

    fun toList() {
        var toListIntent = Intent(applicationContext, MainActivity::class.java)
        startActivity(toListIntent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)
        setSupportActionBar(toolbar)

        var noteInput : TextView = findViewById(R.id.editText)
        noteInput.imeOptions = EditorInfo.IME_ACTION_DONE
        noteInput.setRawInputType(InputType.TYPE_CLASS_TEXT)
        var note = intent.getSerializableExtra(SELECTED_NOTE) as Note
        if (note != null) {
            noteInput.setText(note.text)
        }
        noteInput.setOnEditorActionListener { v, actionId, _ ->
            if (EditorInfo.IME_ACTION_DONE == actionId) {
                var storage = getSharedPreferences(javaClass.`package`.name, Context.MODE_PRIVATE)
                var existingNotes = storage.getString(NOTES_LIST, "[]")
                var notesList :List<Note> = emptyList()
                val notesType = object : TypeToken<List<Note>>() {}.type
                if (existingNotes != null) notesList = Json.instance.fromJson(existingNotes, notesType)
                var note = Note(noteInput.text.toString(), UUID.randomUUID().toString(), Date())
                var newList = notesList.plus(note)
                var jsonNotes = Json.instance.toJson(newList)
                storage.edit().putString(NOTES_LIST, jsonNotes).apply()
                toList()
                true
            }
            false
        }
    }

}
