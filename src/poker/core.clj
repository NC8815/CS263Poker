(ns poker.core
  (:gen-class))

(defn -main
  []
;(showHands (dealDeck (getPlayers)))

)

(defn newDeck []
  (shuffle (for [x (range 13) y (range 4)] [x y]))
)

(defn getPlayers []    ;ask the user how many players, make sure it's a number
  (println "How many players? (2-4)")
  (let [v (read-string (read-line))]            ;reads the input as a number
    (if (and (integer? v) )                                 ;if it's an integer
      v                                                     ;return the input
        (do (println "That is not a good answer") (recur) ) ;otherwise ask again
    )
  )
)

(defn dealDeck [numPlayers] ;dealing a hand to each player
  (let [undealt (newDeck)] ;first we need a deck
    (loop [      deck undealt      currentPlayer 0        hands []      ] ; keeping track of our undealt cards, 
                                                                          ;our current player, and our dealt hands
        (if (= currentPlayer numPlayers) hands;if we've dealt a hand for each player return the hands
          (recur (drop 5 deck)   (inc currentPlayer)    (conj hands (into [] (take 5 deck)))     )
        ) ;otherwise, log the dealing of the hand, then deal to the next player
    )   
  )
)

(defn cardName [card] ;given a hand and a card, sort that hand low to high, 
                                          ;then give that card name followed by a space
  (let [suits ["♥" "♣" "♠" "♦"] 
        pips ["2" "3" "4" "5" "6" "7" "8" "9" "10" "J" "Q" "K" "A"]]
    (str (get pips (first card)) (get suits (last card)) " ")
  )
)

(defn showHands [dealtHands]
  (for [playerNum dealtHands]
    (println-str  (for [card playerNum]
        (cardName card ))
    )
  )
)

  
 
  
  
  
  
  
  
  
  
  
  
  
  