package com.example.yarginy.tz_middle.mappers;

import com.example.yarginy.tz_middle.handlers.TopicHandler;
import com.example.yarginy.tz_middle.models.Topic;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Collection;

@Mapper
public interface TopicMapper {
    @Select("select * from topic where id=#{id}")
    @Results({
                @Result(property = "id", column = "id", id = true),
                @Result(property = "name", column = "name"),
                @Result(property = "description", column = "description"),
                @Result(property = "items", column = "id",
                        many = @Many(select = "com.example.yarginy.tz_middle.mappers.ItemMapper.selectByTopic")),
            })
    @ResultType(Topic.class)
    void selectTopicById(Integer id, TopicHandler topicHandler);

    @Select("select * from topic where id=#{id}")
    @Results({
            @Result(property = "id", column = "id", id = true),
            @Result(property = "name", column = "name"),
            @Result(property = "description", column = "description")
    })
    Topic selectTopicViewById(Integer id);

    @Select("select * from topic")
    @Results({
            @Result(property = "id", column = "id", id = true),
            @Result(property = "name", column = "name"),
            @Result(property = "description", column = "description"),
            @Result(property = "items", column = "id",
                    many = @Many(select = "com.example.yarginy.tz_middle.mappers.ItemMapper.selectByTopic")),
    })
    @ResultType(Topic.class)
    Collection<Topic> selectAll(TopicHandler topicHandler);

    @Insert("insert into topic(name, description) values(#{name}, #{description})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    boolean insert(Topic topic);

    @Update("update topic set name=#{name}, description=#{description} where id=#{id}")
    boolean update(Topic topic);

    @Delete("delete from topic where id=#{id}")
    boolean delete(Topic topic);
}
