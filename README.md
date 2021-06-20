# SecApp

    Gıyaseddin Tanrikulu - 20170808072
    Erkam Dogan - 20160808036

  We use SQLite to meet the out database needs locally in our Android applications. SQLite is a database that works without the need for a panel or server side.

  Classes we will use for SQLite:

  1. SQLiteOpenHelper: It is inherited in our class that will be written for database access. The creation of tables or schematic changes are performed with these classes.

  1. SQLiteDatabase: Class that hosts CRUD(Create, Read, Update and Delete) operations

  1. ContentValues: The class will be used when creating our parameter values

  1. Cursor: Allows us to work on the records we retrieved from our database.


  We extend our DatabaseHelper class from SQLiteOpenHelper class. In this way, it will enable us to perform CRUD(Create, Read, Update, Delete) operations, which are the basic operations of databases. After this process, there will be 2 methods that we need to implement.

  1. onCreate(): function is called only once when the application is loaded. With this method, the database to be used in the application and the tables to be used in this database are created.

  1. onUpdate: It is used when any update operation is required on the tables.

In the TablesInfo class, we define the tables and create its columns. And this is also the place where we keep password entries.

A password entry consists of an application name and a password, idea is to keep them locally without any online access to keep them safe in your phone.
This application is secured with a PIN system, that is initially "000000".

Demo Video: https://youtu.be/hrvCjwYnlQc
