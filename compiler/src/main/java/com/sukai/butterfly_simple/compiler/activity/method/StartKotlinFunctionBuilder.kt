package com.sukai.butterfly_simple.compiler.activity.method

import com.squareup.kotlinpoet.*
import com.sukai.butterfly_simple.compiler.activity.ActivityClass
import com.sukai.butterfly_simple.compiler.activity.ActivityClassBuilder
import com.sukai.butterfly_simple.compiler.activity.entity.OptionalField
import com.sukai.butterfly_simple.compiler.prebuilt.ACTIVITY_BUILDER
import com.sukai.butterfly_simple.compiler.prebuilt.CONTEXT
import com.sukai.butterfly_simple.compiler.prebuilt.INTENT

/**
 * Created by sukaidev on 2019/10/21.
 *
 */
class StartKotlinFunctionBuilder(private val activityClass: ActivityClass) {

    fun build(fileBuilder: FileSpec.Builder) {
        val name = ActivityClassBuilder.METHOD_NAME + activityClass.simpleName
        val functionBuilder = FunSpec.builder(name)
            .receiver(CONTEXT.kotlin)
            .addModifiers(KModifier.PUBLIC)
            .returns(UNIT)
            .addStatement(
                "val intent = %T(this,%T::class.java)",
                INTENT.kotlin,
                activityClass.typeElement
            )

        activityClass.fields.forEach { field ->
            val name = field.name
            val className = field.asKotlinTypeName()

            if (field is OptionalField) {
                functionBuilder.addParameter(
                    ParameterSpec.builder(
                        name,
                        className
                    ).defaultValue("null").build()
                )
            } else {
                functionBuilder.addParameter(name, className)
            }

            functionBuilder.addStatement("intent.putExtra(%S,%L)", name, name)
        }

        functionBuilder.addStatement("%T.INSTANCE.startActivity(this,intent)", ACTIVITY_BUILDER.kotlin)
        fileBuilder.addFunction(functionBuilder.build())
    }
}