package org.drulabs.myshelf.ui.home

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.content_main.*
import org.drulabs.myshelf.R
import org.drulabs.myshelf.data.Book
import org.drulabs.myshelf.ui.browse.BrowseBooksActivity
import org.drulabs.myshelf.ui.common.BooksAdapter
import org.drulabs.myshelf.ui.common.PresenterCreator
import org.drulabs.myshelf.ui.common.SwipeToDeleteHelper

class HomeActivity : AppCompatActivity(), HomeContract.View, BooksAdapter.BookListener {

    companion object {
        const val BROWSE_BOOK_CODE = 24
        const val BROWSE_BOOK_KEY = "selected_book"
    }

    private lateinit var adapter: BooksAdapter

    private lateinit var presenter: HomeContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)

        presenter = PresenterCreator.createHomePresenter(this, this)

        adapter = BooksAdapter(this)

        setupRecyclerView()

        presenter.loadBooks()

        fabAddBook.setOnClickListener {
            val browseBookIntent = Intent(this, BrowseBooksActivity::class.java)
            startActivityForResult(browseBookIntent, BROWSE_BOOK_CODE)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_clear -> {
                presenter.clearShelf()
                return false
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                BROWSE_BOOK_CODE -> {
                    val book = data?.getParcelableExtra<Book>(BROWSE_BOOK_KEY)
                    book?.let { presenter.addBook(it) }
                }
            }
        }
    }

    override fun onBookAdded(book: Book) {
        adapter.add(book)
        onShelfUpdated()
    }

    override fun onBookDeleted(book: Book) {
        adapter.remove(book)
        onShelfUpdated()
    }

    override fun onBooksLoaded(books: List<Book>) {
        adapter.resetBookList(books)
        onShelfUpdated()
    }

    override fun onBooksCleared() {
        adapter.clearAll()
        onShelfUpdated()
    }

    private fun onShelfUpdated() {
        rvMyShelf.visibility = if (adapter.isEmpty()) View.GONE else View.VISIBLE
        tvEmptyShelfMsg.visibility = if (adapter.isEmpty()) View.VISIBLE else View.GONE
    }

    override fun onBookTapped(book: Book) {
        presenter.openBookDetails(applicationContext, book)
    }

    override fun onBookRemoved(book: Book) {
        presenter.deleteBook(book)
    }

    private fun setupRecyclerView(){
        rvMyShelf.layoutManager = LinearLayoutManager(
            applicationContext,
            LinearLayoutManager.VERTICAL,
            false
        )
        rvMyShelf.adapter = adapter

        val itemTouchHelper = ItemTouchHelper( SwipeToDeleteHelper(this, adapter))
        itemTouchHelper.attachToRecyclerView(rvMyShelf)
    }
}
