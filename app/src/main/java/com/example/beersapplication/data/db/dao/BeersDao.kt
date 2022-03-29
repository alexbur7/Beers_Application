package com.example.beersapplication.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.beersapplication.data.db.entity.BeerDb
import io.reactivex.rxjava3.core.Single

@Dao
interface BeersDao {

    @Query("SELECT * FROM Beers")
    fun getAllBeers(): Single<List<BeerDb>>

    @Query("SELECT * FROM Beers WHERE fortress >= :fortress ORDER BY fortress")
    fun getStrongBeers(fortress: Float): Single<List<BeerDb>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBeers(beers: List<BeerDb>)
}
