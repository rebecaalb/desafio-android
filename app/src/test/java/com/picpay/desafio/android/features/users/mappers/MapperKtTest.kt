package com.picpay.desafio.android.features.users.mappers

import android.os.Build
import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.features.users.model.UserUI
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.robolectric.annotation.Config

@Config(sdk = [Build.VERSION_CODES.Q])
@RunWith(JUnit4::class)
class MapperKtTest {

    @Test
    fun mapUserUI() {
        assertEquals(
            UserUI("https://randomuser.me/api/portraits/men/1.jpg", "Sandrine Spinka", 1, "Tod86"),
            User("https://randomuser.me/api/portraits/men/1.jpg", "Sandrine Spinka", 1, "Tod86").toUI()
        )

        assertEquals(
            UserUI("", "Sandrine Spinka", 1, "Tod86"),
            User(name = "Sandrine Spinka", id = 1, username = "Tod86").toUI()
        )

        assertEquals(
            UserUI("", "Sandrine Spinka", -1, "Tod86"),
            User(name = "Sandrine Spinka", username = "Tod86").toUI()
        )

        assertEquals(
            UserUI("", "", -1, ""),
            User().toUI()
        )
    }

}