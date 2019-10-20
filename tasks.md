# Tasks

App description available in [README.md](/README.md)

#### Task 1: Create a SharedPreferences
Open DataProviderImpl class and locate `FIXME-TASK-01`. Create a `SharedPreference` instance with name `"PREFS_NAME" (Defined in Constants.kt)` and `Context.MODE_PRIVATE` by using the context passed in the constructor.

#### Task 2: Add a book in SharedPreference
In `addToMyShelf` function, add the book in shared preferences with book title as key and string representation of book as value. Use `apply()` instead of commit() to save changes.

#### Task 3: Remove a book from SharedPreference
In `removeFromMyShelf` function, remove the book from shared preferences. Use `apply()` instead of commit() to save changes.

#### Task 4: Clear SharedPreference
In `resetShelf` function, clear all data from shared preferences. Use `apply()` instead of commit() to save changes.
