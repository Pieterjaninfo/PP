% this is for the monkey

%state(Monkey,Position,Box,Has).


goal(state(_,_,_,has)).

init(state(atdoor, onfloor, atwindow, hasnot)).
s2(state(atwindow, onfloor, atwindow, hasnot)).

move(state(middle, onbox, middle, hasnot), grasp, state(middle, onbox, middle, has)).
move(state(Pos, onfloor, Pos, Has), climb, state(Pos, onbox, Pos, Has)).
move(state(L1, onfloor, L1, Has), push(L1,L2), state(L2, onfloor, L2, Has)).
move(state(L1, onfloor, Box, Has), walk(L1,L2), state(L2, onfloor, Box, Has)).





solve(state(_,_,_,has)).
solve(State1) :- move(State1, _, State2), solve(State2).

% it allows termination/causes infinite loop [ERROR: Out of local stack]

solve2(state(_,_,_,has), []).
solve2(State1, [Move|L]) :- move(State1, Move, State2), solve2(State2, L).

