# 사다리 게임
## 진행 방법
* 사다리 게임 게임 요구사항을 파악한다.
* 요구사항에 대한 구현을 완료한 후 자신의 github 아이디에 해당하는 브랜치에 Pull Request(이하 PR)를 통해 코드 리뷰 요청을 한다.
* 코드 리뷰 피드백에 대한 개선 작업을 하고 다시 PUSH한다.
* 모든 피드백을 완료하면 다음 단계를 도전하고 앞의 과정을 반복한다.

## 온라인 코드 리뷰 과정
* [텍스트와 이미지로 살펴보는 온라인 코드 리뷰 과정](https://github.com/nextstep-step/nextstep-docs/tree/master/codereview)

## 사다리 게임 요구사항 분석  

> **기능 요구사항**  
> 사다리 게임에 참여하는 사람에 이름을 최대5글자까지 부여할 수 있다. 사다리를 출력할 때 사람 이름도 같이 출력한다.  
> 사람 이름은 쉼표(,)를 기준으로 구분한다.  
> 사람 이름을 5자 기준으로 출력하기 때문에 사다리 폭도 넓어져야 한다.  
> 사다리 타기가 정상적으로 동작하려면 라인이 겹치지 않도록 해야 한다.  
> |-----|-----| 모양과 같이 가로 라인이 겹치는 경우 어느 방향으로 이동할지 결정할 수 없다.  

1. 이름 목록을 입력받는다.  
    - InputText를 ","으로 split한다.  
    - 이름 길이가 5가 넘는 경우 Exception 처리 한다. 
    - 사람 수가 1 이하이면 Exception 처리 한다.  
2. 실행 결과를 입력한다.  
    - InputText를 ","으로 split한다.  
    - 이름의 개수와 같은 수만큼 입력받아야 한다. 다르면 Exceptionㅊ  처리 한다.  
    - 결과도 마찬가지로 길이가 5보다 작아야 한다.  
3. 사다리 높이를 입력받는다. 
    - 높이가 1보다 작은 경우 Exception 처리 한다.  
    - 숫자 외의 정보가 입력되었을 경우 Exception 처리한다.  
4. (사람 수 - 1) * 사다리 높이의 2차원 배열이 생성된다.  
5. 다음과 같은 조건으로 배열에 Line을 넣거나 뺀다.
    - (x, y)에 Line이 true일 경우, (x+1, y)에 Line이 있으면 안된다.  
        * (x-1, y)에 Line이 true일 경우 무조건 false
        * (x-1, y)에 Line이 false일 경우 true 또는 false  
6. 2차원 배열 구성에 따라 사다리 모양을 출력한다.  
    - 이름은 어떻게 출력할까?  
        - Line 구성 개수를 상수로 정한다. (5개) -> 한 배열당 6개의 문자(| 포함)를 자치함    
        - 이름 앞에 오는 빈칸은 6 - 글자수??  
    - 배열 하나의 값당 "-----" 또는 "     "을 출력하고 |으로 끝맺음  
    - 한줄 씩 만들고 출력
    - 실행 결과도 이름과 같은 조건으로 출력한다.  
7. 사다리 수행 결과를 저장한다.  
    - 결과를 보고싶은 사람을 지정할 수 있도록 Map 형태로 저장한다.  
    - Ladder가 가지고 있는 Line을 차례대로 순환하며 진행한다.  
    - 현재 Participant의 index와 같은 index인 Point와 index-1 Point의 hasLine 여부를 보면 된다.  
        - 양쪽을 검사하여 hasLine인 경우 index를 -1 또느 +1한다.  
        - 양쪽 모두 hasLine이 false인 경우 다음 Line으로 넘어간다.  
        - 양쪽 모두 hasLine이 true일리는 Ladder 생성시 검사하므로 안전하다고 판단한다.  
8. 원하는 결과를 출력한다.  
    - Key : Value
     
## 테스트 코드 목록  

1. 이름 목록을 입력받아 이름 길이가 5가 넘는 경우 Exception 처리를 하는지 여부  
2. 사다리 높이 값 검증 (1보다 큰지, 숫자가 입력되었는지)  
3. (사람 수 - 1) * 사다리 높이의 2차원 배열이 생성되었는지. (생성된 배열의 length를 확인한다.)  
4. Line이 들어가는 조건 검증 (x-1, y)에 true인데 (x,y)에 true가 들어가지는 않는지  
5. 입력받은 실행 결과의 개수가 입력한 이름의 개수와 같지 않으면 Exception 처리를 하는 지 여부  
6. 입력받은 실행 결과의 길이도 5가 넘는 경우 Exception 처리를 하는지 여부  
7. 게임 수행 결과 원하는 답이 나오는지 여부    

## 객체 및 책임 설계  

1. Point:  index값, boolean(선 여부) 값을 가진다.  
    - index-1 Point의 선 여부에 따라 선 여부를 검증한다.  
    - 선 여부를 판단해 선을 가지는 로직을 수행한다.  
2. Line: ```List<Point>```. x축  
    - 사람 수에 따라 Point 인스턴스를 생성한다.  
3. Ladder(Lines): ```List<Line>```. 전체 사다리  
    - 높이를 입력받아 해당 높이 개수의 Line을 생성한다.  
4. Participant: 게임 참가자. name을 받는다. 
    - name 글자가 5글자가 넘는지 여부를 검증한다.  
4. InputView: 정보를 입력받는다. (이름 목록, 높이)
5. OutputView: 사다리 정보를 출력한다.  
6. LadderGameController: Controller 역할. 
    - 사람 수와 높이를 입력받아 Ladder 객체를 생성한다.  
7. LadderGame: Game의 구성 정보 객체를 감싸 관리한다.  
    - Game의 Participant 목록과 Ladder를 가지고 있다. (step2)  
    - Game의 결과(GameResult)도 가지고 있다.(추가)  
8. GameResult: Game의 결과를 관리한다. Map이 아니라, 각 GameResult 객체는 하나의 결과만을 갖는다. (participant, result)  
9. LadderResult: List<String>으로 받은 실행결과를 감싸는 일급콜렉션  

## 고민점
  
- 사다리를 그리는 옵션 (선은 "-" 5개)은 누가 가질까? (전략?)  
- 이름을 출력하는 옵션 (6 - 글자수)와 같은 로직은 누가 가질까? (전략?)  
- 게임 결과를 찾아나가는 기능은 누가 가지고 있어야 하지? - Ladder    
- Ladder에서는 결과를 찾아나가는 기능을 언제 수행할까? - 호출하면? 생성 즉시?  
- 사다리의 정답을 찾아나가는 과정에서 State를 갖도록 하면 어떨까? 어떻게 할 수 있을까?  
    - Point가 State를 갖도록 한다. 여기서 hasLine을 관리한다.  
    - PreviousPoint도 여기서 관리할 수 있지 않을까? getPreviousPoint를 Line에서 가지고 있는 찝찝함도 없앨 수 있을 것 같다... 일단 해본다.    
