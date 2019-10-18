package com.sukai.butterfly_simple.compiler.activity

import com.squareup.javapoet.JavaFile
import com.squareup.javapoet.TypeSpec
import com.sukai.butterfly_simple.compiler.activity.method.ConstantBuilder
import com.sukai.butterfly_simple.compiler.activity.method.StartMethodBuilder
import javax.annotation.processing.Filer
import javax.lang.model.element.Modifier

/**
 * Created by sukaidev on 2019/10/04.
 *
 */
class ActivityClassBuilder(private val activityClass: ActivityClass) {

    companion object {
        const val POSIX = "Builder"
        const val METHOD_NAME = "start"
        const val METHOD_NAME_NO_OPTIONAL = METHOD_NAME + "WithoutOptional"
        const val METHOD_NAME_FOR_OPTIONAL = METHOD_NAME + "WithOptional"
        const val METHOD_NAME_FOR_OPTIONALs = METHOD_NAME + "WithOptionals"
    }

    fun build(filer: Filer) {
        if (activityClass.isAbstract) return
        val typeBuilder = TypeSpec.classBuilder(activityClass.simpleName + POSIX)
            .addModifiers(Modifier.PUBLIC, Modifier.FINAL)

        ConstantBuilder(activityClass).build(typeBuilder)
        StartMethodBuilder(activityClass).build(typeBuilder)

        writeJavaToFile(filer, typeBuilder.build())
    }

    private fun writeJavaToFile(filer: Filer, typeSpec: TypeSpec) {
        try {
            val file = JavaFile.builder(activityClass.packageName, typeSpec).build()
            file.writeTo(filer)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}