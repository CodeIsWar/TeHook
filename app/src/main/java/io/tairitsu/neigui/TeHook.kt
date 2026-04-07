package io.tairitsu.neigui

import android.util.Log
import io.github.libxposed.api.XposedModule
import io.github.libxposed.api.XposedModuleInterface.PackageReadyParam

class TeHook : XposedModule() {
    companion object {
        const val TAG = "TeHook"
    }
    override fun onPackageReady(param: PackageReadyParam) {
        if (!param.isFirstPackage) return
        try {
            val loader = param.classLoader
            val saClass = loader.loadClass("sa.a")
            val vaClass = loader.loadClass("va.a")
            val callbackClass = loader.loadClass("com.xiaomi.security.xsof.IMiSafetyDetectCallback")
            val constructor =
                    saClass.getDeclaredConstructor(
                            Int::class.javaPrimitiveType,
                            vaClass,
                            callbackClass
                    )
            hook(constructor).intercept { chain ->
                val args = chain.args.toTypedArray()
                args[0] = 11
                chain.proceed(args)
            }
            log(Log.INFO, TAG, "hook success")
        } catch (e: Throwable) {
            log(Log.ERROR, TAG, "class not found", e)
        }
    }
}
