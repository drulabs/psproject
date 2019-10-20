package org.drulabs.myshelf.ui.home

import android.content.Context
import android.content.Intent
import android.net.Uri
import org.drulabs.myshelf.data.Book
import org.drulabs.myshelf.data.DataProvider

class HomePresenter(
    private val view: HomeContract.View,
    private val dataProvider: DataProvider
) : HomeContract.Presenter {

    override fun addBook(book: Book) {
        dataProvider.addToMyShelf(book)
        view.onBookAdded(book)
    }

    override fun deleteBook(book: Book) {
        dataProvider.removeFromMyShelf(book)
        view.onBookDeleted(book)
    }

    override fun openBookDetails(context: Context, book: Book) {
        val openBookDetailIntent = Intent(Intent.ACTION_VIEW)
        openBookDetailIntent.data = Uri.parse(book.wikiLink)
        context.startActivity(openBookDetailIntent)
    }

    override fun loadBooks() {
        val books = dataProvider.getBooksFromMyShelf()
        view.onBooksLoaded(books)
    }

    override fun clearShelf() {
        dataProvider.resetShelf()
        view.onBooksCleared()
    }
}