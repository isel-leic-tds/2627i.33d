fun main() {
    val stk = MutableStack<Char>()
    stk.push('A')
    stk.push('B')
    print(stk.top)
    stk.push('C')              // push da figura
    while( !stk.isEmpty() ) {
        val elem = stk.pop()
        print(elem)
    }
    // Output: BCBA

    val empty = Stack<Char>()
    val one = empty.push('A')
    val two = one.push('B')
    print(empty.isEmpty())
    print(two.pop().top)
    // Output: trueA
    val res = one.pop2()
    print(res.first.isEmpty())
    print(res.second)
    // Output: trueA
}
