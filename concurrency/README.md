## 동시성 제어

#### Atomic Class 사용
```
CAS (Compare And Swap) 알고리즘을 이용하여 thread safe 보장
저장된 값과 현재 변경하려는 값을 비교하여 다르다면 값이 변경되지 못하게함
```

#### Synchronized 키워드
```
하나의 스레드에서만 실행될 수 있도록 block 합니다.
메소드에 걸 경우 Synchronized 가 걸린 다른 메소드도 똑같은 진입 조건을 가지게됨
statements 로 메소드 내에서 걸 경우 그 코드 블락 안에서만 동작함
```