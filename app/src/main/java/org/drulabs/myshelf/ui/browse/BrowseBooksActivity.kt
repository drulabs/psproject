package org.drulabs.myshelf.ui.browse

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_browse_books.*
import org.drulabs.myshelf.R
import org.drulabs.myshelf.data.Book
import org.drulabs.myshelf.data.DataProviderImpl
import org.drulabs.myshelf.ui.common.BooksAdapter
import org.drulabs.myshelf.ui.home.HomeActivity

class BrowseBooksActivity : AppCompatActivity(), BooksAdapter.BookListener {

    private lateinit var adapter: BooksAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_browse_books)

        supportActionBar?.title = getString(R.string.title_select_book)

        rvAllBooks.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )

        adapter = BooksAdapter(this)

        adapter.resetBookList(DataProviderImpl(this).getAllBooks())

        rvAllBooks.adapter = adapter
    }

    override fun onBookTapped(book: Book) {
        val dataIntent = Intent()
        dataIntent.putExtra(HomeActivity.BROWSE_BOOK_KEY, book)
        setResult(Activity.RESULT_OK, dataIntent)
        finish()
    }

    override fun onBookRemoved(book: Book) {
        // not needed
    }
}
