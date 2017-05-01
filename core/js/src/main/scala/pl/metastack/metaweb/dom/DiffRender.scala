package pl.metastack.metaweb.dom

import pl.metastack.metaweb
import pl.metastack.metaweb.HtmlHelpers

object DiffRender {
  def render(diff: metaweb.Diff): Unit =
    diff match {
      case metaweb.Diff.SetAttribute(ref, attribute, value) =>
        val dom = DOM.get(ref)
        if (!HtmlHelpers.BooleanAttributes.contains(attribute.name))
          dom.setAttribute(attribute.name, value.toString)
        else {
          if (value.asInstanceOf[Boolean]) dom.setAttribute(attribute.name, "")
          else dom.removeAttribute(attribute.name)
        }

      case metaweb.Diff.UpdateAttribute(ref, attribute, f) =>
        val dom = DOM.get(ref)
        if (HtmlHelpers.BooleanAttributes.contains(attribute.name)) {
          val fBoolean = f.asInstanceOf[Boolean => Boolean]
          val current = dom.hasAttribute(attribute.name)
          if (fBoolean(current)) dom.setAttribute(attribute.name, "")
          else                   dom.removeAttribute(attribute.name)
        } else {
          val fString = f.asInstanceOf[Option[String] => Option[String]]
          val current = Option(dom.getAttribute(attribute.name))
          fString(current) match {
            case None    => dom.removeAttribute(attribute.name)
            case Some(s) => dom.setAttribute(attribute.name, s)
          }
        }

      case metaweb.Diff.RemoveAttribute(ref, attribute) =>
        DOM.get(ref).removeAttribute(attribute.name)

      case metaweb.Diff.ReplaceChildren(ref, children) =>
        val dom = DOM.get(ref)
        dom.clear()
        children.foreach(child => dom.appendChild(DOM.render(child)))

      case metaweb.Diff.Replace(ref, replacement) =>
        val dom = DOM.get(ref)
        dom.parentNode.replaceChild(DOM.render(replacement), dom)

      case metaweb.Diff.PrependChild(ref, child) =>
        DOM.get(ref).prependChild(DOM.render(child))

      case metaweb.Diff.AppendChild(ref, child) =>
        DOM.get(ref).appendChild(DOM.render(child))

      case metaweb.Diff.RemoveChild(ref) =>
        val dom = DOM.get(ref)
        dom.parentNode.removeChild(dom)

      case metaweb.Diff.Noop =>
    }

  def render(diff: Diff): Unit =
    diff match {
      case Diff.SetEvent(set, f) => set(f)
      case Diff.DetachEvent(set) => set(null)
    }
}
