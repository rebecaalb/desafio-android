package com.picpay.desafio.utilities

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.picpay.desafio.android.domain.model.User

object Mock {

    object API {
        const val USERS = "/users"
    }

    object Data {
        val usersJson = "[{\"id\":\"1\",\"name\":\"Sandrine Spinka\",\"img\":\"https://randomuser.me/api/portraits/men/1.jpg\",\"username\":\"Tod86\"},{\"id\":\"2\",\"name\":\"Carli Carroll\",\"img\":\"https://randomuser.me/api/portraits/men/2.jpg\",\"username\":\"Constantin_Sawayn\"},{\"id\":\"3\",\"name\":\"Annabelle Reilly\",\"img\":\"https://randomuser.me/api/portraits/men/3.jpg\",\"username\":\"Lawrence_Nader62\"}]"

        val users: List<User> by lazy {
            val jsonUsers = "[{\"id\":\"1\",\"name\":\"Sandrine Spinka\",\"img\":\"https://randomuser.me/api/portraits/men/1.jpg\",\"username\":\"Tod86\"},{\"id\":\"2\",\"name\":\"Carli Carroll\",\"img\":\"https://randomuser.me/api/portraits/men/2.jpg\",\"username\":\"Constantin_Sawayn\"},{\"id\":\"3\",\"name\":\"Annabelle Reilly\",\"img\":\"https://randomuser.me/api/portraits/men/3.jpg\",\"username\":\"Lawrence_Nader62\"}]"
            Gson().fromJson(jsonUsers, object : TypeToken<List<User>>() {}.type)
        }
    }

}