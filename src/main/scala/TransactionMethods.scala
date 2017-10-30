
object TransactionMethods{
  implicit class TransactionDataProcess(tc: TransactionCollection) {
    def sum(transactionId: Long): Double = {
      val t = tc.transactions.find(_._1 == transactionId)

      t match {
        case Some((x, y)) =>
          val amount = y.amount
          amount + tc.transactions.filter(_._2.parent_id == Some(x)).map(_._2.amount).sum
        case _ => 0.0
      }
    }

    def put(id: Long, t: Transaction): Unit = tc.transactions.put(id, t)

    def get(id: Long): Option[Transaction] = tc.transactions.get(id)

    def getTransactionsWithType(t: String): List[Long] = tc.transactions.filter(_._2.`type` == t).keys.toList
  }
}