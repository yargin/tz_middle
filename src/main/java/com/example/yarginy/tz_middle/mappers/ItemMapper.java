package com.example.yarginy.tz_middle.mappers;

import com.example.yarginy.tz_middle.models.Item;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Collection;

@Mapper
public interface ItemMapper {
    @Select("select * from topic_items i join topic t on i.topic_id = t.id")
    Collection<Item> selectAll();

    @Insert("insert into topic_items(name, \"order\", topic_id) values (#{name}, #{order}, #{topic.id})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    boolean insert(Item item);

    @Update("update topic_item set name=#{name}, \"order\"=#{order}, topic_id=#{topic.id}")
    boolean update(Item item);

    @Delete("delete from topic_items where id = #{id}")
    boolean delete(Item item);
}