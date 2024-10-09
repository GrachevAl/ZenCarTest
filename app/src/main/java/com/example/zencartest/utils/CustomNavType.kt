package com.example.zencartest.utils

import android.os.Bundle
import androidx.navigation.NavType
import com.example.zencartest.navigation.Destinations
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object CustomNavType {
    val ListDataUsersType = object : NavType<Destinations.ListDataUsers>(
        isNullableAllowed = false,
    ) {
        override fun get(bundle: Bundle, key: String): Destinations.ListDataUsers? {
            return Json.decodeFromString(bundle.getString(key) ?: return null)
        }

        override fun parseValue(value: String): Destinations.ListDataUsers {
            return Json.decodeFromString(value)
        }

        override fun put(bundle: Bundle, key: String, value: Destinations.ListDataUsers) {
            bundle.putString(key, Json.encodeToString(value))
        }

        override fun serializeAsValue(value: Destinations.ListDataUsers): String {
            return Json.encodeToString(value)
        }
    }
}
