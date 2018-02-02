(ns prime-table.core-test
  (:require [clojure.test :refer :all]
            [prime-table.core :refer :all]))

(deftest initial-primes
  (testing "Test for initial primes"
    (is (= '(2) (n-primes 1)))
    (is (= '(2 3 5 7 11 13 17 19 23 29) (n-primes 10)))))

(deftest table-building
  (testing "Test table-building functions"
    (is (= '(2 4 6 8) (build-row 2 '(2 3 4))))
    (is (= '(("*" 2 3) (2 4 6) (3 6 9)) (build-table '(2 3))))))