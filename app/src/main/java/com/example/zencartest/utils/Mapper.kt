package com.example.zencartest.utils

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.zencartest.data.model.UserEntity
import com.example.zencartest.domain.model.User
import java.util.Date

fun UserEntity.toUser(): User {
    return User(
        id = this.id,
        name = this.name,
        birthDate = this.birthDate,
        photoUrl = this.photoUrl,
        timeAdded= Date().time,
        token = this.token,
    )
}

@RequiresApi(Build.VERSION_CODES.O)
fun User.toUserEntity(): UserEntity {
    return UserEntity(
        id = getUniqueIdentifier(),
        name = this.name,
        birthDate = this.birthDate,
        photoUrl = this.photoUrl,
        timeAdded= Date().time,
        token = generateRandomToken(),
    )
}
