package model

@JvmInline
value class Name(val value: String) {
    init {
        require(value.isNotBlank() && value.all{ it.isLetterOrDigit() }){ "Invalid name $value" }
    }
    override fun toString() = value
}