(ns moc.log)

(defn- logger [level message]
  (let [debug-info (str "[" level "]")]
    (locking *out*
      (println debug-info message)
      (flush))))

(defn debug [& args]
  (logger "DEBUG" (apply str args)))

(defn info [& args]
  (logger "INFO" (apply str args)))

(defn warning [& args]
  (logger "WARNING" (apply str args)))

(defn error [& args]
  (logger "ERROR" (apply str args)))

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
