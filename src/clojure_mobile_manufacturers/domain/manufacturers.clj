(ns clojure-mobile-manufacturers.domain.manufacturers
  (:refer-clojure :exclude [get update])
  (:require [clojure.java.jdbc :as jdbc]
            [clojure.java.jdbc.sql :as sql]))

(def mysql-db {:subprotocol "mysql"
               :subname "//localhost:3306/clojure_crud?characterEncoding=utf8"
               :user "root"
               :password "root1997"
               :zeroDateTimeBehaviour "convertToNull"})

(def now (str (java.sql.Timestamp. (System/currentTimeMillis))))

(defn allManufacturers []
  (jdbc/query mysql-db
              ["SELECT * FROM manufacturers"]))

(defn deleteManufacturer [id]
  (jdbc/delete! mysql-db :manufacturers (sql/where {:manufacturerId id})))

(defn get [id]
  (first (jdbc/query mysql-db
                     (sql/select * :manufacturers (sql/where {:manufacturerId id})))))

(defn update [id params]
  (jdbc/update! mysql-db :manufacturers params (sql/where {:manufacturerId id})))

(defn insertManufacturer [params]
  (jdbc/insert! mysql-db :manufacturers params))