(ns choose.doo-runner
  (:require [doo.runner :refer-macros [doo-tests]]
            [choose.core-test]))

(doo-tests 'choose.core-test)

