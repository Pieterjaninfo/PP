-- Haskell Session 3 of block 2
import FPPrac.Trees
exRoseTree = RoseNode "root" [RoseNode "leftchild" [RoseNode "grandleftchild1" [], RoseNode "grandleftchild2" []], RoseNode "rightchild"[]]

--Exercise 1
--a
data Tree1a = Leaf1a Int
            | Node1a Int Tree1a Tree1a
            deriving Show
exTree1a = Node1a 20 (Node1a 10 (Node1a 4 (Leaf1a 1) (Leaf1a 2)) (Node1a 3 (Leaf1a 5) (Leaf1a 7))) (Node1a 9 (Node1a 6 (Leaf1a 8) (Leaf1a 12)) (Node1a 13 (Leaf1a 15) (Leaf1a 17)))

pp1a :: Tree1a -> RoseTree
pp1a (Leaf1a n) = RoseNode (show n) []
pp1a (Node1a n left right) = RoseNode (show n) [pp1a left, pp1a right]

--b
data Tree1b = Leaf1b (Int, Int)
            | Node1b (Int, Int) Tree1b Tree1b
            deriving Show
exTree1b = Node1b (69,96) (Node1b (11,11) (Leaf1b (4,4)) (Leaf1b (42,1))) (Leaf1b (14,1))

pp1b :: Tree1b -> RoseTree
pp1b (Leaf1b n) = RoseNode (show n) []
pp1b (Node1b n left right) = RoseNode (show n) [pp1b left, pp1b right]

--c 
data Tree1c = Leaf1c Int
            | Node1c Tree1c Tree1c
            deriving Show
exTree1c = Node1c (Node1c (Leaf1c 4) (Leaf1c 3)) (Leaf1c 14)
pp1c :: Tree1c -> RoseTree
pp1c (Leaf1c n) = RoseNode (show n) []
pp1c (Node1c left right) = RoseNode "" [pp1c left, pp1c right]

--d
{-
data Tree1d = Leaf1d (Int,Int)
            | Node1d Tree1d Tree1d
            deriving Show
exTree1d = Node1d (Node1d (Leaf1d (4,4)) (Leaf1d (42,1))) (Leaf1d (14,1))

pp1d :: Tree1d -> RoseTree
pp1d (Leaf1d n) = RoseNode (show n) []
pp1d (Node1d left right) = RoseNode "" [(pp1d left), (pp1d right)]
-}
data Tree1d = Leaf1d (Int,Int)
            | Node1d [Tree1d]
            deriving Show
exTree1d = Node1d [Node1d [Leaf1d (1,1)], Node1d [Leaf1d (2,2), Leaf1d (3,3), Leaf1d (4,4)], Leaf1d (5,5)]

pp1d :: Tree1d -> RoseTree
pp1d (Leaf1d n) = RoseNode (show n) []
pp1d (Node1d xs) = RoseNode "" (map pp1d xs)


-- Exercise 2
--a
treeAdd :: Int -> Tree1a -> Tree1a
treeAdd x (Leaf1a n) = Leaf1a (n+x)
treeAdd x (Node1a n left right) = Node1a (n+x) (treeAdd x left) (treeAdd x right)

--b
treeSquare :: Tree1a -> Tree1a
treeSquare (Leaf1a n) = Leaf1a (n^2)
treeSquare (Node1a n left right) = Node1a (n^2) (treeSquare left) (treeSquare right)

--c
mapTree :: (Int -> Int) -> Tree1a -> Tree1a
mapTree f (Leaf1a n) = Leaf1a (f n)
mapTree f (Node1a n left right) = Node1a (f n) (mapTree f left) (mapTree f right)

treeAdd2 :: Int -> Tree1a -> Tree1a
treeAdd2 x tree = mapTree (+x) tree
treeSquare2 :: Tree1a -> Tree1a
treeSquare2 tree = mapTree (^2) tree

--d
addNode :: Tree1b -> Tree1a
addNode (Leaf1b (n1,n2)) = Leaf1a (n1+n2)
addNode (Node1b (n1,n2) left right) = Node1a (n1+n2) (addNode left) (addNode right)

--e
mapTree2 :: ((Int,Int) -> Int) -> Tree1b -> Tree1a
mapTree2 f (Leaf1b n) = Leaf1a (f n)
mapTree2 f (Node1b n left right) = Node1a (f n) (mapTree2 f left) (mapTree2 f right)

-- showRoseTreeList [pp1b $ exTree1b, pp1a $ mapTree2 (\(a,b) -> a+b) exTree1b]


--Exercise 3
--a
binMirror :: Tree1a -> Tree1a
binMirror (Leaf1a n) = Leaf1a n
binMirror (Node1a n left right) = Node1a n (binMirror right) (binMirror left)
--showRoseTreeList [pp1a exTree1a, pp1a $ binMirror exTree1a, pp1a $ binMirror $ binMirror exTree1a]

--b
binMirror2 :: Tree1d -> Tree1d
binMirror2 (Leaf1d (n1,n2)) = Leaf1d (n2,n1)
binMirror2 (Node1d xs) = Node1d (map binMirror2 (reverse xs))


--Exercise 4
data Tree4 = Leaf4
           | Node4 Int Tree4 Tree4
           deriving Show
exTree4 = Node4 21 (Node4 10 (Node4 9 Leaf4 Leaf4) (Node4 12 Leaf4 Leaf4)) (Node4 23 Leaf4 Leaf4)

exTree4b = Node4 21 (Node4 10 (Node4 9 Leaf4 Leaf4) (Node4 12 Leaf4 (Node4 15 Leaf4 Leaf4))) (Node4 23 Leaf4 Leaf4)

pp4 :: Tree4 -> RoseTree
pp4 Leaf4 = RoseNode "" [] 
pp4 (Node4 n left right) = RoseNode (show n) [pp4 left, pp4 right]

--a
insertTree :: Int -> Tree4 -> Tree4
insertTree x Leaf4 = Node4 x Leaf4 Leaf4
insertTree x (Node4 n left right)
   | x < n = Node4 n (insertTree x left) right
   | otherwise = Node4 n left (insertTree x right)


--b
makeTree :: [Int] -> Tree4
makeTree xs = makeTree' xs Leaf4
makeTree' [] tree = tree
makeTree' (x:xs) tree = makeTree' xs (insertTree x tree)

makeTree2 :: [Int] -> Tree4
makeTree2 xs = makeTree2' xs Leaf4
makeTree2' xs tree = foldr insertTree Leaf4 (reverse xs)

--c
makeList :: Tree4 -> [Int]
makeList Leaf4 = []
makeList (Node4 n left right) = [n] ++ makeList left ++ makeList right

makeSortedList :: Tree4 -> [Int]
makeSortedList Leaf4 = []
makeSortedList (Node4 n left right) = makeSortedList left ++ [n] ++ makeSortedList right

--d 
sortList :: [Int] -> [Int]
sortList xs = makeSortedList $ makeTree xs

--e
sortTree :: Tree4 -> Tree4
sortTree tree = makeTree $ makeList tree


-- Exercise 5
subtreeAt :: Int -> Tree4 -> Tree4
subtreeAt x Leaf4 = error "A node with value n does not exist!"
subtreeAt x (Node4 n left right)
    | x == n = Node4 n left right
    | x < n = subtreeAt x left
    | otherwise = subtreeAt x right
--showRoseTreeList [pp4 exTree4, pp4 $ sortTree exTree4, pp4 $ subtreeAt 12 $ sortTree exTree4]


-- Exercise 6
cutOffAt :: Int -> Tree4 -> Tree4
cutOffAt 0 _ = Leaf4
cutOffAt _ Leaf4 = Leaf4
cutOffAt x (Node4 n left right) = Node4 n (cutOffAt (x-1) left) (cutOffAt (x-1) right)


-- Exercise 7
--a
replace :: String -> Int -> Tree1a -> Tree1a
replace "" x (Leaf1a n) = Leaf1a x
replace "" x (Node1a n left right) = Node1a x left right
replace path x (Leaf1a n) = error "Invalid path does not lead to a node!"
replace path x (Node1a n left right)
    | head path == 'l' = Node1a n (replace (tail path) x left) right
    | head path == 'r' = Node1a n left (replace (tail path) x right)

--b
subTree :: String -> Tree1a -> Tree1a
subTree "" tree = tree
subTree path (Leaf1a n) = error "Invalid path does not lead to a node!"
subTree path (Node1a n left right)
    | head path == 'l' = subTree (tail path) left
    | head path == 'r' = subTree (tail path) right

--c
rightNeighbour path tree
    | last path == 'l' = 3 
    | last path == 'r' = 3


rightNeighb = 1


remove_r [] = error "unable to backtrack any further"
remove_r xs
    | last xs == 'r' = remove_r (init xs)
    | otherwise = xs

remove_l [] = error "unable to backtrack any further"
remove_l xs
    | last xs == 'l' = remove_l (init xs)
    | otherwise = xs




-- Exercise 8
--a
depth :: Tree4 -> Int
depth Leaf4 = 0
depth (Node4 n left right) = 1 + maximum [depth left, depth right]

isBalanced :: Tree4 -> Bool
isBalanced Leaf4 = True
isBalanced (Node4 n left right) = abs (depth left - depth right) <= 1 && isBalanced left && isBalanced right

--b
balance :: Tree4 -> Tree4
balance tree = balance' (makeSortedList tree)

balance' :: [Int] -> Tree4
balance' [] = Leaf4
balance' [x] = Node4 x Leaf4 Leaf4
balance' [x1, x2] = Node4 x1 Leaf4 (balance' [x2])
balance' xs = Node4 r (balance' left) (balance' right)
    where (left, r:right) = splitAt (length xs `div` 2)  xs

--c
checkCorrectlyBalanced :: Tree4 -> (Bool,Bool)
checkCorrectlyBalanced tree = (isBalanced tree, isBalanced $ balance tree)


