package console

data class CommandLine(val name: String, val args:List<String>)

tailrec fun readCommand(): CommandLine {
    print("> ")
    val line = readln().uppercase().split(' ').filter { it.isNotEmpty() }
    return if (line.isNotEmpty())
        CommandLine(line[0], line.drop(1))
    else readCommand()
}