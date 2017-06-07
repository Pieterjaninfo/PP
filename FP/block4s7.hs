-- Haskell session 7 of block 4
import Data.Maybe

data Expr = Const Int                   -- constant n
          | Var String                  -- variable x
          | BinOp String Expr Expr      -- expression e0 OP e1
          | App Expr Expr               -- expression f e
          | Ifte Expr Expr Expr         -- if ex0 then ex1 else ex2
          | Lambda Type Expr            -- Labda expression
          deriving Show

data Type = IntType                     -- type int
          | BoolType                    -- type boolean
          | Tuple                       -- two tuple
          | Triple                      -- three tuple
          | FunType Type Type           -- type t0 -> t1
          deriving (Show, Eq)

type Tuple  = (Type,Type)
type Triple = (Type,Type,Type) 

type Env = [(String, Type)]


env   = [ ("a", IntType)
        , ("b", IntType)
        , ("c", Tuple)
        , ("d", Triple)
        , ("e", BoolType)
        , ("+", FunType IntType (FunType IntType IntType))
        , ("2*", FunType IntType IntType)
        , ("&&", FunType BoolType (FunType BoolType BoolType))
        , ("||", FunType BoolType (FunType BoolType BoolType))
        , ("==", FunType IntType (FunType IntType BoolType))
        , ("!=", FunType IntType (FunType IntType BoolType))
        , ("<=", FunType IntType (FunType IntType BoolType))
        , (">=", FunType IntType (FunType IntType BoolType))
        , ("<", FunType IntType (FunType IntType BoolType))
        , (">", FunType IntType (FunType IntType BoolType))
        , ("===", FunType Tuple (FunType Tuple BoolType))
        , ("====", FunType Triple (FunType Triple BoolType))
        ]

--Exercise 1
typeOf :: Env -> Expr -> Type
typeOf env (Var x)
    | t == Nothing    = error "Var nonexisting in environment"
    | otherwise       = fromJust t
    where t = lookup x env

typeOf env (Const x) = IntType

typeOf env (BinOp op e e') = case t_op of
    Nothing                             -> error "Unknown operator"
    Just (FunType t0 (FunType t1 t2))
        | t0 == t_e && t1 == t_e'       -> t2   
        | otherwise                     -> error "Unmatching operator type with argument types"
    Just _                              -> error "Arguments mismatch"
    where t_op = lookup op env
          t_e  = typeOf env e
          t_e' = typeOf env e'

typeOf env (App f x)
    | t_x == arg    = res
    | otherwise     = error "Invalid argument type not accepted"
    where (FunType arg res) = typeOf env f
          t_x = typeOf env x


--Exericse 2
typeOf env (Ifte cond ex1 ex2)
    | t_ex1 /= t_ex2        = error "Result type of then and else part do not match"    
    | t_cond == BoolType    = t_ex1
    | otherwise             = error "Condition not of type BoolType"
    where t_cond = typeOf env cond
          t_ex1  = typeOf env ex1
          t_ex2  = typeOf env ex2


--Exercise 3
--added Tuple/Triple

--Exercise 4
typeOf env (Lambda a ex) =  FunType a t_ex
    where t_ex = typeOf env ex



