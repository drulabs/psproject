package org.drulabs.myshelf.testdata

import com.google.gson.Gson
import org.drulabs.myshelf.data.Book

object TestDataProvider {

    fun provideSampleBook(): Book {
        val jsonRep = "{\n" +
            "    \"author\": \"Vyasa\",\n" +
            "    \"country\": \"India\",\n" +
            "    \"imageLink\": \"images/the-mahab-harata.jpg\",\n" +
            "    \"language\": \"Sanskrit\",\n" +
            "    \"link\": \"https://en.wikipedia.org/wiki/Mahabharata\",\n" +
            "    \"pages\": 276,\n" +
            "    \"title\": \"Mahabharata\",\n" +
            "    \"year\": -700\n" +
            "  }"
        return Gson().fromJson(jsonRep, Book::class.java)
    }

}