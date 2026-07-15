(ns statute.facts-test
  (:require [clojure.string :as str]
            [clojure.test :refer [deftest is]]
            [statute.facts :as facts]))

(deftest bel-has-spec-basis
  (let [sb (facts/spec-basis "BEL")]
    (is (= 3 (count sb)))
    (is (every? #(str/starts-with? (:statute/url %) "https://www.ejustice.just.fgov.be/") sb))
    (is (every? :statute/law-number sb))))

(deftest unknown-jurisdiction-has-no-spec-basis
  (is (nil? (facts/spec-basis "ATL")))
  (is (nil? (facts/spec-basis "ZZZ"))))

(deftest coverage-is-honest
  (let [c (facts/coverage ["BEL" "JPN" "ATL"])]
    (is (= 3 (:requested c)))
    (is (= 1 (:covered c)))
    (is (= ["ATL" "JPN"] (:missing-jurisdictions c)))))

(deftest by-topic-filters
  (is (= ["bel.loi-contrats-de-travail-1978"]
         (mapv :statute/id (facts/by-topic "BEL" :labor))))
  (is (empty? (facts/by-topic "BEL" :environment)))
  (is (empty? (facts/by-topic "ATL" :labor))))
