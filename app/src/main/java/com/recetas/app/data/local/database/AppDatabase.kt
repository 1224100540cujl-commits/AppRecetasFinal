package com.recetas.app.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.recetas.app.data.local.dao.*
import com.recetas.app.data.model.*

@Database(
    entities = [
        Recipe::class,
        Rating::class,
        RecipeCollection::class,
        RecipeCollectionItem::class,
        Tag::class,
        RecipeTag::class,
        Notification::class,
        UserStats::class,
        RecipeMedia::class
    ],
    version = 8,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun recipeDao(): com.recetas.app.data.local.dao.RecipeDao
    abstract fun ratingDao(): com.recetas.app.data.local.dao.RatingDao
    abstract fun collectionDao(): com.recetas.app.data.local.dao.CollectionDao
    abstract fun notificationDao(): NotificationDao
    abstract fun tagDao(): TagDao
    abstract fun recipeMediaDao(): RecipeMediaDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null


        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "recipe_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                instance
            }
        }
    }
}