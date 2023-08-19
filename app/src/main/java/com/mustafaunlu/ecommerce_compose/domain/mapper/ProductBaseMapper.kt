package com.mustafaunlu.ecommerce_compose.domain.mapper

interface ProductBaseMapper<I, O> {
    fun map(input: I): O
}
