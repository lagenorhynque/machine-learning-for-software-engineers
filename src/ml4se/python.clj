(ns ml4se.python
  (:require [clojure.java.shell :refer [sh]]
            [clojure.string :as str]
            [libpython-clj.python :as py]))

(def home (System/getenv "HOME"))

(def venv
  (->> (sh "pipenv" "--venv")
       :out
       str/trim-newline))

(def venv-python (str venv "/bin/python"))

(def python-version
  (->> (sh venv-python "--version")
       :out
       str/trim-newline))

(def dylib-path
  (->> (sh "find"
           (->> (sh "echo" python-version)
                :out
                (sh "cut" "-d" " " "-f" "2" :in)
                :out
                (str home "/.pyenv/versions/")
                (str/trim-newline))
           "-name"
           "*.dylib")
       :out
       (sh "head" "-n" "1" :in)
       :out
       str/trim-newline))

(py/initialize! :python-executable venv-python
                :library-path dylib-path)
