(ns choose.routes.home
  (:require
    [choose.layout :as layout]
    [choose.db.core :as db]
    [compojure.core :refer [defroutes GET]]
    [ring.util.http-response :as response]
    [ring.middleware.anti-forgery :refer [*anti-forgery-token*]]
    [clojure.java.io :as io]
    [hiccup.page :refer [html5 include-js include-css]]
    [hiccup.element :refer [javascript-tag]]
  )
)

(def title "Choose")

(defn home-page []
  (html5
    [:head
      [:title title]
    ]
    [:body
      [:h1 title]
      [:div.app
        "some text"
      ]
      (include-css
        "/assets/bootstrap/css/bootstrap.min.css"
        "/css/screen.css"
      )
      (javascript-tag
        (str "var csrfToken = \"" *anti-forgery-token* "\";")
      )
      (include-js
        "/js/app.js"
      )
    ]
  )
)

(defn intro-page []
  (layout/render "home.html"))

(defroutes home-routes
  (GET "/" []
       (home-page))
  (GET "/intro" []
       (intro-page))
  (GET "/docs" []
       (-> (response/ok (-> "docs/docs.md" io/resource slurp))
           (response/header "Content-Type" "text/plain; charset=utf-8"))))

