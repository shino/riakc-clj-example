(ns RiakCljExample.core)

(defn wrap-p-tag [str-seq]
  (map #(format "<p>%s</p>" %) str-seq)
  )

(defn -main [] (println (wrap-p-tag ["Hello !" "RiakCljExample"])))
