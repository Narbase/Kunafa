package com.narbase.kunafa.core.hydration

inline fun <reified H> hydrate(reference: H, block: H.() -> Unit) {
    block(reference)

//    val className = H::class.simpleName
//    val elements = document.querySelectorAll("*[data-kssr-ref^='$className']")
//
//    for (node in elements.asList()) {
//        val refName = (node as HTMLElement).dataset["kssrRef"] ?: continue
//        val fieldName = refName.split("-").getOrNull(1) ?: continue
//    }
}