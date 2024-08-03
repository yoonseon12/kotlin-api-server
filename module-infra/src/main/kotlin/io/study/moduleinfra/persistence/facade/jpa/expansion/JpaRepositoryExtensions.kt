package io.study.moduleinfra.persistence.facade.jpa.expansion

import org.springframework.data.repository.CrudRepository

fun <T, ID> CrudRepository<T, ID>.findByIdOrElseNull(id: ID): T? {
    return this.findById(id!!).orElse(null)
}