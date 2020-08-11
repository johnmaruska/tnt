(ns tnt.server
  (:require [hiccup.page :refer [html5 include-css include-js]]))

;; TODO: proper router using reitit-ring. with coercion, swagger, middleware, etc.

(defn loading-page []
  (html5
   [:head
    [:meta {:charset "UTF-8"}]
    [:meta {:name "viewport"
            :content "width=device-width, initial-scale=1"}]
    (include-css "/css/style.css")]
   [:body {:class "body-container"}
    [:div#app [:h2 "Please don't show"]]
    (include-js "/cljs-out/dev-main.js")]))

(defn index-handler [_request]
  {:status  200
   :headers {"Content-Type" "text/html"}
   :body    (loading-page)})

(defn handler [request]
  (if (and (= :get (:request-method request))
           (= "/"  (:uri request)))
    (index-handler request)
    {:status 404
     :headers {"Content-Type" "text/plain"}
     :body "Not Found"}))
