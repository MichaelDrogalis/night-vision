(ns night-vision.goggles
  (:require [clojure.pprint :refer [pprint]]
            [dire.core :as d]))

(defonce pre-log
  (fn [f divider]
    (d/with-pre-hook! f
      (fn [& args]
        (pprint divider)
        (pprint (str "Invoking " f))
        (pprint (str "Actual arguments are: " args))
        (pprint divider)))))

(defonce post-log
  (fn [f divider]
    (d/with-post-hook! f
      (fn [result]
        (pprint divider)
        (pprint (str "Returning from " f))
        (pprint (str "Return value is: " result))
        (pprint divider)))))

(defn introspect-ns! [target-ns]
  (let [all-vars (vals (ns-publics target-ns))
        fns (filter #(contains? (meta %) :arglists) all-vars)
        divider "------------------------------------------------"]
    (doseq [f fns]
      (d/remove-pre-hook! f pre-log)
      (d/remove-post-hook! f post-log)
      (pre-log f divider)
      (post-log f divider))))

