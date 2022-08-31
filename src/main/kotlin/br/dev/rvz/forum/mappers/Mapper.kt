package br.dev.rvz.forum.mappers

interface Mapper<T, U> {
    fun map(t: T): U
}
