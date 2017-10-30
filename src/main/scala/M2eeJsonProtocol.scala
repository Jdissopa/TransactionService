import spray.json._

object M2eeJsonProtocol extends DefaultJsonProtocol {

  implicit object MapJsonFormat extends JsonFormat[Map[String, Any]] {
  def write(m: Map[String, Any]) = {
    JsObject(m.mapValues {
      case v: String => JsString(v)
      case v: Int => JsNumber(v)
      case v: Map[String, Any] => write(v)
      case v: Any => JsString(v.toString)
    })
  }

    def read(value: JsValue) = ???
  }
}