package pl.metastack.metaweb.tag

import pl.metastack.metarx._
import pl.metastack.metaweb.state

/**
 * The HTML Acronym Element (<code>&lt;acronym&gt;)</code> allows authors to clearly indicate a sequence of characters that compose an acronym or abbreviation for a word.
<p><strong>Usage note: </strong>This element has been removed in HTML5 and shouldn't be used anymore. Instead web developers should use the <a href="/en-US/docs/Web/HTML/Element/abbr" title="The HTML <abbr> Element (or HTML Abbreviation Element) represents an abbreviation and optionally provides a full description for it. If present, the title attribute must contain this full description and nothing else."><code>&lt;abbr&gt;</code></a> element.</p>
 */
class Acronym extends state.Tag("acronym") with HTMLTag {
}