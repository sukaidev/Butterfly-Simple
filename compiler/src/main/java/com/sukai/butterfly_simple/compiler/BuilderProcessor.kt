package com.sukai.butterfly_simple.compiler

import com.bennyhuo.aptutils.AptContext
import com.bennyhuo.aptutils.logger.Logger
import com.bennyhuo.aptutils.types.isSubTypeOf
import com.sukai.butterfly_simple.annotations.Builder
import com.sukai.butterfly_simple.annotations.Optional
import com.sukai.butterfly_simple.annotations.Required
import com.sukai.butterfly_simple.compiler.activity.ActivityClass
import com.sukai.butterfly_simple.compiler.activity.entity.Field
import com.sukai.butterfly_simple.compiler.activity.entity.OptionalField
import com.sun.tools.javac.code.Symbol
import java.lang.Exception
import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.ProcessingEnvironment
import javax.annotation.processing.RoundEnvironment
import javax.lang.model.SourceVersion
import javax.lang.model.element.Element
import javax.lang.model.element.ElementKind
import javax.lang.model.element.TypeElement

class BuilderProcessor : AbstractProcessor() {

    private val supportedAnnotations = setOf(Builder::class.java, Required::class.java, Optional::class.java)

    override fun getSupportedSourceVersion() = SourceVersion.RELEASE_7

    override fun getSupportedAnnotationTypes() =
            supportedAnnotations.mapTo(HashSet<String>(), Class<*>::getName)

    override fun init(p0: ProcessingEnvironment) {
        super.init(p0)
        AptContext.init(p0)
    }

    override fun process(annotations: MutableSet<out TypeElement>, env: RoundEnvironment): Boolean {
        val activityClasses = HashMap<Element, ActivityClass>()
        env.getElementsAnnotatedWith(Builder::class.java)
                .filter {
                    it.kind.isClass
                }
                .forEach {
                    try {
                        if (it.asType().isSubTypeOf("android.app.Activity")) {
                            activityClasses[it] = ActivityClass(it as TypeElement)
                        } else {
                            Logger.error(it, "Unsupported typeElement:${it.simpleName}")
                        }
                    } catch (e: Exception) {
                        Logger.logParsingError(it, Builder::class.java, e)
                    }
                }

        env.getElementsAnnotatedWith(Required::class.java)
                .filter {
                    it.kind == ElementKind.FIELD
                }
                .forEach {
                    activityClasses[it.enclosingElement]?.fields?.add(Field(it as Symbol.VarSymbol))
                            ?: Logger.error(it, "Field $it annotated as Required while ${it.enclosedElements} not annotated.")
                }


        env.getElementsAnnotatedWith(Optional::class.java)
                .filter {
                    it.kind == ElementKind.FIELD
                }
                .forEach {
                    activityClasses[it.enclosingElement]?.fields?.add(OptionalField(it as Symbol.VarSymbol))
                            ?: Logger.error(it, "Field $it annotated as Optional while ${it.enclosedElements} not annotated.")
                }

        activityClasses.values.forEach {
//            Logger.warn(it.toString())
            it.builder.build(AptContext.filer)
        }
        return true
    }
}