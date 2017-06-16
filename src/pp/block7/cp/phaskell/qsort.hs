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
    
main :: IO ()
main = do
    t0 <- getCurrentTime
    let input = (take 200000 (randomRs (0, 100) (mkStdGen 42))) :: [Int]
    seq (forceList input) (return ())
    
    t1 <- getCurrentTime
    let r = sum (qsort input)
    seq r (return ())
    t2 <- getCurrentTime
    
    putStrLn("sum of sort:  " ++ show r)
    putStrLn("time to sort: " ++ show (t2 `diffUTCTime` t1))