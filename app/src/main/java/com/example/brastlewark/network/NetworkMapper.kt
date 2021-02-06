package com.example.brastlewark.network

import com.example.brastlewark.model.Gnome
import com.example.brastlewark.util.EntityMapper
import javax.inject.Inject

class NetworkMapper
@Inject
constructor() : EntityMapper<GnomeNetworkEntity, Gnome>{
    override fun mapFromEntity(entity: GnomeNetworkEntity): Gnome =
        Gnome(
            id = entity.id,
            name = entity.name,
            avatar = entity.avatar,
            age = entity.age,
            weight = entity.weight,
            height = entity.height,
            hairColor = entity.hairColor,
            professions = entity.professions,
            friends = entity.friends
        )

    override fun mapToEntity(domainModel: Gnome): GnomeNetworkEntity =
        GnomeNetworkEntity(
            id = domainModel.id,
            name = domainModel.name,
            avatar = domainModel.avatar,
            age = domainModel.age,
            weight = domainModel.weight,
            height = domainModel.height,
            hairColor = domainModel.hairColor,
            professions = domainModel.professions,
            friends = domainModel.friends
        )
}