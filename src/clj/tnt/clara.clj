(ns tnt.clara
  (:require [clara.rules :refer :all]))

(defrecord StatScore
    [base-score
     racial-bonus
     ability-improvements
     misc-bonus
     stacking-bonus
     set-score])

(defrecord Attributes
    [strength     :- AttributeScore
     dexterity    :- AttributeScore
     constitution :- AttributeScore
     intelligence :- AttributeScore
     wisdom       :- AttributeScore
     charisma     :- AttributeScore])

(defrecord Character
    [name
     attributes :- Attributes])

(defrule stat-total
  [Character ]
  =>
  (insert! (->)))
(defrule stat-modifier
  )
;;;;;;;;;;;



(defrule armor-class-blocks-targeted-attack
  [Attack (= "targeted" type) (= ?attacker attacker) (= ?defender defender)]
  [SpellHit (= (:name ?attacker) character-name) (= ?hit-value value)]
  =>
  (insert! (->ArmorClassBlocksAttack ?attacker ?defender (boolean (< ac-value))))
  )

;; A attacks B with spell
;; B has armor class of 10
;; A gets 11 attack value
;; B is hit

(defrecord Attack
    [attacker :- Character
     defender :- Character
     damage   :- Damage  ; could also be list of damages if multiple types
     type  ;; melee, ranged, spell
     ])

(defrecord DamagePotential [num-dice faces type])
(defrecord Damage [target amount type])

(defn spell-attack-lands?
  [attacker :- Character
   defender :- Character
   ability  :- Spell]
  (let [spell-hit-modifier (get-spell-hit-modifier attacker)
        armor-class        (get-armor-class        defender)
        hit-value          (roll/to-hit spell-hit-modifier)]
    (boolean (>= hit-value armor-class))))

(defrecord ProficiencyBonus [character value])
(defrecord SpellAttack [attacker defender spell])
(defrecord SpellHit    [character value])
(defrecord SpellModifier       [character value])
(defrecord SpellAttackModifier [character value])
(defrecord ArmorClass  [character value])

(defrule spell-attack-lands
  [SpellAttack (= ?attacker attacker ) (= ?defender defender) (= ?spell spell)]
  [SpellHit    (= ?attacker character) (= ?hit-value value)]
  [ArmorClass  (= ?defender character) (= ?ac-value  value)]
  =>
  (insert! (->AttackLands ?attacker ?defender ?spell)))
