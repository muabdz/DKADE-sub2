package com.dicoding.muadz.footballmatchschedule.favorite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.dicoding.muadz.footballmatchschedule.modals.Favorite
import org.jetbrains.anko.db.*

class MatchDatabaseOpenHelper(ctx: Context): ManagedSQLiteOpenHelper(ctx, "FavoriteMatch.db", null, 1){

    companion object {
        private var instance: MatchDatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): MatchDatabaseOpenHelper{
            if (instance == null){
                instance = MatchDatabaseOpenHelper(ctx.applicationContext)
            }
            return instance as MatchDatabaseOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(
            Favorite.TABLE_FAVORITE, true,
            Favorite.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            Favorite.MATCH_ID to TEXT + UNIQUE,
            Favorite.MATCH_DATE to TEXT,
            Favorite.HOME_ID to TEXT,
            Favorite.AWAY_ID to TEXT,
            Favorite.HOME_NAME to TEXT,
            Favorite.AWAY_NAME to TEXT,
            Favorite.HOME_SCORE to TEXT,
            Favorite.AWAY_SCORE to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(Favorite.TABLE_FAVORITE, true)
    }
}