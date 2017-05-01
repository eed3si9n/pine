package pl.metastack.metaweb.dom

import org.scalajs.dom

object Document {
  def dragEnd: Event[dom.DragEvent] =
    new Event(dom.document.ondragend = _)
  def keyDown: Event[dom.KeyboardEvent] =
    new Event(dom.document.onkeydown = _)
  def dragOver: Event[dom.DragEvent] =
    new Event(dom.document.ondragover = _)
  def keyUp: Event[dom.KeyboardEvent] =
    new Event(dom.document.onkeyup = _)
  def reset: Event[dom.Event] =
    new Event(dom.document.onreset = _)
  def mouseUp: Event[dom.MouseEvent] =
    new Event(dom.document.onmouseup = _)
  def dragStart: Event[dom.DragEvent] =
    new Event(dom.document.ondragstart = _)
  def drag: Event[dom.DragEvent] =
    new Event(dom.document.ondrag = _)
  def mouseOver: Event[dom.MouseEvent] =
    new Event(dom.document.onmouseover = _)
  def dragLeave: Event[dom.DragEvent] =
    new Event(dom.document.ondragleave = _)
  def pause: Event[dom.Event] =
    new Event(dom.document.onpause = _)
  def mouseDown: Event[dom.MouseEvent] =
    new Event(dom.document.onmousedown = _)
  def seeked: Event[dom.Event] =
    new Event(dom.document.onseeked = _)
  def click: Event[dom.MouseEvent] =
    new Event(dom.document.onclick = _)
  def waiting: Event[dom.Event] =
    new Event(dom.document.onwaiting = _)
  def durationChange: Event[dom.Event] =
    new Event(dom.document.ondurationchange = _)
  def blur: Event[dom.FocusEvent] =
    new Event(dom.document.onblur = _)
  def emptied: Event[dom.Event] =
    new Event(dom.document.onemptied = _)
  def seeking: Event[dom.Event] =
    new Event(dom.document.onseeking = _)
  def canPlay: Event[dom.Event] =
    new Event(dom.document.oncanplay = _)
  def stalled: Event[dom.Event] =
    new Event(dom.document.onstalled = _)
  def mouseMove: Event[dom.MouseEvent] =
    new Event(dom.document.onmousemove = _)
  def rateChange: Event[dom.Event] =
    new Event(dom.document.onratechange = _)
  def loadStart: Event[dom.Event] =
    new Event(dom.document.onloadstart = _)
  def dragEnter: Event[dom.DragEvent] =
    new Event(dom.document.ondragenter = _)
  def submit: Event[dom.Event] =
    new Event(dom.document.onsubmit = _)
  // def progress: DomEvent[js.Any] =
  //    new DomEvent(dom.document.onprogress = _)
  def dblClick: Event[dom.MouseEvent] =
    new Event(dom.document.ondblclick = _)
  def contextMenu: Event[dom.MouseEvent] =
    new Event(dom.document.oncontextmenu = _)
  def change: Event[dom.Event] =
    new Event(dom.document.onchange = _)
  def loadedMetadata: Event[dom.Event] =
    new Event(dom.document.onloadedmetadata = _)
  def play: Event[dom.Event] =
    new Event(dom.document.onplay = _)
  def error: Event[dom.Event] =
    new Event(dom.document.onerror = _)
  def playing: Event[dom.Event] =
    new Event(dom.document.onplaying = _)
  def canPlayThrough: Event[dom.Event] =
    new Event(dom.document.oncanplaythrough = _)
  def abort: Event[dom.UIEvent] =
    new Event(dom.document.onabort = _)
  def readyStateChange: Event[dom.Event] =
    new Event(dom.document.onreadystatechange = _)
  def keyPress: Event[dom.KeyboardEvent] =
    new Event(dom.document.onkeypress = _)
  def loadedData: Event[dom.Event] =
    new Event(dom.document.onloadeddata = _)
  def suspend: Event[dom.Event] =
    new Event(dom.document.onsuspend = _)
  def focus: Event[dom.FocusEvent] =
    new Event(dom.document.onfocus = _)
  def timeUpdate: Event[dom.Event] =
    new Event(dom.document.ontimeupdate = _)
  def select: Event[dom.UIEvent] =
    new Event(dom.document.onselect = _)
  def drop: Event[dom.DragEvent] =
    new Event(dom.document.ondrop = _)
  def mouseOut: Event[dom.MouseEvent] =
    new Event(dom.document.onmouseout = _)
  def ended: Event[dom.Event] =
    new Event(dom.document.onended = _)
  def scroll: Event[dom.UIEvent] =
    new Event(dom.document.onscroll = _)
  def mouseWheel: Event[dom.WheelEvent] =
    new Event(dom.document.onmousewheel = _)
  def load: Event[dom.Event] =
    new Event(dom.document.onload = _)
  def volumeChange: Event[dom.Event] =
    new Event(dom.document.onvolumechange = _)
  def input: Event[dom.Event] =
    new Event(dom.document.oninput = _)
}
