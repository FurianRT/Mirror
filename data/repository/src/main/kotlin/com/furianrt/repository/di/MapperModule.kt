package com.furianrt.repository.di

import com.furianrt.domain.entities.*
import com.furianrt.repository.base.BaseMapper
import com.furianrt.repository.mappers.database.*
import com.furianrt.repository.mappers.database.EntryDbMapper
import com.furianrt.repository.mappers.database.ImageDbMapper
import com.furianrt.repository.mappers.database.MoodDbMapper
import com.furianrt.repository.mappers.database.OccupationDbMapper
import com.furianrt.repository.mappers.database.OccupationIconDbMapper
import com.furianrt.repository.mappers.domain.*
import com.furianrt.repository.mappers.domain.EntryDomainMapper
import com.furianrt.repository.mappers.domain.ImageDomainMapper
import com.furianrt.repository.mappers.domain.MoodDomainMapper
import com.furianrt.repository.mappers.domain.OccupationDomainMapper
import com.furianrt.repository.mappers.domain.OccupationIconDomainMapper
import com.furianrt.storage.database.entities.*
import com.furianrt.storage.database.entities.relations.DbFullEntry
import com.furianrt.storage.database.entities.relations.DbMoodWithIcon
import com.furianrt.storage.database.entities.relations.DbOccupationWithIcon
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface MapperModule {

    @Binds
    fun entryDbMapper(imp: EntryDbMapper): BaseMapper<DbFullEntry, Entry>

    @Binds
    fun imageDbMapper(imp: ImageDbMapper): BaseMapper<DbImage, Image>

    @Binds
    fun moodDbMapper(imp: MoodDbMapper): BaseMapper<DbMoodWithIcon, Mood>

    @Binds
    fun occupationDbMapper(imp: OccupationDbMapper): BaseMapper<DbOccupationWithIcon, Occupation>

    @Binds
    fun occupationIconDbMapper(imp: OccupationIconDbMapper): BaseMapper<DbOccupationIcon, OccupationIcon>

    @Binds
    fun moodIconDbMapper(imp: MoodIconDbMapper): BaseMapper<DbMoodIcon, MoodIcon>

    @Binds
    fun entryDomainMapper(imp: EntryDomainMapper): BaseMapper<Entry, DbFullEntry>

    @Binds
    fun imageDomainMapper(imp: ImageDomainMapper): BaseMapper<Image, DbImage>

    @Binds
    fun moodDomainMapper(imp: MoodDomainMapper): BaseMapper<Mood, DbMoodWithIcon>

    @Binds
    fun occupationDomainMapper(imp: OccupationDomainMapper): BaseMapper<Occupation, DbOccupationWithIcon>

    @Binds
    fun occupationIconDomainMapper(imp: OccupationIconDomainMapper): BaseMapper<OccupationIcon, DbOccupationIcon>

    @Binds
    fun moodIconDomainMapper(imp: MoodIconDomainMapper): BaseMapper<MoodIcon, DbMoodIcon>
}