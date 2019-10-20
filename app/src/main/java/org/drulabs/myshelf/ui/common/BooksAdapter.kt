package org.drulabs.myshelf.ui.common

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_book.view.*
import org.drulabs.myshelf.R
import org.drulabs.myshelf.data.Book

class BooksAdapter(
    val listener: BookListener,
    bookList: MutableList<Book> = ArrayList()
) : RecyclerView.Adapter<BooksAdapter.BookHolder>() {

    private val books: MutableList<Book>

    init {
        books = ArrayList()
        books.addAll(bookList)
    }

    fun resetBookList(books: List<Book>) {
        this.books.clear()
        this.books.addAll(books)
        this.books.sortWith(kotlin.Comparator { book1, book2 ->
            book2.year - book1.year
        })
        notifyDataSetChanged()
    }

    fun delete(position: Int) {
        if (position < 0 || position >= books.size) {
            return
        }
        val deletedBook = books.removeAt(position)
        listener.onBookRemoved(deletedBook)
        notifyDataSetChanged()
    }

    fun isEmpty(): Boolean {
        return books.isEmpty()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookHolder {
        val inflater = LayoutInflater.from(parent.context)
        val convertView = inflater.inflate(R.layout.item_book, parent, false)
        return BookHolder(convertView)
    }

    override fun getItemCount() = this.books.size

    override fun onBindViewHolder(holder: BookHolder, position: Int) {
        val book = books[position]
        holder.bind(book)
    }

    fun add(book: Book) {
        if (!books.contains(book)) {
            books += book
            this.books.sortWith(kotlin.Comparator { book1, book2 ->
                book2.year - book1.year
            })
            notifyDataSetChanged()
        }
    }

    fun remove(book: Book) {
        books.remove(book)
        notifyDataSetChanged()
    }

    fun clearAll() {
        books.clear()
        notifyDataSetChanged()
    }

    inner class BookHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(book: Book) {
            itemView.bookName.text = String.format(
                itemView.context.getString(R.string.format_book_title),
                book.title,
                book.year
            )
            itemView.authorName.text = book.author
            itemView.bookLanguage.text = book.language

            itemView.setOnClickListener {
                listener.onBookTapped(book)
            }
        }
    }

    interface BookListener {
        fun onBookTapped(book: Book)
        fun onBookRemoved(book: Book)
    }
}
