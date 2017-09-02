package pine

import pine.internal.ParseError

import scala.collection.immutable.StringOps

object HtmlHelpers {
  /** An end tag must not specified for void elements. List of elements taken
    * from http://www.w3.org/TR/html-markup/syntax.html#syntax-elements
    */
  val VoidElements = Set("area", "base", "br", "col", "command", "embed", "hr",
    "img", "input", "keygen", "link", "meta", "param", "source", "track", "wbr")

  /** Content of these tags is treated as raw text */
  val CdataElements = Set("script", "style")

  /** Boolean attributes don't require a value */
  val BooleanAttributes = Set("allowfullscreen", "async", "autofocus",
    "autoplay", "checked", "compact", "controls", "declare", "default",
    "defaultchecked", "defaultmuted", "defaultselected", "defer", "disabled",
    "draggable", "enabled", "formnovalidate", "hidden", "indeterminate", "inert",
    "ismap", "itemscope", "loop", "multiple", "muted", "nohref", "noresize",
    "noshade", "novalidate", "nowrap", "open", "pauseonexit", "readonly",
    "required", "reversed", "scoped", "seamless", "selected", "sortable",
    "spellcheck", "translate", "truespeed", "typemustmatch", "visible")

  /** @note < and > do not need to be escaped */
  def encodeAttributeValue(value: Any): String =
    "\"" +
      value.toString
        .replaceAll("&", "&amp;")
        .replaceAll("\"", "&quot;") +
      "\""

  def decodeAttributeValue(value: String): String =
    value
      .replaceAll("&lt;",   "<")
      .replaceAll("&gt;",   ">")
      .replaceAll("&amp;",  "&")
      .replaceAll("&quot;", "\"")

  /**
    * @note " and ' may be represented by &quot; or &apos;, but this is not
    *       compulsory
    * @see scala.xml.Utility.escape()
    */
  def encodeText(text: String, xml: Boolean): String =
    (text: StringOps).flatMap { c =>
      c match {
        case '<' => "&lt;"
        case '>' => "&gt;"

        // TODO In HTML5, we only have to replace ampersands if they are
        // ambiguous. As per:
        // https://www.w3.org/TR/html5/syntax.html#syntax-ambiguous-ampersand
        //
        // > An ambiguous ampersand is a U+0026 AMPERSAND character (&) that is
        // > followed by one or more alphanumeric ASCII characters, followed by a
        // > ";" (U+003B) character, where these characters do not match any of
        // > the names given in the named character references section.
        case '&' => "&amp;"

        case '\r' | '\n' | '\t' => c.toString
        case _ if c >= ' ' => c.toString
        case _ => ""
      }
    }

  /** From http://hohonuuli.blogspot.com/2012/10/simple-hex-string-to-ascii-function-for.html */
  def parseHexBinary(hex: String): String = {
    val sb = new StringBuilder

    for (i <- 0 until hex.length by 2) {
      val str = hex.substring(i, i + 2)
      sb.append(Integer.parseInt(str, 16).toChar)
    }

    sb.toString
  }

  private def stripLeading(str: String, c: Char): String =
    if (str.isEmpty) str
    else {
      var i = 0
      while (str(i) == c) i += 1
      str.substring(i)
    }

  def decodeEntity(e: String, xml: Boolean): Option[String] =
    if (e.startsWith("#x0"))
      Some(new String(parseHexBinary(stripLeading(e.substring(3), '0'))))
    else if (e.startsWith("#")) Some(e.substring(1).toInt.toChar.toString)
    else if (e.isEmpty) None
    else if (xml) XmlEntities.entities.get(e)
    else          HtmlEntities.entities.get(e)

  def decodeText(text: String, xml: Boolean): String = {
    val reader = new Reader(text)

    def f(): String =
      reader.collect('&') match {
        case None         => reader.rest()
        case Some(prefix) =>
          reader.collect(';') match {
            case None    => reader.rest()
            case Some(e) =>
              val ent = decodeEntity(e, xml).getOrElse(
                throw new ParseError(s"Invalid entity '$e'"))
              prefix + ent + f()
          }
      }

    f()
  }

  def encodeAttributes(attributes: Map[String, Any]): String =
    attributes.map { case (key, value) =>
      s"$key=" + encodeAttributeValue(value)
    }.mkString(" ")

  def node(tagName: String,
           attributes: Map[String, Any],
           contents: List[String],
           xml: Boolean): String = {
    val attrs =
      if (attributes.isEmpty) ""
      else s" ${encodeAttributes(attributes)}"

    // As Pine does not support DTDs, we do not know which elements were
    // declared EMPTY.
    // See also https://www.w3.org/TR/REC-xml/#NT-EmptyElemTag
    if (!xml && HtmlHelpers.VoidElements.contains(tagName) && contents.isEmpty)
      s"<$tagName$attrs/>"
    else {
      val children = contents.mkString
      s"<$tagName$attrs>$children</$tagName>"
    }
  }
}
