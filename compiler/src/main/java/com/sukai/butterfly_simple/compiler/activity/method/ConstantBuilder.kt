package com.sukai.butterfly_simple.compiler.activity.method

import com.squareup.javapoet.FieldSpec
import com.squareup.javapoet.TypeSpec
import com.sukai.butterfly_simple.compiler.activity.ActivityClass
import com.sukai.butterfly_simple.compiler.utils.camelToUnderline
import javax.lang.model.element.Modifier

/**
 * Created by sukaidev on 2019/10/04.
 *
 */
class ConstantBuilder(private val activityClass: ActivityClass) {

    // public static final
    fun build(typeBuilder: TypeSpec.Builder) {
        activityClass.fields.forEach {
            typeBuilder.addField(
                FieldSpec.builder(
                    String::class.java,
                    it.prefix + it.name.camelToUnderline().toUpperCase(),
                    Modifier.PUBLIC,
                    Modifier.STATIC,
                    Modifier.FINAL
                ).initializer(
                    "\$S", it.name
                ).build()
            )
        }
    }
}