package net.avatarapps.dopa.dashboard.dashboard.view.drugs

import net.avatarapps.dopa.dashboard.common.DopaColors
import net.avatarapps.dopa.dashboard.dashboard.view.DashboardPlainPresenter
import net.avatarapps.dopa.dashboard.dashboard.view.DashboardPlainViewContent
import net.avatarapps.dopa.dashboard.dashboard.view.smartoffers.loadingIndicator
import net.avatarapps.dopa.dashboard.network.ServerCaller
import net.avatarapps.dopa.dashboard.storage.StorageManager
import net.avatarapps.kunafa.core.ViewContent.ViewContent
import net.avatarapps.kunafa.core.components.*
import net.avatarapps.kunafa.core.components.layout.Alignment
import net.avatarapps.kunafa.core.components.layout.DetachedView
import net.avatarapps.kunafa.core.components.layout.JustifyContent
import net.avatarapps.kunafa.core.components.layout.LinearLayout
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
    override var pageViewContent: ViewContent = DrugsLayoutView(plainPresenter)

    init {
        plainPresenter.view = pageViewContent as DrugsLayoutView
    }
}

class DrugsLayoutView(private val drugsPresenter: DrugsPresenter) : ViewContent() {
    private var buttonsLayout: LinearLayout? = null
    private var loadingImage: ImageView? = null
    private var statusTextView: TextView? = null

    override fun DetachedView.contentDefinition() {
        verticalLayout {
            id = "Vertical layout"
            padding = 20.px
            marginStart = 0.px
            width = matchParent
            height = matchParent
            isScrollableVertically = true
            alignItems = Alignment.Center

            loadingImage = loadingIndicator()
            buttonsLayout = horizontalLayout {
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
                            drugsPresenter.onFileChanged(this)
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


                    onClick = {
                        drugsPresenter.onDownloadFileClicked()
                    }

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


            statusTextView = textView {
                text = ""
                width = matchParent
                height = wrapContent
                paddingStart = 10.px
                paddingEnd = 10.px
                marginTop = 16.px
                marginBottom = 8.px
                textAlign = TextView.TextAlign.Center
                textSize = 18.px
                textColor = DopaColors.mainLight
            }

        }

    }

    fun showLoadingImage() {
        buttonsLayout?.isVisible = false
        loadingImage?.isVisible = true
        statusTextView?.isVisible = false
    }

    fun showButtonsLayout() {
        buttonsLayout?.isVisible = true
        loadingImage?.isVisible = false
        statusTextView?.isVisible = false
    }

    fun showIncorrectFileFormat() {
        statusTextView?.isVisible = true
        buttonsLayout?.isVisible = true
        loadingImage?.isVisible = false

        statusTextView?.text = "Incorrect file format"
        statusTextView?.textColor = DopaColors.redLight
    }

    fun showNetworkError() {
        statusTextView?.isVisible = true
        buttonsLayout?.isVisible = true
        loadingImage?.isVisible = false

        statusTextView?.text = "Network error"
        statusTextView?.textColor = DopaColors.separatorLight
    }

    fun showFileUploadedSuccessfully() {
        statusTextView?.isVisible = true
        buttonsLayout?.isVisible = true
        loadingImage?.isVisible = false

        statusTextView?.text = "Uploaded successfully"
        statusTextView?.textColor = DopaColors.greenLight
    }
}

class DrugsPresenter : DashboardPlainPresenter() {
    var view: DrugsLayoutView? = null

    fun onFileChanged(t: TextInput) {

        view?.showLoadingImage()

        val files = (t.element as HTMLInputElement).files
        val formData = FormData()
        formData.asDynamic().append("csv_file".asDynamic(), files?.get(0).asDynamic())

        uploadServerCall(formData,
                onSuccess = { xmlHttpRequest ->
                    if (xmlHttpRequest.status == 200.toShort()) {
                        view?.showFileUploadedSuccessfully()
                    } else {
                        view?.showIncorrectFileFormat()
                    }
                }
                ,
                onError = {
                    view?.showNetworkError()

                })


    }

    private fun uploadServerCall(formData: FormData, onSuccess: (XMLHttpRequest) -> Unit, onError: () -> Unit) {
        val url = "/api/agent/v1/drugs/upload"
        val accessToken = StorageManager.accessToken
        val headers = mapOf("Authorization" to (accessToken ?: ""))
        val request = XMLHttpRequest()
        request.onerror = { onError() }
        request.onload = { onSuccess(request) }
        request.open("POST", "${ServerCaller.BASE_URL}$url")
        headers.forEach { request.setRequestHeader(it.key, it.value) }
        request.send(formData)
    }

    override fun onViewCreated(view: View) {

    }

    fun onDownloadFileClicked() {
        view?.showLoadingImage()

        ServerCaller.downloadDrugsFile(
                onSuccess = { xmlHttpRequest ->
                    if (xmlHttpRequest.status >= 200.toShort() || xmlHttpRequest.status < 400.toShort()) {
                        view?.showButtonsLayout()
                    } else {
                        view?.showNetworkError()
                    }

                },
                onError = {
                    view?.showNetworkError()
                }
        )
    }

}
