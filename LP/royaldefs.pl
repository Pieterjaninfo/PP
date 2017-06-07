% X is _ of Y
child(X,Y) :- father(Y,X). 
child(X,Y) :- mother(Y,X). 

father(X,Y) :- husband(X,Z), mother(Z,Y).

grandparent(X,Y) :- child(Z,X), child(Y,Z).

brother(X,Y) :- male(X), child(X,Z), child(Y,Z), not(X=Y).
sister(X,Y) :- female(X), child(X,Z), child(Y,Z), not(X=Y).

aunt(X,Y) :- sister(X,Z), child(Y,Z). 
aunt(X,Y) :- brother(A,Z), child(Y,Z), husband(A,X).

uncle(X,Y) :- brother(X,Z), child(Y,Z). 
uncle(X,Y) :- sister(A,Z), child(Y,Z), husband(X,A).

nephew(X,Y) :- male(X), aunt(Y,X). 
nephew(X,Y) :- male(X), uncle(Y,X).

niece(X,Y) :- female(X), aunt(Y,X). 
niece(X,Y) :- female(X), uncle(Y,X).

cousin(X,Y) :- niece(Y,Z), child(X,Z). 
cousin(X,Y) :- nephew(Y,Z), child(X,Z).

ancestor(X,Y) :- child(Y,X).
ancestor(X,Y) :- ancestor(X,Z), child(Y,Z).
