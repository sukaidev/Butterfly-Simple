package com.sukai.butterfly_simple.compiler.activity.entity

import com.bennyhuo.aptutils.types.asTypeMirror
import com.sukai.butterfly_simple.annotations.Optional
import com.sun.tools.javac.code.Symbol
import javax.lang.model.type.TypeKind

/**
 * Created by sukaidev on 2019/10/03.
 *
 */
class OptionalField(symbol: Symbol.VarSymbol) : Field(symbol) {

    var defaultValue: Any? = null
        private set

    override val prefix = "OPTIONAL_"

    init {
        val optional = symbol.getAnnotation(Optional::class.java)
        when (symbol.type.kind) {
            TypeKind.BOOLEAN -> defaultValue = optional.booleanValue
            TypeKind.BYTE, TypeKind.SHORT, TypeKind.INT, TypeKind.LONG, TypeKind.CHAR ->
                defaultValue = optional.intValue
            TypeKind.FLOAT, TypeKind.DOUBLE -> defaultValue = optional.floatValue
            else -> if(symbol.type == String::class.java.asTypeMirror())
                defaultValue = """""${optional.stringValue}""""
        }
    }

    override fun compareTo(other: Field): Int {
        return if(other is OptionalField){
            super.compareTo(other)
        }else {
            1
        }
    }
}