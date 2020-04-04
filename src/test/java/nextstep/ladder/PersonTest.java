package nextstep.ladder;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class PersonTest {
    @Test
    @DisplayName("이름을 입력했을 떄, 5자가 넘으면 Exception을 발생한다.")
    void personValidate() {
        String name = "yeoseon";

        assertThatIllegalArgumentException().isThrownBy(() -> {
            new Person(name);
        }).withMessage("이름은 5자 이하로 입력해주세요.");
    }
}
