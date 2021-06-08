(ns clojure-mobile-manufacturers.controller.controller
  (:require
   [clojure-mobile-manufacturers.domain.products :as products-domain]
   [clojure-mobile-manufacturers.domain.manufacturers :as manufacturers-domain]
   [clostache.parser :as clostache]
   [clojure.java.io :as io]))

(defn read-template [template-name]
  (slurp (io/resource
          (str "views/" template-name ".mustache"))))

(defn render-template [template-file params]
  (clostache/render (read-template template-file) params))

(defn index []
  (render-template "index" {}))

(defn manufacturers []
  (render-template "manufacturers" {:manufacturers (manufacturers-domain/allManufacturers)}))

(defn products []
  (render-template "products" {:products (products-domain/allProducts)}))

(defn createProduct []
  (render-template "createProduct" {:manufacturers (manufacturers-domain/allManufacturers)
                                    :categories (products-domain/allCategories)}))

(defn createManufacturer []
  (render-template "createManufacturer" {}))

(defn updateProduct [id]
  (render-template "updateProduct" {:products (products-domain/get id)
                                    :manufacturers (manufacturers-domain/allManufacturers)
                                    :categories (products-domain/allCategories)}))

(defn updateManufacturer [id]
  (render-template "updateManufacturer" {:manufacturers (manufacturers-domain/get id)}))
