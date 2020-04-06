package nextstep.ladder.domain;

public class Participant {
    public static final String PARTICIPANT_NAME_LENGTH_ERROR = "이름은 5자 이하로 입력해주세요. [이름: %s]";
    public static final int MAX_PARTICIPANT_NAME_LENGTH = 5;
    private String name;

    public Participant(String name) {
        assertParticipantName(name);
        this.name = name;
    }

    private void assertParticipantName(String name) {
        if(name.length() > MAX_PARTICIPANT_NAME_LENGTH) {
            throw new IllegalArgumentException(String.format(PARTICIPANT_NAME_LENGTH_ERROR, name));
        }
    }

    public String getName() {
        return name;
    }
}
