/* The donkey fell into an icecream stand..........  now that's a sweet ass. */


/*
1. Thursday -> not Peppermint, not Rockland
2. Granite -> day before Chocolatechip, day after Gary's
3. Tom's -> Peanutbutter, not Tuesday
4. Wednesday -> Coffeebean, not Alice's
5. Marsh -> day before Sally's
6. Boulder

days: tuesday...friday
food: peppermint, chocolatechip, peanutbutter, coffeebean
names: gary, tom, alice, sally
places: rockland, granite, marsh, boudler
*/
/* Hints:
    1) Four ordered days (Tue-Fri) each with  a UNIQUE Name, City and Flavour
    2) Sally's icecream was NOT in Rockland. 
       Sherry did NOT get peppermint stick ice cream on Thursday night.
    3) Sherry stopped at the ice cream stand in Granite on the day before 
       she got chocolate chip icecream, AND  the day after she stopped at Gary's.
    4) At Tom's she got peanut butter, but NOT on tuesday.
    5) Sherry got Coffee bean icecream on Wed. but NOT at Alice's.
    6) Sherry stopped in Marsh the day before she stopped at Sally's.
    7) Sherry also got icecream in Boulder.
*/
%stand(Name,City,Day,Flavour).

go(Tour) :-
    /* Hint nr 1 */
    length(Tour, 4),
    before(stand(_,_,tuesday,_), stand(_,_,wednesday,_), Tour),
    before(stand(_,_,wednesday,_), stand(_,_,thursday,_), Tour),
    before(stand(_,_,thursday,_), stand(_,_,friday,_), Tour),

    member(stand(_,rockland,_,_), Tour),
    member(stand(_,granite,_,_), Tour),
    member(stand(_,marsh,_,_), Tour),
    member(stand(_,boulder,_,_), Tour),

    member(stand(sally,_,_,_), Tour),
    member(stand(gary,_,_,_), Tour),
    member(stand(tom,_,_,_), Tour),
    member(stand(alice,_,_,_), Tour),


    member(stand(_,_,_,peppermint), Tour),    
    member(stand(_,_,_,peanutbutter), Tour),    
    member(stand(_,_,_,chocolatechip), Tour),    
    member(stand(_,_,_,coffeebean), Tour),    

    /* Hint nr 2 */
    member(stand(sally,_,_,_), Tour),
    member(stand(sally,NotRockland,_,_), Tour),
    member(stand(_,_,thursday,NotPeppermint), Tour),
    not(NotRockland = rockland),
    not(NotPeppermint = peppermint),
    
    /* Hint nr 3*/
    before(stand(_,granite,_,_), stand(_,_,_,chocolatechip), Tour),
    before(stand(gary,_,_,_), stand(_,granite,_,_), Tour),

    /* Hint nr 4*/
    member(stand(tom,_,NotTuesday,peanutbutter), Tour),    
    not(NotTuesday = tuesday),

    /* Hint nr 5*/
    member(stand(NotAlice,_,wednesday, coffeebean), Tour),
    member(stand(alice,_,_,_), Tour),
    not(NotAlice = alice),

    /* Hint nr 6*/
    before(stand(_,marsh,_,_), stand(sally,_,_,_), Tour).
    
    


before(X1,X2, L) :- append(_,[X1,X2 | _], L).




d1(stand(garry, rockland, tuesday, peanutbutter)). 
d2(stand(sally, boulder, wednesday, coffeebean)). 

