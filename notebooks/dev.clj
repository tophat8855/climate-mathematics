(ns dev
  (:require [scicloj.clay.v2.api :as clay]))

(clay/make! {:format              [:quarto :html]
             :base-source-path    "notebooks"
             :source-path         ["index.clj"
                                   "climate_mathematics/chapter_2.clj"]
             :base-target-path    "docs"
             :book                {:title "Climate Mathematics"}
             :clean-up-target-dir true})
