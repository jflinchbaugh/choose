(ns choose.env
  (:require [clojure.tools.logging :as log]))

(def defaults
  {:init
   (fn []
     (log/info "\n-=[choose started successfully]=-"))
   :stop
   (fn []
     (log/info "\n-=[choose has shut down successfully]=-"))
   :middleware identity})
