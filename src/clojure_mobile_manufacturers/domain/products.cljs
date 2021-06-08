(ns clojure-mobile-manufacturers.domain.products
  (:refer-clojure :exclude [get])
  (:require [clojure.java.jdbc :as jdbc]
            [clojure.java.jdbc.sql :as sql]))

(def mysql-db {:subprotocol "mysql"
               :subname "//localhost:3306/clojure_crud?characterEncoding=utf8"
               :user "root"
               :password "root1997"
               :zeroDateTimeBehaviour "convertToNull"})

(defn allProducts []
  (jdbc/query mysql-db
              ["SELECT * FROM products"]))

(defn allCategories []
  (jdbc/query mysql-db
              ["SELECT * FROM categories"]))

(defn deleteProduct [id]
  (jdbc/delete! mysql-db :products (sql/where {:productId id})))

(defn get [id]
  (first (jdbc/query mysql-db
                     (sql/select * :products (sql/where {:productId id})))))

(defn update [id params]
  (jdbc/update! mysql-db :products params (sql/where {:productId id})))

(defn insertProduct [params]
  (jdbc/insert! mysql-db :products params))