package com.dicoding.muadz.footballmatchschedule.favorite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.dicoding.muadz.footballmatchschedule.models.FavoriteTeam
import org.jetbrains.anko.db.*

class TeamDatabaseOpenHelper(ctx: Context): ManagedSQLiteOpenHelper(ctx, "FavoriteTeam.db", null, 1){

    companion object {
        private var instance: TeamDatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): TeamDatabaseOpenHelper{
            if (instance == null){
                instance = TeamDatabaseOpenHelper(ctx.applicationContext)
            }
            return instance as TeamDatabaseOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(FavoriteTeam.TABLE_FAVORITE, true,
            FavoriteTeam.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            FavoriteTeam.TEAM_ID to TEXT + UNIQUE,
            FavoriteTeam.TEAM_NAME to TEXT,
            FavoriteTeam.TEAM_BADGE to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(FavoriteTeam.TABLE_FAVORITE, true)
    }
}