package com.home.work.repository;

import com.home.work.domain.Member;
import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends CrudRepository<Member, String> {
}
