package com.github.jianlu8023.example.integration.middleware.redis.template;


import org.springframework.data.redis.core.*;

// @Component
public class JsonRedisTemplate extends RedisTemplate<String, Object> {

    // public JsonRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
    //
    //     // 构造函数注入 RedisConnectionFactory，设置到父类
    //     // super.setConnectionFactory(redisConnectionFactory);
    //     //
    //     // // 使用 Jackson 提供的通用 Serializer
    //     // GenericJackson2JsonRedisSerializer serializer = new GenericJackson2JsonRedisSerializer();
    //     // serializer.configure(mapper -> {
    //     //     // 如果涉及到对 java.time 类型的序列化，反序列化那么需要注册 JavaTimeModule
    //     //     mapper.registerModule(new JavaTimeModule());
    //     // });
    //     //
    //     // // String 类型的 key/value 序列化
    //     // super.setKeySerializer(StringRedisSerializer.UTF_8);
    //     // super.setValueSerializer(serializer);
    //     //
    //     // // Hash 类型的 key/value 序列化
    //     // super.setHashKeySerializer(StringRedisSerializer.UTF_8);
    //     // super.setHashValueSerializer(serializer);
    //
    //
    //     JsonRedisTemplate<String, String> redisTemplate = new JsonRedisTemplate<String, String>();
    //     redisTemplate.setConnectionFactory(redisConnectionFactory);
    //
    //     StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
    //
    //     redisTemplate.setKeySerializer(stringRedisSerializer); // key的序列化类型
    //
    //     Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
    //     ObjectMapper objectMapper = new ObjectMapper();
    //     objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
    //     // 方法过期，改为下面代码
    //     //        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
    //     objectMapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance,
    //             ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
    //     jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
    //     jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
    //
    //     redisTemplate.setValueSerializer(jackson2JsonRedisSerializer); // value的序列化类型
    //     redisTemplate.setHashKeySerializer(stringRedisSerializer);
    //     redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
    //     redisTemplate.afterPropertiesSet();
    //     // return redisTemplate;
    // }
    //
    //
    //
    //
    // /**
    //  * 给一个指定的 key 值附加过期时间
    //  *
    //  * @param key
    //  * @param time
    //  *
    //  * @return
    //  */
    // public boolean expire(String key, long time) {
    //     return expire(key, time, TimeUnit.SECONDS);
    // }
    //
    // /**
    //  * 根据key 获取过期时间
    //  *
    //  * @param key
    //  *
    //  * @return
    //  */
    // public long getTime(String key) {
    //     return getExpire(key, TimeUnit.SECONDS);
    // }
    //
    // /**
    //  * 根据key 获取过期时间
    //  *
    //  * @param key
    //  *
    //  * @return
    //  */
    // public Boolean hasKey(String key) {
    //     return hasKey(key);
    // }
    //
    // /**
    //  * 移除指定key 的过期时间
    //  *
    //  * @param key
    //  *
    //  * @return
    //  */
    // public Boolean persist(String key) {
    //     return.boundValueOps(key).persist();
    // }
    //
    // //- - - - - - - - - - - - - - - - - - - - -  String类型 - - - - - - - - - - - - - - - - - - - -
    //
    // /**
    //  * 根据key获取值
    //  *
    //  * @param key 键
    //  *
    //  * @return 值
    //  */
    // public Object get(String key) {
    //     return key == null ? null : opsForValue().get(key);
    // }
    //
    // /**
    //  * 将值放入缓存
    //  *
    //  * @param key   键
    //  * @param value 值
    //  *
    //  * @return true成功 false 失败
    //  */
    // public void set(String key, String value) {
    //     opsForValue().set(key, value);
    // }
    //
    // /**
    //  * 将值放入缓存并设置时间
    //  *
    //  * @param key   键
    //  * @param value 值
    //  * @param time  时间(秒) -1为无期限
    //  *
    //  * @return true成功 false 失败
    //  */
    // public void set(String key, String value, long time) {
    //     if (time > 0) {
    //         opsForValue().set(key, value, time, TimeUnit.SECONDS);
    //     } else {
    //         opsForValue().set(key, value);
    //     }
    // }
    //
    // /**
    //  * 批量添加 key (重复的键会覆盖)
    //  *
    //  * @param keyAndValue
    //  */
    // public void batchSet(Map<String, String> keyAndValue) {
    //     opsForValue().multiSet(keyAndValue);
    // }
    //
    // /**
    //  * 批量添加 key-value 只有在键不存在时,才添加
    //  * map 中只要有一个key存在,则全部不添加
    //  *
    //  * @param keyAndValue
    //  */
    // public void batchSetIfAbsent(Map<String, String> keyAndValue) {
    //     opsForValue().multiSetIfAbsent(keyAndValue);
    // }
    //
    // /**
    //  * 对一个 key-value 的值进行加减操作,
    //  * 如果该 key 不存在 将创建一个key 并赋值该 number
    //  * 如果 key 存在,但 value 不是长整型 ,将报错
    //  *
    //  * @param key
    //  * @param number
    //  */
    // public Long increment(String key, long number) {
    //     return opsForValue().increment(key, number);
    // }
    //
    // /**
    //  * 对一个 key-value 的值进行加减操作,
    //  * 如果该 key 不存在 将创建一个key 并赋值该 number
    //  * 如果 key 存在,但 value 不是 纯数字 ,将报错
    //  *
    //  * @param key
    //  * @param number
    //  */
    // public Double increment(String key, double number) {
    //     return opsForValue().increment(key, number);
    // }
    //
    // //- - - - - - - - - - - - - - - - - - - - -  set类型 - - - - - - - - - - - - - - - - - - - -
    //
    // /**
    //  * 将数据放入set缓存
    //  *
    //  * @param key 键
    //  *
    //  * @return
    //  */
    // public void sSet(String key, String value) {
    //     opsForSet().add(key, value);
    // }
    //
    // /**
    //  * 获取变量中的值
    //  *
    //  * @param key 键
    //  *
    //  * @return
    //  */
    // public Set<Object> members(String key) {
    //     return opsForSet().members(key);
    // }
    //
    // /**
    //  * 随机获取变量中指定个数的元素
    //  *
    //  * @param key   键
    //  * @param count 值
    //  *
    //  * @return
    //  */
    // public void randomMembers(String key, long count) {
    //     opsForSet().randomMembers(key, count);
    // }
    //
    // /**
    //  * 随机获取变量中的元素
    //  *
    //  * @param key 键
    //  *
    //  * @return
    //  */
    // public Object randomMember(String key) {
    //     return opsForSet().randomMember(key);
    // }
    //
    // /**
    //  * 弹出变量中的元素
    //  *
    //  * @param key 键
    //  *
    //  * @return
    //  */
    // public Object pop(String key) {
    //     return opsForSet().pop("setValue");
    // }
    //
    // /**
    //  * 获取变量中值的长度
    //  *
    //  * @param key 键
    //  *
    //  * @return
    //  */
    // public long size(String key) {
    //     return opsForSet().size(key);
    // }
    //
    // /**
    //  * 根据value从一个set中查询,是否存在
    //  *
    //  * @param key   键
    //  * @param value 值
    //  *
    //  * @return true 存在 false不存在
    //  */
    // public boolean sHasKey(String key, Object value) {
    //     return opsForSet().isMember(key, value);
    // }
    //
    // /**
    //  * 检查给定的元素是否在变量中。
    //  *
    //  * @param key 键
    //  * @param obj 元素对象
    //  *
    //  * @return
    //  */
    // public boolean isMember(String key, Object obj) {
    //     return opsForSet().isMember(key, obj);
    // }
    //
    // /**
    //  * 转移变量的元素值到目的变量。
    //  *
    //  * @param key     键
    //  * @param value   元素对象
    //  * @param destKey 元素对象
    //  *
    //  * @return
    //  */
    // public boolean move(String key, String value, String destKey) {
    //     return opsForSet().move(key, value, destKey);
    // }
    //
    // /**
    //  * 批量移除set缓存中元素
    //  *
    //  * @param key    键
    //  * @param values 值
    //  *
    //  * @return
    //  */
    // public void remove(String key, Object... values) {
    //     opsForSet().remove(key, values);
    // }
    //
    // /**
    //  * 通过给定的key求2个set变量的差值
    //  *
    //  * @param key     键
    //  * @param destKey 键
    //  *
    //  * @return
    //  */
    // public Set<Set> difference(String key, String destKey) {
    //     return opsForSet().difference(key, destKey);
    // }
    //
    //
    // //- - - - - - - - - - - - - - - - - - - - -  hash类型 - - - - - - - - - - - - - - - - - - - -
    //
    // /**
    //  * 加入缓存
    //  *
    //  * @param key 键
    //  * @param map 键
    //  *
    //  * @return
    //  */
    // public void add(String key, Map<String, String> map) {
    //     opsForHash().putAll(key, map);
    // }
    //
    // /**
    //  * 获取 key 下的 所有  hashkey 和 value
    //  *
    //  * @param key 键
    //  *
    //  * @return
    //  */
    // public Map<Object, Object> getHashEntries(String key) {
    //     return opsForHash().entries(key);
    // }
    //
    // /**
    //  * 验证指定 key 下 有没有指定的 hashkey
    //  *
    //  * @param key
    //  * @param hashKey
    //  *
    //  * @return
    //  */
    // public boolean hashKey(String key, String hashKey) {
    //     return opsForHash().hasKey(key, hashKey);
    // }
    //
    // /**
    //  * 获取指定key的值string
    //  *
    //  * @param key  键
    //  * @param key2 键
    //  *
    //  * @return
    //  */
    // public String getMapString(String key, String key2) {
    //     return opsForHash().get("map1", "key1").toString();
    // }
    //
    // /**
    //  * 获取指定的值Int
    //  *
    //  * @param key  键
    //  * @param key2 键
    //  *
    //  * @return
    //  */
    // public Integer getMapInt(String key, String key2) {
    //     return (Integer) opsForHash().get("map1", "key1");
    // }
    //
    // /**
    //  * 弹出元素并删除
    //  *
    //  * @param key 键
    //  *
    //  * @return
    //  */
    // public String popValue(String key) {
    //     return opsForSet().pop(key).toString();
    // }
    //
    // /**
    //  * 删除指定 hash 的 HashKey
    //  *
    //  * @param key
    //  * @param hashKeys
    //  *
    //  * @return 删除成功的 数量
    //  */
    // public Long delete(String key, String... hashKeys) {
    //     return redisTemplate.opsForHash().delete(key, hashKeys);
    // }
    //
    // /**
    //  * 给指定 hash 的 hashkey 做增减操作
    //  *
    //  * @param key
    //  * @param hashKey
    //  * @param number
    //  *
    //  * @return
    //  */
    // public Long increment(String key, String hashKey, long number) {
    //     return redisTemplate.opsForHash().increment(key, hashKey, number);
    // }
    //
    // /**
    //  * 给指定 hash 的 hashkey 做增减操作
    //  *
    //  * @param key
    //  * @param hashKey
    //  * @param number
    //  *
    //  * @return
    //  */
    // public Double increment(String key, String hashKey, Double number) {
    //     return redisTemplate.opsForHash().increment(key, hashKey, number);
    // }
    //
    // /**
    //  * 获取 key 下的 所有 hashkey 字段
    //  *
    //  * @param key
    //  *
    //  * @return
    //  */
    // public Set<Object> hashKeys(String key) {
    //     return redisTemplate.opsForHash().keys(key);
    // }
    //
    // /**
    //  * 获取指定 hash 下面的 键值对 数量
    //  *
    //  * @param key
    //  *
    //  * @return
    //  */
    // public Long hashSize(String key) {
    //     return redisTemplate.opsForHash().size(key);
    // }
    //
    // //- - - - - - - - - - - - - - - - - - - - -  list类型 - - - - - - - - - - - - - - - - - - - -
    //
    // /**
    //  * 在变量左边添加元素值
    //  *
    //  * @param key
    //  * @param value
    //  *
    //  * @return
    //  */
    // public void leftPush(String key, Object value) {
    //     redisTemplate.opsForList().leftPush(key, value);
    // }
    //
    // /**
    //  * 获取集合指定位置的值。
    //  *
    //  * @param key
    //  * @param index
    //  *
    //  * @return
    //  */
    // public Object index(String key, long index) {
    //     return redisTemplate.opsForList().index("list", 1);
    // }
    //
    // /**
    //  * 获取指定区间的值。
    //  *
    //  * @param key
    //  * @param start
    //  * @param end
    //  *
    //  * @return
    //  */
    // public List<Object> range(String key, long start, long end) {
    //     return redisTemplate.opsForList().range(key, start, end);
    // }
    //
    // /**
    //  * 把最后一个参数值放到指定集合的第一个出现中间参数的前面，
    //  * 如果中间参数值存在的话。
    //  *
    //  * @param key
    //  * @param pivot
    //  * @param value
    //  *
    //  * @return
    //  */
    // public void leftPush(String key, String pivot, String value) {
    //     redisTemplate.opsForList().leftPush(key, pivot, value);
    // }
    //
    // /**
    //  * 向左边批量添加参数元素。
    //  *
    //  * @param key
    //  * @param values
    //  *
    //  * @return
    //  */
    // public void leftPushAll(String key, String... values) {
    //     //        redisTemplate.opsForList().leftPushAll(key,"w","x","y");
    //     redisTemplate.opsForList().leftPushAll(key, values);
    // }
    //
    // /**
    //  * 向集合最右边添加元素。
    //  *
    //  * @param key
    //  * @param value
    //  *
    //  * @return
    //  */
    // public void leftPushAll(String key, String value) {
    //     redisTemplate.opsForList().rightPush(key, value);
    // }
    //
    // /**
    //  * 向左边批量添加参数元素。
    //  *
    //  * @param key
    //  * @param values
    //  *
    //  * @return
    //  */
    // public void rightPushAll(String key, String... values) {
    //     //redisTemplate.opsForList().leftPushAll(key,"w","x","y");
    //     redisTemplate.opsForList().rightPushAll(key, values);
    // }
    //
    // /**
    //  * 向已存在的集合中添加元素。
    //  *
    //  * @param key
    //  * @param value
    //  *
    //  * @return
    //  */
    // public void rightPushIfPresent(String key, Object value) {
    //     redisTemplate.opsForList().rightPushIfPresent(key, value);
    // }
    //
    // /**
    //  * 向已存在的集合中添加元素。
    //  *
    //  * @param key
    //  *
    //  * @return
    //  */
    // public long listLength(String key) {
    //     return redisTemplate.opsForList().size(key);
    // }
    //
    // /**
    //  * 移除集合中的左边第一个元素。
    //  *
    //  * @param key
    //  *
    //  * @return
    //  */
    // public void leftPop(String key) {
    //     redisTemplate.opsForList().leftPop(key);
    // }
    //
    // /**
    //  * 移除集合中左边的元素在等待的时间里，如果超过等待的时间仍没有元素则退出。
    //  *
    //  * @param key
    //  *
    //  * @return
    //  */
    // public void leftPop(String key, long timeout, TimeUnit unit) {
    //     redisTemplate.opsForList().leftPop(key, timeout, unit);
    // }
    //
    // /**
    //  * 移除集合中右边的元素。
    //  *
    //  * @param key
    //  *
    //  * @return
    //  */
    // public void rightPop(String key) {
    //     redisTemplate.opsForList().rightPop(key);
    // }
    //
    // /**
    //  * 移除集合中右边的元素在等待的时间里，如果超过等待的时间仍没有元素则退出。
    //  *
    //  * @param key
    //  *
    //  * @return
    //  */
    // public void rightPop(String key, long timeout, TimeUnit unit) {
    //     redisTemplate.opsForList().rightPop(key, timeout, unit);
    // }
}
