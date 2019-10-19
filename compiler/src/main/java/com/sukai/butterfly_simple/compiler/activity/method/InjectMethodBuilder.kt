package com.sukai.butterfly_simple.compiler.activity.method

import com.squareup.javapoet.MethodSpec
import com.squareup.javapoet.TypeName
import com.squareup.javapoet.TypeSpec
import com.sukai.butterfly_simple.compiler.activity.ActivityClass
import com.sukai.butterfly_simple.compiler.activity.entity.OptionalField
import com.sukai.butterfly_simple.compiler.prebuilt.ACTIVITY
import com.sukai.butterfly_simple.compiler.prebuilt.BUNDLE
import com.sukai.butterfly_simple.compiler.prebuilt.BUNDLE_UTILS
import javax.lang.model.element.Modifier

/**
 * Created by sukaidev on 2019/10/19.
 *
 */
class InjectMethodBuilder(private val activityClass: ActivityClass) {
    fun build(typeBuilder: TypeSpec.Builder) {
        val injectMethodBuilder = MethodSpec.methodBuilder("inject")
            .addParameter(ACTIVITY.java, "instance")
            .addParameter(BUNDLE.java, "savedInstanceState")
            .addModifiers(Modifier.PUBLIC)
            .returns(TypeName.VOID)
            .beginControlFlow("if (instance instanceof \$T)", activityClass.typeElement)
            .addStatement(
                "\$T typedInstance = (\$T) instance",
                activityClass.typeElement,
                activityClass.typeElement
            )
            .addStatement(
                "\$T extras = savedInstanceState == null ? typedInstance.getIntent().getExtras():savedInstanceState",
                BUNDLE.java
            )
            .beginControlFlow("if (extras != null)")

        activityClass.fields.forEach { field ->
            val name = field.name
            val typeName = field.asJavaTypeName().box()

            if (field is OptionalField) {
                injectMethodBuilder.addStatement(
                    "\$T \$LValue = \$T.<\$T>get(extras,\$S,\$L)", typeName, name,
                    BUNDLE_UTILS.java, typeName, name, field.defaultValue
                )
            } else {
                injectMethodBuilder.addStatement(
                    "\$T \$LValue = \$T.<\$T>get(extras,\$S)", typeName, name,
                    BUNDLE_UTILS.java, typeName, name
                )
            }

            if (field.isPrivate) {
                injectMethodBuilder.addStatement(
                    "typedInstance.set\$L(\$LValue)",
                    name.capitalize(),
                    name
                )
            } else {
                injectMethodBuilder.addStatement("typedInstance.\$L = \$LValue", name, name)
            }
        }

        injectMethodBuilder.endControlFlow().endControlFlow()
        typeBuilder.addMethod(injectMethodBuilder.build())
    }
}

