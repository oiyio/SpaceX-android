package com.omeriyioz.spacex.viewbinding


import android.app.Activity
import android.view.LayoutInflater
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/*
Reflection is used in the below viewBinding extension function.
# the following must be added to use view binding extension for fragment and activity
-keep class com.ttech.android.onlineislem.databinding.* {
    public static ** inflate( ** );
    public static ** bind( ** );
}
* */

inline fun <reified T : ViewBinding> Activity.viewBinding() = ActivityViewBindingDelegate(T::class.java)

class ActivityViewBindingDelegate<T : ViewBinding>(private val bindingClass: Class<T>) : ReadOnlyProperty<Activity, T> {
    /**
     * initiate variable for binding view
     */
    private var binding: T? = null

    @Suppress("UNCHECKED_CAST")
    override fun getValue(thisRef: Activity, property: KProperty<*>): T {
        binding?.let { return it }

        /**
         * inflate View class
         */
        val inflateMethod = bindingClass.getMethod("inflate", LayoutInflater::class.java)

        /**
         * Bind layout
         */
        val invokeLayout = inflateMethod.invoke(null, thisRef.layoutInflater) as T

        /**
         * Set the content view
         */
        thisRef.setContentView(invokeLayout.root)

        return invokeLayout.also { this.binding = it }
    }
}

