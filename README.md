# SharedPreferences in Android

### Objective: Learn data persistence with SharedPreferences

The app follows MVP architectural pattern. Finish the `FIXME`s in `DataProviderImpl` class to finish the project.

### App description

This project is a simple book shelf app. This app has 2 screens:
- Home screen: Users can see books they have added in the home screen. When there are no books, an empty shelf message is displayed. Allowed actions:
    - Add a new book to shelf by tapping on the floating action button
    - Delete an existing book by swiping the book left or right
    - Clear the shelf from overflow settings menu
    - Launch the wikipedia page of the book by tapping on the book

- Browse books screen: Launched by tapping on the floating action button on the home screen. This displays a list of available books (100), and allows user to select a book by tapping on it. Allowed actions:
    - Select a book by tapping it. This action navigates back to home screen and updates the book shelf
    
