package org.drulabs.myshelf.data

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.drulabs.myshelf.utils.PREFS_NAME
import org.drulabs.myshelf.utils.readRawResource

class DataProviderImpl(private val context: Context) : DataProvider {

    // FIXME-TASK-01: Create shared preferences here

    override fun getAllBooks(): List<Book> {
        val allBooksString = readRawResource(context, "books")
        val bookListType = object : TypeToken<List<Book>>() {}.type
        val books = Gson().fromJson<MutableList<Book>>(allBooksString, bookListType)
        books.sortWith(kotlin.Comparator { book1, book2 ->
            return@Comparator (book2.year - book1.year)
        })
        return books
    }

    override fun getBooksFromMyShelf(): List<Book> {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .all
            .map {
                Book.fromJSON(it.value.toString())
            }
    }

    override fun addToMyShelf(book: Book) {
        // FIXME-TASK-02
    }

    override fun removeFromMyShelf(book: Book) {
        // FIXME-TASK-03
    }

    override fun resetShelf() {
        // FIXME-TASK-04
    }
}