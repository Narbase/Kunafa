package com.narbase.kunafa.core.hydration

import com.narbase.kunafa.core.components.View

object UnknownMountedView : View() {
    override val isViewMounted = true

    override fun mount(child: View) {

    }

    override fun mountAfter(child: View, referenceNode: View) {

    }

    override fun mountBefore(child: View, referenceNode: View) {

    }

}