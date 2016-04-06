
(ns emacri.popup
  (:require [khroma.runtime :as runtime]
            [khroma.log :as console]
            [khroma.tabs :as chrome-tabs]
            [cljs.core.async :refer [>! <!]]
            [goog.object :as goog])
  (:require-macros [cljs.core.async.macros :refer [go]]))

(defn init []
  (let [bg (runtime/connect)
        tabs (.. js/chrome -tabs (query (clj->js {}) #(dorun (map
                                                              (fn [tab]
                                                                (console/debug (str "id: " (js-keys tab))))
                                                              %))))
        ctabs (chrome-tabs/query)]
    (go (>! bg :lol-i-am-a-popup)
        (console/log "Background said: " (<! bg))
        (console/debug "query: " (<! ctabs)))))
