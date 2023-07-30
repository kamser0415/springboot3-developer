import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class JunitTest {

    @DisplayName("1+2는 3이다.")
    @Test
    void test(){
        int a = 1;
        int b = 2;
        int sum = 3;
        Assertions.assertThat(a + b).isEqualTo(sum);
    }

    @DisplayName("1+3는 3이다.")
    @Test
    void test2(){
        //given
        int a = 1;
        int b = 3;
        int sum = 3;

        //when
        Assertions.assertThat(a + b).isNotEqualTo(sum);

        //then
    }
}
