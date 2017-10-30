import akka.actor.Actor
import spray.http.{MediaTypes, StatusCodes}
import spray.routing.HttpService

class TransactionActor extends Actor with TransactionRoute {
  def actorRefFactory = context
  def receive = runRoute(route)
}

trait TransactionRoute extends HttpService {

  import spray.httpx.SprayJsonSupport._
  import Transaction._
  import TransactionMethods._

  val tCollection = TransactionCollection()

  val route = {
    pathPrefix("transactionservice") {
      respondWithMediaType(MediaTypes.`application/json`) {
        path("transaction" / Segment) { (id) =>
          if(id.forall(_.isDigit)) {
            put {
              entity(as[Transaction]) { trans =>
                tCollection.put(id.toLong, trans)
                complete(Map("status" -> "ok"))
              }
            } ~
            get {
              complete(tCollection.get(id.toLong))
            }
          } else
            complete(StatusCodes.NotAcceptable, Map("error" -> "Transaction ID must be number"))
        } ~
        path("types" / Segment) { (`type`) =>
          get {
            complete(tCollection.getTransactionsWithType(`type`))
          }
        } ~
        path("sum" / Segment) { (id) =>
          if(id.forall(_.isDigit)) {
            get {
              complete(Map("sum" -> tCollection.sum(id.toLong)))
            }
          } else
            complete(StatusCodes.NotAcceptable, Map("error" -> "Transaction ID must be number"))
        }
      }
    }
  }
}

