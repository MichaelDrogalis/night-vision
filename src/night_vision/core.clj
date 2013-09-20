(ns night-vision.goggles
  (:require [clojure.pprint]
            [dire.core]))

(defn introspect-ns! [target-ns]
  (let [all-vars (vals (ns-publics target-ns))
        fns (filter #(contains? (meta %) :arglists) all-vars)
        divider "------------------------------------------------"]
    (doseq [f fns]
      (dire.core/with-pre-hook! f
        (fn [& args]
          (clojure.pprint/pprint divider)
          (clojure.pprint/pprint (str "Invoking " f))
          (clojure.pprint/pprint (str "Actual arguments are: " args))
          (clojure.pprint/pprint divider)))
      (dire.core/with-post-hook! f
        (fn [result]
          (clojure.pprint/pprint divider)
          (clojure.pprint/pprint (str "Returning from " f))
          (clojure.pprint/pprint (str "Return value is: " result))
          (clojure.pprint/pprint divider))))))

