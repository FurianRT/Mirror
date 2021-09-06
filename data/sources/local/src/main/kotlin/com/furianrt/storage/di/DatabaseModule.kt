package com.furianrt.storage.di

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.furianrt.storage.R
import com.furianrt.storage.database.MirrorDatabase
import com.furianrt.storage.database.entities.DbMood
import com.furianrt.storage.database.entities.DbMoodIcon
import com.furianrt.storage.database.entities.link.DbMoodIconLink
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {

    private const val DATABASE_NAME = "mirror.db"
    private const val ICON_GREAT_DEFAULT_NAME = "mood_great_1"
    private const val ICON_GOOD_DEFAULT_NAME = "mood_good_1"
    private const val ICON_OKAY_DEFAULT_NAME = "mood_okay_1"
    private const val ICON_BAD_DEFAULT_NAME = "mood_bad_1"
    private const val ICON_AWFUL_DEFAULT_NAME = "mood_awful_1"

    private const val MOOD_GREAT_DEFAULT_ID = "id_default_great_mood"
    private const val MOOD_GOOD_DEFAULT_ID = "id_default_good_mood"
    private const val MOOD_OKAY_DEFAULT_ID = "id_default_okay_mood"
    private const val MOOD_BAD_DEFAULT_ID = "id_default_bad_mood"
    private const val MOOD_AWFUL_DEFAULT_ID = "id_default_awful_mood"

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext appContext: Context
    ) = Room.databaseBuilder(appContext, MirrorDatabase::class.java, DATABASE_NAME)
        .addCallback(object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                createInitialData(db, appContext)
            }
        })
        .build()

    private fun createInitialData(db: SupportSQLiteDatabase, context: Context) {
        insertMood(
            db,
            ICON_GREAT_DEFAULT_NAME,
            MOOD_GREAT_DEFAULT_ID,
            context.getString(R.string.mood_title_great),
            DbMood.Category.GREAT
        )
        insertMood(
            db,
            ICON_GOOD_DEFAULT_NAME,
            MOOD_GOOD_DEFAULT_ID,
            context.getString(R.string.mood_title_good),
            DbMood.Category.GOOD
        )
        insertMood(
            db,
            ICON_OKAY_DEFAULT_NAME,
            MOOD_OKAY_DEFAULT_ID,
            context.getString(R.string.mood_title_okay),
            DbMood.Category.OKAY
        )
        insertMood(
            db,
            ICON_BAD_DEFAULT_NAME,
            MOOD_BAD_DEFAULT_ID,
            context.getString(R.string.mood_title_bad),
            DbMood.Category.BAD
        )
        insertMood(
            db,
            ICON_AWFUL_DEFAULT_NAME,
            MOOD_AWFUL_DEFAULT_ID,
            context.getString(R.string.mood_title_awful),
            DbMood.Category.AWFUL
        )
    }

    private fun insertMood(
        db: SupportSQLiteDatabase,
        iconName: String,
        moodId: String,
        moodName: String,
        moodCategory: DbMood.Category
    ) {
        with(ContentValues()) {
            put(DbMoodIcon.FIELD_NAME, iconName)
            db.insert(DbMoodIcon.TABLE_NAME, SQLiteDatabase.CONFLICT_ABORT, this)
            clear()

            put(DbMood.FIELD_ID, moodId)
            put(DbMood.FIELD_NAME, moodName)
            put(DbMood.FIELD_CATEGORY, DbMood.Converter().categoryToString(moodCategory))
            put(DbMood.FIELD_CUSTOM, false)
            db.insert(DbMood.TABLE_NAME, SQLiteDatabase.CONFLICT_ABORT, this)
            clear()

            put(DbMoodIconLink.FIELD_MOOD_ID, moodId)
            put(DbMoodIconLink.FIELD_ICON_NAME, iconName)
            db.insert(DbMoodIconLink.TABLE_NAME, SQLiteDatabase.CONFLICT_ABORT, this)
            clear()
        }
    }

    @Provides
    fun provideEntryDao(database: MirrorDatabase) = database.entryDao()

    @Provides
    fun provideImageDao(database: MirrorDatabase) = database.imageDao()

    @Provides
    fun provideOccupationDao(database: MirrorDatabase) = database.occupationDao()

    @Provides
    fun provideMoodDao(database: MirrorDatabase) = database.moodDao()

    @Provides
    fun provideMoodIconDao(database: MirrorDatabase) = database.moodIconDao()

    @Provides
    fun provideOccupationIconDao(database: MirrorDatabase) = database.occupationIconDao()

    @Provides
    fun provideMoodIconLinkDao(database: MirrorDatabase) = database.moodIconLinkDao()

    @Provides
    fun provideOccupationIconLinkDao(database: MirrorDatabase) = database.occupationIconLinkDao()

    @Provides
    fun provideEntryOccupationLinkDao(database: MirrorDatabase) = database.entryOccupationLinkDao()

    @Provides
    fun provideEntryMoodLinkDao(database: MirrorDatabase) = database.entryMoodLinkDao()
}