package jetbrains.kotlin.course.almost.done

fun applyFilter(picture: String, filter: String): String {
    val trimmedPicture = trimPicture(picture)
    return when (filter) {
        "squared" -> applySquaredFilter(trimmedPicture)
        "borders" -> applyBordersFilter(trimmedPicture)
        else -> "error"
    }
}

fun applySquaredFilter(picture: String): String {
    val pictureWidth = getPictureWidth(picture)
    val stringBuilder = StringBuilder()
    val borderedHorizontalLine = borderSymbol.toString().repeat(pictureWidth * 2 + 8)

    stringBuilder.append(borderedHorizontalLine).append(newLineSymbol)

    picture.lines().forEach {
        val separationNumber = pictureWidth - it.length + 1
        stringBuilder
            .append(borderSymbol)
            .append(separator)
            .append(it)
            .append(separator.toString().repeat(separationNumber))
            .append(borderSymbol)
            .append(borderSymbol)
            .append(separator)
            .append(it)
            .append(separator.toString().repeat(separationNumber))
            .append(borderSymbol)
            .append(newLineSymbol)
    }
    stringBuilder.append(borderedHorizontalLine).append(newLineSymbol)
    picture.lines().forEach {
        val separationNumber = pictureWidth - it.length + 1
        stringBuilder
            .append(borderSymbol)
            .append(separator)
            .append(it)
            .append(separator.toString().repeat(separationNumber))
            .append(borderSymbol)
            .append(borderSymbol)
            .append(separator)
            .append(it)
            .append(separator.toString().repeat(separationNumber))
            .append(borderSymbol)

            .append(newLineSymbol)
    }

    stringBuilder.append(borderedHorizontalLine).append(newLineSymbol)

    return stringBuilder.toString()
}

fun applyBordersFilter(picture: String): String {
    val pictureWidth = getPictureWidth(picture)
    val stringBuilder = StringBuilder()
    val borderedHorizontalLine = borderSymbol.toString().repeat(pictureWidth + 4)

    stringBuilder.append(borderedHorizontalLine).append(newLineSymbol)
    picture.lines().forEach {
        val separationNumber = pictureWidth - it.length + 1
        stringBuilder
            .append(borderSymbol)
            .append(separator)
            .append(it)
            .append(separator.toString().repeat(separationNumber))
            .append(borderSymbol)
            .append(newLineSymbol)
    }
    stringBuilder.append(borderedHorizontalLine).append(newLineSymbol)
    return stringBuilder.toString()
}

fun trimPicture(picture: String): String = picture.trimIndent()

fun main() {
    println(applyFilter(simba, "squared"))   // an example with the simba picture
    println(applyFilter(monkey, "squared"))  // an example with the monkey picture
    println(applyFilter(android, "squared")) // an example with the android picture (this picture has different line lengths
}
