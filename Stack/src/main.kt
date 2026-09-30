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
}
