package com.narbase.kunafa.core.hydration

import com.narbase.kunafa.core.components.View

expect class Reference<V : View>

//expect fun <V : View> reference(): Reference<V>
expect inline fun <reified V : View> reference(): Reference<V>


