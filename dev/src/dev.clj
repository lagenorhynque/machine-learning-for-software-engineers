(ns dev
  (:require [clojure.java.io :as io]
            [clojure.repl :refer :all]
            [clojure.spec.alpha :as s]
            [clojure.test :as test]
            [clojure.spec.test.alpha :as stest]
            [ml4se.python]))

(require '[libpython-clj.python :as py]
         '[libpython-clj.require :refer [require-python]])
