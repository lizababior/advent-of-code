import java.math.BigInteger
import java.security.MessageDigest
import kotlin.io.path.Path
import kotlin.io.path.readText

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = Path("src/$name.txt").readText().trim().lines()

/**
 * Converts string to md5 hash.
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')

/**
 * The cleaner shorthand for printing output.
 */
fun Any?.println() = println(this)

/**
 * Parses a list of strings into a list of integer lists.
 */
fun parseNumberLists(input: List<String>): List<List<Int>> =
    input.filter { it.isNotBlank() }
        .map { line ->
            line.trim()
                .split(Regex("\\s+"))
                .filter { it.all { char -> char.isDigit() } }
                .map { it.toInt() }
        }