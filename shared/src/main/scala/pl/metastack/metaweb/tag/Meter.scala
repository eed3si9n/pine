package pl.metastack.metaweb.tag

import pl.metastack.metarx._
import pl.metastack.metaweb.state

/**
 * The <strong>HTML <code>&lt;meter&gt;</code> Element</strong> represents either a scalar value within a known range or a fractional value.
<strong>Usage note: </strong>Unless the 
<strong>value</strong> attribute is between 0 and 1 (inclusive), the 
<strong>min</strong> attribute and 
<strong>max</strong> attribute should define the range so that the 
<strong>value</strong> attribute's value is within it.
 */
class Meter extends state.Tag("meter") with HTMLTag {
  /**
   * The current numeric value. This must be between the minimum and maximum values (
<strong>min</strong> attribute and 
<strong>max</strong> attribute) if they are specified. If unspecified or malformed, the value is 0. If specified, but not within the range given by the 
<strong>min</strong> attribute and 
<strong>max</strong> attribute, the value is equal to the nearest end of the range.
   */
  def value: StateChannel[String] = attribute("value").asInstanceOf[StateChannel[String]]
  /**
   * The lower numeric bound of the measured range. This must be less than the maximum value (
<strong>max</strong> attribute), if specified. If unspecified, the minimum value is 0.
   */
  def min: StateChannel[Double] = attribute("min").asInstanceOf[StateChannel[Double]]
  /**
   * The upper numeric bound of the measured range. This must be greater than the minimum value (
<strong>min</strong> attribute), if specified. If unspecified, the maximum value is 1.
   */
  def max: StateChannel[Double] = attribute("max").asInstanceOf[StateChannel[Double]]
  /**
   * The upper numeric bound of the low end of the measured range. This must be greater than the minimum value (
<strong>min</strong> attribute), and it also must be less than the high value and maximum value (
<strong>high</strong> attribute and 
<strong>max</strong> attribute, respectively), if any are specified. If unspecified, or if less than the minimum value, the 
<strong>low</strong> value is equal to the minimum value.
   */
  def low: StateChannel[Double] = attribute("low").asInstanceOf[StateChannel[Double]]
  /**
   * The lower numeric bound of the high end of the measured range. This must be less than the maximum value (
<strong>max</strong> attribute), and it also must be greater than the low value and minimum value (
<strong>low</strong> attribute and 
<strong>min</strong> attribute, respectively), if any are specified. If unspecified, or if greater than the maximum value, the 
<strong>high</strong> value is equal to the maximum value.
   */
  def high: StateChannel[Double] = attribute("high").asInstanceOf[StateChannel[Double]]
  /**
   * This attribute indicates the optimal numeric value. It must be within the range (as defined by the 
<strong>min</strong> attribute and 
<strong>max</strong> attribute). When used with the 
<strong>low</strong> attribute and 
<strong>high</strong> attribute, it gives an indication where along the range is considered preferable. For example, if it is between the 
<strong>min</strong> attribute and the 
<strong>low</strong> attribute, then the lower range is considered preferred.
   */
  def optimum: StateChannel[Double] = attribute("optimum").asInstanceOf[StateChannel[Double]]
  /**
   * This attribute associates the element with a 
<code>form</code> element that has ownership of the 
<code>meter</code> element. For example, a 
<code>meter</code> might be displaying a range corresponding to an 
<code>input</code> element of 
<strong>type</strong> 
<em>number</em>. This attribute is only used if the 
<code>meter</code> element is being used as a form-associated element; even then, it may be omitted if the element appears as a descendant of a 
<code>form</code> element.
   */
  def form: StateChannel[String] = attribute("form").asInstanceOf[StateChannel[String]]
}