(ns moc.routes.dispatch)

(defmulti dispatch :bidi/id)

(defmethod dispatch nil [req]
  (println req)
  {:status 404})
