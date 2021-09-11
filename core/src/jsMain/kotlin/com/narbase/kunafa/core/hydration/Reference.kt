@file:Suppress("unused")

package com.narbase.kunafa.core.hydration

import com.narbase.kunafa.core.components.*
import kotlinx.browser.document
import org.w3c.dom.*
import kotlin.reflect.KClass
import kotlin.reflect.KProperty

actual class Reference<V : View>(private val viewClass: KClass<V>) {
    var view: V? = null

    @Suppress("UNCHECKED_CAST")
    actual operator fun getValue(parent: Any, property: KProperty<*>): V? {
        if (view != null) {
            return view
        }
        val parentRef = (parent as? ViewGroup)?.ref ?: parent::class.simpleName
        view = ref("$parentRef-${property.name}", viewClass) as? V

        return view
    }

    actual operator fun setValue(parent: Any, property: KProperty<*>, value: V?) {
        throw RuntimeException("Cannot set value in front end")
    }

    companion object {

        fun ref(reference: String, viewClass: KClass<*>): View? {

            val node = document.querySelector("*[data-kssr-ref^='$reference']") as? HTMLElement
                    ?: return null

            val newView = when (viewClass) {
                Anchor::class -> Anchor(UnknownMountedView, node as? HTMLAnchorElement ?: return null)
                Checkbox::class -> Checkbox(UnknownMountedView, node as? HTMLInputElement ?: return null)
                Button::class -> Button(UnknownMountedView, node as? HTMLButtonElement ?: return null)
                Form::class -> Form(UnknownMountedView, node as? HTMLFormElement ?: return null)
                FieldSet::class -> FieldSet(UnknownMountedView, node as? HTMLFieldSetElement ?: return null)
                Legend::class -> Legend(UnknownMountedView, node as? HTMLLegendElement ?: return null)
                ImageView::class -> ImageView(UnknownMountedView, node as? HTMLImageElement ?: return null)
                Radio::class -> Radio(UnknownMountedView, node as? HTMLInputElement ?: return null)
                Table::class -> Table(UnknownMountedView, node as? HTMLTableElement ?: return null)
                TableBody::class -> TableBody(UnknownMountedView, node as? HTMLTableSectionElement ?: return null)
                TableFooter::class -> TableFooter(UnknownMountedView, node as? HTMLTableSectionElement ?: return null)
                TableHeader::class -> TableHeader(UnknownMountedView, node as? HTMLTableSectionElement ?: return null)
                TableHeaderCell::class -> TableHeaderCell(UnknownMountedView, node as? HTMLTableCellElement
                        ?: return null)
                TableCell::class -> TableCell(UnknownMountedView, node as? HTMLTableCellElement ?: return null)
                TableRow::class -> TableRow(UnknownMountedView, node as? HTMLTableRowElement ?: return null)
                TextInput::class -> TextInput(UnknownMountedView, node as? HTMLInputElement ?: return null)
                TextView::class -> TextView(UnknownMountedView, node as? HTMLElement ?: return null)
                UList::class -> UList(UnknownMountedView, node as? HTMLUListElement ?: return null)
                ListItem::class -> ListItem(UnknownMountedView, node as? HTMLLIElement ?: return null)
                View::class -> View(UnknownMountedView, node as? HTMLElement ?: return null)
                CustomView::class -> CustomView(UnknownMountedView, node as? HTMLElement ?: return null)
                else -> null
            }
            newView?.skipBaseClass()
            newView?.visit(null) {}
            return newView

        }
    }

}

actual class GroupReference<G : ViewGroup>(private val group: G) {

    actual operator fun getValue(parent: Any, property: KProperty<*>): G {
        val parentRef = (parent as? ViewGroup)?.ref ?: parent::class.simpleName
        group.ref = "$parentRef-${property.name}"
        return group
    }

    actual operator fun setValue(any: Any, property: KProperty<*>, value: G) {
    }
}

actual inline fun <reified V : View> reference() = Reference(V::class)
actual inline fun <reified G : ViewGroup> reference(viewGroup: G) = GroupReference(viewGroup)

inline fun <reified V : View> ref(reference: String): V? = Reference.ref(reference, V::class) as? V
