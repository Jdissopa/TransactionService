
import spray.json._

import scala.collection.mutable

case class Transaction(amount: Double, `type`:String, parent_id:Option[Long])
case class TransactionCollection(transactions: mutable.Map[Long, Transaction] = mutable.Map.empty[Long, Transaction])

object Transaction extends DefaultJsonProtocol {
  implicit val transactionFormat = jsonFormat3(Transaction.apply)

  implicit object transactionCollectionFormat extends RootJsonFormat[TransactionCollection] {
    def read(value: JsValue) = ???
    def write(f: TransactionCollection) = JsObject(
      Map(
        "transaction" -> JsArray(f.transactions.map(_.toJson).toList)
      )
    )
  }
}