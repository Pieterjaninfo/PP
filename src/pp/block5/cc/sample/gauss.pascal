Program gauss;

Var total, i, n: Integer;

Begin
    In("Upperbound? ", n);
    i := 0;
    total := 0;
    While i < n Do
    Begin
        i := i + 1;
        total := total + i
    End;
    Out("Total: ", total)
End.