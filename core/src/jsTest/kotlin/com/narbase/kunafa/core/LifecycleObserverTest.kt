package com.narbase.kunafa.core

import com.narbase.kunafa.core.components.View
import com.narbase.kunafa.core.lifecycle.LifecycleObserver
import com.narbase.kunafa.core.lifecycle.LifecycleOwner
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class LifecycleObserverTest {

    class MockMountedView private constructor(parent: View? = null) : View(parent) {
        override val isViewMounted = true

        companion object {
            operator fun invoke(): MockMountedView {
                return MockMountedView(MockMountedView())
            }
        }
    }

    class MockLifecycleObserver : LifecycleObserver {
        var onViewCreatedCalled = false
        var viewWillMountCalled = false
        var onViewMountedCalled = false
        var viewWillBeRemovedCalled = false
        var onViewRemovedCalled = false

        override fun onViewCreated(lifecycleOwner: LifecycleOwner) {
            onViewCreatedCalled = true
        }

        override fun viewWillMount(lifecycleOwner: LifecycleOwner) {
            viewWillMountCalled = true
        }

        override fun onViewMounted(lifecycleOwner: LifecycleOwner) {
            onViewMountedCalled = true
        }

        override fun viewWillBeRemoved(lifecycleOwner: LifecycleOwner) {
            viewWillBeRemovedCalled = true
        }

        override fun onViewRemoved(lifecycleOwner: LifecycleOwner) {
            onViewRemovedCalled = true
        }

        fun resetState() {
            onViewCreatedCalled = false
            viewWillMountCalled = false
            onViewMountedCalled = false
            viewWillBeRemovedCalled = false
            onViewRemovedCalled = false
        }

        fun assertAllMethodsCalled() {
            assertTrue(onViewCreatedCalled, "Expected 'onViewCreated' to be called, but it was not.")
            assertTrue(viewWillMountCalled, "Expected 'viewWillMount' to be called, but it was not.")
            assertTrue(onViewMountedCalled, "Expected 'onViewMounted' to be called, but it was not.")
            assertTrue(viewWillBeRemovedCalled, "Expected 'viewWillBeRemoved' to be called, but it was not.")
            assertTrue(onViewRemovedCalled, "Expected 'onViewRemoved' to be called, but it was not.")
        }

        fun assertNoMethodsCalled() {
            assertFalse(onViewCreatedCalled, "Expected 'onViewCreated' not to be called, but it was.")
            assertFalse(viewWillMountCalled, "Expected 'viewWillMount' not to be called, but it was.")
            assertFalse(onViewMountedCalled, "Expected 'onViewMounted' not to be called, but it was.")
            assertFalse(viewWillBeRemovedCalled, "Expected 'viewWillBeRemoved' not to be called, but it was.")
            assertFalse(onViewRemovedCalled, "Expected 'onViewRemoved' not to be called, but it was.")
        }
    }


    private fun triggerLifecycleChanges(view: View) {
        view.postOnViewCreated()
        view.postViewWillMount()
        view.postOnViewMounted()
        view.postViewWillBeRemoved()
        view.postOnViewRemoved()
    }

    @Test
    fun testUnbindLifecycleObserver() {
        val view = MockMountedView()
        val observer = MockLifecycleObserver()
        view.bind(observer)

        triggerLifecycleChanges(view)
        observer.assertAllMethodsCalled()

        observer.resetState()
        view.unbind(observer)

        triggerLifecycleChanges(view)
        observer.assertNoMethodsCalled()
    }

    @Test
    fun testClearAllLifecycleObservers() {
        val view = MockMountedView()
        val observers = listOf(MockLifecycleObserver(), MockLifecycleObserver())
        observers.forEach { view.bind(it) }

        triggerLifecycleChanges(view)
        observers.forEach { it.assertAllMethodsCalled() }

        observers.forEach { it.resetState() }
        view.clearAllLifecycleObservers()

        triggerLifecycleChanges(view)
        observers.forEach { it.assertNoMethodsCalled() }
    }

}