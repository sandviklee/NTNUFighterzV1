# Base description

Base contains the packs: **dbaccess**, **users** og **json**. Base has everything to do with users etc.

---

**dbaccess** are classes that save userinformation. They should also be able to manage and read users.

**users** are classes that make users.

**json** is also a module, and contains deserializers and serializers.

# `Persistence`
This module is meant to represent the persistence layer and uses DAOs as its only connection point to the persistence layer. It contains all the logic for saving and loading data for the program.

## The `DAO` package:
The project makes use of the DAO (Data Access Object) design pattern to access information stored in data storage. The DAO package contains all DAOs in the project.

### `CRUDDAO`
The CRUDDAO (Create, Read, Update, Delete Data Access Object) is a generic interface supporting:
- add(T object) - create
- ArrayList<T> loadAll() - reads all regardless of attributes
- T get(K id) - reads all regardless of attributes
- update(K id, T object) - update
- delete(K id) - delete

The module also has an implementation of this interface `UserDAOImpl` for user information.

### `RODAO`
The RODAO (Read Only Data Access Object) is a generic interface supporting:
- ArrayList<T> getAll() - reads all regardless of attributes
- T find(K id) - reads all regardless of attributes 

Two classes implement this interface `CharacterInformationDAOImpl` and `CharacterAttriburesDAOImpl`.

## The `utils` package:

`ReadWriteToFile` gives the DAO implementations a way to read the content of files and write to them.

### `json` sub package
Contains all the serializers, deserializers and modules for objectmappers for Users, CharacterAttributes and CharacterInfo.


