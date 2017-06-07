-- Haskell session 5 of block 3
import FP_Core
import FPPrac.Trees

--Exercise 2
codeGenerator :: Expr -> [Instr]
codeGenerator expr = codeGenerator' expr ++ [EndProg]
codeGenerator' (Const n) = [PushConst n]
codeGenerator' (BinExpr op left right) = codeGenerator' left ++ codeGenerator' right ++ [Calc op]

--Exercise 3
ppExpr :: Expr -> RoseTree
ppExpr (Const n) = RoseNode (show n) []
ppExpr (BinExpr op left right) = RoseNode (show op) [ppExpr left, ppExpr right]

--Exercise 4
--

--Exercise 5
codeGenerator2 :: Stmnt -> [Instr]
codeGenerator2 (Assign n expr) = codeGenerator' expr ++ [Store n, EndProg]


--Exercise 6
class CodeGen a where
    codeGen :: a -> [Instr]
    codeGen' :: a -> [Instr]
    pp :: a -> RoseTree

instance CodeGen Expr where
    codeGen expr = codeGen' expr ++ [EndProg]
    codeGen' (Const n) = [PushConst n]
    codeGen' (Var pointer) = [PushAddr pointer]
    codeGen' (BinExpr op left right) = codeGen' left ++ codeGen' right ++ [Calc op]

    pp (Const n) = RoseNode (show n) []
    pp (Var pointer) = RoseNode ("Var " ++ show pointer) []
    pp (BinExpr op left right) = RoseNode (show op) [pp left, pp right]


instance CodeGen Stmnt where
    codeGen stmnt = codeGen' stmnt ++ [EndProg]
    codeGen' (Assign pointer expr) = codeGen' expr ++ [Store pointer]
    codeGen' (Repeat expr ss) = [PushPC] ++ codeGen' expr ++ (concat $ map codeGen' ss) ++ [EndRep]

    pp (Assign pointer expr) = RoseNode ("Assign " ++ show pointer) [pp expr]
    pp (Repeat (Const n) ss) = RoseNode ("Repeat " ++ show n) (map pp ss)
    pp (Repeat (Var n) ss) = RoseNode ("Repeat Var" ++ show n) (map pp ss)
    pp (Repeat (BinExpr op left right) ss) = RoseNode "Repeat tree" (map pp ss)

--Exercise 7
--

--Exercise 8
repexpr = Repeat (Const 3) [Assign 3 (Const 0), Assign 5 (Const 10), Assign 2 (Var 5)]
repexpr2 = Repeat (Const 3) [Assign 3 (BinExpr Add (Const 0) (Const 1)), Assign 5 (Const 10), Assign 2 (Var 5)]

repexpr3 = Repeat (BinExpr Add (Const 1) (Const 2)) [Assign 3 (BinExpr Add (Const 0) (Const 1)), Assign 5 (Const 10), Assign 2 (Var 5)]



incexpr = Repeat (Const 10) [Assign 0 (BinExpr Add (Var 0) (Const 1)), Assign 1 (BinExpr Add (Var 0) (Var 1))]
incexprb = Repeat (Const 5) [Repeat (Const 2) [Assign 0 (BinExpr Add (Var 0) (Const 1)), Assign 1 (BinExpr Add (Var 0) (Var 1))]]
incexprc = Repeat (Const 3) [Repeat (Const 5) [Repeat (Const 2) [Assign 0 (BinExpr Add (Var 0) (Const 1)), Assign 1 (BinExpr Add (Var 0) (Var 1))]]]



