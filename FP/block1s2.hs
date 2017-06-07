import Data.Char
import Data.List

{-instance Num a => Num [a] where
    xs + ys = zipWith (+) xs ys
-}

-- Exercise 1
myfilter :: (a -> Bool) -> [a] -> [a]
myfilter f [] = []
myfilter f (x:xs)
    | f x = x : myfilter f xs
    | otherwise = myfilter f xs

{-
foldl (-) 100 [1,2,3] = ((((100) - 1) - 2) - 3)
foldr (-) 100 [1,2,3] = (3 - (2 - (1 - (100))))
-}
{-
myfoldl :: (a -> b -> a) -> a -> [b] -> a
myfoldl f a [] = a
myfoldl f a xs = f (myfoldl f a (init xs)) (last xs)
-}
myfoldl :: (a -> b -> a) -> a -> [b] -> a
myfoldl f a [] = a
myfoldl f a (x:xs) = myfoldl f (f a x) xs 
{-
myfoldr :: (a -> b -> b) -> b -> [a] -> b
myfoldr f a [] = a
myfoldr f a xs = f (last xs) (myfoldr f a (init xs))
-}
myfoldr :: (a -> b -> b) -> b -> [a] -> b
myfoldr f a [] = a
myfoldr f a (x:xs) = f x (myfoldr f a xs)

myzipWith :: (a -> b -> c) -> [a] -> [b] -> [c]
myzipWith f [] _ = []
myzipWith f _ [] = []
myzipWith f (x:xs) (y:ys) = f x y : myzipWith f xs ys

-- Exercise 2
--a
{-
(unique) name, age, sexe, place of residence
Type: ([Char], Int, [Char], [Char])

let db = [("jan",20,"male","enschede"), ("peter",69,"male","amsterdam"),("garen",31,"male","damacia"), ("boy",39,"male","enschede"),("sona",30,"female","enschede"), ("jinx",25,"female","damacia"),("girl",39,"female","enschede"), ("orianna",40,"female","nexus")]
-}

--b
getname :: (a,b,c,d) -> a
getage :: (a,b,c,d) -> b
getsexe :: (a,b,c,d) -> c
getpos :: (a,b,c,d) -> d

getname (x,_,_,_) = x 
getage (_,x,_,_) = x
getsexe (_,_,x,_) = x
getpos (_,_,_,x) = x

type Person = (String, Int, String, String)

--c
increaseAge_r :: Num b => b -> [(a,b,c,d)] -> [(a,b,c,d)]
increaseAge_r n [] = []
increaseAge_r n (x:xs) = (getname x, getage x + n, getsexe x, getpos x) : increaseAge_r n xs

increaseAge_l n xs = [(getname x, getage x + n, getsexe x, getpos x) | x<-xs]

increaseAge_h n xs = map incr xs
    where incr x = (getname x, getage x + n, getsexe x, getpos x)

--d
getwomen30to40_r :: (Num b, Ord b) => [(a,b,[Char],d)] -> [(a,b,[Char],d)]
getwomen30to40_r [] = []
getwomen30to40_r (x:xs)
    | gender == "female" && age >= 30 && age <= 40 = x : getwomen30to40_r xs
    | otherwise = getwomen30to40_r xs
    where gender = getsexe x
          age = getage x

getwomen30to40_l xs = [x | x<-xs, getsexe x == "female", getage x >= 30, getage x <= 40]

getwomen30to40_h xs = filter p xs
    where p x = getsexe x == "female" && getage x >= 30 && getage x <= 40

--e
getPerson :: [Char] -> [([Char],b,c,d)] -> ([Char],b,c,d)
getPerson y (x:xs)
    | map toLower y == map toLower (getname x) = x
    | otherwise = getPerson y xs

--f
sortbyage :: Ord b => [(a,b,c,d)] -> [(a,b,c,d)]
sortbyage xs = sba_isort xs

sba_isort :: Ord b => [(a,b,c,d)] -> [(a,b,c,d)]
sba_isort' :: Ord b => [(a,b,c,d)] -> [(a,b,c,d)] -> [(a,b,c,d)]
sba_ins :: Ord b => (a,b,c,d) -> [(a,b,c,d)] -> [(a,b,c,d)]

sba_isort xs = sba_isort' xs []
sba_isort' [] ys = ys
sba_isort' (x:xs) ys = sba_isort' xs (sba_ins x ys)
sba_ins y [] = [y]
sba_ins y (x:xs)
    | getage y <= getage x = y : (x:xs)
    | otherwise = x : sba_ins y xs

-- Exercise 3
--a
sieve :: [Integer]
sieve' :: Integral a => [a] -> [a]
sieve = sieve' [2..]
sieve' (x:xs) = x : sieve' (filter (\y -> y `mod` x /= 0) xs)

isPrime :: Integer -> Bool
--isPrime n = take 1 (dropWhile (<n) sieve) == [n]
isPrime n = last (takeWhile (<=n) sieve) == n

getnPrimes :: Int -> [Integer]
getnPrimes n = take n sieve

getPrimes_ln :: Integer -> [Integer]
getPrimes_ln n = takeWhile (<n) sieve

--b
dividers :: Integral a => a -> [a]
dividers n = [x | x<-[1..n], n `mod` x == 0]

isPrime2 :: Integral a => a -> Bool
isPrime2 n = length (dividers n) == 2

-- Exercise 4
--a
pyth :: (Num a, Eq a, Enum a) => a -> [(a,a,a)]
pyth n = [(a,b,c) | a<-[1..n], b<-[1..n], c<-[1..n], a^2+b^2 == c^2]

--b
pyth' :: Integral a => a -> [(a,a,a)]
pyth' n = [(a,b,c) | a<-[1..n], b<-[a..n], c<-[1..n], a^2+b^2 == c^2, gcd a b == 1]

-- Exercise 5
--a
increasing :: Ord a => [a] -> Bool
increasing [x] = True
increasing (x:y:xs)
    | x < y = increasing (y:xs)
    | otherwise = False

--b
weakIncr :: (Fractional a, Real a)=> [a] -> Bool
weakIncr [x] = True
weakIncr xs
    | last xs > average (init xs) = weakIncr (init xs)
    | otherwise = False
    where average xs = realToFrac (sum xs) / genericLength xs

-- Exercise 6
--a
sublist :: Eq a => [a] -> [a] -> Bool
sublist xs [] = False
sublist xs (y:ys)
    | sublist' xs (y:ys) = True
    | otherwise = sublist xs ys

sublist' :: Eq a => [a] -> [a] -> Bool
sublist' [] _ = True
sublist' _ [] = False
sublist' (x:xs) (y:ys) = x == y && sublist' xs ys

--b
partsublist :: Eq a => [a] -> [a] -> Bool
partsublist [] _ = True
partsublist _ [] = False
partsublist (x:xs) (y:ys)
    | x == y = partsublist xs ys
    | otherwise = partsublist (x:xs) ys


-- Exercise 7
--a
bsort :: Ord a => [a] -> [a]
bsort [] = []
bsort xs = bsort (init new) ++ [(last new)]
    where new = bubble xs

bubble :: Ord a => [a] -> [a]  
bubble [x] = [x]
bubble (x:y:xs)
    | x > y = y : bubble (x:xs)
    | otherwise = x : bubble (y:xs)

--b
mmsort :: Ord a => [a] -> [a]
mmsort [] = []
mmsort [x] = [x]
mmsort xs = min : mmsort (xs \\ [min, max]) ++ [max]
    where min = minimum xs
          max = maximum xs

--c
isort :: Ord a => [a] -> [a]
isort (x:xs) = ins x (isort xs)

ins :: Ord a => a -> [a] -> [a]
ins x [] = [x]
ins x (y:ys)
    | x > y = y : ins x ys
    | otherwise = x : y : ys

--d
msort :: Ord a => [a] -> [a]
msort [x] = [x]
msort xs = merge (msort fst) (msort snd)
    where (fst, snd) = splitAt (length xs `div` 2) xs

merge :: Ord a => [a] -> [a] -> [a]
merge xs [] = xs
merge [] ys = ys 
merge (x:xs) (y:ys)
    | x < y = x : merge xs (y:ys)
    | otherwise = y : merge (x:xs) (ys)

--e
qsort :: Ord a => [a] -> [a]
qsort [] = []
qsort [x] = [x]
qsort (x:xs) = qsort([a | a<-xs, a <= x]) ++ [x] ++ qsort([b | b<-xs, b > x])


