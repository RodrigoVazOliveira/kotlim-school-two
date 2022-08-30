package br.dev.rvz.forum.mappers

interface Mapper<T, U> {
    fun map(topicRequestDTO: T): U
}
