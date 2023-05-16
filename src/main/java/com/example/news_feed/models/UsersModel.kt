package com.example.news_feed.models

sealed class UsersModel(
    open val title: String,
    open val subtitle: String
) {
    data class UserDefault(
        override val title: String,
        override val subtitle: String
    ) : UsersModel(title, subtitle)

    data class UserImage(
        override val title: String,
        override val subtitle: String,
        val imageUrl: String?,
    ) : UsersModel(title, subtitle)

    data class UserDesc(
        override val title: String,
        override val subtitle: String,
        val imageUrl: String?
    ) : UsersModel(title, subtitle)

    data class UserCircle(
        override val title: String,
        override val subtitle: String,
        val imageUrl: String?
    ) : UsersModel(title, subtitle)

    companion object Factory {
        private fun getUsers(user: User): UsersModel {
            if (user.id % 4 == 0)
                return UserCircle(
                    user.firstName,
                    user.lastName,
                    user.avatar,
                )
            else if (user.id % 3 == 0)
                return UserDefault(
                    user.firstName,
                    user.email
                )
            else if (user.id % 2 == 0)
                return UserImage(
                    user.firstName,
                    user.lastName,
                    user.avatar
                )
            else
                return UserDesc(
                    user.firstName,
                    user.lastName,
                    user.avatar,
                )
        }
        fun getAllNews(users: UsersResponse): List<UsersModel> {
            return users.data.map { getUsers(it) }
        }
    }
}
