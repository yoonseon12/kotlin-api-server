package io.study.moduleinfra.member

import org.springframework.data.repository.CrudRepository

fun <T, ID> CrudRepository<T, ID>.findByIdOrElseNull(id: ID): T? {
    return this.findById(id!!).orElse(null)
}