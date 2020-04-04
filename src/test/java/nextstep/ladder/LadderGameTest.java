package nextstep.ladder;

import nextstep.ladder.controller.LadderGame;
import nextstep.ladder.domain.Ladder;
import nextstep.ladder.domain.Participant;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LadderGameTest {
    @Test
    @DisplayName("입력받은 참가자 이름(width)과 사다리 높이(height) 만큼의 사다리가 생성되는지 확인한다.")
    void createLadder() {
        int height = 5;
        List<Participant> participants = new ArrayList<>();
        participants.add(new Participant("pobi"));
        participants.add(new Participant("honux"));
        participants.add(new Participant("crong"));
        participants.add(new Participant("jk"));

        Ladder ladder = LadderGame.createLadder(participants, height);
        assertThat(ladder.getWidth()).isEqualTo(3);
        assertThat(ladder.getHeight()).isEqualTo(5);
    }
}
