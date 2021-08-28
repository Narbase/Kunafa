@file:Suppress("unused", "MemberVisibilityCanBePrivate")

package com.narbase.kunafa.core.css

import com.narbase.kunafa.core.components.Page.useRtl
import com.narbase.kunafa.core.dimensions.Dimension
import com.narbase.kunafa.core.dimensions.LinearDimension
import com.narbase.kunafa.core.drawable.Color
import kotlinx.browser.document
import org.w3c.dom.Element
import org.w3c.dom.HTMLStyleElement
import org.w3c.dom.css.CSSStyleDeclaration
import org.w3c.dom.css.CSSStyleSheet

// Color Properties
var RuleSet.color by RuleDelegate<Color?>("color")
var RuleSet.opacity by RuleDelegate<Double?>("opacity")

// Background and Border Properties
var RuleSet.background by RuleDelegate<String?>("background")
var RuleSet.backgroundAttachment by RuleDelegate<String?>("background-attachment")
var RuleSet.backgroundBlendMode by RuleDelegate<String?>("background-blend-mode")
var RuleSet.backgroundColor by RuleDelegate<Color?>("background-color")
var RuleSet.backgroundImage by RuleDelegate<String?>("background-image")
var RuleSet.backgroundPosition by RuleDelegate<String?>("background-position")
var RuleSet.backgroundRepeat by RuleDelegate<String?>("background-repeat")
var RuleSet.backgroundClip by RuleDelegate<String?>("background-clip")
var RuleSet.backgroundOrigin by RuleDelegate<String?>("background-origin")
var RuleSet.backgroundSize by RuleDelegate<String?>("background-size")
var RuleSet.border by RuleDelegate<String?>("border")
var RuleSet.borderBottom by RuleDelegate<String?>("border-bottom")
var RuleSet.borderBottomColor by RuleDelegate<Color?>("border-bottom-color")
var RuleSet.borderBottomLeftRadius by RuleDelegate<LinearDimension?>("border-bottom-left-radius")
var RuleSet.borderBottomRightRadius by RuleDelegate<LinearDimension?>("border-bottom-right-radius")
var RuleSet.borderBottomStyle by RuleDelegate<String?>("border-bottom-style")
var RuleSet.borderBottomWidth by RuleDelegate<String?>("border-bottom-width")
var RuleSet.borderColor by RuleDelegate<Color?>("border-color")
var RuleSet.borderImage by RuleDelegate<String?>("border-image")
var RuleSet.borderImageOutset by RuleDelegate<String?>("border-image-outset")
var RuleSet.borderImageRepeat by RuleDelegate<String?>("border-image-repeat")
var RuleSet.borderImageSlice by RuleDelegate<String?>("border-image-slice")
var RuleSet.borderImageSource by RuleDelegate<String?>("border-image-source")
var RuleSet.borderImageWidth by RuleDelegate<String?>("border-image-width")
var RuleSet.borderLeft by RuleDelegate<String?>("border-left")
var RuleSet.borderLeftColor by RuleDelegate<Color?>("border-left-color")
var RuleSet.borderLeftStyle by RuleDelegate<String?>("border-left-style")
var RuleSet.borderLeftWidth by RuleDelegate<String?>("border-left-width")
var RuleSet.borderRadius by RuleDelegate<Dimension?>("border-radius")
var RuleSet.borderRight by RuleDelegate<String?>("border-right")
var RuleSet.borderRightColor by RuleDelegate<Color?>("border-right-color")
var RuleSet.borderRightStyle by RuleDelegate<String?>("border-right-style")
var RuleSet.borderRightWidth by RuleDelegate<String?>("border-right-width")
var RuleSet.borderStyle by RuleDelegate<String?>("border-style")
var RuleSet.borderTop by RuleDelegate<String?>("border-top")
var RuleSet.borderTopColor by RuleDelegate<Color?>("border-top-color")
var RuleSet.borderTopLeftRadius by RuleDelegate<LinearDimension?>("border-top-left-radius")
var RuleSet.borderTopRightRadius by RuleDelegate<LinearDimension?>("border-top-right-radius")
var RuleSet.borderTopStyle by RuleDelegate<String?>("border-top-style")
var RuleSet.borderTopWidth by RuleDelegate<String?>("border-top-width")
var RuleSet.borderWidth by RuleDelegate<String?>("border-width")
var RuleSet.boxDecorationBreak by RuleDelegate<String?>("box-decoration-break")
var RuleSet.boxShadow by RuleDelegate<String?>("box-shadow")

// Basic Box Properties
var RuleSet.bottom by RuleDelegate<LinearDimension?>("bottom")
var RuleSet.clear by RuleDelegate<String?>("clear")
var RuleSet.clip by RuleDelegate<String?>("clip")
var RuleSet.display by RuleDelegate<String?>("display")
var RuleSet.float by RuleDelegate<String?>("float")
var RuleSet.height by DynamicDimensionRuleDelegate("height")
var RuleSet.left by RuleDelegate<LinearDimension?>("left")
var RuleSet.margin by RuleDelegate<Dimension?>("margin")
var RuleSet.marginBottom by RuleDelegate<LinearDimension?>("margin-bottom")
var RuleSet.marginLeft by RuleDelegate<LinearDimension?>("margin-left")
var RuleSet.marginRight by RuleDelegate<LinearDimension?>("margin-right")
var RuleSet.marginTop by RuleDelegate<LinearDimension?>("margin-top")
var RuleSet.maxHeight by DynamicDimensionRuleDelegate("max-height")
var RuleSet.maxWidth by DynamicDimensionRuleDelegate("max-width")
var RuleSet.minHeight by DynamicDimensionRuleDelegate("min-height")
var RuleSet.minWidth by DynamicDimensionRuleDelegate("min-width")
var RuleSet.overflow by RuleDelegate<String?>("overflow")
var RuleSet.overflowX by RuleDelegate<String?>("overflow-x")
var RuleSet.overflowY by RuleDelegate<String?>("overflow-y")
var RuleSet.padding by RuleDelegate<Dimension?>("padding")
var RuleSet.paddingBottom by RuleDelegate<LinearDimension?>("padding-bottom")
var RuleSet.paddingLeft by RuleDelegate<LinearDimension?>("padding-left")
var RuleSet.paddingRight by RuleDelegate<LinearDimension?>("padding-right")
var RuleSet.paddingTop by RuleDelegate<LinearDimension?>("padding-top")
var RuleSet.position by RuleDelegate<String?>("position")
var RuleSet.right by RuleDelegate<LinearDimension?>("right")
var RuleSet.top by RuleDelegate<LinearDimension?>("top")
var RuleSet.visibility by RuleDelegate<String?>("visibility")
var RuleSet.width by DynamicDimensionRuleDelegate("width")
var RuleSet.verticalAlign by RuleDelegate<String?>("vertical-align")
var RuleSet.zIndex by RuleDelegate<Int?>("z-index")

// Flexible Box Layout
var RuleSet.alignContent by RuleDelegate<Alignment?>("align-content")
var RuleSet.alignItems by RuleDelegate<Alignment?>("align-items")
var RuleSet.alignSelf by RuleDelegate<Alignment?>("align-self")
var RuleSet.flex by RuleDelegate<String?>("flex")
var RuleSet.flexBasis by RuleDelegate<String?>("flex-basis")
var RuleSet.flexDirection by RuleDelegate<String?>("flex-direction")
var RuleSet.flexFlow by RuleDelegate<String?>("flex-flow")
var RuleSet.flexGrow by RuleDelegate<String?>("flex-grow")
var RuleSet.flexShrink by RuleDelegate<String?>("flex-shrink")
var RuleSet.flexWrap by RuleDelegate<String?>("flex-wrap")
var RuleSet.justifyContent by RuleDelegate<JustifyContent?>("justify-content")
var RuleSet.order by RuleDelegate<String?>("order")

// Text Properties
var RuleSet.hangingPunctuation by RuleDelegate<String?>("hanging-punctuation")
var RuleSet.hyphens by RuleDelegate<String?>("hyphens")
var RuleSet.letterSpacing by RuleDelegate<String?>("letter-spacing")
var RuleSet.lineBreak by RuleDelegate<String?>("line-break")
var RuleSet.lineHeight by RuleDelegate<String?>("line-height")
var RuleSet.overflowWrap by RuleDelegate<String?>("overflow-wrap")
var RuleSet.tabSize by RuleDelegate<String?>("tab-size")
var RuleSet.textAlign by RuleDelegate<TextAlign?>("text-align")
var RuleSet.textAlignLast by RuleDelegate<String?>("text-align-last")
var RuleSet.textIndent by RuleDelegate<String?>("text-indent")
var RuleSet.textJustify by RuleDelegate<String?>("text-justify")
var RuleSet.textTransform by RuleDelegate<String?>("text-transform")
var RuleSet.whiteSpace by RuleDelegate<String?>("white-space")
var RuleSet.wordBreak by RuleDelegate<String?>("word-break")
var RuleSet.wordSpacing by RuleDelegate<String?>("word-spacing")
var RuleSet.wordWrap by RuleDelegate<String?>("word-wrap")

// Text Decoration Properties
var RuleSet.textDecoration by RuleDelegate<String?>("text-decoration")
var RuleSet.textDecorationColor by RuleDelegate<Color?>("text-decoration-color")
var RuleSet.textDecorationLine by RuleDelegate<String?>("text-decoration-line")
var RuleSet.textDecorationStyle by RuleDelegate<String?>("text-decoration-style")
var RuleSet.textShadow by RuleDelegate<String?>("text-shadow")
var RuleSet.textUnderlinePosition by RuleDelegate<String?>("text-underline-position")

// Font Properties
var RuleSet.font by RuleDelegate<String?>("font")
var RuleSet.fontFamily by RuleDelegate<String?>("font-family")
var RuleSet.fontFeatureSettings by RuleDelegate<String?>("font-feature-settings")
var RuleSet.fontKerning by RuleDelegate<String?>("font-kerning")
var RuleSet.fontLanguageOverride by RuleDelegate<String?>("font-language-override")
var RuleSet.fontSize by RuleDelegate<LinearDimension?>("font-size")
var RuleSet.fontSizeAdjust by RuleDelegate<String?>("font-size-adjust")
var RuleSet.fontStretch by RuleDelegate<String?>("font-stretch")
var RuleSet.fontStyle by RuleDelegate<String?>("font-style")
var RuleSet.fontSynthesis by RuleDelegate<String?>("font-synthesis")
var RuleSet.fontVariant by RuleDelegate<String?>("font-variant")
var RuleSet.fontVariantAlternates by RuleDelegate<String?>("font-variant-alternates")
var RuleSet.fontVariantCaps by RuleDelegate<String?>("font-variant-caps")
var RuleSet.fontVariantEastAsian by RuleDelegate<String?>("font-variant-east-asian")
var RuleSet.fontVariantLigatures by RuleDelegate<String?>("font-variant-ligatures")
var RuleSet.fontVariantNumeric by RuleDelegate<String?>("font-variant-numeric")
var RuleSet.fontVariantPosition by RuleDelegate<String?>("font-variant-position")
var RuleSet.fontWeight by RuleDelegate<String?>("font-weight")

// Writing Modes Properties
var RuleSet.direction by RuleDelegate<String?>("direction")
var RuleSet.textOrientation by RuleDelegate<String?>("text-orientation")
var RuleSet.textCombineUpright by RuleDelegate<String?>("text-combine-upright")
var RuleSet.unicodeBidi by RuleDelegate<String?>("unicode-bidi")
var RuleSet.writingMode by RuleDelegate<String?>("writing-mode")

// Table Properties
var RuleSet.borderCollapse by RuleDelegate<String?>("border-collapse")
var RuleSet.borderSpacing by RuleDelegate<String?>("border-spacing")
var RuleSet.captionSide by RuleDelegate<String?>("caption-side")
var RuleSet.emptyCells by RuleDelegate<String?>("empty-cells")
var RuleSet.tableLayout by RuleDelegate<String?>("table-layout")

// Lists and Counters Properties
var RuleSet.counterIncrement by RuleDelegate<String?>("counter-increment")
var RuleSet.counterReset by RuleDelegate<String?>("counter-reset")
var RuleSet.listStyle by RuleDelegate<String?>("list-style")
var RuleSet.listStyleImage by RuleDelegate<String?>("list-style-image")
var RuleSet.listStylePosition by RuleDelegate<String?>("list-style-position")
var RuleSet.listStyleType by RuleDelegate<String?>("list-style-type")

// Animation Properties
var RuleSet.animation by RuleDelegate<String?>("animation")
var RuleSet.animationDelay by RuleDelegate<String?>("animation-delay")
var RuleSet.animationDirection by RuleDelegate<String?>("animation-direction")
var RuleSet.animationDuration by RuleDelegate<String?>("animation-duration")
var RuleSet.animationFillMode by RuleDelegate<String?>("animation-fill-mode")
var RuleSet.animationIterationCount by RuleDelegate<String?>("animation-iteration-count")
var RuleSet.animationName by RuleDelegate<String?>("animation-name")
var RuleSet.animationPlayState by RuleDelegate<String?>("animation-play-state")
var RuleSet.animationTimingFunction by RuleDelegate<String?>("animation-timing-function")

// Transform Properties
var RuleSet.backfaceVisibility by RuleDelegate<String?>("backface-visibility")
var RuleSet.perspective by RuleDelegate<String?>("perspective")
var RuleSet.perspectiveOrigin by RuleDelegate<String?>("perspective-origin")
var RuleSet.transform by RuleDelegate<String?>("transform")
var RuleSet.transformOrigin by RuleDelegate<String?>("transform-origin")
var RuleSet.transformStyle by RuleDelegate<String?>("transform-style")

// Transitions Properties
var RuleSet.transition by RuleDelegate<String?>("transition")
var RuleSet.transitionProperty by RuleDelegate<String?>("transition-property")
var RuleSet.transitionDuration by RuleDelegate<String?>("transition-duration")
var RuleSet.transitionTimingFunction by RuleDelegate<String?>("transition-timing-function")
var RuleSet.transitionDelay by RuleDelegate<String?>("transition-delay")

// Basic User Interface Properties
var RuleSet.boxSizing by RuleDelegate<String?>("box-sizing")
var RuleSet.content by RuleDelegate<String?>("content")
var RuleSet.cursor by RuleDelegate<String?>("cursor")
var RuleSet.imeMode by RuleDelegate<String?>("ime-mode")
var RuleSet.navDown by RuleDelegate<String?>("nav-down")
var RuleSet.navIndex by RuleDelegate<String?>("nav-index")
var RuleSet.navLeft by RuleDelegate<String?>("nav-left")
var RuleSet.navRight by RuleDelegate<String?>("nav-right")
var RuleSet.navUp by RuleDelegate<String?>("nav-up")
var RuleSet.outline by RuleDelegate<String?>("outline")
var RuleSet.outlineColor by RuleDelegate<Color?>("outline-color")
var RuleSet.outlineOffset by RuleDelegate<String?>("outline-offset")
var RuleSet.outlineStyle by RuleDelegate<String?>("outline-style")
var RuleSet.outlineWidth by RuleDelegate<String?>("outline-width")
var RuleSet.resize by RuleDelegate<String?>("resize")
var RuleSet.textOverflow by RuleDelegate<String?>("text-overflow")

// Multi-column Layout Properties
var RuleSet.breakAfter by RuleDelegate<String?>("break-after")
var RuleSet.breakBefore by RuleDelegate<String?>("break-before")
var RuleSet.breakInside by RuleDelegate<String?>("break-inside")
var RuleSet.columnCount by RuleDelegate<String?>("column-count")
var RuleSet.columnFill by RuleDelegate<String?>("column-fill")
var RuleSet.columnGap by RuleDelegate<String?>("column-gap")
var RuleSet.columnRule by RuleDelegate<String?>("column-rule")
var RuleSet.columnRuleColor by RuleDelegate<Color?>("column-rule-color")
var RuleSet.columnRuleStyle by RuleDelegate<String?>("column-rule-style")
var RuleSet.columnRuleWidth by RuleDelegate<String?>("column-rule-width")
var RuleSet.columnSpan by RuleDelegate<String?>("column-span")
var RuleSet.columnWidth by RuleDelegate<String?>("column-width")
var RuleSet.columns by RuleDelegate<String?>("columns")
var RuleSet.widows by RuleDelegate<String?>("widows")

// Paged Media
var RuleSet.orphans by RuleDelegate<String?>("orphans")
var RuleSet.pageBreakAfter by RuleDelegate<String?>("page-break-after")
var RuleSet.pageBreakBefore by RuleDelegate<String?>("page-break-before")
var RuleSet.pageBreakInside by RuleDelegate<String?>("page-break-inside")

// Generated Content for Paged Media
var RuleSet.marks by RuleDelegate<String?>("marks")
var RuleSet.quotes by RuleDelegate<String?>("quotes")

// Filter Effects Properties
var RuleSet.filter by RuleDelegate<String?>("filter")

// Image Values and Replaced Content
var RuleSet.imageOrientation by RuleDelegate<String?>("image-orientation")
var RuleSet.imageRendering by RuleDelegate<String?>("image-rendering")
var RuleSet.imageResolution by RuleDelegate<String?>("image-resolution")
var RuleSet.objectFit by RuleDelegate<String?>("object-fit")
var RuleSet.objectPosition by RuleDelegate<String?>("object-position")

// Masking Properties
var RuleSet.mask by RuleDelegate<String?>("mask")
var RuleSet.maskType by RuleDelegate<String?>("mask-type")

// Speech Properties
var RuleSet.mark by RuleDelegate<String?>("mark")
var RuleSet.markAfter by RuleDelegate<String?>("mark-after")
var RuleSet.markBefore by RuleDelegate<String?>("mark-before")
var RuleSet.phonemes by RuleDelegate<String?>("phonemes")
var RuleSet.rest by RuleDelegate<String?>("rest")
var RuleSet.restAfter by RuleDelegate<String?>("rest-after")
var RuleSet.restBefore by RuleDelegate<String?>("rest-before")
var RuleSet.voiceBalance by RuleDelegate<String?>("voice-balance")
var RuleSet.voiceDuration by RuleDelegate<String?>("voice-duration")
var RuleSet.voicePitch by RuleDelegate<String?>("voice-pitch")
var RuleSet.voicePitchRange by RuleDelegate<String?>("voice-pitch-range")
var RuleSet.voiceRate by RuleDelegate<String?>("voice-rate")
var RuleSet.voiceStress by RuleDelegate<String?>("voice-stress")
var RuleSet.voiceVolume by RuleDelegate<String?>("voice-volume")

// Marquee Properties
var RuleSet.marqueeDirection by RuleDelegate<String?>("marquee-direction")
var RuleSet.marqueePlayCount by RuleDelegate<String?>("marquee-play-count")
var RuleSet.marqueeSpeed by RuleDelegate<String?>("marquee-speed")
var RuleSet.marqueeStyle by RuleDelegate<String?>("marquee-style")

// Exotic
var RuleSet.zoom by RuleDelegate<String?>("zoom")
var RuleSet.src by RuleDelegate<String?>("src") // @font-face

var RuleSet.isScrollableVertically by ScrollableRuleDelegate(isVertically = true)
var RuleSet.isScrollableHorizontally by ScrollableRuleDelegate(isVertically = false)

fun RuleSet.active(rules: RuleSet.() -> Unit) = this.addPseudo(":active", rules)
fun RuleSet.after(rules: RuleSet.() -> Unit) = this.addPseudo(":after", rules)
fun RuleSet.before(rules: RuleSet.() -> Unit) = this.addPseudo(":before", rules)
fun RuleSet.checked(rules: RuleSet.() -> Unit) = this.addPseudo(":checked", rules)
fun RuleSet.disabled(rules: RuleSet.() -> Unit) = this.addPseudo(":disabled", rules)
fun RuleSet.empty(rules: RuleSet.() -> Unit) = this.addPseudo(":empty", rules)
fun RuleSet.enabled(rules: RuleSet.() -> Unit) = this.addPseudo(":enabled", rules)
fun RuleSet.firstChild(rules: RuleSet.() -> Unit) = this.addPseudo(":first-child", rules)
fun RuleSet.firstLetter(rules: RuleSet.() -> Unit) = this.addPseudo(":first-letter", rules)
fun RuleSet.firstLine(rules: RuleSet.() -> Unit) = this.addPseudo(":first-line", rules)
fun RuleSet.firstOfType(rules: RuleSet.() -> Unit) = this.addPseudo(":first-of-type", rules)
fun RuleSet.focus(rules: RuleSet.() -> Unit) = this.addPseudo(":focus", rules)
fun RuleSet.hover(rules: RuleSet.() -> Unit) = this.addPseudo(":hover", rules)
fun RuleSet.inRange(rules: RuleSet.() -> Unit) = this.addPseudo(":in-range", rules)
fun RuleSet.invalid(rules: RuleSet.() -> Unit) = this.addPseudo(":invalid", rules)
fun RuleSet.lastChild(rules: RuleSet.() -> Unit) = this.addPseudo(":last-child", rules)
fun RuleSet.lastOfType(rules: RuleSet.() -> Unit) = this.addPseudo(":last-of-type", rules)
fun RuleSet.onlyChild(rules: RuleSet.() -> Unit) = this.addPseudo(":only-child", rules)
fun RuleSet.onlyOfType(rules: RuleSet.() -> Unit) = this.addPseudo(":only-of-type", rules)
fun RuleSet.optional(rules: RuleSet.() -> Unit) = this.addPseudo(":optional", rules)
fun RuleSet.outOfRange(rules: RuleSet.() -> Unit) = this.addPseudo(":out-of-range", rules)
fun RuleSet.readOnly(rules: RuleSet.() -> Unit) = this.addPseudo(":read-only", rules)
fun RuleSet.readWrite(rules: RuleSet.() -> Unit) = this.addPseudo(":read-write", rules)
fun RuleSet.required(rules: RuleSet.() -> Unit) = this.addPseudo(":required", rules)
fun RuleSet.root(rules: RuleSet.() -> Unit) = this.addPseudo(":root", rules)
fun RuleSet.selection(rules: RuleSet.() -> Unit) = this.addPseudo(":selection", rules)
fun RuleSet.target(rules: RuleSet.() -> Unit) = this.addPseudo(":target", rules)
fun RuleSet.unvisited(rules: RuleSet.() -> Unit) = this.addPseudo(":link", rules)
fun RuleSet.valid(rules: RuleSet.() -> Unit) = this.addPseudo(":valid", rules)
fun RuleSet.visited(rules: RuleSet.() -> Unit) = this.addPseudo(":visited", rules)

fun RuleSet.media(name: String, rules: RuleSet.() -> Unit) = this.addAtRule("media $name", rules)

fun Keyframes.from(rules: RuleSet.() -> Unit) = this.addKeyframeRule("from", rules)
fun Keyframes.to(rules: RuleSet.() -> Unit) = this.addKeyframeRule("to", rules)
fun Keyframes.percent(percent: Number, rules: RuleSet.() -> Unit) = this.addKeyframeRule("$percent%", rules)
fun Keyframes.custom(value: String, rules: RuleSet.() -> Unit) = this.addKeyframeRule(value, rules)

val Selector.active get() = PseudoSelector(this, ":active")
val Selector.after get() = PseudoSelector(this, ":after")
val Selector.before get() = PseudoSelector(this, ":before")
val Selector.checked get() = PseudoSelector(this, ":checked")
val Selector.disabled get() = PseudoSelector(this, ":disabled")
val Selector.empty get() = PseudoSelector(this, ":empty")
val Selector.enabled get() = PseudoSelector(this, ":enabled")
val Selector.firstChild get() = PseudoSelector(this, ":first-child")
val Selector.firstLetter get() = PseudoSelector(this, ":first-letter")
val Selector.firstLine get() = PseudoSelector(this, ":first-line")
val Selector.firstOfType get() = PseudoSelector(this, ":first-of-type")
val Selector.focus get() = PseudoSelector(this, ":focus")
val Selector.hover get() = PseudoSelector(this, ":hover")
val Selector.inRange get() = PseudoSelector(this, ":in-range")
val Selector.invalid get() = PseudoSelector(this, ":invalid")
val Selector.lastChild get() = PseudoSelector(this, ":last-child")
val Selector.lastOfType get() = PseudoSelector(this, ":last-of-type")
val Selector.onlyChild get() = PseudoSelector(this, ":only-child")
val Selector.onlyOfType get() = PseudoSelector(this, ":only-of-type")
val Selector.optional get() = PseudoSelector(this, ":optional")
val Selector.outOfRange get() = PseudoSelector(this, ":out-of-range")
val Selector.readOnly get() = PseudoSelector(this, ":read-only")
val Selector.readWrite get() = PseudoSelector(this, ":read-write")
val Selector.required get() = PseudoSelector(this, ":required")
val Selector.root get() = PseudoSelector(this, ":root")
val Selector.selection get() = PseudoSelector(this, ":selection")
val Selector.target get() = PseudoSelector(this, ":target")
val Selector.unvisited get() = PseudoSelector(this, ":link")
val Selector.valid get() = PseudoSelector(this, ":valid")
val Selector.visited get() = PseudoSelector(this, ":visited")

// With selector
//fun RuleSet.hover(affectedSelector: Selector, rules: RuleSet.() -> Unit) = this.addPseudo(":hover", rules)

var RuleSet.end: LinearDimension?
    get() = if (useRtl) left else right
    set(value) {
        if (useRtl) left = value else right = value
    }

var RuleSet.start: LinearDimension?
    get() = if (useRtl) right else left
    set(value) {
        if (useRtl) right = value else left = value
    }

var CSSStyleDeclaration.end: String
    get() = if (useRtl) left else right
    set(value) {
        if (useRtl) left = value else right = value
    }

var CSSStyleDeclaration.start: String
    get() = if (useRtl) right else left
    set(value) {
        if (useRtl) right = value else left = value
    }

var RuleSet.marginEnd: LinearDimension?
    get() = if (useRtl) marginLeft else marginRight
    set(value) {
        if (useRtl) marginLeft = value else marginRight = value
    }

var RuleSet.marginStart: LinearDimension?
    get() = if (useRtl) marginRight else marginLeft
    set(value) {
        if (useRtl) marginRight = value else marginLeft = value
    }


var RuleSet.paddingEnd: LinearDimension?
    get() = if (useRtl) paddingLeft else paddingRight
    set(value) {
        if (useRtl) paddingLeft = value else paddingRight = value
    }

var RuleSet.paddingStart: LinearDimension?
    get() = if (useRtl) paddingRight else paddingLeft
    set(value) {
        if (useRtl) paddingRight = value else paddingLeft = value
    }

var RuleSet.navEnd: String?
    get() = if (useRtl) navLeft else navRight
    set(value) {
        if (useRtl) navLeft = value else navRight = value
    }

var RuleSet.navStart: String?
    get() = if (useRtl) navRight else navLeft
    set(value) {
        if (useRtl) navRight = value else navLeft = value
    }


var RuleSet.borderEnd: String?
    get() = if (useRtl) borderLeft else borderRight
    set(value) {
        if (useRtl) borderLeft = value else borderRight = value
    }

var RuleSet.borderStart: String?
    get() = if (useRtl) borderRight else borderLeft
    set(value) {
        if (useRtl) borderRight = value else borderLeft = value
    }

var RuleSet.borderBottomEndRadius: LinearDimension?
    get() = if (useRtl) borderBottomLeftRadius else borderBottomRightRadius
    set(value) {
        if (useRtl) borderBottomLeftRadius = value else borderBottomRightRadius = value
    }

var RuleSet.borderBottomStartRadius: LinearDimension?
    get() = if (useRtl) borderBottomRightRadius else borderBottomLeftRadius
    set(value) {
        if (useRtl) borderBottomRightRadius = value else borderBottomLeftRadius = value
    }

var RuleSet.borderTopEndRadius: LinearDimension?
    get() = if (useRtl) borderTopLeftRadius else borderTopRightRadius
    set(value) {
        if (useRtl) borderTopLeftRadius = value else borderTopRightRadius = value
    }

var RuleSet.borderTopStartRadius: LinearDimension?
    get() = if (useRtl) borderTopRightRadius else borderTopLeftRadius
    set(value) {
        if (useRtl) borderTopRightRadius = value else borderTopLeftRadius = value
    }


var RuleSet.borderEndColor: Color?
    get() = if (useRtl) borderLeftColor else borderRightColor
    set(value) {
        if (useRtl) borderLeftColor = value else borderRightColor = value
    }


var RuleSet.borderStartColor: Color?
    get() = if (useRtl) borderRightColor else borderLeftColor
    set(value) {
        if (useRtl) borderRightColor = value else borderLeftColor = value
    }

var RuleSet.borderEndStyle: String?
    get() = if (useRtl) borderLeftStyle else borderRightStyle
    set(value) {
        if (useRtl) borderLeftStyle = value else borderRightStyle = value
    }

var RuleSet.borderStartStyle: String?
    get() = if (useRtl) borderRightStyle else borderLeftStyle
    set(value) {
        if (useRtl) borderRightStyle = value else borderLeftStyle = value
    }

var RuleSet.borderEndWidth: String?
    get() = if (useRtl) borderLeftWidth else borderRightWidth
    set(value) {
        if (useRtl) borderLeftWidth = value else borderRightWidth = value
    }

var RuleSet.borderStartWidth: String?
    get() = if (useRtl) borderRightWidth else borderLeftWidth
    set(value) {
        if (useRtl) borderRightWidth = value else borderLeftWidth = value
    }



inline class Alignment(
        val name: String
) {
    override fun toString() = name

    companion object {
        val Start = Alignment("flex-start")
        val End = Alignment("flex-end")
        val Center = Alignment("center")
        val Baseline = Alignment("baseline")
        val Stretch = Alignment("stretch")
    }
}

inline class JustifyContent(
        val name: String
) {
    override fun toString() = name

    companion object {
        val Start = JustifyContent("flex-start")
        val End = JustifyContent("flex-end")
        val Center = JustifyContent("center")
        val SpaceBetween = JustifyContent("space-between")
        val SpaceAround = JustifyContent("space-around")
        val SpaceEvenly = JustifyContent("space-evenly")
    }
}

inline class TextAlign(val name: String) {
    override fun toString() = name

    companion object {
        val Left = TextAlign("left")
        val Right = TextAlign("right")
        val Start = if (useRtl) TextAlign("right")
        else TextAlign("left")
        val End = if (useRtl) TextAlign("left")
        else TextAlign("right")
        val Center = TextAlign("center")
        val Justify = TextAlign("justify")
        val Initial = TextAlign("initial")
        val Inherit = TextAlign("inherit")
    }
}


fun classRuleSet(classNamePrefix: String? = null, rules: RuleSet.() -> Unit): RuleSet {
    val className = ClassNameGenerator.getClassName(classNamePrefix)
    val selector = ClassSelector(className)
    val ruleSet = RuleSet(selector).apply { rules() }
    addRuleSetToDocument(ruleSet)
    return ruleSet
}

fun classRuleSet(classNamePrefix: String? = null, ruleSet: RuleSet): RuleSet {
    val className = ClassNameGenerator.getClassName(classNamePrefix)
    val selector = ClassSelector(className)
    ruleSet.selector = selector
    addRuleSetToDocument(ruleSet)
    return ruleSet
}

fun stringRuleSet(selector: String, rules: RuleSet.() -> Unit): RuleSet {
    val stringSelector = StringSelector(selector)
    val ruleSet = RuleSet(stringSelector).apply { rules() }
    addRuleSetToDocument(ruleSet)
    return ruleSet
}

fun keyframes(userIndent: String, keyframesRules: Keyframes.() -> Unit): Keyframes {
    val keyframes = Keyframes(userIndent).apply { keyframesRules() }
    addKeyframesToDocument(keyframes)
    return keyframes
}

private fun addRuleSetToDocument(ruleSet: RuleSet) {
    val sheetElement = getOrCreateKunafaSheet()
    val sheet = sheetElement.sheet as? CSSStyleSheet
    ruleSet.toRulesList().forEach {
        sheet?.insertRule(it.toString(), sheet.cssRules.length)
    }
}

private var kunafaStyleElement: Element? = null
private fun getOrCreateKunafaSheet(): HTMLStyleElement {
    val existingElement = kunafaStyleElement ?: document.getElementById("kunafa-styles")
    val element = existingElement ?: createNewStyleElement()
    return element as HTMLStyleElement
}

private fun createNewStyleElement(): Element {
    val element = document.createElement("style").apply {
        id = "kunafa-styles"
        document.head?.appendChild(this)
    }
    kunafaStyleElement = element
    return element
}

private fun addKeyframesToDocument(keyframes: Keyframes) {
    val sheetElement = getOrCreateKunafaSheet()
    val sheet = sheetElement.sheet as? CSSStyleSheet
    sheet?.insertRule(keyframes.toString(), sheet.cssRules.length)
}