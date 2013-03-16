(ns RiakCljExample.core
  (:import
   [com.basho.riak.client RiakFactory]
   [com.basho.riak.client IRiakObject]
   [com.basho.riak.client.builders RiakObjectBuilder]))

(defn- httpclient []
  (RiakFactory/httpClient))
(defn- pbclient []
  (RiakFactory/pbcClient))

(defn fetch-bucket [rclient bucket-name]
    (-> (.fetchBucket rclient bucket-name)
        (.execute)))

(defn get-robj [rclient bucket-name key-name]
  (-> (fetch-bucket rclient bucket-name)
      (.fetch key-name)
      (.execute)))

;; pbc link walk error: ClassCastException at PBClientAdapter.java:380.
;; HTTP works.
(defn link-walk
  "Return link walk results of the type
   public interface WalkResult extends Iterable<Collection<IRiakObject>>"
  [rclient bucket-name key-name tag-name]
  (let [robj (get-robj rclient bucket-name key-name)]
    (-> (.walk rclient robj)
        (.addStep bucket-name tag-name)
        (.addStep bucket-name tag-name)
        (.execute))))

(defn get-robj-and-print-result
  [rclient bucket-name key-name]
  (let [robj (get-robj rclient "people" "sean")]
    (println "get-robj" robj)
    (println "get-robj value" (.getValueAsString robj))
    (println "get-robj hasLinks" (-> (.hasLinks robj) (.toString)))
    (println "get-robj getLinks" (-> (.getLinks robj) (.toString)))))
    
  
(defn link-walk-and-print-result
  [rclient bucket-name key-name tag-name]
  (let [walk-result (link-walk rclient "people" "sean" "friend")]
    (println (flatten (map (fn [col]
                             (map (fn [obj] (.getValueAsString obj)) col))
                           walk-result)))))

(defn -main []
  (let [httpc (httpclient)
        pbc   (pbclient)]
    (try
      (get-robj-and-print-result httpc "people" "sean")
      (link-walk-and-print-result httpc "people" "sean" "friend")
      (link-walk-and-print-result pbc "people" "sean" "friend")
      )))
