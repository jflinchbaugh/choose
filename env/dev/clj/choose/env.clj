(ns choose.env
  (:require [selmer.parser :as parser]
            [clojure.tools.logging :as log]
            [choose.dev-middleware :refer [wrap-dev]]))

(def defaults
  {:init
   (fn []
     (parser/cache-off!)
     (log/info "\n-=[choose started successfully using the development profile]=-"))
   :stop
   (fn []
     (log/info "\n-=[choose has shut down successfully]=-"))
   :middleware wrap-dev})
