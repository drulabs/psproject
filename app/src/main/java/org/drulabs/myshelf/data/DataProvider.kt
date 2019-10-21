package org.drulabs.myshelf.data

interface DataProvider {

    fun getAllBooks(): List<Book>

    fun getBooksFromMyShelf(): List<Book>

    fun addToMyShelf(book: Book)

    fun removeFromMyShelf(book: Book)

    fun resetShelf()
}