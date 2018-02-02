(ns prime-table.core
  (:gen-class))

;; generating primes
(defn is-prime?
  "Check if a number is prime"
  [n]
  (or (= n 2)
      (not-any? #(= 0 (mod n %)) (range 3 (inc (Math/sqrt n)) 2))))

(defn n-primes
  "Build list of first n primes"
  [n]
  (take n (filter #(is-prime? %) (cons 2 (iterate (partial + 2) 3)))))


;; multiplication table building
(defn build-row
  "Build individual row (list) for multiplication table"
  [n row]
  (cons n (map #(* % n) row)))

(defn build-table
  "Build multiplication table"
  [first-row]
  (cons (cons "*" first-row)
        (map #(build-row % first-row) first-row)))


;; formatting
(defn pad-row
  "Apply padding to a row"
  [row padding]
  (map
    #(apply str %1 (take (- (inc %2) (count (str %1))) (repeat " ")))
    row padding))

(defn format-table
  "Format multiplication table"
  [times-table]
  (let [spacing (map #(count (str %)) (last times-table))]
    (map
      #(apply println %)
      (map #(pad-row % spacing) times-table))))

;; compose and print table
(defn -main [arg]
  "Build a multiplications table for 1 to n primes"
  (let [n (read-string arg)]
    ;; input validation
    (if (and (integer? n)
             (> n 0))
      (-> n
          n-primes
          build-table
          format-table
          ;; dorun for handling laziness to output properly to terminal
          dorun)
      (println "Input must be a number greater than 0"))))