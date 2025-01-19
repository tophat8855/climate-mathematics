^{:kindly/hide-code true
  :kindly/kind :kind/hiccup}
(ns chapter-1
  (:require [scicloj.kindly.v4.kind :as kind]))
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
(def data
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
(kind/table data)

^{:kindly/hide-code true}
(def plotly-example
  {:data   [{:x (map :time data)
             :y (map :position data)
             :type     :scatter
             :mode     :markers}]
   :layout {:title "Plotly example"}})

^{:kindly/hide-code true}
(kind/plotly plotly-example)
