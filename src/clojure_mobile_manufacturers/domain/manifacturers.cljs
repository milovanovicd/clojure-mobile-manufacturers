(ns clojure-mobile-manufacturers.domain.manifacturers
  (:refer-clojure :exclude [get])
  (:require [clojure.java.jdbc :as jdbc]
            [clojure.java.jdbc.sql :as sql]))

(def mysql-db {:subprotocol "mysql"
               :subname "//localhost:3306/clojure_crud?characterEncoding=utf8"
               :user "root"
               :password "root1997"
               :zeroDateTimeBehaviour "convertToNull"})

(defn allManifacturers []
  (jdbc/query mysql-db
              ["SELECT * FROM manifacturers"]))

(defn deleteManifacturer [id]
  (jdbc/delete! mysql-db :manifacturers (sql/where {:manifacturerId id})))

(defn get [id]
  (first (jdbc/query mysql-db
                     (sql/select * :manifacturers (sql/where {:manifacturerId id})))))

(defn update [id params]
  (jdbc/update! mysql-db :manifacturers params (sql/where {:manifacturerId id})))

(defn insertManifacturer [params]
  (jdbc/insert! mysql-db :manifacturers params))