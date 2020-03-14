(ns ml4se.02-square-error
  (:require [ml4se.python]
            [libpython-clj.python :as py]
            [libpython-clj.require :refer [require-python]]))

(require-python '[matplotlib.pyplot :as plt]
                '[numpy :as np]
                '[numpy.random :as np.rand]
                '[pandas :as pd])

(defn create-dataset [num]
  (reduce (fn [dataset i]
            (let [x (double (/ i (dec num)))
                  y (+ (np/sin (* 2 np/pi x))
                       (np.rand/normal :scale 0.3))]
              (py/py. dataset append (pd/Series [x y] :index ["x" "y"])
                      :ignore_index true)))
          (pd/DataFrame :columns ["x" "y"])
          (range num)))
