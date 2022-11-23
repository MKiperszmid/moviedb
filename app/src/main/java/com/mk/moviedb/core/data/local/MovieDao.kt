package com.mk.moviedb.core.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mk.moviedb.core.data.local.entity.MovieEntity

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: MovieEntity)

    @Query(
        """
        SELECT * FROM MovieEntity
        """
    )
    suspend fun getMovies(): List<MovieEntity>
}
