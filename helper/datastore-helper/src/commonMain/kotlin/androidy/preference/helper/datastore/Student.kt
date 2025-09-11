//package androidy.compose.datastore
//
//import kotlin.reflect.KProperty
//
//
//class Student private constructor(
//    val delegate: Person
//) {
//    companion object {
//        fun of(person: Person): Student {
//            println("person name:  " + person.name)
//            return Student(person)
//        }
//    }
//}
//
//operator fun Student.getValue(receiver: Any?, property: KProperty<*>): Person =
//    delegate
//
//
//class StudentProvider(
//    private val person: Person
//) {
//    companion object {
//        fun of(person: Person): StudentProvider {
//            return StudentProvider(person)
//        }
//    }
//
//    operator fun provideDelegate(thisRef: Any?, property: kotlin.reflect.KProperty<*>): Student {
//        println("property name:  "+property.name)
//        return Student.of(person)
//    }
//}
//
//class Person(val name: String)
//
//inline val Person.getting
//    get() = StudentProvider.of(this)
//
//
//fun main() {
//    val m = Person("tom")
//    m.run {
//        val desktop by getting
//    }
//}
