package pl.metastack.metaweb.macros

import java.io.File

import scala.language.reflectiveCalls
import scala.language.experimental.macros
import scala.reflect.macros.blackbox.Context

import pl.metastack.metaweb._
import pl.metastack.metaweb.internal.HtmlParser

object ExternalHtml {
  trait Method {
    /** Expose `html` as a global method */
    def html(fileName: String): Tag = macro HtmlImpl
  }

  def convert(c: Context)(node: Node, root: Boolean): c.Expr[Node] = {
    import c.universe._

    node match {
      case Text(text) => c.Expr(q"pl.metastack.metaweb.Text($text)")
      case tag: Tag =>
        val tagType = TypeName(tag.tagName.capitalize)
        val tagAttrs = tag.attributes.mapValues(_.toString)
        val tagChildren = tag.children.map(convert(c)(_, root = false))

        try {
          c.typecheck(q"val x: pl.metastack.metaweb.tag.$tagType")
          c.Expr(q"new pl.metastack.metaweb.tag.$tagType($tagAttrs, Seq(..$tagChildren))")
        } catch { case t: Throwable =>
          c.Expr(q"pl.metastack.metaweb.tag.HTMLTag.fromTag(${tag.tagName}, $tagAttrs, Seq(..$tagChildren))")
        }
    }
  }

  def HtmlImpl(c: Context)(fileName: c.Expr[String]): c.Expr[Tag] = {
    val fileNameValue = Helpers.literalValueExpr(c)(fileName)
    val html = io.Source.fromFile(new File(fileNameValue)).mkString
    val node = HtmlParser.fromString(html)
    convert(c)(node, root = true)
      .asInstanceOf[c.Expr[Tag]]
  }
}