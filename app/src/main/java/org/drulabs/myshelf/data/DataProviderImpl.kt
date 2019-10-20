package org.drulabs.myshelf.data

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.drulabs.myshelf.utils.PREFS_NAME
import org.drulabs.myshelf.utils.readRawResource

class DataProviderImpl(private val context: Context) : DataProvider {

    private val sharedPrefs: SharedPreferences =
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

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
        return sharedPrefs.all.map {
            Book.fromJSON(it.value.toString())
        }
    }

    override fun getBookByTitle(title: String): Book? {
        val bookJsonRep = sharedPrefs.getString(title, null)
        return if (bookJsonRep == null) null else Book.fromJSON(bookJsonRep)
    }

    override fun addToMyShelf(book: Book) {
        sharedPrefs.edit().putString(book.title, book.toString()).apply()
    }

    override fun removeFromMyShelf(book: Book) {
        sharedPrefs.edit().remove(book.title).apply()
    }

    override fun resetShelf() {
        sharedPrefs.edit().clear().apply()
    }
}