package br.dev.rvz.forum.models

object UserTest {
    fun buildNotSave() = User(
        id = null,
        name = "Jorge",
        email = "jorge@gmail.com",
        password = "123456",
        role = listOf()
    )

    fun buildSaved() = User(
        id = 1,
        name = "Jorge",
        email = "jorge@gmail.com",
        password = "123456",
        role = listOf()
    )
}