package com.sukai.butterfly_simple.compiler.utils

import java.lang.StringBuilder

/**
 * Created by sukaidev on 2019/10/04.
 *
 */

fun String.camelToUnderline(): String {
    return fold(StringBuilder()) { acc, c ->
        if (c.isUpperCase()) {
            acc.append("_").append(c.toLowerCase())
        } else acc.append(c)
    }.toString()
}

//fun String.underlineToCamel(): String {
//    var upperNext = false
//    return fold(StringBuilder()) { acc, c ->
//        if (c == '_')
//            upperNext = true
//        else acc.append(if (upperNext) c.toUpperCase() else c)
//    }.toString()
//}