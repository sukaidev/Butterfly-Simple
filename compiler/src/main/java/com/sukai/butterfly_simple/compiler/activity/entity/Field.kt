package com.sukai.butterfly_simple.compiler.activity.entity

import com.sun.tools.javac.code.Symbol

/**
 * Created by sukaidev on 2019/10/03.
 *
 */
open class Field(private val symbol: Symbol.VarSymbol):Comparable<Field>{

    val name = symbol.qualifiedName.toString()

    open val prefix = "REQUIRED_"

    val isPrivate = symbol.isPrivate

    val isPrimitive = symbol.type.isPrimitive

    override fun toString(): String {
        return "$name:${symbol.type}"
    }

    override fun compareTo(other: Field): Int {
        return name.compareTo(other.name)
    }
}