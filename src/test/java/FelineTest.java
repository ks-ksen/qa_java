import com.example.Feline;
import org.junit.Test;

import java.util.List;
import static org.junit.Assert.*;

public class FelineTest {

    private Feline feline = new Feline();

    @Test
    // проверяем, что возвращается список еды для хищника
    public void testEatMeat() throws Exception {
        // Вызываем метод eatMeat() у объекта feline
        List<String> food = feline.eatMeat();

        // Проверяем результат
        assertEquals("Животные", food.get(0));
        assertEquals("Птицы", food.get(1));
        assertEquals("Рыба", food.get(2));
        // Проверяем размер списка
        assertEquals(3, food.size());
    }

    @Test
    //проверяем, что возвращается "Кошачьи"
    public void testGetFamily() {

        String family = feline.getFamily();

        assertEquals("Кошачьи", family);
    }

    @Test
    // должен вернуть 1 котенка
    public void testGetKittensDefault() {

        int kittens = feline.getKittens();

        // Проверяем, что возвращается 1 (значение по умолчанию)
        assertEquals(1, kittens);
    }

    @Test
    //проверяем, что возвращает переданное значение
    public void testGetKittensWithCount() {
        //метод с параметром 5
        int kittens = feline.getKittens(5);

        // число, которое мы передали
        assertEquals(5, kittens);
    }
}