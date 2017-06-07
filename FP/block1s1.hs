import Data.Char

-- Exercise 1
f :: Num a => a -> a
f x = 2*x^2 + 3*x - 5

-- Exercise 2
code :: Int -> Char -> Char
code n x
    | isLower && val + n > 122 = chr(val + n - 26)
    | isLower && val + n < 97  = chr(val + n + 26)    
    | isLower = chr(val + n)
    | isUpper && val + n > 90 = chr(val + n - 26)
    | isUpper && val + n < 65 = chr(val + n + 26)
    | isUpper = chr(val + n)
    | otherwise = x
    where val = ord x
          isLower = val >= 97 && val <= 122
          isUpper = val >= 65 && val <= 90

-- Exercise 3
interest :: Num a => a -> a -> Int -> a
interest b i 0 = b
interest b i n = i * interest b i (n-1)

-- Exercise 4
discr :: (Num a, Ord a) => a -> a -> a -> a
discr a b c
    | val >= 0 = val 
    | otherwise = error "negative discriminant"
    where val = b^2 - 4*a*c

root1 :: (Floating a, Ord a) => a -> a -> a -> a
root1 a b c = (-b + sqrt(discr a b c)) / (2*a)
root2 a b c = (-b - sqrt(discr a b c)) / (2*a)

-- Exercise 5
extrX :: Fractional a => a -> a -> a
extrX a b = -b / (2*a)
extrY :: Fractional a => a -> a -> a -> a
extrY a b c = a * (extrX a b)^2 + b* extrX a b + c

-- Exercise 6
mylength :: Num a => [t] -> a
mylength [] = 0
mylength (x:xs) = 1 + mylength xs

mysum :: Num a => [a] -> a
mysum [] = 0
mysum (x:xs) = x + mysum xs

myreverse :: [a] -> [a]
myreverse [] = []
myreverse (x:xs) = myreverse xs ++ [x]

mytake :: (Num a, Eq a) => [t] -> a -> [t]
mytake (x:xs) 0 = []
mytake [] n = []
mytake (x:xs) n = x : mytake xs (n-1)

myelem :: Eq a => [a] -> a -> Bool
myelem [] y = False
myelem (x:xs) y
    | x == y = True
    | otherwise = myelem xs y

myconcat :: [[a]] -> [a]
myconcat [] = []
myconcat (x:xs) = x ++ myconcat xs

mymaximum :: Ord a => [a] -> a
mymaximum [x] = x
mymaximum (x:y:xs)
    | x >= y = mymaximum (x:xs)
    | otherwise = mymaximum (y:xs)

myzip :: [a] -> [b] -> [(a,b)]
myzip xs [] = []
myzip [] ys = []
myzip (x:xs) (y:ys) = (x,y) : myzip xs ys

-- Exercise 7
r :: (Num a, Enum a) => a -> a -> [a]
r a d = [a + d*i | i <- [0..]]

r1 :: (Num a, Enum a) => a -> a -> Int -> [a]
r1 a d n = take n (r a d)

total :: (Num a, Enum a) => a -> a -> Int -> Int -> a
total a d i j = sum (drop (i-1) (take (j) (r a d)))

-- Exercise 8
allEqual :: Ord a => [a] -> Bool
allEqual [x] = True
allEqual (x:y:xs)
    | x == y = allEqual (y:xs)
    | otherwise = False
{-  NOT A PRETTY SOLUTION, REFER TO HIGHER-ORDER SOLUTION BELOW
isAs :: (Num a, Enum a, Ord a) => [a] -> Bool
isAs xs = allEqual [fst pair - (snd pair * d) | d <- [xs !! 1 - xs !! 0], pair <- (zip xs [0..])]
-}
isAs :: (Num a, Ord a) => [a] -> Bool
isAs [x] = True
isAs xs = allEqual $ zipWith (-) (tail xs) (init xs)

-- Exercise 9
equalLength :: Foldable a => [a b] -> Bool
equalLength [x] = True
equalLength (x:y:xs)
    | length x == length y = equalLength (y:xs)
    | otherwise = False

matrixTotalRows :: Num a => [[a]] -> [a]
--matrixTotalRows xs = [sum x | x <- xs]
matrixTotalRows xs = map sum xs

transpose :: [[a]] -> [[a]]
transpose ([]:_) = []
transpose xs = map head xs : transpose (map tail xs)

matrixTotalCols :: Num a => [[a]] -> [a]
matrixTotalCols xs = [sum x | x <- transpose xs]

