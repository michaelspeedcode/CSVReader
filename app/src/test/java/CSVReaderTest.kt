import com.speed.mvvm.CSVReader
import com.speed.mvvm.data.network.model.Person
import org.junit.Assert.assertEquals
import org.junit.Test
import java.io.ByteArrayInputStream


class CSVReaderTest {

    private val issuesTest1 = "\"First name\",\"Sur name\",\"Issue count\",\"Date of birth\"\n" +
            "\"\",\"\",0,\"\"\n" + //item 1
            "\"\",\"\",1,\"\"\n" + //item 2
            "\"\",\"\",2,\"\"\n" //item 3

    private val issuesTestMalformed= "\"First name\",\"Sur name\",\"Issue count\",\"Date of birth\"\n" +
            "\",\"\"\n" +  //item 1
            "\"\"\",1,\"\"\n" + //item 2
            "\"\"\"\n"  //item 3

    private fun getPeopleList(maxIndex: Int): List<Person> {
        val people = arrayListOf<Person>()
        for (i in 0..maxIndex) {
            people.add(Person("", "", "$i", ""))
        }
        return people
    }

    @Throws(Exception::class)
    @Test
    fun testDateFormatter() {
        val inputStream = ByteArrayInputStream(issuesTest1.toByteArray())

        assertEquals(getPeopleList(2), CSVReader.createListFromFile(inputStream))
    }

    @Throws(Exception::class)
    @Test
    fun testDateFormatterMalformed() {
        val inputStream = ByteArrayInputStream(issuesTestMalformed.toByteArray())

        assertEquals(emptyList<Person>(), CSVReader.createListFromFile(inputStream))
    }

}