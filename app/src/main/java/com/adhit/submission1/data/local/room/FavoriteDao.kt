package com.adhit.submission1.data.local.room


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.adhit.submission1.data.local.entity.Favorite

@Dao
interface FavoriteDao {

    @Query("SELECT * FROM Favorite ORDER BY username ASC")
    fun getAllUsers(): LiveData<List<Favorite>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addUser(favorite: Favorite)

    @Query("SELECT * FROM Favorite WHERE username= :username")
    fun getUserByUsername(username: String): LiveData<Favorite>
    @Delete
    fun delete(user: Favorite)
}