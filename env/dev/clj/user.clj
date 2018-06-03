(ns user
  (:require [choose.config :refer [env]]
            [clojure.spec.alpha :as s]
            [expound.alpha :as expound]
            [mount.core :as mount]
            [choose.figwheel :refer [start-fw stop-fw cljs]]
            [choose.core :refer [start-app]]
            [choose.db.core]
            [conman.core :as conman]
            [luminus-migrations.core :as migrations]))

(alter-var-root #'s/*explain-out* (constantly expound/printer))

(defn start []
  (mount/start-without #'choose.core/repl-server))

(defn stop []
  (mount/stop-except #'choose.core/repl-server))

(defn restart []
  (stop)
  (start))

(defn restart-db []
  (mount/stop #'choose.db.core/*db*)
  (mount/start #'choose.db.core/*db*)
  (binding [*ns* 'choose.db.core]
    (conman/bind-connection choose.db.core/*db* "sql/queries.sql")))

(defn reset-db []
  (migrations/migrate ["reset"] (select-keys env [:database-url])))

(defn migrate []
  (migrations/migrate ["migrate"] (select-keys env [:database-url])))

(defn rollback []
  (migrations/migrate ["rollback"] (select-keys env [:database-url])))

(defn create-migration [name]
  (migrations/create name (select-keys env [:database-url])))


