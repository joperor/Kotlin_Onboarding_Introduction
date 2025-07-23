package jetbrains.kotlin.course.almost.done

fun photoshop() {
    val picture = getPicture()
    val filter = chooseFilter()
    println("The old image:")
    println(trimPicture(picture))
    println("The transformed picture:")
    println(applyFilter(picture, filter))
}

//Seleccion para imagenes predefinidas o subidas por el usario
fun getPicture(): String {
    println("Do you want to use a predefined picture or a custom one? Please input 'yes' for a predefined image or 'no' for a custom one")
    do {
        when (safeReadLine().lowercase()) {
            "yes" -> {
                return choosePicture()
            }
            "no" -> {
                println("Please input a custom picture")
                return safeReadLine()
            }
            else -> println("Please input 'yes' or 'no'")
        }
    } while (true)
}

//Comprueba la entrada de texto, permitiendo solamente los admitidos por el programa
fun choosePicture(): String {
    do {
        println("Please choose a picture. The possible options are: ${allPictures()}")
        getPictureByName(safeReadLine())?.let { return it }
    } while (true)
}

//Comprueba la entrada de texto, permitiendo solamente los admitidos por el programa
fun chooseFilter(): String {
    println("Please choose the filter: 'borders' or 'squared'")
    do {
         when (val input = safeReadLine().lowercase()) {
            "borders", "squared" -> return input
            else -> println("Please input 'borders' or 'squared'")
        }
    } while (true)
}

fun safeReadLine(): String = readlnOrNull() ?: error("Your input is incorrect, sorry")

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
    photoshop()
}
