## 동시성 제어

#### Atomic Class 사용
```
CAS (Compare And Swap) 알고리즘을 이용하여 thread safe 보장
저장된 값과 현재 변경하려는 값을 비교하여 다르다면 값이 변경되지 못하게함
```

#### Synchronized 키워드
```
하나의 스레드에서만 실행될 수 있도록 block 함
메소드에 걸 경우 Synchronized 가 걸린 다른 메소드도 똑같은 진입 조건을 가지게됨
statements 로 메소드 내에서 걸 경우 그 코드 블락 안에서만 동작함
```

#### Distributed Lock (분산 락)
```
분산 서버 환경에서 자원에 대해 동시성을 보장하는 방법
위의 Synchronized 같은 경우 한 서버 내에서 동시성이 보장되지만
여러 서버에 동시에 같은 요청이 온다면 동시성 제어가 불가능함 이는 분산락이 아님
대표적으로 redisson pub/sub 을 이용하여 많이 구현
여러 블로그의 낙관적 락, 비관적 락에 대한 글을 보면 분산락이라는 말이 없는데 이 또한 분산락이라 생각됨
```

#### Optimistic Lock (낙관적 락)
```
DB 가 아닌 애플리케이션단에서 거는 락으로 트랜잭션 충돌이 없다고 가정함, 트랜잭션이 필요없음
트랜잭션 충돌시에 ObjectOptimisticLockingFailureException 예외가 발생함
예외 발생시 롤백을 직접 구현해주어야함
version 을 이용하여 조회 시점의 version 과 커밋 직전의 version 이 다를 경우 수정 불가함
트랜잭션 충돌이 많다면 성능이 오히려 안좋을 수 있다
```

#### Pessimistic Lock (비관적 락)
```
트랜잭션 충돌이 있다고 가정함, 트랜잭션 필요
DB 단에서 PESSIMISTIC_WRITE - exclusive lock, PESSIMISTIC_READ - shared lock 을 건다
디비에 락을 거는 것이기 때문에 성능상 좋지 않지만, 대량의 요청이 한번에 올 경우 낙관적 락에 비해 성능상 이점이 있을 수 있다.
(트랜잭션 충돌 여부를 인지하는 시점 때문에)
데드락에 대해 염두해야함
```