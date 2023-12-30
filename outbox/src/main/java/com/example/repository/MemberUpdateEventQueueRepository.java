package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberUpdateEventQueueRepository extends JpaRepository<MemberUpdateEventQueue, Long> {
}
