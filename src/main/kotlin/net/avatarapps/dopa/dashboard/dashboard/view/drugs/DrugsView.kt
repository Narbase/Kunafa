package net.avatarapps.dopa.dashboard.dashboard.view.drugs

import net.avatarapps.dopa.dashboard.common.DopaColors
import net.avatarapps.dopa.dashboard.dashboard.view.DashboardPlainPresenter
import net.avatarapps.dopa.dashboard.dashboard.view.DashboardPlainViewContent
import net.avatarapps.dopa.dashboard.network.ServerCaller
import net.avatarapps.dopa.dashboard.storage.StorageManager
import net.avatarapps.kunafa.core.ViewContent.ViewContent
import net.avatarapps.kunafa.core.components.*
import net.avatarapps.kunafa.core.components.layout.Alignment
import net.avatarapps.kunafa.core.components.layout.DetachedView
import net.avatarapps.kunafa.core.components.layout.JustifyContent
import net.avatarapps.kunafa.core.dimensions.dependent.matchParent
import net.avatarapps.kunafa.core.dimensions.dependent.wrapContent
import net.avatarapps.kunafa.core.dimensions.independent.px
import net.avatarapps.kunafa.core.drawable.Color
import org.w3c.dom.HTMLInputElement
import org.w3c.files.get
import org.w3c.xhr.FormData
import org.w3c.xhr.XMLHttpRequest

class DrugsView : DashboardPlainViewContent("Drugs management") {
    override val plainPresenter = DrugsPresenter()

    override var pageViewContent = object : ViewContent() {
        override fun DetachedView.contentDefinition() {
            verticalLayout {
                id = "Vertical layout"
                padding = 20.px
                marginStart = 0.px
                width = matchParent
                height = matchParent
                isScrollableVertically = true
                alignItems = Alignment.Center

                horizontalLayout {
                    padding = 16.px
                    justifyContent = JustifyContent.Center

                    verticalLayout {
                        padding = 16.px
                        alignItems = Alignment.Center
                        marginEnd = 16.px

                        val input = textInput {
                            type = "file"
                            isVisible = false
                            (element as HTMLInputElement).accept = ".csv"
                            element.addEventListener("change", {
                                plainPresenter.onFileChanged(this)
                            })

                        }

                        imageView {
                            height = 160.px
                            img.src = "/public/img/upload_csv.png"
                        }

                        textView {
                            text = "Upload CSV file"
                            width = matchParent
                            height = wrapContent
                            paddingStart = 10.px
                            paddingEnd = 10.px
                            marginTop = 16.px
                            marginBottom = 8.px
                            textAlign = TextView.TextAlign.Center
                            textSize = 24.px
                            textColor = DopaColors.mainDark
                        }

                        onClick = {
                            input.element.click()
                        }

                        element.onmouseover = {
                            background = Color.rgb(167, 167, 167, 0.21)
                            element.style.cursor = "pointer"
                            asDynamic()
                        }
                        element.onmouseleave = {
                            background = Color.transparent
                            element.style.cursor = ""
                            asDynamic()

                        }

                    }

                    verticalLayout {
                        padding = 16.px
                        alignItems = Alignment.Center
                        marginStart = 16.px

                        imageView {
                            height = 160.px
                            img.src = "/public/img/download_csv.png"
                        }

                        textView {
                            text = "Download CSV file"
                            width = matchParent
                            height = wrapContent
                            paddingStart = 10.px
                            paddingEnd = 10.px
                            marginTop = 16.px
                            marginBottom = 8.px
                            textAlign = TextView.TextAlign.Center
                            textSize = 24.px
                            textColor = DopaColors.main
                        }
                        element.onmouseover = {
                            background = Color.rgb(167, 167, 167, 0.21)
                            element.style.cursor = "pointer"
                            asDynamic()
                        }
                        element.onmouseleave = {
                            background = Color.transparent
                            element.style.cursor = ""
                            asDynamic()

                        }

                    }


                }


            }

        }
    }
}

class DrugsPresenter : DashboardPlainPresenter() {

    fun onFileChanged(t: TextInput) {
        val url = "/api/agent/v1/drugs/upload"
        val accessToken = StorageManager.accessToken
        val headers = mapOf("Authorization" to (accessToken ?:""))

        val files = (t.element as HTMLInputElement).files
        val formData = FormData()

        formData.asDynamic().append("csv_file".asDynamic(), files?.get(0).asDynamic())

        val request = XMLHttpRequest()
        request.open("POST", "${ServerCaller.BASE_URL}$url")
        headers?.forEach { request.setRequestHeader(it.key, it.value) }
        request.send(formData)


//        files?.let { list ->
//            for (i in 0 until list.length) {
//                console.log(t)
//                println(list[i]?.name)
//            }
//        }
    }

    override fun onViewCreated(view: View) {

    }
}
