package com.neo.mapper;

import java.util.List;

import com.neo.model.User;
import org.apache.ibatis.annotations.*;

import com.neo.enums.UserSexEnum;

@Mapper
public interface UserMapper {

	///能用 #{} 的地方就用 #{}，
	//表名作参数时，必须用 ${}。如：select * from ${tableName}
	@Select("SELECT * FROM users")//查询类的注解，所有的查询均使用这个
	@Results({//修饰返回的结果集，关联实体类属性和数据库字段一一对应，如果实体类属性和数据库属性名保持一致，就不需要这个属性来修饰。
		@Result(property = "userSex",  column = "user_sex", javaType = UserSexEnum.class),
		@Result(property = "nickName", column = "nick_name")
	})
	List<User> getAll();
	
	@Select("SELECT * FROM users WHERE id = #{id}")
	@Results({
		@Result(property = "userSex",  column = "user_sex", javaType = UserSexEnum.class),
		@Result(property = "nickName", column = "nick_name")
	})
	User getOne(Long id);

	@Insert("INSERT INTO users(userName,passWord,user_sex) VALUES(#{userName}, #{passWord}, #{userSex})")//插入数据库使用，直接传入实体类会自动解析属性到对应的值
	void insert(User user);


	@Update("UPDATE users SET userName=#{userName},nick_name=#{nickName} WHERE id =#{id}")//负责修改，也可以直接传入对象
	void update(User user);

	@Delete("DELETE FROM users WHERE id =#{id}")//负责删除
	void delete(Long id);

}