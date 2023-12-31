# 이벤트 기반 아키텍처에서 이벤트 소실없는 순서 보장 아키텍처 구축

## [내가 쓴 블로그 글](https://yanglet.tistory.com/42)

## 실행 방법
```
# 깃 클론
# cd outbox
# ./build.sh
# client.http 파일 순서대로 API 호출
# localhost:8080 (Kafka-ui 접속)
```

## 구현
```
- Transactional Outbox Pattern 을 이용해 트랜잭션을 묶어 이벤트 발행
여기서 이벤트 발행을 debezium 에게 맡김 (Message Relay)
- 사실 그닥 어울리는 예제는 아니지만 구현에 의미를 두고 만듦
```