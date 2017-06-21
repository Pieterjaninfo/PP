module Main where 
import Data.Time 
import Control.Parallel     -- cabal install parallel
import System.Random        -- cabal install random

forceList :: [a] -> ()
forceList [] = ()
forceList (x:xs) = x `pseq` forceList xs

qsort :: Ord a => [a] -> [a]
qsort [] = []
qsort (x:xs) = l ++ x : h where
    l = qsort [y | y <- xs, y < x]
    h = qsort [y | y <- xs, y >= x]
    



qsortpar :: Ord a => [a] -> [a]
qsortpar [] = []
qsortpar (x:xs) = (forceList l) `par` ((forceList h) `pseq` (l ++ x : h))
    where l = qsortpar [y | y <- xs, y < x]
          h = qsortpar [y | y <- xs, y >= x]


main :: IO ()
main = do
    t0 <- getCurrentTime
    let input = (take 200000 (randomRs (0, 100) (mkStdGen 42))) :: [Int]
    seq (forceList input) (return ())
    {-
    t1 <- getCurrentTime
    let r = sum (qsort input)
    seq r (return ())
    t2 <- getCurrentTime
    
    putStrLn("sum of sort:  " ++ show r)
    putStrLn("time to sort: " ++ show (t2 `diffUTCTime` t1))
    -}

    t3 <- getCurrentTime
    let r2 = sum (qsortpar input)
    seq r2 (return ())
    t4 <- getCurrentTime

    putStrLn("sum of sort par:  " ++ show r2)
    putStrLn("time to sort par: " ++ show (t4 `diffUTCTime` t3))
