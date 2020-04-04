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
2. 사다리 높이를 입력받는다. 
    - 높이가 1보다 작은 경우 Exception 처리 한다.  
    - 숫자 외의 정보가 입력되었을 경우 Exception 처리한다.  
3. (사람 수 - 1) * 사다리 높이의 2차원 배열이 생성된다.  
4. 다음과 같은 조건으로 배열에 Line을 넣거나 뺀다.
    - (x, y)에 Line이 true일 경우, (x+1, y)에 Line이 있으면 안된다.  
        * (x-1, y)에 Line이 true일 경우 무조건 false
        * (x-1, y)에 Line이 false일 경우 true 또는 false  
5. 2차원 배열 구성에 따라 사다리 모양을 출력한다.  
    - 이름은 어떻게 출력할까?  
        - Line 구성 개수를 상수로 정한다. (5개) -> 한 배열당 6개의 문자(| 포함)를 자치함    
        - 이름 앞에 오는 빈칸은 6 - 글자수??  
    - 배열 하나의 값당 "-----" 또는 "     "을 출력하고 |으로 끝맺음  
    - 한줄 씩 만들고 출력
     
__
## 테스트 코드 목록  

1. 이름 목록을 입력받아 이름 길이가 5가 넘는 경우 Exception 처리를 하는지 여부  
2. 사다리 높이 값 검증 (1보다 큰지, 숫자가 입력되었는지)  
3. (사람 수 - 1) * 사다리 높이의 2차원 배열이 생성되었는지. (생성된 배열의 length를 확인한다.)  
4. Line이 들어가는 조건 검증 (x-1, y)에 true인데 (x,y)에 true가 들어가지는 않는지  

## 객체 및 책임 설계  

1. Point:  index값, boolean(선 여부) 값을 가진다.  
    - index-1 Point의 선 여부에 따라 선 여부를 검증한다.  
    - 선 여부를 판단해 선을 가지는 로직을 수행한다.  
2. Line: ```List<Point>```. x축  
    - 사람 수에 따라 Point 인스턴스를 생성한다.  
3. Ladder(Lines) : ```List<Line>```. 전체 사다리  
    - 높이를 입력받아 해당 높이 개수의 Line을 생성한다.  
4. Person : 게임 참가자. name을 받는다. 
    - name 글자가 5글자가 넘는지 여부를 검증한다.  
4. InputView: 정보를 입력받는다. (이름 목록, 높이)
5. OutputView: 사다리 정보를 출력한다.  
6. LadderGame : Controller 역할. 
    - 사람 수와 높이를 입력받아 Ladder 객체를 생성한다.  

## 고민점  
- 사다리를 그리는 옵션 (선은 "-" 5개)은 누가 가질까? (전략?)  
- 이름을 출력하는 옵션 (6 - 글자수)와 같은 로직은 누가 가질까? (전략?)  
