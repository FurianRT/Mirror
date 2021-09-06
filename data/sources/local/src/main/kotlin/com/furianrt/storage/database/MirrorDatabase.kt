package com.furianrt.storage.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.furianrt.storage.database.dao.*
import com.furianrt.storage.database.entities.*
import com.furianrt.storage.database.entities.link.DbEntryMoodLink
import com.furianrt.storage.database.entities.link.DbEntryOccupationLink
import com.furianrt.storage.database.entities.link.DbMoodIconLink
import com.furianrt.storage.database.entities.link.DbOccupationIconLink

@Database(
    entities = [
        DbEntry::class,
        DbMood::class,
        DbImage::class,
        DbOccupation::class,
        DbEntryMoodLink::class,
        DbEntryOccupationLink::class,
        DbMoodIcon::class,
        DbMoodIconLink::class,
        DbOccupationIcon::class,
        DbOccupationIconLink::class
    ],
    version = MirrorDatabase.DATABASE_VERSION,
    exportSchema = false
)
@TypeConverters(DbMood.Converter::class)
internal abstract class MirrorDatabase : RoomDatabase() {

    companion object {
        const val DATABASE_VERSION = 1
    }

    abstract fun entryDao(): EntryDao
    abstract fun imageDao(): ImageDao
    abstract fun occupationDao(): OccupationDao
    abstract fun moodDao(): MoodDao
    abstract fun moodIconDao(): MoodIconDao
    abstract fun occupationIconDao(): OccupationIconDao
    abstract fun moodIconLinkDao(): MoodIconLinkDao
    abstract fun occupationIconLinkDao(): OccupationIconLinkDao
    abstract fun entryOccupationLinkDao(): EntryOccupationLinkDao
    abstract fun entryMoodLinkDao(): EntryMoodLinkDao
}