package com.narbase.kunafa.core

import com.narbase.kunafa.core.components.page
import com.narbase.kunafa.core.components.textView
import com.narbase.kunafa.core.components.view
import com.narbase.kunafa.core.routing.link
import com.narbase.kunafa.core.routing.matchFirst
import com.narbase.kunafa.core.routing.redirect
import com.narbase.kunafa.core.routing.route
import kotlinx.browser.document
import kotlinx.browser.window
import org.w3c.dom.HTMLElement
import org.w3c.dom.get
import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlin.test.assertNull

/*
 * NARBASE TECHNOLOGIES CONFIDENTIAL
 * ______________________________
 * [2017] -[2019] Narbase Technologies
 * All Rights Reserved.
 * Created by islam
 * On: 2023/12/14.
 */

class ComponentRouteTest {
    @Test
    fun simpleRoutingShouldMountComponent() {
        page {
            route("/") {
                textView {
                    id = "test_view"
                    text = "Test view"
                }
            }
        }
        println("Element is viewed")
        assertNotNull(getById("test_view"))
        println("Element found")
    }

    @Test
    fun navigationBetweenTwoPagesShouldWorkWhenClickingOnLinks() {
        page {
            route("/") {
                view {
                    id = "rootView"
                    link("/view2") {
                        textView {
                            id = "linkToView2"
                            text = "linkToView2"
                        }
                    }
                    link("/view1") {
                        textView {
                            id = "linkToView1"
                            text = "linkToView1"
                        }
                    }
                }
            }

            route("/view1") {
                textView {
                    id = "view1"
                    text = "view1"
                }

            }
            route("/view2") {
                textView {
                    id = "view2"
                    text = "view2"
                }
            }
        }
        printCurrentPath()
        printDivs()
        clickById("linkToView2")
        printCurrentPath()

        assertNotNull(getById("view2"))
        assertNull(getById("view1"))

        clickById("linkToView1")
        printCurrentPath()

        assertNotNull(getById("view1"))
        assertNull(getById("view2"))

    }

    @Test
    fun redirectShouldWorkWhenClickingOnLinks() {
        page {
            route("/") {
                view {
                    id = "rootView"
                    link("/view2") {
                        textView {
                            id = "linkToView2"
                            text = "linkToView2"
                        }
                    }
                    link("/view1") {
                        textView {
                            id = "linkToView1"
                            text = "linkToView1"
                        }
                    }
                    link("/wrongUrl") {
                        textView {
                            id = "linkToWrongUrl"
                            text = "linkToView1"
                        }
                    }

                    route("/view1") {
                        textView {
                            id = "view1"
                            text = "view1"
                        }
                    }

                    route("/view2") {
                        view {
                            redirect("/catchAll", isAbsoluteDestination = true)
                        }
                    }
                    route("/catchAll") {
                        textView {
                            id = "catchAll"
                            text = "catchAll"
                        }
                    }
                }
            }

        }
        printCurrentPath()
        printDivs()
        clickById("linkToView2")
        printCurrentPath()
        assertNotNull(getById("catchAll"))

    }

    @Test
    fun matchFirstShouldWorkWhenClickingOnLinks() {
        page {
            route("/") {
                view {
                    id = "rootView"
                    link("/view1") {
                        textView {
                            id = "linkToView1"
                            text = "linkToView1"
                        }
                    }
                    link("/view2") {
                        textView {
                            id = "linkToView2"
                            text = "linkToView2"
                        }
                    }
                    link("/wrongUrl") {
                        textView {
                            id = "linkToWrongUrl"
                            text = "linkToView1"
                        }
                    }

                    matchFirst {
                        route("/view1") {
                            textView {
                                id = "view1"
                                text = "view1"
                            }
                        }
                        route("/view2") {
                            textView {
                                id = "view2"
                                text = "view2"
                            }
                        }
                        route("/catchAll") {
                            textView {
                                id = "catchAll"
                                text = "catchAll"
                            }
                        }
                        redirect("/catchAll", isAbsoluteDestination = true, isExact = false)
                    }
                }
            }

        }
        printCurrentPath()
        printDivs()
        printCurrentPath()
        clickById("linkToView2")
        assertNotNull(getById("view2"))
        clickById("linkToWrongUrl")
        printDivs()
        printCurrentPath()
        assertNotNull(getById("catchAll"))
        assertNull(getById("view2"))

    }

    private fun clickById(elementId: String) {
        (getById(elementId) as HTMLElement).click()
    }

    private fun getById(elementId: String) = document.getElementById(elementId)

    private fun printDivs() {
        val collection = document.getElementsByTagName("div")
        (0 until collection.length).forEach {
            println("Found: ${collection[it]?.id}")
        }
    }

    private fun printCurrentPath() {
        println("Path: ${window.location.href}")
    }

}