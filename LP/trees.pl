/* A nice lil'ol lemon tree for the good 'ol monkey */

/*  t(t(nil,2,nil),4,t(nil,5,t(nil,18,nil)))
    t(t(nil,4,nil),2,nil)
    t(nil,2,t(nil,4,t(nil,1,nil)))
    t(t(nil,5,nil),2,t(nil,4,nil)) */

left(t(L,_,_), L).
right(t(_,_,R), R).
val(t(_,N,_), N).

istree(nil).
istree(t(L,_,R)) :- istree(L), istree(R).

min(t(nil,N,nil), N).
min(t(L,N,nil), N) :- min(L,Y), N =< Y, !.
min(t(L,_,nil), Y) :- min(L,Y), !.
min(t(nil,N,R), N) :- min(R,Y), N =< Y, !.
min(t(nil,_,R), Y) :- min(R,Y), !.
min(t(L,N,R), N) :- min(L,X), min(R,Y), N =< X, N =< Y, !.
min(t(L,N,R), X) :- min(L,X), min(R,Y), X =< N, X =< Y, !.
min(t(L,N,R), Y) :- min(L,X), min(R,Y), Y =< N, Y =< X, !.


max(t(nil,N,nil), N).
max(t(L,N,nil), N) :- max(L,Y), N >= Y, !.
max(t(L,_,nil), Y) :- max(L,Y), !.
max(t(nil,N,R), N) :- max(R,Y), N >= Y, !.
max(t(nil,_,R), Y) :- max(R,Y), !.
max(t(L,N,R), N) :- max(L,X), max(R,Y), N >= X, N >= Y, !.
max(t(L,N,R), X) :- max(L,X), max(R,Y), X >= N, X >= Y, !.
max(t(L,N,R), Y) :- max(L,X), max(R,Y), Y >= N, Y >= X, !.


issorted(t(nil, _, nil)).
issorted(t(t(L,X,R),N,nil)) :- X =< N, issorted(t(L,X,R)).
issorted(t(nil,N,t(L,Y,R))) :- Y >= N, issorted(t(L,Y,R)).
issorted(t(t(L,X,R),N,t(L2,Y,R2))) :- X =< N, N < Y, issorted(t(L,X,R)), issorted(t(L2,Y,R2)).

/*
issorted(nil).
issorted(t(L,N,R)) :- max(L,X), X =< N, min(R,Y), Y >= N, issorted(L), issorted(R).
*/


find(t(L,N,R), N, t(L,N,R)).
find(t(L,N,_), X, S) :- X < N, find(L, X, S), !.
find(t(_,N,R), X, S) :- X > N, find(R, X, S).


insert(nil, N, t(nil,N,nil)).
insert(t(L,X,R), N, t(S,X,R)) :- N < X, insert(L, N, S), !.
insert(t(L,X,R), N, t(L,X,S)) :- N >= X, insert(R, N, S).

/*
deleteAll(t(_,N,_), N, nil).
deleteAll(t(L,X,R), N, t(S, X, R)) :- N < X, deleteAll(L, N, S), !.
deleteAll(t(L,X,R), N, t(L, X, S)) :- N > X, deleteAll(R, N, S).
*/


insertTree(nil, T, T). 
insertTree(t(L1,X1,R1), T2, t(S,X1,R1)) :- val(T2,X2), X2 =< X1, insertTree(L1, T2, S).
insertTree(t(L1,X1,R1), T2, t(L1,X1,S)) :- val(T2,X2), X2 >= X1, insertTree(R1, T2, S).

delete(nil, nil).
delete(t(L,_,nil), L).
delete(t(nil,_,R), R).
delete(t(L,_,R), S) :- insertTree(L,R,S).

deleteAll(nil, _, nil). 
deleteAll(t(L,N,R), N, S2) :- delete(t(L,N,R),S), deleteAll(S,N,S2), !.
deleteAll(t(L,X,R), N, t(L2,X,R)) :- N < X, deleteAll(L, N, L2).
deleteAll(t(L,X,R), N, t(L,X,R2)) :- N > X, deleteAll(R, N, R2).


listtree([], nil).
listtree([H|L], T2) :- listtree(L, T1), insert(T1, H, T2).



treelist(nil,[]).
treelist(t(L,X,R), A) :- deleteAll(L, X, L2), deleteAll(R,X,R2), treelist(L2, LL), treelist(R2, RR), append(LL, [X|RR], A).


append([], L2, L2).
append([X|L1], L2, [X|R]) :- append(L1, L2, R).


treesort(L, R) :- listtree(L, T), treelist(T, R).




