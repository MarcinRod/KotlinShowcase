package pl.marrod.kotlinshowcase.data

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.AltRoute
import androidx.compose.material.icons.automirrored.rounded.List
import androidx.compose.material.icons.rounded.*
import androidx.compose.ui.graphics.vector.ImageVector
import pl.marrod.kotlinshowcase.R

// ─── Helpers required by specific demo lambdas ───────────────────────────────

private interface DemoClickable {
    fun click(): String
    fun describe(): String = "I am clickable"
}

private enum class DemoStatus { LOADING, SUCCESS, ERROR }

private object DemoLogger {
    fun log(msg: String) = "LOG: $msg"
}

private data class DemoProduct(val name: String, val price: Double)

// ─── Data model ───────────────────────────────────────────────────────────────

data class KotlinConceptDetail(
    val id: String,
    @param:StringRes val titleRes: Int,
    @param:StringRes val descriptionRes: Int,
    @param:StringRes val cardDescriptionRes: Int,
    val codeSnippet: String,
    val demoLogic: () -> String,
    val icon: ImageVector
)

// ─── Concept catalogue ────────────────────────────────────────────────────────

object KotlinDataProvider {
    val concepts = listOf(

        // 1. Variables
        KotlinConceptDetail(
            id = "variables",
            titleRes = R.string.concept_variables_title,
            descriptionRes = R.string.concept_variables_description,
            cardDescriptionRes = R.string.concept_variables_card_desc,
            codeSnippet = """
                val pi = 3.14           // read-only, 
                                        // inferred Double
                var counter = 0         // mutable
                
                counter = 5             // OK
                // pi = 3.1415          // Compile error!
                
                // Explicit type annotation
                val name: String = "Android"
                var age: Int = 20
                
                // Declaration without initialisation
                var result: Int
                result = 42
            """.trimIndent(),
            demoLogic = {
                val pi = 3.14
                var counter = 0
                counter = 5
                val name: String = "Android"
                var age: Int = 20
                var result: Int
                result = 42
                buildString {
                    appendLine("val pi = 3.14          → $pi")
                    appendLine("var counter = 0; = 5   → $counter")
                    appendLine("val name: String       → \"$name\"")
                    appendLine("var age: Int           → $age")
                    appendLine("var result: Int; = 42  → $result")
                }
            },
            icon = Icons.Rounded.Abc
        ),

        // 2. Basic Data Types
        KotlinConceptDetail(
            id = "data_types",
            titleRes = R.string.concept_data_types_title,
            descriptionRes = R.string.concept_data_types_description,
            cardDescriptionRes = R.string.concept_data_types_card_desc,
            codeSnippet = """
                val count: Int = 42
                val big: Long = 9_999_999_999L
                val price: Double = 19.99
                val ratio: Float = 0.5f
                val isReady: Boolean = true
                val greeting: String = "Hello, Kotlin!"
                val letter: Char = 'K'
                
                // Collections as types
                val names: List<String> = listOf("Alice", "Bob")
                val scores: MutableList<Int> = mutableListOf(10, 20)
            """.trimIndent(),
            demoLogic = {
                val count: Int = 42
                val big: Long = 9_999_999_999L
                val price: Double = 19.99
                val ratio: Float = 0.5f
                val isReady: Boolean = true
                val greeting: String = "Hello, Kotlin!"
                val letter: Char = 'K'
                val names: List<String> = listOf("Alice", "Bob")
                buildString {
                    appendLine("Int:     $count")
                    appendLine("Long:    $big")
                    appendLine("Double:  $price")
                    appendLine("Float:   $ratio")
                    appendLine("Boolean: $isReady")
                    appendLine("String:  \"$greeting\"")
                    appendLine("Char:    '$letter'")
                    appendLine("List:    $names")
                }
            },
            icon = Icons.Rounded.DataObject
        ),

        // 3. Conditionals
        KotlinConceptDetail(
            id = "conditionals",
            titleRes = R.string.concept_conditionals_title,
            descriptionRes = R.string.concept_conditionals_description,
            cardDescriptionRes = R.string.concept_conditionals_card_desc,
            codeSnippet = """
                val x = 10
                
                // if as an expression 
                // (no ternary needed)
                val label = if (x > 5) "large" else "small"
                
                // when expression
                val result = when (x) {
                    1    -> "one"
                    in 2..10 -> "between 2 and 10"
                    else -> "other"
                }
                
                println(label)  // large
                println(result) // between 2 and 10
            """.trimIndent(),
            demoLogic = {
                val x = 10
                val label = if (x > 5) "large" else "small"
                val result = when (x) {
                    1        -> "one"
                    in 2..10 -> "between 2 and 10"
                    else     -> "other"
                }
                buildString {
                    appendLine("x = $x")
                    appendLine("if (x > 5) → \"$label\"")
                    appendLine("when ($x)  → \"$result\"")
                }
            },
            icon = Icons.AutoMirrored.Rounded.AltRoute
        ),

        // 4. Strings
        KotlinConceptDetail(
            id = "strings",
            titleRes = R.string.concept_strings_title,
            descriptionRes = R.string.concept_strings_description,
            cardDescriptionRes = R.string.concept_strings_card_desc,
            codeSnippet = """
                val name = "Android"
                
                // String templates
                val greeting = "Hello, ${'$'}name!"
                val info = "Length: ${'$'}{name.length} chars"
                
                // Multi-line raw string
                val block = ${"\"\"\""}
                    Line one
                    Line two
                ${"\"\"\""}.trimIndent()
                
                // Common operations
                val upper = name.uppercase()          // ANDROID
                val sub   = name.substring(0, 3)     // And
                val has   = name.contains("dro")     // true
                val parts = "a,b,c".split(",")       // [a, b, c]
                val trim  = "  hi  ".trim()           // "hi"
                val same  = "hello".equals("HELLO", ignoreCase = true) // true
                val num   = 123.toString()            // "123"
            """.trimIndent(),
            demoLogic = {
                val name = "Android"
                val greeting = "Hello, $name!"
                val upper = name.uppercase()
                val sub   = name.substring(0, 3)
                val has   = name.contains("dro")
                val parts = "a,b,c".split(",")
                val trim  = "  hi  ".trim()
                val same  = "hello".equals("HELLO", ignoreCase = true)
                val num   = 123.toString()
                buildString {
                    appendLine("name             = \"$name\"")
                    appendLine("template         = \"$greeting\"")
                    appendLine("uppercase()      = \"$upper\"")
                    appendLine("substring(0,3)   = \"$sub\"")
                    appendLine("contains(\"dro\") = $has")
                    appendLine("\"a,b,c\".split  = $parts")
                    appendLine("\"  hi  \".trim  = \"$trim\"")
                    appendLine("equals(ignore)   = $same")
                    appendLine("123.toString()   = \"$num\"")
                }
            },
            icon = Icons.Rounded.TextFields
        ),

        // 5. Loops
        KotlinConceptDetail(
            id = "loops",
            titleRes = R.string.concept_loops_title,
            descriptionRes = R.string.concept_loops_description,
            cardDescriptionRes = R.string.concept_loops_card_desc,
            codeSnippet = """
                // for with range (inclusive)
                for (i in 1..5) println(i)       // 1 2 3 4 5
                
                // downTo — counting backwards
                for (i in 5 downTo 1) println(i) // 5 4 3 2 1
                
                // until — exclusive upper bound
                for (i in 1 until 5) println(i)  // 1 2 3 4
                
                // step — custom increment
                for (i in 0..10 step 2) println(i) // 0 2 4 6 8 10
                
                // for over a collection
                val fruits = listOf("Apple", "Banana", "Cherry")
                for (fruit in fruits) println(fruit)
                
                // while loop
                var count = 3
                while (count > 0) { count-- }
            """.trimIndent(),
            demoLogic = {
                buildString {
                    append("1..5:          ")
                    for (i in 1..5) append("$i ")
                    appendLine()
                    append("5 downTo 1:    ")
                    for (i in 5 downTo 1) append("$i ")
                    appendLine()
                    append("1 until 5:     ")
                    for (i in 1 until 5) append("$i ")
                    appendLine()
                    append("0..10 step 2:  ")
                    for (i in 0..10 step 2) append("$i ")
                    appendLine()
                    val fruits = listOf("Apple", "Banana", "Cherry")
                    appendLine("Collection:")
                    for (fruit in fruits) appendLine("  - $fruit")
                    var count = 3
                    append("while > 0:     ")
                    while (count > 0) { append("$count "); count-- }
                }
            },
            icon = Icons.Rounded.Repeat
        ),

        // 6. Ranges
        KotlinConceptDetail(
            id = "ranges",
            titleRes = R.string.concept_ranges_title,
            descriptionRes = R.string.concept_ranges_description,
            cardDescriptionRes = R.string.concept_ranges_card_desc,
            codeSnippet = """
                val range     = 1..10           // 1 to 10 inclusive
                val untilEnd  = 1 until 10      // 1 to 9
                val backwards = 10 downTo 1     // 10, 9 … 1
                val stepped   = 1..10 step 3    // 1, 4, 7, 10
                
                // Membership check
                if (5 in range) println("5 is in 1..10")
                
                // Range in a condition
                val age = 18
                if (age in 13..19) println("Teenager")
                
                // Iterate
                for (i in 1..3) println(i)      // 1 2 3
            """.trimIndent(),
            demoLogic = {
                buildString {
                    append("1..10:         ")
                    for (i in 1..10) append("$i ")
                    appendLine()
                    append("1 until 10:    ")
                    for (i in 1 until 10) append("$i ")
                    appendLine()
                    append("10 downTo 1:   ")
                    for (i in 10 downTo 1) append("$i ")
                    appendLine()
                    append("1..10 step 3:  ")
                    for (i in 1..10 step 3) append("$i ")
                    appendLine()
                    appendLine("5 in 1..10 → ${5 in 1..10}")
                    val age = 18
                    appendLine("age=$age in 13..19 → ${age in 13..19} (Teenager)")
                }
            },
            icon = Icons.Rounded.LinearScale
        ),

        // 7. Functions
        KotlinConceptDetail(
            id = "functions",
            titleRes = R.string.concept_functions_title,
            descriptionRes = R.string.concept_functions_description,
            cardDescriptionRes = R.string.concept_functions_card_desc,
            codeSnippet = """
                // Basic function
                fun add(a: Int, b: Int): Int {
                    return a + b
                }
                
                // Single-expression function
                fun greetMsg(name: String) = "Hello, ${'$'}name!"
                
                // Default argument
                fun greet(name: String = "Guest") {
                    println("Hello, ${'$'}name!")
                }
                greet()        // Hello, Guest!
                greet("Anna")  // Hello, Anna!
                
                // Named arguments
                fun order(item: String, qty: Int, urgent: Boolean = false) { }
                order(item = "Coffee", qty = 2, urgent = true)
                
                // vararg
                fun sum(vararg nums: Int) = nums.sum()
                val total = sum(1, 2, 3, 4) // 10
            """.trimIndent(),
            demoLogic = {
                fun add(a: Int, b: Int) = a + b
                fun greetMsg(name: String) = "Hello, $name!"
                fun greet(name: String = "Guest") = "Hello, $name!"
                fun sum(vararg nums: Int) = nums.sum()
                buildString {
                    appendLine("add(3, 4)          → ${add(3, 4)}")
                    appendLine("greetMsg(\"Anna\")  → ${greetMsg("Anna")}")
                    appendLine("greet()            → ${greet()}")
                    appendLine("greet(\"Maria\")    → ${greet("Maria")}")
                    appendLine("sum(1,2,3,4)       → ${sum(1, 2, 3, 4)}")
                }
            },
            icon = Icons.Rounded.Functions
        ),

        // 8. Lambda Functions
        KotlinConceptDetail(
            id = "lambdas",
            titleRes = R.string.concept_lambdas_title,
            descriptionRes = R.string.concept_lambdas_description,
            cardDescriptionRes = R.string.concept_lambdas_card_desc,
            codeSnippet = """
                // No-parameter lambda
                val sayHi = { println("Hi!") }
                sayHi()
                
                // Single-parameter lambda
                val double = { x: Int -> x * 2 }
                println(double(5)) // 10
                
                // Lambda as function argument
                fun execute(action: () -> Unit) {
                    action()
                }
                execute { println("Lambda called!") }
                
                // 'it' — implicit single parameter
                val numbers = listOf(1, 2, 3, 4, 5)
                val evens = numbers.filter { it % 2 == 0 } // [2, 4]
            """.trimIndent(),
            demoLogic = {
                val sayHi = { "Hi!" }
                val double = { x: Int -> x * 2 }
                fun execute(action: () -> String) = action()
                val numbers = listOf(1, 2, 3, 4, 5)
                val evens   = numbers.filter { it % 2 == 0 }
                val doubled = numbers.map { it * 2 }
                buildString {
                    appendLine("val sayHi = { \"Hi!\" }")
                    appendLine("sayHi()            → ${sayHi()}")
                    appendLine()
                    appendLine("val double = { x: Int -> x * 2 }")
                    appendLine("double(5)          → ${double(5)}")
                    appendLine()
                    appendLine("execute { \"done\" } → ${execute { "done" }}")
                    appendLine()
                    appendLine("numbers = $numbers")
                    appendLine("filter { even }    → $evens")
                    appendLine("map { * 2 }        → $doubled")
                }
            },
            icon = Icons.Rounded.Code
        ),

        // 9. Collections
        KotlinConceptDetail(
            id = "collections",
            titleRes = R.string.concept_collections_title,
            descriptionRes = R.string.concept_collections_description,
            cardDescriptionRes = R.string.concept_collections_card_desc,
            codeSnippet = """
                // List
                val list    = listOf("A", "B", "C")
                val mutable = mutableListOf(1, 2, 3)
                mutable.add(4)
                
                // Set — duplicates are ignored
                val set = setOf("cat", "dog", "cat") // {cat, dog}
                
                // Map
                val map        = mapOf("one" to 1, "two" to 2)
                val mutableMap = mutableMapOf("a" to 10)
                mutableMap["b"] = 20
                
                // Higher-order operations
                val nums    = listOf(1, 2, 3, 4, 5)
                val evens   = nums.filter { it % 2 == 0 } // [2, 4]
                val doubled = nums.map { it * 2 }         // [2,4,6,8,10]
                val total   = nums.sum()                  // 15
                nums.forEach { println(it) }
            """.trimIndent(),
            demoLogic = {
                val list    = listOf("A", "B", "C")
                val mutable = mutableListOf(1, 2, 3)
                mutable.add(4)
                val set        = setOf("cat", "dog", "cat")
                val map        = mapOf("one" to 1, "two" to 2)
                val mutableMap = mutableMapOf("a" to 10)
                mutableMap["b"] = 20
                val nums    = listOf(1, 2, 3, 4, 5)
                val evens   = nums.filter { it % 2 == 0 }
                val doubled = nums.map { it * 2 }
                val total   = nums.sum()
                buildString {
                    appendLine("listOf(\"A\",\"B\",\"C\")     → $list")
                    appendLine("mutableListOf + add(4) → $mutable")
                    appendLine("setOf (dedup)          → $set")
                    appendLine("mapOf                  → $map")
                    appendLine("mutableMapOf + [b]=20  → $mutableMap")
                    appendLine()
                    appendLine("nums = $nums")
                    appendLine("filter { even }        → $evens")
                    appendLine("map { * 2 }            → $doubled")
                    appendLine("sum()                  → $total")
                }
            },
            icon = Icons.AutoMirrored.Rounded.List
        ),

        // 10. Null Safety
        KotlinConceptDetail(
            id = "null_safety",
            titleRes = R.string.concept_null_safety_title,
            descriptionRes = R.string.concept_null_safety_description,
            cardDescriptionRes = R.string.concept_null_safety_card_desc,
            codeSnippet = """
                var text: String = "Hello"
                // text = null           // Compile error!
                
                var nullable: String? = null
                
                // Safe call — skips the call if nullable is null,
                // prints "null" instead of throwing NPE
                println(nullable?.length)
                
                // Elvis — default value when null
                val len = nullable?.length ?: 0
                
                // Not-null assertion — throws NPE if null
                // val forced = nullable!!.length
                
                // Safe cast — returns null if cast fails
                val obj: Any = "Kotlin"
                val str = obj as? String  // "Kotlin"
                val num = obj as? Int     // null
                
                // Conversion helpers
                val parsed: Int? = "123".toIntOrNull() // 123
                val bad: Int?    = "abc".toIntOrNull() // null
            """.trimIndent(),
            demoLogic = {
                val nullable: String? = null
                val len    = nullable?.length ?: 0
                val safe   = nullable ?: "Default"
                val obj: Any = "Kotlin"
                val str    = obj as? String
                val num    = obj as? Int
                val parsed = "123".toIntOrNull()
                val bad    = "abc".toIntOrNull()
                buildString {
                    appendLine("nullable = null")
                    appendLine("nullable?.length ?: 0  → $len")
                    appendLine("nullable ?: \"Default\" → \"$safe\"")
                    appendLine()
                    appendLine("obj = \"Kotlin\" (Any)")
                    appendLine("obj as? String         → $str")
                    appendLine("obj as? Int            → $num")
                    appendLine()
                    appendLine("\"123\".toIntOrNull()  → $parsed")
                    appendLine("\"abc\".toIntOrNull()  → $bad")
                }
            },
            icon = Icons.Rounded.Security
        ),

        // 11. Classes
        KotlinConceptDetail(
            id = "classes",
            titleRes = R.string.concept_classes_title,
            descriptionRes = R.string.concept_classes_description,
            cardDescriptionRes = R.string.concept_classes_card_desc,
            codeSnippet = """
                // Simple class with primary constructor
                class Person(val name: String, var age: Int)
                val p = Person("Jan", 30)
                
                // Class with method
                class Calculator {
                    fun add(a: Int, b: Int): Int = a + b
                }
                
                // Primary + secondary constructor + init block
                class User(val name: String) {
                    var age: Int = 0
                
                    init {
                        println("Created user: ${'$'}name")
                    }
                
                    constructor(name: String, age: Int) : this(name) {
                        this.age = age
                    }
                }
                
                val u1 = User("Alice")        // init fires
                val u2 = User("Bob", 25)      // init fires too
            """.trimIndent(),
            demoLogic = {
                class Person(val name: String, var age: Int)
                val p = Person("Jan", 30)

                class Calculator {
                    fun add(a: Int, b: Int) = a + b
                }
                val calc = Calculator()

                val initLog = StringBuilder()
                class User(val name: String) {
                    var age: Int = 0
                    init { initLog.append("init: Created $name") }
                    constructor(name: String, age: Int) : this(name) { this.age = age }
                }
                val u1 = User("Alice")
                val u2 = User("Bob", 25)

                buildString {
                    appendLine("Person(\"Jan\", 30)")
                    appendLine("  name = ${p.name}, age = ${p.age}")
                    appendLine()
                    appendLine("Calculator().add(2, 3) → ${calc.add(2, 3)}")
                    appendLine()
                    appendLine(initLog.toString())
                    appendLine("User(\"Alice\")     → name=${u1.name}, age=${u1.age}")
                    appendLine("User(\"Bob\", 25)   → name=${u2.name}, age=${u2.age}")
                }
            },
            icon = Icons.Rounded.Class
        ),

        // 12. Inheritance
        KotlinConceptDetail(
            id = "inheritance",
            titleRes = R.string.concept_inheritance_title,
            descriptionRes = R.string.concept_inheritance_description,
            cardDescriptionRes = R.string.concept_inheritance_card_desc,
            codeSnippet = """
                // open class allows subclassing
                open class Animal(val name: String) {
                    open fun makeSound() = println("${'$'}name makes a sound")
                }
                
                class Dog(name: String) : Animal(name) {
                    override fun makeSound() = println("${'$'}name says: Woof!")
                }
                
                Dog("Rex").makeSound() // Rex says: Woof!
                
                // Interface with default implementation
                interface Clickable {
                    fun click()
                    fun describe() = println("I am clickable")
                }
                
                class Button : Clickable {
                    override fun click() = println("Button clicked")
                }
                
                // Multiple interfaces
                interface Flyable { fun fly() }
                
                class SuperBird : Clickable, Flyable {
                    override fun click() = println("Bird clicked")
                    override fun fly()   = println("Bird is flying")
                }
            """.trimIndent(),
            demoLogic = {
                open class Animal(val name: String) {
                    open fun makeSound() = "$name makes a sound"
                }
                class Dog(name: String) : Animal(name) {
                    override fun makeSound() = "$name says: Woof!"
                }
                class Cat(name: String) : Animal(name) {
                    override fun makeSound() = "$name says: Meow!"
                }
                class Button : DemoClickable {
                    override fun click() = "Button clicked"
                }

                val animal = Animal("Creature")
                val dog    = Dog("Rex")
                val cat    = Cat("Whiskers")
                val btn    = Button()
                buildString {
                    appendLine("Animal.makeSound() → ${animal.makeSound()}")
                    appendLine("Dog.makeSound()    → ${dog.makeSound()}")
                    appendLine("Cat.makeSound()    → ${cat.makeSound()}")
                    appendLine()
                    appendLine("Button.click()     → ${btn.click()}")
                    appendLine("Button.describe()  → ${btn.describe()}")
                }
            },
            icon = Icons.Rounded.AccountTree
        ),

        // 13. Special Classes
        KotlinConceptDetail(
            id = "special_classes",
            titleRes = R.string.concept_special_classes_title,
            descriptionRes = R.string.concept_special_classes_description,
            cardDescriptionRes = R.string.concept_special_classes_card_desc,
            codeSnippet = """
                // data class — auto equals, toString, copy
                data class Product(val name: String, val price: Double)
                val coffee = Product("Coffee", 12.99)
                val cheaper = coffee.copy(price = 9.99)
                println(coffee) // Product(name=Coffee, price=12.99)
                
                // enum class — fixed set of named values
                enum class Status { LOADING, SUCCESS, ERROR }
                val s = Status.SUCCESS
                
                // enum with properties
                enum class OrderState(val label: String) {
                    NEW("New"), DONE("Done"), CANCELLED("Cancelled")
                }
                
                // object — singleton
                object Logger {
                    fun log(msg: String) = println("LOG: ${'$'}msg")
                }
                Logger.log("App started")
                
                // companion object — static-like members
                class User(val name: String) {
                    companion object {
                        fun anonymous() = User("Guest")
                    }
                }
                val guest = User.anonymous()
            """.trimIndent(),
            demoLogic = {
                val coffee  = DemoProduct("Coffee", 12.99)
                val cheaper = coffee.copy(price = 9.99)
                val status  = DemoStatus.SUCCESS

                class DemoUser(val name: String)
                fun anonymousUser() = DemoUser("Guest")
                val guest = anonymousUser()

                buildString {
                    appendLine("=== data class ===")
                    appendLine("coffee  = $coffee")
                    appendLine("cheaper = $cheaper")
                    appendLine("coffee == cheaper → ${coffee == cheaper}")
                    appendLine()
                    appendLine("=== enum class ===")
                    appendLine("DemoStatus.SUCCESS → $status")
                    appendLine("All values → ${DemoStatus.entries.toList()}")
                    appendLine()
                    appendLine("=== object (singleton) ===")
                    appendLine(DemoLogger.log("App started"))
                    appendLine()
                    appendLine("=== companion object ===")
                    appendLine("anonymousUser().name → ${guest.name}")
                }
            },
            icon = Icons.Rounded.Stars
        ),

        // 14. Extension Functions & Properties
        KotlinConceptDetail(
            id = "extensions",
            titleRes = R.string.concept_extensions_title,
            descriptionRes = R.string.concept_extensions_description,
            cardDescriptionRes = R.string.concept_extensions_card_desc,
            codeSnippet = """
                // Extension function
                fun String.shout(): String = this.uppercase() + "!!!"
                println("hello".shout()) // HELLO!!!
                
                // Extension on a custom type
                fun Int.isEven(): Boolean = this % 2 == 0
                println(4.isEven()) // true
                
                // Extension property (getter only — no backing field)
                val String.wordCount: Int
                    get() = this.trim().split(" ").size
                
                println("Hello World".wordCount) // 2
                println("One".wordCount)          // 1
            """.trimIndent(),
            demoLogic = {
                fun String.shout() = this.uppercase() + "!!!"
                fun Int.isEven()   = this % 2 == 0
                fun wordCount(s: String) = s.trim().split(" ").size
                buildString {
                    appendLine("=== Extension function ===")
                    appendLine("\"hello\".shout()           → ${"hello".shout()}")
                    appendLine("\"kotlin\".shout()          → ${"kotlin".shout()}")
                    appendLine()
                    appendLine("=== Extension on Int ===")
                    appendLine("4.isEven()                → ${4.isEven()}")
                    appendLine("7.isEven()                → ${7.isEven()}")
                    appendLine()
                    appendLine("=== Extension property ===")
                    appendLine("wordCount(\"Hello World\")   → ${wordCount("Hello World")}")
                    appendLine("wordCount(\"One two three\") → ${wordCount("One two three")}")
                }
            },
            icon = Icons.Rounded.AddLink
        ),

        // 15. Naming Conventions
        KotlinConceptDetail(
            id = "naming_conventions",
            titleRes = R.string.concept_naming_conventions_title,
            descriptionRes = R.string.concept_naming_conventions_description,
            cardDescriptionRes = R.string.concept_naming_conventions_card_desc,
            codeSnippet = """
                // Variables & properties — camelCase
                val userName: String = "Alice"
                var itemCount: Int = 0
                
                // Classes & objects — PascalCase (nouns)
                class UserProfile
                data class ProductItem(val name: String)
                object NetworkManager
                
                // Functions — camelCase verbs
                fun sendEmail(address: String) { }
                fun fetchData(): List<String> = listOf()
                
                // Boolean functions — is/has/can prefix
                fun isActive(): Boolean = true
                fun hasPermission(): Boolean = false
                fun canEdit(): Boolean = true
                
                // Constants — UPPER_SNAKE_CASE
                const val MAX_RETRY_COUNT = 3
            """.trimIndent(),
            demoLogic = {
                val userName = "Alice"
                var itemCount = 0
                itemCount = 5
                fun sendEmail(address: String) = "Sending to: $address"
                fun fetchData(): List<String> = listOf("Item1", "Item2")
                fun isActive() = true
                fun hasPermission() = false
                fun canEdit() = true
                val MAX_RETRY_COUNT = 3
                buildString {
                    appendLine("=== Variables (camelCase) ===")
                    appendLine("userName   = \"$userName\"")
                    appendLine("itemCount  = $itemCount")
                    appendLine()
                    appendLine("=== Functions (camelCase verbs) ===")
                    appendLine(sendEmail("user@example.com"))
                    appendLine("fetchData() → ${fetchData()}")
                    appendLine()
                    appendLine("=== Boolean functions (is/has/can) ===")
                    appendLine("isActive()      → ${isActive()}")
                    appendLine("hasPermission() → ${hasPermission()}")
                    appendLine("canEdit()       → ${canEdit()}")
                    appendLine()
                    appendLine("=== Constant (UPPER_SNAKE_CASE) ===")
                    appendLine("MAX_RETRY_COUNT = $MAX_RETRY_COUNT")
                    appendLine()
                    appendLine("=== Classes (PascalCase) ===")
                    appendLine("class UserProfile")
                    appendLine("data class ProductItem")
                    appendLine("object NetworkManager")
                }
            },
            icon = Icons.Rounded.Spellcheck
        )
    )
}
