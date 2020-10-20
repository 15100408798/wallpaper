package com.yushang.wallpaper.common.config.Shiro.cache;

import com.yushang.wallpaper.common.utils.log.LoggerUtils;
import org.springframework.core.serializer.support.DeserializingConverter;
import org.springframework.core.serializer.support.SerializingConverter;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

public class DefinedRedisSerializer implements RedisSerializer {

    private SerializingConverter serializingConverter = new SerializingConverter();

    private DeserializingConverter deserializingConverter = new DeserializingConverter();

    @Override
    public byte[] serialize(Object o) throws SerializationException {
        try{
            if (o == null){
                return new byte[0];
            }
            return serializingConverter.convert(o);
        }catch (Exception e){
            LoggerUtils.error("redis序列化失败",e);
            return new byte[0];
        }
    }

    @Override
    public Object deserialize(byte[] bytes) throws SerializationException {
        try{
            if (bytes == null || bytes.length == 0){
                return null;
            }
            return deserializingConverter.convert(bytes);
        }catch (Exception e){
            LoggerUtils.error("redis反序列化失败",e);
            return null;
        }
    }

}
