package com.sukai.butterfly_simple.compiler.prebuilt

import com.bennyhuo.aptutils.types.ClassType

/**
 * Created by sukaidev on 2019/10/18.
 *
 */
val CONTEXT  = ClassType("android.content.Context")

val INTENT = ClassType("android.content.Intent")

val ACTIVITY = ClassType("android.app.Activity")

val BUNDLE = ClassType("android.os.Bundle")

val BUNDLE_UTILS = ClassType("com.sukai.butterfly_simple.runtime.BundleUtils")

val ACTIVITY_BUILDER = ClassType("com.sukai.butterfly_simple.runtime.ActivityBuilder")