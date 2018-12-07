package com.dicoding.muadz.footballmatchschedule.favorite.match

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.dicoding.muadz.footballmatchschedule.models.FavoriteMatch
import org.jetbrains.anko.db.*

class MatchDatabaseOpenHelper(ctx: Context): ManagedSQLiteOpenHelper(ctx, "FavoriteMatch.db", null, 1){

    companion object {
        private var instance: MatchDatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): MatchDatabaseOpenHelper {
            if (instance == null){
                instance =
                        MatchDatabaseOpenHelper(ctx.applicationContext)
            }
            return instance as MatchDatabaseOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(
            FavoriteMatch.TABLE_FAVORITE, true,
            FavoriteMatch.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            FavoriteMatch.MATCH_ID to TEXT + UNIQUE,
            FavoriteMatch.MATCH_DATE to TEXT,
            FavoriteMatch.HOME_ID to TEXT,
            FavoriteMatch.AWAY_ID to TEXT,
            FavoriteMatch.HOME_NAME to TEXT,
            FavoriteMatch.AWAY_NAME to TEXT,
            FavoriteMatch.HOME_SCORE to TEXT,
            FavoriteMatch.AWAY_SCORE to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(FavoriteMatch.TABLE_FAVORITE, true)
    }
}