# SecApp


We use SQLite to meet the database needs locally in our Android applications. SQLite is a database that works without the need for a panel or server side.

Classes we will use for SQLite

SQLiteOpenHelper: It is inherited in our class that will be written for database access. The creation of tables or schematic changes are performed with these classes.

SQLiteDatabase: Class that hosts CRUD(Create, Read, Update and Delete) operations

ContentValues: The class we will use when creating our parameter values

Cursor: Allows us to work on the records we retrieved from our database.

We extend our DatabaseHelper class from SQLiteOpenHelper class. In this way, it will enable us to perform CRUD(Create, Read, Update, Delete) operations, which are the basic operations of databases. After this process, there will be 2 methods that we need to implement.

The onCreate(): function is called only once when the application is loaded. With this method, the database to be used in the application and the tables to be used in this database are created.

onUpdate: It is used when any update operation is required on the tables.

In the TablesInfo class, we define the table you want to create and its columns. We created our table where we will keep our passwords.
