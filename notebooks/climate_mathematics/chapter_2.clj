^{:kindly/hide-code true
  :kindly/kind      :kind/hiccup}
(ns chapter-2
  (:require [scicloj.kindly.v4.kind :as kind]
            [clojisr.v1.applications.plotting :refer [plot->svg]]
            [clojisr.v1.r :as r :refer [r r->clj require-r]]
            [tech.v3.dataset :as dataset]))

;; # Chapter 2: *Basics of R Programming*

;; ---

;; This chapter contains instructions on setting up and using R on your personal computer.
;; I will be using this space to play with ClojisR.

;; Right now, the space below follows the text of the book and I've not gotten to the exercises yet.

;; ## Basics

(r "1+4")

(r "2+pi/4-0.8")

;; Learning "<-", the assignment operator.

(r "x<-1")
(r "y<-2")
(r "z<-4")
(r "t<-2*x^y-z")
(r "t")


(r "tmax <- c(77, 72, 75, 73, 66, 64, 59)")

;; Generating sequences

(r "1: 8")
(r "seq(1,8, by=1)")
(r "seq(1,8, len=8)")
(r/colon 1 8)

;; ## Functions
(def f (r "function(x) x*10"))
(r->clj (f 5 ))

(let [f (r "function(x,y,z) x+y-z/2")]
  (->> [(f 1 2 3)
        (f 4 5 6)]
       (map r->clj)))

(require-r '[graphics :as gr :refer [plot hist]])

(require-r '[ggplot2 :refer [ggplot aes geom_point geom_path geom_line geom_tile xlab ylab labs]])

(r "plot(1:7, c(77, 72, 75, 73, 66, 64, 59))")

;; ### Using basic graphics plot

(plot->svg
 #(gr/plot-default (r->clj (r "1:7")) (r->clj (r "c(77,72,75,73,66,64,59)"))))

;; ### Using ggplot

(plot->svg
 (let [x (r->clj (r "1: 7"))
       y (r->clj (r "c(77, 72, 75, 73, 66, 64, 59)"))]
   (-> {:x x :y y}
       dataset/->dataset
       (ggplot (aes :x x
                    :y y))
       (r/r+ (geom_point)
             (xlab "x")
             (ylab "y")))))

(plot->svg
 (let [{:keys [x y] :as sin-data} (r->clj (r "plot(sin, -pi, 2*pi)"))]
   (-> sin-data
       dataset/->dataset
       sin-data
       (ggplot (aes :x x
                    :y y))
       (r/r+ (geom_path)
             (xlab "x")
             (ylab "y")))))

(r "square <- function(x) x*x")
(def x (r->clj (r "x <- seq(-3, 2, length=100)")))
(def y (r->clj (r "y <- square(x)")))

(plot->svg
 (-> {:x (r->clj x) :y (r->clj y)}
     dataset/->dataset
     (ggplot (aes :x x
                  :y y))
     (r/r+ (geom_line)
           (xlab "x")
           (ylab "y"))))

;; ### 3D Plots

(r "x <- seq(-1, 1, length=100)")
(r "y <- seq(-1, 1, length=100)")
(r "z <- outer(x, y, function(x,y) (1-x^2-y^2))")

;; Create a data frame
(r "data <- expand.grid(x=x, y=y)")

(r "data$z <- as.vector(z)")

;; Create a contour plot using ggplot2
(r "p_contour <- ggplot(data, aes(x=x, y=y, z=z)) +
                  geom_contour() +
                  ggtitle('Contour Plot')")

;; Display the contour plot
(plot->svg (r "p_contour"))

;; Create a filled contour plot using ggplot2
(r "p_filled_contour <- ggplot(data, aes(x=x, y=y, z=z)) +
                         geom_raster(aes(fill=z)) +
                         geom_contour(color='white') +
                         ggtitle('Filled Contour Plot') +
                         theme_minimal()")

;; Display the filled contour plot
(plot->svg (r "p_filled_contour"))

;; ## Symbolic Caclulations by R
(r "D(expression(x^2, 'x'), 'x')")

(r "fx= expression(x^2 * sin(x), 'x')")

(r "D(fx, 'x')")

;; Skipping the matrices section for now.
;; ## Simple Statistics

(r "x=rnorm(10)")
(r "x")
(r "mean(x)")
(r "var(x)")
(r "sd(x)")
(r "median(x)")
(r "quantile(x)")
(r "range(x)")
(r "max(x)")

(r "boxplot(x)")
