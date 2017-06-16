module Main where
import Control.Concurrent
import Control.Concurrent.STM   -- cabal install stm
import Data.Array

-- indicates the (fixed) size of a queue
size :: Int
size = 7

data Queue e = Queue {
    shead :: TVar Int,
    stail :: TVar Int,
    scount :: TVar Int,
    sa :: Array Int (TVar e)
}

newQueue :: IO (Queue a)
newQueue = atomically $ do
    shead <- newTVar 0
    stail <- newTVar 0
    count <- newTVar 0
    arr <- sequence (replicate size (newTVar undefined))
    let ac = array (0, size - 1) (zip [0 .. size - 1] arr)
    return (Queue shead stail count ac)

enqueue :: Queue a -> a -> IO ()
enqueue queue value = atomically $ do
    -- FILL IN !

dequeue :: Queue a -> IO a
dequeue = atomically . dequeue_stm

dequeue_stm :: Queue a -> STM a
dequeue_stm queue = do
    -- FILL IN !! 
            
dequeueEither :: Queue a -> Queue a -> IO a
dequeueEither q1 q2 = atomically $
    -- FILL IN !!

main :: IO ()
main = do
    q <- newQueue 
    enqueue q 1
    enqueue q 2
    enqueue q 3
    enqueue q 4
    enqueue q 5
    enqueue q 6
    enqueue q 7
    w <- dequeue q 
    enqueue q 8 
    -- enqueue q 9 -- note: should block when you add this 

    x <- dequeue q 
    y <- dequeue q 
    z <- dequeue q 
    -- note: try to see if deque blocks if you did not enqueue enough items
        
    putStrLn (show w)
    putStrLn (show x)
    putStrLn (show y)
    putStrLn (show z)