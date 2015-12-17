(ns moc.log)

(defn- logger [level message]
  (locking *out*
    (println level message)))

(defn debug [msg]
  (logger "[DEBUG]" msg))

(defn info [msg]
  (logger "[INFO]" msg))

(defn warning [msg]
  (logger "[WARNING]" msg))

(defn error [msg]
  (logger "[ERROR]" msg))

(defn exception [^Exception e]
  (locking *out*
    (println "[ERROR]" (.toString e))
    (.printStackTrace e)
    (flush)))


(defn wrap-with-logger [handler]
  (fn [req]
    (let [start-time (System/nanoTime)
          res (handler req)
          ms (str (quot (- (System/nanoTime) start-time) 1000000) "ms")]
      (info (str (:uri req)
                 " "
                 (:status res)
                 " "
                 ms
                 " "
                 (pr-str (:params res))))
      res)))
