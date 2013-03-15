(ns RiakCljExample.core
  (:import
   [com.basho.riak.client RiakFactory]
   [com.basho.riak.client IRiakObject]
   [com.basho.riak.client.builders RiakObjectBuilder]))

(defn- client []
  ;; (RiakFactory/pbcClient))
  (RiakFactory/httpClient))

(defn fetch-bucket [rclient bucket-name]
    (-> (.fetchBucket rclient bucket-name)
        (.execute)))

(defn get-robj [rclient bucket-name key-name]
  (-> (fetch-bucket rclient bucket-name)
      (.fetch key-name)
      (.execute)))

(defn link-walk
  "Return link walk results of the type
   public interface WalkResult extends Iterable<Collection<IRiakObject>>"
  [rclient bucket-name key-name tag-name]
  (let [robj (get-robj rclient bucket-name key-name)]
    (-> (.walk rclient robj)
        (.addStep bucket-name tag-name)
        (.execute))))

(defn -main []
  (let [c (client)]
    (try
      ;; (get-robj c "people" "sean")
      (let [walk-result (link-walk c "people" "sean" "friend")]
        (println (flatten (map (fn [col]
                               (map (fn [obj] (.getValueAsString obj)) col))
                             walk-result))))
      ;; (finally (.shutdown c)))))
      )))
