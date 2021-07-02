package com.example.yarginy.tz_middle.mappers;

import com.example.yarginy.tz_middle.models.Topic;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Mapper
public interface TopicMapper {
    @Select("select * from topic")
    Collection<Topic> selectAll();

    @Insert("insert into topic(name, description) values(#{name}, #{description})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
//    @Select("insert into topic(name, description) values(#{name}, #{description}) returning id")
//    @SelectKey(before = false, keyProperty = "id", statement = "select currvala('topic_id_seq')", resultType = Integer.class)
//    @Transactional
    boolean insert(Topic topic);

    @Update("update topic set name=#{name}, description=#{description} where id=#{id}")
    boolean update(Topic topic);

    @Delete("delete from topic where id=#{id}")
    boolean delete(Topic topic);
}
