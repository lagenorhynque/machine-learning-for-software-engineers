(ns ml4se.python
  (:require [clojure.java.shell :refer [sh]]
            [clojure.string :as str]
            [libpython-clj.python :as py]))

(def home (System/getenv "HOME"))

(def venv (-> (sh "pipenv" "--venv")
              :out
              str/trim-newline
              (str "/bin/python")))

(py/initialize! :python-executable venv
                ;; FIXME
                :library-path (str home "/.pyenv/versions/3.8.1/lib/libpython3.8.dylib"))
