package com.example.brastlewark.room

import com.example.brastlewark.model.Gnome
import com.example.brastlewark.util.EntityMapper
import com.google.gson.Gson
import javax.inject.Inject

class GnomeCacheMapper
@Inject
constructor() : EntityMapper<GnomeCacheEntity, Gnome>{
    override fun mapFromEntity(entity: GnomeCacheEntity): Gnome = Gson().fromJson(entity.gnomeJsonObject, Gnome::class.java)

    override fun mapToEntity(domainModel: Gnome): GnomeCacheEntity =
        GnomeCacheEntity(
            id = domainModel.id,
            gnomeJsonObject = Gson().toJson(domainModel)
        )
}