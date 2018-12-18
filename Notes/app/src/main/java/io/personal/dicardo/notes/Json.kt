package io.personal.dicardo.notes

import com.google.gson.Gson
import com.google.gson.GsonBuilder

class Json {
    companion object {
        val instance : Gson by lazy {
            GsonBuilder().create()
        }
    }
}