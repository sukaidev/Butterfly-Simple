package com.sukai.butterfly_simple.compiler.activity

import com.bennyhuo.aptutils.types.packageName
import com.bennyhuo.aptutils.types.simpleName
import com.sukai.butterfly_simple.compiler.activity.entity.Field
import java.util.*
import javax.lang.model.element.Modifier
import javax.lang.model.element.TypeElement

/**
 * Created by sukaidev on 2019/10/03.
 *
 */
class ActivityClass(val typeElement: TypeElement) {

    val simpleName:String = typeElement.simpleName()

    val packageName:String = typeElement.packageName()

    val fields = TreeSet<Field>()

    val isAbstract = typeElement.modifiers.contains(Modifier.ABSTRACT)

    val isKotlin = typeElement.getAnnotation(Metadata::class.java) !=null

    override fun toString(): String {
        return "$packageName.$simpleName[${fields.joinToString()}]"
    }
}