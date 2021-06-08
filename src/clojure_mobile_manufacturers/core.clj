(ns clojure-mobile-manufacturers.core
  (:use compojure.core)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [ring.middleware.basic-authentication :refer :all]
            [ring.util.response :as resp]
            [clojure-mobile-manufacturers.controller.controller :as controller]
            [clojure-mobile-manufacturers.domain.products :as products-domain]
            [clojure-mobile-manufacturers.domain.manufacturers :as manufacturers-domain]))

(defroutes public-routes
  ;; VIEWS
  (GET "/" [] (controller/index))
  (route/resources "/")

  (GET "/index" [] (controller/index))
  (route/resources "/")

  (GET "/manufacturers" [] (controller/manufacturers))
  (route/resources "/")

  (GET "/products" [] (controller/products))
  (route/resources "/")

  (GET "/domain/products/create" [] (controller/createProduct))
  (route/resources "/")

  (GET "/domain/manufacturers/create" [] (controller/createManufacturer))
  (route/resources "/")

  ;; CREATE
  (POST "/domain/manufacturers/insert" [& params]
    (do (manufacturers-domain/insertManufacturer params)
        (resp/redirect "/manufacturers")))

  (POST "/domain/products/insert" [& params]
    (do (products-domain/insertProduct params)
        (resp/redirect "/products")))

  ;; DELETE
  (GET "/domain/products/:id/delete" [id]
    (do (products-domain/deleteProduct id)
        (resp/redirect "/products")))

  (GET "/domain/manufacturers/:id/delete" [id]
    (do (manufacturers-domain/deleteManufacturer id)
        (resp/redirect "/manufacturers")))

  ;; UPDATE VIEW & UPDATE
  (GET "/domain/products/:id/update" [id] (controller/updateProduct id))

  (POST "/domain/products/:productId/update" [& params]
    (do (products-domain/update (:productId params) params)
        (resp/redirect "/products")))

  (GET "/domain/manufacturers/:id/update" [id] (controller/updateManufacturer id))

  (POST "/domain/manufacturers/:manufacturerId/update" [& params]
    (do (manufacturers-domain/update (:manufacturerId params) params)
        (resp/redirect "/manufacturers"))))


(defroutes app-routes
  public-routes
  (route/not-found "404 Not Found"))

(def -main
  (handler/site app-routes))