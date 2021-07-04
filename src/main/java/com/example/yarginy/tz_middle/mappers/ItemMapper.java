package com.example.yarginy.tz_middle.mappers;

import com.example.yarginy.tz_middle.models.Item;
import com.example.yarginy.tz_middle.models.Topic;
import org.apache.ibatis.annotations.ConstructorArgs;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Collection;

@Mapper
public interface ItemMapper {
    @Select("select * from topic_items where topic_id=#{topicId} order by \"order\"")
    Collection<Item> selectByTopicId(Integer topicId);

    @Select("select i.*, t.id, t.name from topic_items i join topic t on i.topic_id = t.id order by t.id, i.\"order\"")
    @Results({
                @Result(property = "id", column = "id", id = true),
                @Result(property = "name", column = "name"),
                @Result(property = "order", column = "order"),
                @Result(property = "topic", column = "topic_id",
                        one = @One(select = "com.example.yarginy.tz_middle.mappers.TopicMapper.selectTopicViewById"))
            })
    Collection<Item> selectAll();

    @Insert("insert into topic_items(name, \"order\", topic_id) values (#{name}, #{order}, #{topic.id})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    boolean insert(Item item);

    @Insert("<script> insert into topic_items(name, \"order\", topic_id) values" +
            "<foreach collection='items' item='item' separator=','>" +
            "(#{item.name}, #{item.order}, #{item.topic.id})</foreach></script>")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    boolean insertAll(Collection<Item> items);

    @Update("update topic_items set name=#{name}, \"order\"=#{order}, topic_id=#{topic.id}")
    boolean update(Item item);

    @Update("<script> <foreach collection='items' item='item' separator=';'>" +
            "update topic_items set name=#{item.name}, \"order\"=#{item.order} where id=#{item.id}" +
            "</foreach> </script>")
    boolean updateAll(Collection<Item> items);

    @Delete("delete from topic_items where id = #{id}")
    boolean delete(Item item);

    @Delete("<script> delete from topic_items where id in ( " +
            "<foreach collection='items' item='item' separator=','>" +
            "#{item.id} </foreach> ) </script>")
    boolean deleteAll(Collection<Item> items);
}