package org.drulabs.myshelf.ui.home

import android.content.Context
import org.drulabs.myshelf.data.Book

interface HomeContract {
    interface View {
        fun onBookAdded(book: Book)
        fun onBookDeleted(book: Book)
        fun onBooksLoaded(books: List<Book>)
        fun onBooksCleared()
    }

    interface Presenter {
        fun addBook(book: Book)
        fun deleteBook(book: Book)
        fun openBookDetails(context: Context, book: Book)
        fun loadBooks()
        fun clearShelf()
    }
}