package com.narbase.kunafa.core.hydration

import com.narbase.kunafa.core.components.*
import kotlinx.browser.document
import org.w3c.dom.*
import kotlin.reflect.KClass
import kotlin.reflect.KProperty

class Reference<V : View>(private val viewClass: KClass<V>) {
    var view: V? = null

    @Suppress("UNCHECKED_CAST")
    operator fun getValue(any: Any, property: KProperty<*>): V? {
        if (view != null) {
            return view
        }
        val node = document.querySelector("*[data-kssr-ref^='${any::class.simpleName}-${property.name}']") as? HTMLElement
                ?: return null

        val newView = when (viewClass) {
            Anchor::class -> Anchor(UnknownMountedView, node as? HTMLAnchorElement ?: return null)
            Button::class -> Button(UnknownMountedView, node as? HTMLButtonElement ?: return null)
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
            TableHeaderCell::class -> TableHeaderCell(UnknownMountedView, node as? HTMLTableCellElement ?: return null)
            TableCell::class -> TableCell(UnknownMountedView, node as? HTMLTableCellElement ?: return null)
            TableRow::class -> TableRow(UnknownMountedView, node as? HTMLTableRowElement ?: return null)
            TextInput::class -> TextInput(UnknownMountedView, node as? HTMLInputElement ?: return null)
            TextView::class -> TextView(UnknownMountedView, node as? HTMLButtonElement ?: return null)
            UList::class -> UList(UnknownMountedView, node as? HTMLUListElement ?: return null)
            ListItem::class -> ListItem(UnknownMountedView, node as? HTMLLIElement ?: return null)
            View::class -> View(UnknownMountedView, node as? HTMLElement ?: return null)
            CustomView::class -> CustomView(UnknownMountedView, node as? HTMLElement ?: return null)
            else -> null
        }
        newView?.skipBaseClass()
        newView?.visit(null) {}
        view = newView as? V
        return view
    }

    operator fun setValue(any: Any, property: KProperty<*>, value: V?) {
        throw RuntimeException("Cannot set value in front end")
    }

}

inline fun <reified V : View> reference() = Reference(V::class)

