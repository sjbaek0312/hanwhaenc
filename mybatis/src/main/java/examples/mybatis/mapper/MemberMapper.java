package examples.mybatis.mapper;

import examples.mybatis.model.Member;
import examples.mybatis.util.Mapper;

@Mapper
public interface MemberMapper {

    Member findById(long id);
    void insert(Member member);

}