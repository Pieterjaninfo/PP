module Main where 
import Control.Concurrent
import Control.Concurrent.MVar

thread_a :: MVar Int -> MVar Float -> IO ()
thread_a send recv = do
    putMVar send 72
    v <- takeMVar recv
    putStrLn ("received: " ++ (show v))

thread_b :: MVar Int -> MVar Float -> IO ()
thread_b recv send = do
    z <- takeMVar recv
    putMVar send (1.2 * (fromIntegral z))

main :: IO ()
main = do
    a <- newEmptyMVar
    b <- newEmptyMVar
    forkIO (thread_a a b)
    forkIO (thread_b a b)
    threadDelay 10000  -- make sure that both thread have ended

-- $ ghc --make -threaded wait.hs
-- $ ./wait +RTS -N2