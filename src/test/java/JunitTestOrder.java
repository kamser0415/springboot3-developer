import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;

public class JunitTestOrder {

    @BeforeAll
    static void beforeAll() {
        System.out.println("@BeforeAll");
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("@BeforeEach");
    }

    @Test
    public void test1() {
        System.out.println("test1");
    }

    @Test
    public void test2() {
        System.out.println("test2");
    }

    @Test
    public void test3() {
        System.out.println("test3");
    }

    @AfterAll
    static void afterAll(){
        System.out.println("@AfterAll");
    }

    @AfterEach
    void afterEach(){
        System.out.println("@AfterEach");
    }
}
