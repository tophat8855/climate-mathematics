^{:kindly/hide-code true
  :kindly/kind      :kind/hiccup}
(ns chapter-1
  (:require [scicloj.kindly.v4.kind :as kind]
            [tablecloth.api :as tc]
            [scicloj.tableplot.v1.hanami :as hanami]
           #_ [scicloj.viz.api :as viz]))

;; # Dimensional Analysis for Climate Science

;; This chapter is an introduction and doesn't use R yet. I'm using this space to see about
;; using Clay for mathematical notation as I do some exercises.

;; I will not share every exercise here, as many of the answrs for chapter 1 are found
;; in the back of the book and I don't want to spoil the fun for anyone else.

;; ## Exercise 1.1

^{:kindly/hide-code true}
(kind/tex "v = \\alpha m^a g^b t^c")

^{:kindly/hide-code true}
(kind/tex "[v] = [\\alpha] [m]^a [g]^b [t]^c")

^{:kindly/hide-code true}
(kind/tex "LT^{-1} = 1 \\times M^a (LT ^{-2})^b T^c")

^{:kindly/hide-code true}
(kind/tex "LT^{-1} = M^a L^b T ^{c-2b+1}")

^{:kindly/hide-code true}
(kind/tex "a = 0, b = 1, c = 2b - 1 = 2 - 1 = 1")

;; So the original equation is

^{:kindly/hide-code true}
(kind/tex "v = \\alpha m^0 g^1 t^1 = \\alpha g t")

;; ## Exercise 1.2

^{:kindly/hide-code true}
(kind/tex "h = \\beta m^a g^b t^c")

^{:kindly/hide-code true}
(kind/tex "[h] = [\\beta] [m]^a [g]^b [t]^c")

^{:kindly/hide-code true}
(kind/tex "L = 1 \\times M^a (LT^{-2})^b T^c")

^{:kindly/hide-code true}
(kind/tex "L = M^a L^b T^{-2} T^c")

^{:kindly/hide-code true}
(kind/tex "1 = M^a L^{b-1} T^{c-2}")

^{:kindly/hide-code true}
(kind/tex "a = 0, b - 1 = 0, c - 2 = 0")

^{:kindly/hide-code true}
(kind/tex "h = \\beta m^0 g^1 t^2 = \\beta g t^2")

;; ## Exercise 1.8

^{:kindly/hide-code true}
(kind/tex "F_g = G\\frac{m_1 m_2}{r^2}")

^{:kindly/hide-code true}
(kind/tex "[F_g] = [G] \\frac{[m_1][m_2]}{[r]^2}")

^{:kindly/hide-code true}
(kind/tex "MLT^{-2} = [G] \\frac{M^2}{L^2}")

^{:kindly/hide-code true}
(kind/tex "[G] = M^{-1} L^{3} T^{-2}")

;; ## Exercise 1.10

;; I am using this example to play with making a scatterplot here. I am using sample data.

^{:kindly/hide-code true}
(def force-data
  [{:time 0.2178 :position 0}
   {:time 0.327 :position 0.15}
   {:time 0.3944 :position 0.3}
   {:time 0.4487 :position 0.45}
   {:time 0.4954 :position 0.6}
   {:time 0.5367 :position 0.75}
   {:time 0.5746 :position 0.9}
   {:time 0.6096 :position 1.05}
   {:time 0.6425 :position 1.2}
   {:time 0.6736 :position 1.35}
   {:time 0.6998 :position 1.5}
   {:time 0.7808 :position 1.65}])

^{:kindly/hide-code true}
(kind/table force-data)

^{:kindly/hide-code true}
(def plotly-example
  {:data   [{:x    (map :time force-data)
             :y    (map :position force-data)
             :type :scatter
             :mode :markers}]
   :layout {:title "Basic Plotly Scatterplot"
            :xaxis {:title "Time"}
            :yaxis {:title "Position"}}})

;; Trying this for a trendline in Plotly:

(defn linear-regression [x y]
  (let [n             (count x)
        sum-x         (reduce + x)
        sum-y         (reduce + y)
        sum-xy        (reduce + (map * x y))
        sum-x-squared (reduce + (map * x x))
        slope         (/ (- (* n sum-xy) (* sum-x sum-y))
                         (- (* n sum-x-squared) (* sum-x sum-x)))
        intercept     (/ (- (* sum-y sum-x-squared) (* sum-x sum-xy))
                         (- (* n sum-x-squared) (* sum-x sum-x)))]
    {:slope slope :intercept intercept}))

(let [{:keys [slope intercept]} (linear-regression (map :time force-data) (map :position force-data))
      trend-line-x              (map :time force-data)
      trend-line-y              (map #(-> (* slope %) (+ intercept)) trend-line-x)]
   ;; Add the trend line to the scatterplot
   (def scatterplot-with-trendline
     (update plotly-example :data conj {:x    trend-line-x
                                        :y    trend-line-y
                                        :type :scatter
                                        :mode :lines
                                        :line {:color "red"}
                                        :name "Trend Line"})))

^{:kindly/hide-code true}
(kind/plotly scatterplot-with-trendline)

^{:kindly/hide-code true}
(-> force-data
    (tc/dataset)
    (hanami/plot
     hanami/point-chart
     {:=x     :time
      :=y     :position
      :=title "Basic Hanami Scatterplot"}))

;; Still need to figure out how to add a trendline in Hanami

#_(-> [{:x 1 :y 2}
       {:x 2 :y 4}
       {:x 3 :y 9}]
    viz/data
    (viz/type :point)
    (viz/mark-size 200)
    (viz/color :x)
    viz/viz)
