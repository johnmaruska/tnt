(ns tnt.handler
  (:require
   [hiccup.page :refer [include-css include-js html5]]
   [reitit.ring :as reitit-ring]))

(def mount-target
  [:div#app
   [:h2 "Welcome to T.N.T."]
   [:p "please wait while Figwheel is waking up ..."]])

(defn head []
  [:head
   [:meta {:charset "utf-8"}]
   [:meta {:name "viewport"
           :content "width=device-width, initial-scale=1"}]
   ;; TODO: minified version in prod
   (include-css "/css/site.css")])

(defn loading-page []
  (html5
   (head)
   [:body {:class "body-container"}
    mount-target
    (include-js "/js/app.js")]))

(defn index-handler
  [_request]
  {:status  200
   :headers {"Content-Type" "text/html"}
   :body    (loading-page)})

(def app
  (reitit-ring/ring-handler
   (reitit-ring/router
    [["/" {:get {:handler index-handler}}]])
   (reitit-ring/routes
    (reitit-ring/create-resource-handler {:path "/" :root "/public"})
    (reitit-ring/create-default-handler))
   {:middleware []}))
